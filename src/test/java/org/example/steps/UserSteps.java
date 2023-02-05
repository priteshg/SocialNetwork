package org.example.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import org.example.api.client.UsersEndpoint;
import org.example.context.TestContext;
import org.example.api.pojo.user.User;
import org.example.utils.UserGenerator;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.assertj.core.api.Java6Assertions.assertThat;


public class UserSteps {
    private final UsersEndpoint usersEndpoint;
    private final TestContext testContext;

    @Inject
    public UserSteps(UsersEndpoint usersEndpoint, TestContext testContext) {
        this.usersEndpoint = usersEndpoint;
        this.testContext = testContext;
    }

    @Given("an existing user")
    public void anExistingUser() {
        Response response = usersEndpoint.createUser(UserGenerator.getSampleUser());
        assertThat(response.getStatusCode()).isEqualTo(SC_CREATED);
        testContext.setUser(response.as(User.class));
    }
}
