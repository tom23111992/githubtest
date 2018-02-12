Feature: Github test

  Background:
    Given I login into Github "tomasz.tutka2016@wp.pl" and "test1234"

  Scenario: Creating a repository
    When I create a repository
    Then Should be content displayed

  Scenario: Push commits
    When I create a repository
    Then Should be content displayed
    And I click create file
    Then Should be file displayed on the list

  Scenario: Creating Pull Request
    When I create a repository
    Then Should be content displayed
    And I create new branch
    And I click create file
    Then Should be file displayed on the list
    And I create pull request
    Then Should be merge request button displayed

  Scenario: Accepting Pull Request
    When I create a repository
    Then Should be content displayed
    And I create new branch
    And I click create file
    Then Should be file displayed on the list
    And I create pull request
    Then Should be merge request button displayed
    And I click merge pull request
    Then Should be merged info displayed

  Scenario: Deleting a repository
    When I create a repository
    Then Should be content displayed
    And I delete a repository