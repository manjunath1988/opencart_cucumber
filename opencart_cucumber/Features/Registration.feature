Feature: Account Registration

  @regression
  Scenario: Successful account registration
    Given the user navigates to Register Account page
    When the user enters the details into below fields
      | firstName | Jhon       |
      | lastname  | Kenedy     |
      | telephone | 9898798798 |
      | password  | test@123   |
    And the user selects the Privacy Policy
    And the user clicks on the Continue button
    Then the user account should get created successfully
