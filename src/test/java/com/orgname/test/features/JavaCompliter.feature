Feature: Java Compiler

  @web
  Scenario: As a student, I want to login to my account
    Given I enter my username and password
    When press submit button
    Then able to login successfully

  @web
  Scenario: As a student, I want to use FAQ
    Given I am at java compiler site
    When press HowTo_FAQ link
    Then HowTo Page should be opened

  @web
  Scenario: As a student, I want to create a new project and run the program
    Given Create a new program
    When Press execute button
    Then program should be executed successfully

  @web
  Scenario: As a student, I want to change programming Language
    Given I opened the Java compiler site
    When select language from Java to "Python 3"
    Then language "Python3" should be changed and page title and code should be changed

  @web
  Scenario: As a student, I want to check the pricing page
    Given I opened the Java compiler site
    When click on the pricing page
    Then pricing page should be opened