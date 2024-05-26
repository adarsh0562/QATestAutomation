@tag
Feature: Error Validation in Ecommerce website

  @ErrorValidation
  Scenario Outline: Negative Test of Submitting the order
  	Given I landed on Ecommerce Page
    And Logged in with username <username> and password <password>
    Then "Incorrect email or password." error message is displayed on login Page

  Examples:
    | username                  | password     | productName    |
    | adarshrajgupt@gmail.com  | Qwerty12345@ | ZARA COAT 3    |
    
