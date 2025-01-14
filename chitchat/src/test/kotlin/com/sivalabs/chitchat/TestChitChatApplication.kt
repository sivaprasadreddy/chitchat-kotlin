package com.sivalabs.chitchat

import org.springframework.boot.fromApplication
import org.springframework.boot.with

fun main(args: Array<String>) {
    fromApplication<MeetupApplication>().with(TestcontainersConfig::class).run(*args)
}
