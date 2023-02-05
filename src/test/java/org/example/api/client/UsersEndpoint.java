package org.example.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.example.api.pojo.user.User;

import java.util.Arrays;
import java.util.List;

public class UsersEndpoint extends Base {
    public static final String USERS = "users";
    public static final String USER_BY_ID = "users/{id}";

    public List<User> getUsers() {
        return Arrays.asList(getBaseRequestSpecification()
                .get(USERS)
                .then()
                .extract()
                .body().as(User[].class));
    }

    public Response createUser(User user) {
        return getBaseRequestSpecification()
                .contentType(ContentType.JSON)
                .body(user)
                .post(USERS);
    }

    public Response getUser(int id) {
        return getBaseRequestSpecification()
                .pathParams("id", id)
                .get(USER_BY_ID);
    }

    public Response deleteUser(int id) {
        return getBaseRequestSpecification()
                .pathParams("id", id)
                .delete(USER_BY_ID);
    }

}
