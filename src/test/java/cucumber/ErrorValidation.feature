
@tag
Feature: Error validation
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce Page 
    When Logged in with username <name> and password <password>
    Then "Incorrect email password." message is displayed

    Examples: 
      | name             | password       | 
      | kenn22@gmail.com | KENNhtike@21   |