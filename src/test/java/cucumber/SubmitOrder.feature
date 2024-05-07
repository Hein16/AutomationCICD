
@tag
Feature: Purchase the order from the Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page 
  #d given htl mhr yay chin dk hr twy koh StepDefinitionImpl java file htl mhr chate p 3 ml
  
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    #Given Logged in with username "kenn22@gmail.com" and password "KENNhtike@22" #d loh yay ll ya dr mk out mhr name nk password so p data twy htae htr dl ek data twy koh variable u p give htl mhr khw htr tr
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage


#d examples htl data twy koh 1 line htae htr yin 1 khr run ml 2 lines htae htr yin 2 khr run line shi thalouk run py ml
    Examples: 
      | name             | password       | productName   |
      | kenn22@gmail.com | KENNhtike@22   | ZARA COAT 3   |
      | hein22@gmail.com | KENNhtike@22   | ZARA COAT 3   |
      
  
#run lyk yin java file htl mhr yay htr dk hr twy nk connect p Given, Given to Then ah hti ah sin lyk run twr ml