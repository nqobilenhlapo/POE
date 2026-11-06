# PROG5121 POE - Task Management System
Student: Nqobile Nhlapo
Module: PROG5121 - Programming 1A

## Description
Java console application for managing tasks using EasyKanban. Includes user registration, login, adding tasks, reports.

## Features (Part 1-3)
- Part 1: Registration, Login with validation
- Part 2: Add Tasks, Show Report, Search, Delete
- Part 3: Unit Tests (JUnit) and GitHub Actions CI

## How to Run
1. Clone: https://github.com/nqobilenhlapo/POE
2. Open in IntelliJ / NetBeans / VS Code
3. Run Main.java
4. Or with Maven: mvn compile exec:java

## How to Run Tests
mvn test

## GitHub Actions - CI
GitHub Actions workflow is configured in .github/workflows/ci.yml
It automatically:
- Builds project with Maven on every push to master
- Runs all JUnit tests using JDK 25
- Status: Success (Green tick in Actions tab)

## Repository Link
https://github.com/nqobilenhlapo/POE

## Tools Used
- Java 25
- Maven
- JUnit 5
- GitHub Actions
