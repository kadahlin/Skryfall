
//
//subprojects {
//
//    group = Publishing.groupId
//    version = Publishing.version
//
//    apply(plugin = "maven-publish")
//    configure<PublishingExtension> {
//        repositories {
//            maven {
//                name = "GitHubPackages"
//                url = uri("https://maven.pkg.github.com/kadahlin/Skryfall")
//                credentials {
//                    username = System.getenv("GITHUB_PACKAGES_USERNAME")
//                    password = System.getenv("GITHUB_PACKAGES_TOKEN")
//                }
//            }
//        }
//    }
//}
