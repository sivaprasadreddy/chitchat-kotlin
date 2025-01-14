package com.sivalabs.chitchat

import org.springframework.boot.fromApplication
import org.springframework.boot.with

fun main(args: Array<String>) {
    fromApplication<ChitChatApplication>().with(TestcontainersConfig::class).run(*args)
}
