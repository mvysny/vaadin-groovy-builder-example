![Java CI](https://github.com/mvysny/vaadin-groovy-builder-example/workflows/Java%20CI/badge.svg)

# Vaadin 14 Groovy Builder Example App / Archetype

This project can be used as a starting point to create your own Vaadin 14+ application.
It has the necessary dependencies and files to get you started.
Just clone this repo and start experimenting! Import it to the IDE of your choice as a Gradle project.
                                              
Uses [Vaadin Groovy Builder](https://github.com/mvysny/vaadin-groovy-builder) Groovy
bindings to the [Vaadin](https://vaadin.com) framework. Uses [Vaadin-Boot](https://github.com/mvysny/vaadin-boot).

# Preparing Environment

The Vaadin 14 build requires node.js and npm. Vaadin Gradle plugin will install it for
you automatically (handy for the CI); alternatively you can install it to your OS:

* Windows: [node.js Download site](https://nodejs.org/en/download/) - use the .msi 64-bit installer
* Linux: `sudo apt install npm`

Also make sure that you have Java 11 JDK installed.

## Getting Started

To quickly start the app, just type this into your terminal:

```bash
git clone https://github.com/mvysny/vaadin-groovy-builder-example
cd vaadin-groovy-builder-example
./gradlew run
```

Gradle will automatically download all dependencies and will run your app. Your app will be running on
[http://localhost:8080](http://localhost:8080).

We suggest you use [Intellij IDEA](https://www.jetbrains.com/idea/download)
to develop the project further; the Community edition is enough for all development purposes.
From your IDE, simply run the `Main.main()` function.

> This is a port of [Skeleton Starter Flow](https://github.com/vaadin/skeleton-starter-flow) to Groovy+Gradle.

## Supported Modes

Runs in Vaadin 14 npm mode, using the [Vaadin Gradle Plugin](https://github.com/vaadin/vaadin-gradle-plugin).

Both the [development and production modes](https://vaadin.com/docs/v14/flow/production/tutorial-production-mode-basic.html) are supported.
To prepare for development mode, just run:

```bash
./gradlew clean vaadinPrepareFrontend
```

To build in production mode, just run:

```bash
./gradlew clean build -Pvaadin.productionMode
```

If you don't have node installed in your CI environment, Vaadin Gradle plugin will
install it for you automatically:

```bash
./gradlew clean build -Pvaadin.productionMode
```

# Workflow

To compile the entire project in production mode, run `./gradlew -Pvaadin.productionMode`.

To run the application in development mode, run `./gradlew clean build example:run` and open [http://localhost:8080/](http://localhost:8080/).

To produce a runnable production-mode app:
- run `./gradlew -Pvaadin.productionMode`
- You will find the app zip file in the `example/build/distributions/` folder.
- To revert your environment back to development mode, just run `./gradlew` or `./gradlew vaadinPrepareFrontend`
  (omit the `-Pvaadin.productionMode`) switch.

This will allow you to quickly start the example app and allow you to do some basic modifications.

Note that the app doesn't build to WAR, but builds into a self-contained runnable app instead.

## Dissection of project files

Let's look at all files that this project is composed of, and what are the points where you'll add functionality:

| Files | Meaning
| ----- | -------
| [build.gradle.kts](build.gradle.kts) | [Gradle](https://gradle.org/) build tool configuration files. Gradle is used to compile your app, download all dependency jars and build a war file
| [gradlew](gradlew), [gradlew.bat](gradlew.bat), [gradle/](gradle) | Gradle runtime files, so that you can build your app from command-line simply by running `./gradlew`, without having to download and install Gradle distribution yourself.
| [.github](.github) | Configuration file for the GitHub Workflows which tells GitHub how to build the app. GitHub Workflows automatically builds your app and runs all the tests after every commit.
| [.gitignore](.gitignore) | Tells [Git](https://git-scm.com/) to ignore files that can be produced from your app's sources - be it files produced by Gradle, Intellij project files etc.
| [src/main/resources/](src/main/resources) | A bunch of static files not compiled by Groovy in any way; see below for explanation.
| [simplelogger.properties](src/main/resources/simplelogger.properties) | We're using [Slf4j](https://www.slf4j.org/) for logging and this is the configuration file for [Slf4j Simple Logger](https://www.slf4j.org/api/org/slf4j/impl/SimpleLogger.html).
| [webapp/](src/main/webapp) | static files provided as-is to the browser.
| [src/main/groovy/](src/main/groovy) | The main Groovy sources of your web app. You'll be mostly editing files located in this folder.
| [MainView.groovy](src/main/groovy/com/vaadin/flow/demo/helloworld/MainView.groovy) | When Servlet Container (such as [Tomcat](http://tomcat.apache.org/)) starts your app, it will show the components attached to the main route, in this case, the `MainView` class.
| [MainViewTest.groovy](src/test/groovy/com/vaadin/flow/demo/helloworld/MainViewTest.groovy) | Automatically run by Gradle to test your UI; see [Karibu Testing](https://github.com/mvysny/karibu-testing) for more information.

# More Resources

* The DSL technique is used to allow you to nest your components in a structured code. This is provided by the
  Vaadin Groovy Builder library; please visit the [Vaadin Groovy Builder home page](https://github.com/mvysny/vaadin-groovy-builder) for more information.
* The browserless testing is demonstrated in the [MainViewTest.groovy](src/test/groovy/com/vaadin/flow/demo/helloworld/MainViewTest.groovy) file.
  Please read [Browserless Web Testing](https://github.com/mvysny/karibu-testing) for more information.
