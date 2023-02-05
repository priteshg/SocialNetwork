Feature: Adding comments to posts


  Scenario: Add a comment to a post
    Given an existing post
    When a comment is added to the post
    Then a 201 response is returned
    And the response returns the comment with the correct postId
    And the comment is added to the post


  Scenario: Add many comments to a post
    Given an existing post
    When 10 comments are added to the post
    Then the comments are added to the post

  @sad
  Scenario Outline: invalid comment fields
    Given an existing post
    When a comment is added to the post with field:"<field>" having value:"null"
    Then a 404 response is returned
    Examples:
      | field |
      | email |
      | name  |
      | body  |

  @sad
  Scenario Outline: comment with an invalid email
    Given an existing post
    When a comment is added to the post with field:"email" having value:"<invalidEmail>"
    Then a 404 response is returned
    Examples:
      | invalidEmail                        |
      | Abc.example.com                     |
      | A@b@c@example.com                   |

  @sad
  Scenario Outline: comment with invalid name
    Given an existing post
    When a comment is added to the post with field:"name" having value:"<invalidName>"
    Then a 404 response is returned
    Examples:
      | invalidName |
      | !£          |
      | 'Andy       |

  @sad
  Scenario: Add a comment to a non exiting post
    When a comment is added to a non existing post
    Then a 404 response is returned
