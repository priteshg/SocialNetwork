Feature: Adding posts


  Scenario: create a post
    Given an existing user
    When the user creates a new post
    Then a 201 response is returned
    And the response returns the requested post
    And the post is added to the social network

  Scenario: create multiple posts
    Given an existing user
    When the user creates 10 posts
    Then the posts are added to the social network


  @sad
  Scenario Outline: invalid post fields
    Given an existing user
    When the user creates a new post with "<field>" having no entry
    Then a 404 response is returned
    Examples:
      | field |
      | title |
      | body  |