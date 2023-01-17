Feature: A set of scenarios for testing the "example" module

  Scenario:a user adds a product to the cart
    Given user at home page
    And  user logged in
    When clicks on product
    And adds to cart
    Then productAdded

  Scenario: admin deletes user
    Given admin at home page
    And admin logged in
    When click on dashboard
    And go to management
    And search a user
    And click on delete user
    Then user deleted successfully


