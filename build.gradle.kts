import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    groovy
    id("org.gretty") version "3.0.1"
    war
    id("com.vaadin") version "0.6.0"
}

val vaadin_version = "14.1.19"

defaultTasks("clean", "build")

repositories {
    jcenter()
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        // to see the exceptions of failed tests in Travis-CI console.
        exceptionFormat = TestExceptionFormat.FULL
    }
}

dependencies {
    // Karibu-DSL dependency
    implementation("com.github.mvysny.vaadin-groovy-builder:vaadin-groovy-builder-v14:0.0.2")

    // Vaadin 14
    implementation("com.vaadin:vaadin-core:${vaadin_version}") {
        // Webjars are only needed when running in Vaadin 13 compatibility mode
        listOf("com.vaadin.webjar", "org.webjars.bowergithub.insites",
                "org.webjars.bowergithub.polymer", "org.webjars.bowergithub.polymerelements",
                "org.webjars.bowergithub.vaadin", "org.webjars.bowergithub.webcomponents")
                .forEach { exclude(group = it) }
    }
    providedCompile("javax.servlet:javax.servlet-api:3.1.0")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:1.7.30")

    // test support
    testImplementation("com.github.mvysny.kaributesting:karibu-testing-v10-groovy:1.1.20")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}
