package com.sivalabs.chitchat

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfig::class)
@SpringBootTest
class ChitChatApplicationTests {
    @Test
    fun contextLoads() {
    }
}
