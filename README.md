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


### 🔐 Login — `LoginTest.java`

1. Verify login with valid username and valid password
2. Verify login with valid username and invalid password
3. Verify login with invalid username and valid password
4. Verify login with invalid username and invalid password
5. Verify login with blank username and blank password
6. Verify login with blank username and valid password
7. Verify login with valid username and blank password
8. Verify password field is masked

### 🛍️ Products — `ProductTest.java`

9. Verify Products page is displayed successfully
10. Verify all products are displayed
11. Verify product count is correct
12. Verify each product has a name
13. Verify each product has a price
14. Verify product images are displayed
15. Verify Cart buttons are displayed and enabled
16. Verify a single product can be added to Cart
17. Verify multiple products can be added to Cart
18. Verify a product can be removed from the Products page
19. Verify products can be sorted from A to Z
20. Verify products can be sorted from Z to A
21. Verify product prices can be sorted from Low to High
22. Verify product prices can be sorted from High to Low

### 🛒 Cart — `CartTest.java`

23. Verify Cart page is loaded successfully
24. Verify added product is displayed in Cart
25. Verify multiple products are displayed in Cart
26. Verify product price is displayed in Cart
27. Verify product names on Products page and Cart page match
28. Verify a product can be removed from Cart
29. Verify removing one product from multiple products updates the Cart correctly

### 💳 Checkout — `CheckoutTest.java`

30. Verify checkout can be initiated from Cart
31. Verify checkout information validation
32. Verify Checkout Overview page is displayed
33. Verify selected product details on Checkout Overview
34. Verify total product price on Checkout Overview
35. Verify tax calculation on Checkout Overview
36. Verify checkout is completed successfully
37. Verify user can navigate back to Products page after successful checkout


## Purpose

This project is a personal learning project to strengthen Java, Selenium, and TestNG automation framework development skills.
