![Java CI](https://github.com/mvysny/vaadin-groovy-builder-example/workflows/Java%20CI/badge.svg)

# Vaadin 14 Groovy Builder Example App / Archetype

This project can be used as a starting point to create your own Vaadin 14+ application.
It has the necessary dependencies and files to get you started.
Just clone this repo and start experimenting! Import it to the IDE of your choice as a Gradle project.
                                              
Uses [Vaadin Groovy Builder](https://github.com/mvysny/vaadin-groovy-builder) Groovy
bindings to the [Vaadin](https://vaadin.com) framework.

# Preparing Environment

The Vaadin 14 build requires node.js and npm. You can either use the Vaadin Gradle plugin to install it for
you (the `vaadinPrepareNode` task, handy for the CI), or you can install it to your OS:

* Windows: [node.js Download site](https://nodejs.org/en/download/) - use the .msi 64-bit installer
* Linux: `sudo apt install npm`

To make Vaadin Gradle plugin install node.js+npm for you, just run the following command
in the project's sources (you only need to run this command once):

```
./gradlew vaadinPrepareNode
```

Also make sure that you have Java 8 (or higher) JDK installed.

## Getting Started

To quickly start the app, just type this into your terminal:

```bash
git clone https://github.com/mvysny/vaadin-groovy-builder-example
cd vaadin-groovy-builder-example
./gradlew appRun
```

Gradle will automatically download an embedded servlet container (Jetty) and will run your app in it. Your app will be running on
[http://localhost:8080](http://localhost:8080).

We suggest you use [Intellij IDEA](https://www.jetbrains.com/idea/download)
to edit the project files. The Community edition is enough to run the server
via Gretty's `./gradlew appRun`. The Ultimate edition will allow you to run the
project in Tomcat - this is the recommended
option for a real development.

> This is a port of [Skeleton Starter Flow](https://github.com/vaadin/skeleton-starter-flow) to Groovy+Gradle.

## Supported Modes

Runs in Vaadin 14 npm mode, using the [Vaadin Gradle Plugin](https://github.com/vaadin/vaadin-gradle-plugin).

Both the [development and production modes](https://vaadin.com/docs/v14/flow/production/tutorial-production-mode-basic.html) are supported.
To prepare for development mode, just run:

```bash
./gradlew clean vaadinPrepareFrontend
```

If you don't have node installed, you can use Vaadin plugin to download node.js for you:

```bash
./gradlew vaadinPrepareNode
```

To build in production mode, just run:

```bash
./gradlew clean build -Pvaadin.productionMode
```

If you don't have node installed in your CI environment, you can use Vaadin plugin to download node.js for you beforehand:

```bash
./gradlew clean vaadinPrepareNode build -Pvaadin.productionMode
```

# Workflow

To compile the entire project in production mode, run `./gradlew -Pvaadin.productionMode`.

To run the application in development mode, run `./gradlew appRun` and open [http://localhost:8080/](http://localhost:8080/).

To produce a deployable production-mode WAR:
- run `./gradlew -Pvaadin.productionMode`
- You will find the WAR file in `build/libs/*.war`
- To revert your environment back to development mode, just run `./gradlew` or `./gradlew vaadinPrepareFrontend`
  (omit the `-Pvaadin.productionMode`) switch.

This will allow you to quickly start the example app and allow you to do some basic modifications.

## Development with Intellij IDEA Ultimate

The easiest way (and the recommended way) to develop Karibu-DSL-based web applications is to use Intellij IDEA Ultimate.
It includes support for launching your project in any servlet container (Tomcat is recommended)
and allows you to debug the code, modify the code and hot-redeploy the code into the running Tomcat
instance, without having to restart Tomcat.

1. First, download Tomcat and register it into your Intellij IDEA properly: https://www.jetbrains.com/help/idea/2017.1/defining-application-servers-in-intellij-idea.html
2. Then just open this project in Intellij, simply by selecting `File / Open...` and click on the
   `build.gradle` file. When asked, select "Open as Project".
2. You can then create a launch configuration which will launch this example app in Tomcat with Intellij: just
   scroll to the end of this tutorial: https://kotlinlang.org/docs/tutorials/httpservlets.html
3. Start your newly created launch configuration in Debug mode. This way, you can modify the code
   and press `Ctrl+F9` to hot-redeploy the code. This only redeploys java code though, to
   redeploy resources just press `Ctrl+F10` and select "Update classes and resources"
   
Or watch the [Debugging Vaadin Apps With Intellij video](https://www.youtube.com/watch?v=M0Q7D03bYXc).

## Dissection of project files

Let's look at all files that this project is composed of, and what are the points where you'll add functionality:

| Files | Meaning
| ----- | -------
| [build.gradle.kts](build.gradle.kts) | [Gradle](https://gradle.org/) build tool configuration files. Gradle is used to compile your app, download all dependency jars and build a war file
| [gradlew](gradlew), [gradlew.bat](gradlew.bat), [gradle/](gradle) | Gradle runtime files, so that you can build your app from command-line simply by running `./gradlew`, without having to download and install Gradle distribution yourself.
| [.travis.yml](.travis.yml) | Configuration file for [Travis-CI](http://travis-ci.org/) which tells Travis how to build the app. Travis watches your repo; it automatically builds your app and runs all the tests after every commit.
| [.gitignore](.gitignore) | Tells [Git](https://git-scm.com/) to ignore files that can be produced from your app's sources - be it files produced by Gradle, Intellij project files etc.
| [src/main/resources/](src/main/resources) | A bunch of static files not compiled by Groovy in any way; see below for explanation.
| [simplelogger.properties](src/main/resources/simplelogger.properties) | We're using [Slf4j](https://www.slf4j.org/) for logging and this is the configuration file for [Slf4j Simple Logger](https://www.slf4j.org/api/org/slf4j/impl/SimpleLogger.html).
| [webapp/](src/main/webapp) | static files provided as-is to the browser.
| [src/main/groovy/](src/main/groovy) | The main Groovy sources of your web app. You'll be mostly editing files located in this folder.
| [MainView.kt](src/main/groovy/com/vaadin/flow/demo/helloworld/MainView.groovy) | When Servlet Container (such as [Tomcat](http://tomcat.apache.org/)) starts your app, it will show the components attached to the main route, in this case, the `MainView` class.
| [MainViewTest.kt](src/test/groovy/com/vaadin/flow/demo/helloworld/MainViewTest.groovy) | Automatically run by Gradle to test your UI; see [Karibu Testing](https://github.com/mvysny/karibu-testing) for more information.

# More Resources

* The DSL technique is used to allow you to nest your components in a structured code. This is provided by the
  Vaadin Groovy Builder library; please visit the [Vaadin Groovy Builder home page](https://github.com/mvysny/vaadin-groovy-builder) for more information.
* The browserless testing is demonstrated in the [MainViewTest.groovy](src/test/groovy/com/vaadin/flow/demo/helloworld/MainViewTest.groovy) file.
  Please read [Browserless Web Testing](https://github.com/mvysny/karibu-testing) for more information.
