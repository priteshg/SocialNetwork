# Social Network posts and comments backend feature tests

Automated feature tests to test adding posts and commenting on them using the social network API service.

## Features

There are 2 feature files:
* add_a_post.feature
* add_comments_to_a_post.feature

All scenarios in the feature files create data required to run the scenario so are data independent. 

All data is cleared by the use of cucumber hooks. 
See SocialNetwork\src\test\java\org\example\steps\Hooks.java

### Add a post

This feature comprises 3 scenarios.

As a precondition a new user is created and used to create the post.

#### Assumptions:
* You verify that the post has been added to the social network by using the endpoint - GET	/posts/{postId}
* Title, and body are required fields - a 404 is returned if null.

### Note:
The verification can be extended further by also testing the post has been added to the users profile when this becomes available.

### Add comments to a post
This feature comprises 6 scenarios.

As a precondition posts are created prior to comments being added. 

#### Assumptions:
* You add a comment by using the endpoint - POST posts/{postId}/comments
* Email, name, and body are required fields - a 404 is returned if null.


## Running The Tests

You can execute all tests by running the following maven command from the root of the project:

```
mvn clean test
```
This will pick up the TestRunner class and execute all scenarios in the features.