package org.example.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.pojo.Post;

import java.util.Arrays;
import java.util.List;

public class PostEndpoint extends Base {
    public static final String POSTS = "posts";
    public static final String POSTS_BY_ID = "posts/{id}";

    public List<Post> getPosts() {
        return Arrays.asList(getBaseRequestSpecification()
                .get(POSTS)
                .then()
                .extract()
                .body().as(Post[].class));
    }

    public Response getPost(int id) {
        return getBaseRequestSpecification()
                .pathParams("id", id)
                .get(POSTS_BY_ID);
    }

    public Response addAPost(Post post) {
        return getBaseRequestSpecification()
                .contentType(ContentType.JSON)
                .body(post)
                .post(POSTS);
    }

    public Response deletePost(int id) {
        return getBaseRequestSpecification()
                .pathParams("id", id)
                .delete(POSTS_BY_ID);
    }




}
