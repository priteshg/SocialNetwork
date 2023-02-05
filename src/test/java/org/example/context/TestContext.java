package org.example.context;

import io.cucumber.guice.ScenarioScoped;
import io.restassured.response.Response;
import lombok.Data;
import org.example.api.pojo.Comment;
import org.example.api.pojo.Post;
import org.example.api.pojo.user.User;

import java.util.List;

@ScenarioScoped
@Data
public class TestContext {
    private Response response;

    private User user;

    private Post returnedPost;
    private Post expectedPost;
    private List<Post> posts;

    private Comment returnedComment;
    private Comment expectedComment;
    private List<Comment> comments;
}
