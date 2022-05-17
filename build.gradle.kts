group = "org.op65n"
version = "0.0.2"

var frontendBuild: String = ":frontend:build"
var backendBuild: String = ":backend:build"

tasks {
    register("build-frontend") {
        dependsOn(frontendBuild)
    }

    register("build-backend") {
        dependsOn(backendBuild)
    }

    register("bundle") {
        dependsOn(frontendBuild)
        dependsOn(backendBuild).mustRunAfter(frontendBuild)
    }
}