plugins {
    id("java")
    id("com.github.johnrengelman.shadow").version("7.1.2")
}

repositories {
    mavenCentral()
}

dependencies {
    // clients
    implementation("io.javalin:javalin:4.6.0")
    implementation("io.sentry:sentry:5.7.4")

    // utilities
    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("org.tomlj:tomlj:1.0.0")

    // logging
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.2")
    implementation("org.apache.logging.log4j:log4j-core:2.17.2")

    // testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}


tasks {
    withType<Test> {
        useJUnitPlatform()

        testLogging {
            showStandardStreams = true
        }
    }

    withType<JavaCompile> {
        options.compilerArgs.add("-Xlint:all")
        options.encoding = "UTF-8"
    }

    withType<Jar> {
        archiveBaseName.set("op65n.org")
        archiveVersion.set("")

        manifest {
            attributes["Main-Class"] = "org.op65n.website.Main"
            attributes["Implementation-Title"] = "op65n.org"
            attributes["Implementation-Version"] = project.version
            attributes["Implementation-Vendor"] = "op65n"
        }
    }

    withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
        archiveBaseName.set("op65n.org")
        archiveClassifier.set("")
        archiveVersion.set("")

        val destination = "org.op65n.website.libs"

        relocate("org.slf4j", "$destination.slf4j")
        relocate("org.jetbrains", "$destination.jetbrains")
        relocate("com.google.code.gson", "$destination.gson")
        relocate("org.tomlj", "$destination.tomlj")
    }

    jar.get().finalizedBy(shadowJar)
}