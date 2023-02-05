package org.example.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.pojo.Comment;

import java.util.Arrays;
import java.util.List;

public class CommentsEndpoint extends Base {
    public static final String COMMENTS = "Comments";
    public static final String COMMENT_BY_ID = "Comments/{id}";

    public static final String COMMENTS_FOR_POST = "posts/{id}/comments";

    public List<Comment> getComments() {
        return Arrays.asList(getBaseRequestSpecification()
                .get(COMMENTS)
                .then()
                .extract()
                .body().as(Comment[].class));
    }

    public Response createComment(int postId, Comment comment) {
        return getBaseRequestSpecification()
                .contentType(ContentType.JSON)
                .body(comment)
                .pathParams("id", postId)
                .post(COMMENTS_FOR_POST);
    }
    public Response getCommentsForPost(int id) {
        return getBaseRequestSpecification()
                .pathParams("id", id)
                .get(COMMENTS_FOR_POST);
    }


    public Response getComment(int id) {
        return getBaseRequestSpecification()
                .pathParams("id", id)
                .get(COMMENT_BY_ID);
    }

    public Response deleteComment(int id) {
        return getBaseRequestSpecification()
                .pathParams("id", id)
                .delete(COMMENT_BY_ID);
    }
}
