# Essential MicroProfile Scaffold

This project uses [MicroProfile](https://microprofile.io/), an open forum to optimize Enterprise Java for a microservices architecture, and [Payara Micro 5](https://www.payara.fish/).
It was generated with [MicroProfile Starter](https://start.microprofile.io/).
It provides a complete **RESTful API** configured, including build, test, and deploy scripts as examples.
It is recommended to have, at least, **Java 11**, [Node.js](https://nodejs.org/en/), [Docker](https://www.docker.com/) and [Ansible](https://www.ansible.com/) installed.

## Table of Contents

- [Project structure](#project-structure)
- [Available gradle tasks](#available-gradle-tasks)
- [Running in development mode](#running-in-development-mode)
- [Linting and formatting code](#linting-and-formatting-code)
- [Running unit tests](#running-unit-tests)
- [Running integration tests](#running-integration-tests)
- [Debugging](#debugging)
- [Commit messages convention](#commit-messages-convention)
- [Building and deploying](#building-and-deploying)
- [Reference documentation](#reference-documentation)

## Project structure

When working in a large team with many developers that are responsible for the same codebase, having a common understanding of how the application should be structured is vital.
Based on best practices from the community, other github projects and developer experience, your project should look like this:

```html
├── ci
|  ├── build
|  └── deploy
├── gradle
├── src
|  ├── integrationTest
|  ├── main
|  |  ├── docker
|  |  ├── java
|  |  |  └── app
|  |  |     ├── AppResource.java
|  |  |     ├── AppService.java
|  |  |     └── JAXRSApplication.java
|  |  ├── resources
|  |  |  └── microprofile-config.properties
|  |  └── webapp
|  └── test
├── .dockerignore
├── .editorconfig
├── .gitignore
├── .prettierrc
├── build.gradle
├── CHANGELOG.md
├── changelog.mustache
├── checkstyle.xml
├── gradlew
├── gradlew.bat
├── LICENSE
├── README.md
└── settings.gradle
```

All of the app's code goes in a folder named `src/main`.
The unit tests and integration tests are in the `src/test` and `src/integrationTest` folders.
Static files are placed in `src/main/resources` folder.

## Available gradle tasks

The tasks in [build.gradle](build.gradle) file were built with simplicity in mind to automate as much repetitive tasks as possible and help developers focus on what really matters.

The next tasks should be executed in a console inside the root directory:

- `./gradlew tasks` - Displays the tasks runnable from root project 'app'.
- `./gradlew microStart` - Starts Payara Micro with the specified configuration.
- `./gradlew check` - Runs all checks.
- `./gradlew test` - Runs the unit tests.
- `./gradlew integrationTest` - Run the integration tests.
- `./gradlew lint` - Runs several static code analysis.
- `./gradlew format` - Applies code formatting steps to source code in-place.
- `./gradlew clean` - Deletes the build directory.
- `./gradlew javadoc` - Generates Javadoc API documentation for the main source code.
- `./gradlew generateOpenApi` - Generates the OpenAPI specification file.
- `./gradlew generateChangelog` - Generates a changelog from GIT repository.
- `./gradlew dependencyUpdates` - Displays the dependency updates for the project.
- `./gradlew war` - Generates a war archive with all the web-app.
- `./gradlew build` - Assembles and tests this project.
- `./gradlew buildImage` - Builds a Docker image of the application.
- `./gradlew release` - Performs release, creates tag and pushes it to remote.
- `./gradlew deploy` - Deploys the application to Docker Swarm.
- `./gradlew help` - Displays a help message.

For more details, read the [Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html) documentation in the [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html).

## Running in development mode

You can run your application in dev mode that enables live coding using `./gradlew war microStart` command.

Alternatively, you can run the application on your local [Payara Server](https://www.payara.fish/) instance.
First, you need to build the project with `./gradlew clean war` command.
After, you can deploy the [microprofile-api.war](build/libs/microprofile-api.war) on Payara Server.

This application includes [Swagger](https://swagger.io/). It is available at <http://localhost:8080/microprofile-api/openapi-ui/>.
The OpenAPI Specification is automatically generated. Use `./gradlew generateOpenApi` to generate the [openapi.yaml](build/openapi.yaml) file.

## Linting and formatting code

A linter is a static code analysis tool used to flag programming errors, bugs, stylistic errors and suspicious constructs.

It includes [Prettier](https://prettier.io/), [Checkstyle](https://checkstyle.sourceforge.io/), [PMD](https://pmd.github.io/) and [SpotBugs](https://spotbugs.github.io/):

- **Prettier** enforces a consistent style by parsing your code and re-printing it with its own rules, wrapping code when necessary.
- **Checkstyle** finds class design problems, method design problems, and others. It also has the ability to check code layout and formatting issues.
- **PMD** finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth.
- **SpotBugs** is used to perform static analysis on Java code. It looks for instances of "bug patterns".

Use `./gradlew lint` to analyze your code. Many problems can be automatically fixed with `./gradlew format` command.
Depending on our editor, you may want to add an editor extension to lint and format your code while you type or on-save.

## Running unit tests

Unit tests are responsible for testing of individual methods or classes by supplying input and making sure the output is as expected.

Use `./gradlew test` to execute the unit tests via [JUnit 5](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/).
Use `./gradlew test -t` to keep executing unit tests in real time while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/test/index.html) file in your web browser.

It's a common requirement to run subsets of a test suite, such as when you're fixing a bug or developing a new test case.
Gradle provides different mechanisms.
For example, the following command lines run either all or exactly one of the tests in the `SomeTestClass` test case:

```bash
./gradlew test --tests SomeTestClass
```

For more details, you can see the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation.

This project uses [JaCoCo](https://www.eclemma.org/jacoco/) which provides code coverage metrics for Java.
The minimum code coverage is set to 80%.
You can see the HTML coverage report opening the [index.html](build/reports/jacoco/test/html/index.html) file in your web browser.

## Running integration tests

Integration tests determine if independently developed units of software work correctly when they are connected to each other.

Use `./gradlew integrationTest` to execute the integration tests via [JUnit 5](https://junit.org/junit5/), [Testcontainers](https://www.testcontainers.org/) and [REST Assured](https://rest-assured.io/).
Use `./gradlew integrationTest -t` to keep executing your tests while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/integrationTest/index.html) file in your web browser.

The first time, you need to build the Docker image used in the integration tests.
For more details, see [Building and deploying](#building-and-deploying) section.

Like unit tests, you can also run subsets of a test suite.
See the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation

## Debugging

You can debug the source code, add breakpoints, inspect variables and view the application's call stack.
Also, you can use the IDE for debugging the source code, unit and integration tests.
You can customize the [log verbosity](https://docs.gradle.org/current/userguide/logging.html#logging) of gradle tasks using the `-i` or `--info` flag.

This project includes [Swagger](https://swagger.io/). To get a visual representation of the interface and send requests for testing purposes go to <http://localhost:8080/microprofile-api/openapi-ui/>.

## Commit messages convention

In order to have a consistent git history every commit must follow a specific template. Here's the template:

```bash
<type>(<ITEM ID>?): <subject>
```

### Type

Must be one of the following:

- **build**: Changes that affect the build system or external dependencies (example scopes: Gradle, Maven)
- **ci**: Changes to our CI configuration files and scripts (example scopes: Jenkins, Travis, Circle, SauceLabs)
- **chore**: Changes to the build process or auxiliary tools and libraries such as documentation generation
- **docs**: Documentation only changes
- **feat**: A new feature
- **fix**: A bug fix
- **perf**: A code change that improves performance
- **refactor**: A code change that neither fixes a bug nor adds a feature
- **revert**: A commit that reverts a previous one
- **style**: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc.)
- **test**: Adding missing tests or correcting existing tests

### ITEM ID

The related **issue** or **user story** or even **defect**.

- For **user stories**, you shoud use `US-` as prefix. Example: `feat(US-4321): ...`
- For **no related issues** or **defects** you should leave it blank. Example: `feat: ...`

### Subject

The subject contains a succinct description of the change.

## Building and deploying

In `ci` folder you can find scripts for your [Jenkins](https://www.jenkins.io/) CI pipeline and an example for deploying your application with Ansible to [Docker Swarm](https://docs.docker.com/engine/swarm/).

This project follows [Semantic Versioning](https://semver.org/) and uses git tags to define the current version of the project.
Use `./gradlew currentVersion` to print the current version extracted from SCM and `./gradlew release` to release the current version.

This project contains a Dockerfile that you can use to build your Docker image. Use `./gradlew buildImage`.
Also, you can deploy this project to Docker Swarm using `./gradlew deploy` command.

## Reference documentation

For further reference, please consider the following sections:

- [Official Gradle documentation](https://docs.gradle.org)
- [Conventional Commits](https://www.conventionalcommits.org/)
- [Git Hooks Tutorial](https://www.atlassian.com/git/tutorials/git-hooks)
- [Boundary Control Entity Architecture](https://vaclavkosar.com/software/Boundary-Control-Entity-Architecture-The-Pattern-to-Structure-Your-Classes)
- [Create a RESTful Web Service with Payara Server](https://blog.payara.fish/create-a-restful-web-service-with-payara-server-netbeans)
- [Using Payara Server with Docker](https://blog.payara.fish/using-payara-server-with-docker)
- [Mockito Tutorial](https://www.baeldung.com/mockito-series)
- [Integration Testing with the Payara and Testcontainers](https://blog.payara.fish/jakarta-ee-integration-testing-with-testcontainers)
