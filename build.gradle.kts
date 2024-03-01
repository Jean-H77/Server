plugins {
    id("java")
    kotlin("jvm")
}

group = "org.server"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.google.protobuf:protobuf-java:3.25.3")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("io.netty:netty-all:4.1.107.Final")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("com.google.guava:guava:33.0.0-jre")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}