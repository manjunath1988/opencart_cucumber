Feature: Login with valid credentials

  @sanity @regression
  Scenario: Successful login with valid credentials
    Given the user navigates to login page
    When the user enters email as "manjunath@testing123.com" and password as "Test@123"
    And the user clicks on the Login button
    Then the user must be redirected to the My Account Page
  
  #@regression
  #Scenario Outline: Login Data Driven
  #  Given the user navigates to login page
  #  When the user enters email as "<email>" and password as "<password>"
  #  And the user clicks on the Login button
  #  Then the user must be redirected to the My Account Page

  #  Examples: 
  #    | email                    | password |
  #    | manjunath@testing.com    | Test@123 |
  #    | testingmanju@test.com    | jio@123  |
  #    | manju@manju.com          | QA@123   |
  #    | manjunath@testing123.com | Test@123 |
