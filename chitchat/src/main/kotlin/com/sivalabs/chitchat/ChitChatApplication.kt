package com.sivalabs.chitchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class MeetupApplication

fun main(args: Array<String>) {
    runApplication<MeetupApplication>(*args)
}
