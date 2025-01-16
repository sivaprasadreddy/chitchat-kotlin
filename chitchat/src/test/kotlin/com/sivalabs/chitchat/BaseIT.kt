package com.sivalabs.chitchat

import com.fasterxml.jackson.databind.ObjectMapper
import com.sivalabs.chitchat.domain.JwtTokenHelper
import io.restassured.RestAssured
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(TestcontainersConfig::class)
@ActiveProfiles("it")
abstract class BaseIT {
    @LocalServerPort
    var serverPort: Int = 0

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var jwtTokenHelper: JwtTokenHelper

    @PostConstruct
    fun initRestAssured() {
        RestAssured.port = serverPort
        RestAssured.urlEncodingEnabled = false
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }
}
