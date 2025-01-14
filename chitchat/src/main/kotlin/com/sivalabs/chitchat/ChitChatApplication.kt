package com.sivalabs.chitchat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class ChitChatApplication

fun main(args: Array<String>) {
    runApplication<ChitChatApplication>(*args)
}
