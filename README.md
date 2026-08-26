# SauceDemo Automation Framework

## Overview

This is a personal Selenium automation framework created for learning and practicing web application automation using Java and TestNG.

## Technologies & Tools

* Java
* Selenium WebDriver
* TestNG
* Maven
* Page Object Model (POM)
* DataProvider
* SoftAssert
* ConfigReader
* TestNG Listener
* Screenshot capture on test failure

## Framework Structure

```text
src
└── test
    └── java
        ├── base
        ├── listeners
        ├── pages
        ├── tests
        └── utilities
```

## Features

* Browser setup and teardown using BaseClass
* Configuration management using properties file
* Page Object Model for maintaining page-related actions
* Data-driven testing using TestNG DataProvider
* Soft assertions using TestNG SoftAssert
* TestNG Listener for test execution events
* Screenshot capture when a test fails
* Maven-based project configuration

## Test Scenario

The framework currently automates the SauceDemo login functionality using multiple sets of valid and invalid login credentials.

## Purpose

This project is a personal learning project to strengthen Java, Selenium, and TestNG automation framework development skills.
