@tag
Feature: Purchase the Order from Ecommerce website

  Background:
    Given I landed on Ecommerce Page

  @PurchaseOrder
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I added Product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

  Examples:
    | username                  | password     | productName    |
    | adarshrajgupta@gmail.com  | Qwerty12345@ | ZARA COAT 3    |
    | rajadarsh711@gmail.com    | Qwerty12345@ | ADIDAS ORIGINAL|
    | adarshgupta@gmail.com     | Qwerty12345@ | IPHONE 13 PRO  |
