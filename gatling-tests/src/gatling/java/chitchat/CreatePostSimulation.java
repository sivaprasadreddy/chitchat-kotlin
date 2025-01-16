package chitchat;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static chitchat.SimulationHelper.getConfig;

public class CreatePostSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = SimulationHelper.getHttpProtocolBuilder();

    FeederBuilder<String> postFeeder = csv("data/feeders/posts.csv").random();
    FeederBuilder<String> credentialsFeeder = csv("data/feeders/credentials.csv").random();

    ChainBuilder login = feed(credentialsFeeder)
            .exec(http("Login")
                    .post("/api/login")
                    .header("Content-Type", "application/json")
                    .body(StringBody("""
                            {
                            "email":"#{username}",
                            "password":"#{password}"
                            }
                            """))
                    .check(status().is(200))
                    .check(jsonPath("$.token").saveAs("accessToken")))

            .pause(1);

    ChainBuilder createPost = feed(postFeeder)
            .exec(http("CreatePost")
                .post("/api/posts")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer #{accessToken}")
                .body(StringBody(
                        """
                            {
                                "content":"#{content}"
                            }
                        """))
                .check(status().is(201))
            )
            .pause(1);


    ChainBuilder createPosts = exec(login)
            .exec(createPost);

    /*
    ScenarioBuilder scnBrowsePosts = scenario("Create Posts")
            .during(Duration.ofMinutes(2), "Counter")
            .on(createPosts);
    */
    ScenarioBuilder scnCreatePosts = scenario("Create Posts").exec(createPosts);

    {
        setUp(
                scnCreatePosts.injectOpen(rampUsers(getConfig().getInt("users")).during(10))
        )
        .protocols(httpProtocol)
            .assertions(
                global().responseTime().max().lt(800),
                global().successfulRequests().percent().is(100.0)
            );
    }
}
