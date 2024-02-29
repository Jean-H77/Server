plugins {
    id("java")
}

group = "org.server"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("io.netty:netty-all:4.1.107.Final")
    implementation("com.google.inject:guice:7.0.0")
}

tasks.test {
    useJUnitPlatform()
}