Feature: Product Page

  Background:
    Given I am logged in with valid credentials

  Scenario: Verify Products page is displayed
    Then I should see "Products" as the page title

  Scenario: Verify adding product to cart updates cart count
    When I add a product to the cart
    Then the cart count should be "1"

  Scenario: Verify sorting products 
    When I sort products by "Price (low to high)" 
    Then products should be sorted in ascending order of price
    
   Scenario: Verify sorting products by high to low
    When I sort products by "Price (high to low)"
    Then products should be sorted in descending order of price
