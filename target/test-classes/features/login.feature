Feature: Login functionality
 Scenario: Successful login with valid credentials
    Given I am on the login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the inventory page
 
 Scenario: Failed login with invalid credentials
    Given I am on the login page
    When I enter username "wrong_user" and password "wrong_pass"
    And I click the login button
    Then I should see an error message "Username and password do not match"