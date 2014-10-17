# Contribution
Contributors are welcome and encouraged!

## Contributors
- [@ImaginateWayne](https://github.com/ImaginateWayne) Owner Maintainer

## Etiquette
[Open Source Contribution Etiquette](http://tirania.org/blog/archive/2010/Dec-31.html) is a great article to read for things to consider when contributing.

### Submitting Helpful Issues

#### Bugs
- Describe steps and conditions to reproduce bugs.
- Label it a bug.
- BONUS POINTS: Write a test to demonstrate the bug!
- DOUBLE BONUS POINTS: Fix bug and submit a pull request!! :D

#### Enhancements and new Features
- Describe what and why this would enhance the project.
- Label it an Enhancement or a Feature.

### Creating Good Pull Requests
- Raise an issue.
- Write tests.
- Develop small atomic changes. 
- Squash working history. [See this guide for a good workflow](https://www.atlassian.com/git/articles/simple-git-workflow-is-simple/)
- Be willing to take on feedback.

#### Refactoring 
- If you've got a burning desired to majorly refactor code, let's talk it through first.
- Be sure to submit a pull request with refactoring changes only, no new functionality.

## Development
### Setup
You will at least need the following:

- Java 1.8 
- Maven 3.x
- Git
- Favourite IDE / Text Editor
- A delicious beverage

### Coding Style Guide 
- Adheres to standards set IntelliJ [Java Code Style](http://www.jetbrains.com/idea/webhelp/code-style-java.html), I have broken one or two rules though...
- Broken Rules
    - I have used upper case for the OS acronym because people are used to seeing it this way.
    - I have used domain abbreviations for the brevity of the syntax. I believe users are familiar with the domain so it's ok.

### Running Tests
Tests can be run as apart of the maven life cycle
#### Unit Tests
```
mvn test
```

#### Integration Tests
```
mvn verify
```

#### Other Tests
There are some tests that aren't included in the unit or integration tests because they are unreliable or are platform
dependant.

##### Unit
- quarantined: currently there is nothing in this suite but it can be used to isolated tests from the main unit tests.
Possible reasons might be failing, unreliable, slow unit tests.

```
mvn test -DunitTestSuite=quarantined
```

##### Integration
- unix: specific tests for unix platforms
- macOsx: specific tests for Mac OSX
- windows: specific tests for Windows

```
mvn verify -DintegrationTestSuite=unix
```

### Releasing to Maven Central

The "release" profile allows anyone with the username and password in their maven "settings.xm" to deploy to the staging repo, 
from there it can be promoted as a release. This is usually done by the project owner [@ImaginateWayne](https://github.com/ImaginateWayne).

#### Release to Snapshot

```
mvn clean deploy
```

#### Release 
```
mvn versions:set -DnewVersion=1.0.0

mvn clean deploy -P release scm:tag

mvn nexus-staging:release

mvn versions:set -DnewVersion=1.1.0-SNAPSHOT 

```

#### Configuration
To understand the release steps see the comments in the "release" profile in the [pom.xml] file.
Further documentation can be found at [SonaType Apache Maven documentation](http://central.sonatype.org/pages/apache-maven.html).