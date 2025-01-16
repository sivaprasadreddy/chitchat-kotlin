plugins {
    java
    id("io.gatling.gradle") version "3.13.1.2"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}
