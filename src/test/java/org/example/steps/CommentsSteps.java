package org.example.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import org.example.api.client.CommentsEndpoint;
import org.example.context.TestContext;
import org.example.api.pojo.Comment;
import org.example.api.pojo.Post;
import org.example.utils.CommentGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class CommentsSteps {
    private final TestContext testContext;
    private final CommentsEndpoint commentsEndpoint;


    @Inject
    public CommentsSteps(TestContext testContext, CommentsEndpoint commentsEndpoint) {
        this.testContext = testContext;
        this.commentsEndpoint = commentsEndpoint;
    }

    @When("a comment is added to the post")
    public void aCommentIsAddedToThePost() {
        Post post = getReturnedPostFromContext();
        Comment comment = CommentGenerator.getSampleComment();
        Response response = commentsEndpoint.createComment(post.getId(), comment);
        testContext.setExpectedComment(comment);
        testContext.setResponse(response);
        addCommentToContext(response);

    }


    @Then("the comment is added to the post")
    public void theCommentIsAddedToThePost() {
        Comment expectedComment = testContext.getReturnedComment();
        Response response = commentsEndpoint.getCommentsForPost(testContext.getReturnedPost().getId());
        assertThat(response.getStatusCode()).isEqualTo(SC_OK);
        Comment[] actualComments = response.as(Comment[].class);
        assertThat(actualComments.length).withFailMessage("Expected 1 comment for post found [%s]", actualComments.length).isEqualTo(1);
        assertThat(expectedComment).isEqualTo(actualComments[0]);
    }


    @When("{int} comments are added to the post")
    public void commentsAreAddedToThePost(int NoOfComments) {
        Post post = getReturnedPostFromContext();
        List<Comment> comments = new ArrayList<>();
        IntStream.range(0, NoOfComments).forEach((i) -> {
            Comment comment = CommentGenerator.getSampleComment();
            comment.setBody("Some text blah blah blah" + i);
            Response response = commentsEndpoint.createComment(post.getId(), comment);
            assertThat(response.getStatusCode()).isEqualTo(SC_CREATED);
            comments.add(response.as(Comment.class));
        });
        testContext.setComments(comments);


    }


    @Then("the comments are added to the post")
    public void theCommentsAreAddedToThePost() {
        int postId = getReturnedPostFromContext().getId();
        List<Comment> expectedComments = testContext.getComments();
        Response response = commentsEndpoint.getCommentsForPost(postId);
        assertThat(response.getStatusCode()).isEqualTo(SC_OK);
        Comment[] actualComments = response.as(Comment[].class);
        assertThat(actualComments.length).isEqualTo(expectedComments.size());

        List<Comment> sortedExpectedComments = expectedComments.stream().sorted(Comparator.comparingInt(Comment::getId)).collect(Collectors.toList());
        List<Comment> sortedActualComments = Arrays.stream(actualComments).sorted(Comparator.comparingInt(Comment::getId)).collect(Collectors.toList());
        assertThat(sortedActualComments).isEqualTo(sortedExpectedComments);

    }

    @When("a comment is added to the post with field:{string} having value:{string}")
    public void aCommentIsAddedToThePostWithHavingNoEntry(String field, String value) {
        Post post = getReturnedPostFromContext();
        Comment comment = CommentGenerator.getSampleComment();

        if (value.equalsIgnoreCase("null")) {
            value = null;
        }
        switch (field) {
            case "email":
                comment.setEmail(value);
                break;
            case "name":
                comment.setName(value);
                break;
            case "body":
                comment.setBody(value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + field);
        }
        Response response = commentsEndpoint.createComment(post.getId(), comment);
        testContext.setResponse(response);
        addCommentToContext(response);
    }

    @When("a comment is added to a non existing post")
    public void aCommentIsAddedToANonExistingPost() {
        int nonExistingPostId = 99999;
        Comment comment = CommentGenerator.getSampleComment();
        Response response = commentsEndpoint.createComment(nonExistingPostId, comment);
        testContext.setResponse(response);
    }


    @Then("the response returns the comment with the correct postId")
    public void theResponseReturnsTheCommentWithTheCorrectPostId() {
        Comment expectedComment = testContext.getExpectedComment();
        Comment returnedComment = testContext.getReturnedComment();
        assertThat(returnedComment).isEqualToIgnoringNullFields(expectedComment);
        assertThat(returnedComment.getPostId()).isEqualTo(getReturnedPostFromContext().getId());

    }

    private Post getReturnedPostFromContext() {
        Post post = testContext.getReturnedPost();
        if (post == null) {
            throw new RuntimeException("Post has not been created");
        }
        return post;
    }

    @SneakyThrows
    private void addCommentToContext(Response response) {
        testContext.setReturnedComment(response.as(Comment.class));
    }

}
