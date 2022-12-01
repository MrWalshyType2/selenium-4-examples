# Selenium 4

The examples in this project assume that the EdgeDriver and GeckoDriver have been added to the path.

## Project setup

Create a Maven project.

### POM Requirements

Each Maven project has a **Page Object Model** (POM) file, in XML, that contains project information and configuration details used for building the project.

___

**Required dependencies**

List the following dependencies in the POM:

*Selenium 4.x.x*:

```xml
<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.1.0</version>
</dependency>
```

*JUnit 4.13.x*:

```xml
<!-- https://mvnrepository.com/artifact/junit/junit -->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

___

**Required properties**

```xml
<maven.compiler.target>11</maven.compiler.target>
<maven.compiler.source>11</maven.compiler.source>
<maven.compiler.release>11</maven.compiler.release>
```

## Core concepts

### WebDriver

WebDriver is a bidirectional API used for passing commands to a browser and receiving information with an official W3C recommendation.

```
|-----------| ----> |-----------------------| ----> |---------|
| WebDriver |       | Driver Implementation |       | Browser |
|-----------| <---- |-----------------------| <---- |---------|
```

- WebDriver implementations exist that implement the W3C specified API, such as ChromeDriver, GeckoDriver, etc....

The WebDriver API is not for testing, reporting or writing acceptance criteria; it is in essence acting as a user interacting with the browser.

