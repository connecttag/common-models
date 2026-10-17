plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinSerialization)
    `maven-publish`
}

group = "org.connecttag.libs.kotlin"
version = "1.0.0"

kotlin {
    jvm()
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.kotlinx.serialization.json)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

publishing {
    publications {
        withType<MavenPublication>().configureEach {
            pom {
                name.set("ConnectTag Common Models")
                description.set("Pure Kotlin Multiplatform domain primitives (Result, CollectionResult, DomainError, UiState) with Kotlinx Serialization")
                url.set("https://github.com/connecttag/common-models")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("connecttagye")
                        name.set("Connect Tag")
                        email.set("connecttagye@gmail.com")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/connecttag/common-models.git")
                    developerConnection.set("scm:git:ssh://git@github.com:connecttag/common-models.git")
                    url.set("https://github.com/connecttag/common-models/tree/main")
                }
            }
        }
    }
}
