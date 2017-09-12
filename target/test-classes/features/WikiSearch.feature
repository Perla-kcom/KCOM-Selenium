Feature: Wikipedia Search Results validation

  @Regression
  Scenario: Verify Wikipedia search results
    Given user is on landing page
    When I enter search string english in search text box
    Then I should see data populated text as english
    And I select english as the search language
    Then I click search button
    And I should see search page heading as english
    Then I check search results displayed in english
    And I navigate search results page
    Then I verify search results