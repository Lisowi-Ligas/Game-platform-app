import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "ie.setu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

dependencies {
    testImplementation(kotlin("test"))
    // dependencies for logging
    implementation("io.github.microutils:kotlin-logging:3.0.4")
    }

    dependencies {
        testImplementation(kotlin("test"))
        // dependencies for logging
        implementation("io.github.microutils:kotlin-logging:2.1.23")
        implementation("org.slf4j:slf4j-simple:2.0.3")

        //For Streaming to XML and JSON
        implementation("com.thoughtworks.xstream:xstream:1.4.18")
        implementation("org.codehaus.jettison:jettison:1.4.1")
    }
