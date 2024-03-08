plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

val projectVersion: String by project
val isSnapshot: String by project

group = "de.cloudvex"
version = projectVersion

repositories {
    mavenCentral()
}

dependencies {

    // Kotlin
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.+")

    // Database
    implementation("redis.clients:jedis:5.1.0")

    // Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test")

}

publishing {
    repositories {
        maven {
            val repoReleasesUrl = uri("https://repo.planetturia.net/releases")
            val repoSnapshotsUrl = uri("https://repo.planetturia.net/snapshots")

            name = "planetturiaRepo"
            url = if (isSnapshot.toBoolean()) {
                repoSnapshotsUrl
            } else {
                repoReleasesUrl
            }

            credentials(PasswordCredentials::class)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.planetturia"
            artifactId = "kutils"
            version = projectVersion
            from(components["java"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}