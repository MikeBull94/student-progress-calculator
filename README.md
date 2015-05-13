# Student Progress Calculator

## Introduction

A student progress calculator in which users may enter a student's name, personal number, enrolled course, and module marks for the program to calculate pass rates and earned credits.

The application has been developed using the [Model–view–controller][mvc] pattern.

## Building

[Gradle][gradle] is used as the build system. Java 8 ([Oracle Java][oracle]) or [OpenJDK][openjdk] on Linux is required.

Run `gradle` to build the application.

## Dependencies

The following Java libraries are used by the application:

* [Google Gson][gson]
* [SLF4J][slf4j]

## Screenshots

![Create Profile][create_profile]

![Input Marks][input_marks]

![Overview Results][overview_results]

[create_profile]: /img/create_profile.png
[input_marks]: /img/input_marks.png
[overview_results]: /img/overview_results.png

[mvc]: http://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
[gradle]: http://www.gradle.org/
[oracle]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[openjdk]: http://openjdk.java.net/
[gson]: https://code.google.com/p/google-gson/
[slf4j]: http://www.slf4j.org/