package org.example.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.example.api.client.PostEndpoint;
import org.example.context.TestContext;
import org.example.api.pojo.Post;
import org.example.api.pojo.user.User;
import org.example.utils.PostGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class PostSteps {

    private final PostEndpoint postEndpoint;
    private final TestContext testContext;
    private final UserSteps userSteps;

    @Inject
    public PostSteps(PostEndpoint postEndpoint, TestContext testContext, UserSteps userSteps) {
        this.postEndpoint = postEndpoint;
        this.testContext = testContext;
        this.userSteps = userSteps;
    }


    @When("the user creates a new post")
    public void userCreateANewPost() {
        User user = testContext.getUser();
        Post post = PostGenerator.getSamplePost(user.getId());
        Response response = postEndpoint.addAPost(post);
        testContext.setResponse(response);
        testContext.setExpectedPost(post);
        testContext.setReturnedPost(response.as(Post.class));
    }

    @When("the user creates {int} posts")
    public void userCreatesNPosts(int n) {
        User user = testContext.getUser();
        List<Post> posts = new ArrayList<>();
        IntStream.range(0, n).forEach((i) -> {
            Post post = PostGenerator.getSamplePost(user.getId());
            post.setTitle("test title " + i);
            Response response = postEndpoint.addAPost(post);
            assertThat(response.getStatusCode()).isEqualTo(SC_CREATED);
            posts.add(response.as(Post.class));
        });
        testContext.setPosts(posts);
    }

    @Then("the post is added to the social network")
    public void thePostIsAddedToSocialNetwork() {
        Post post = testContext.getResponse().as(Post.class);
        verifyPostHasBeenAdded(post);
    }


    @Then("the posts are added to the social network")
    public void thePostsAreAddedToTheSocialNetwork() {
        testContext.getPosts().forEach((this::verifyPostHasBeenAdded));
    }


    private void verifyPostHasBeenAdded(Post post) {
        Response response = postEndpoint.getPost(post.getId());
        assertThat(response.getStatusCode()).isEqualTo(SC_OK);
        assertThat(post).isEqualTo(response.as(Post.class));
    }

    @Given("an existing post")
    public void anExistingPost() {
        userSteps.anExistingUser();
        userCreateANewPost();
    }


    @When("the user creates a new post with {string} having no entry")
    public void theUserCreatesANewPostWithStringHavingNoEntry(String field) {
        User user = testContext.getUser();
        Post post = PostGenerator.getSamplePost(user.getId());
        switch (field) {
            case "title":
                post.setTitle(null);
                break;
            case "body":
                post.setBody(null);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + field);
        }

        Response response = postEndpoint.addAPost(post);
        testContext.setResponse(response);
        testContext.setPosts(List.of(response.as(Post.class)));
    }

    @And("the response returns the requested post")
    public void theResponseReturnsTheRequestedPost() {
        Post expectedPost = testContext.getExpectedPost();
        Post returnedPost = testContext.getReturnedPost();
        assertThat(returnedPost).isEqualToIgnoringNullFields(expectedPost);
    }
}

