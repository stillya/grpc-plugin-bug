import com.google.protobuf.gradle.*

plugins {
    id("java")
    id("com.google.protobuf") version "0.8.19"
    id("com.dorongold.task-tree") version "2.1.0"
}

repositories {
    mavenCentral()
}

java {
    withSourcesJar()
}

/**
 * Dependencies
 */

dependencies {
    implementation("com.google.protobuf:protoc:3.21.4")
    implementation("io.grpc:protoc-gen-grpc-java:1.48.0")

    // grpc kotlin
    implementation("io.grpc:grpc-protobuf:1.48.0")
    implementation("com.google.protobuf:protobuf-java-util:3.21.3")
    implementation("com.google.protobuf:protobuf-kotlin:3.21.3")
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.10")

    // kotlin logging
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")

    // our protobufs
    protobuf(files("../common-proto/src/main/proto/commons/"))
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.4"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.48.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc") { }
                id("grpckt") { }
            }
        }
    }
}

sourceSets.main {
    java.srcDirs(
        "build/generated/source/proto/main/grpc",
        "build/generated/source/proto/main/grpckt",
        "build/generated/source/proto/main/java",
        "build/generated/source/proto/main/kotlin"
    )
}