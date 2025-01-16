package chitchat;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import static utils.SimulationHelper.getConfig;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import utils.SimulationHelper;

public class PostsBrowsingSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = SimulationHelper.getHttpProtocolBuilder();

    FeederBuilder<String> searchFeeder = csv("data/feeders/search.csv").random();

    ChainBuilder postsByPageNo = repeat(5, "n").on(
        exec(session -> session.set("pageNo", (int)session.get("n") + 1))
        .exec(
            http("Posts Page #{pageNo}")
                .get("/api/posts?page=#{pageNo}")
                .check(status().is(200))
        )
        .pause(1)
    );

    ChainBuilder search = feed(searchFeeder)
            .exec(
                http("Search")
                    .get("/api/posts?query=#{query}")
                    .check(status().is(200)))
            .pause(3);

    ChainBuilder browsePosts =
            exec(postsByPageNo)
            .exec(search);

    /*ScenarioBuilder scnBrowsePosts = scenario("Browse Posts")
            .during(Duration.ofMinutes(2), "Counter")
            .on(browsePosts);*/

    ScenarioBuilder scnBrowsePosts = scenario("Browse Posts").exec(browsePosts);

    {
        setUp(
            scnBrowsePosts.injectOpen(rampUsers(getConfig().getInt("users")).during(10))
        )
        .protocols(httpProtocol)
            .assertions(
                global().responseTime().max().lt(800),
                global().successfulRequests().percent().is(100.0)
            );
    }
}
