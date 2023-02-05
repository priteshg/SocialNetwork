package org.example.steps;

import com.google.inject.Inject;
import io.cucumber.java.After;
import org.example.api.client.PostEndpoint;
import org.example.api.client.UsersEndpoint;
import org.example.context.TestContext;

public class Hooks {

    private final TestContext testContext;
    private final UsersEndpoint usersEndpoint;
    private final PostEndpoint postEndpoint;

    @Inject
    public Hooks(TestContext testContext, UsersEndpoint usersEndpoint, PostEndpoint postEndpoint) {
        this.testContext = testContext;
        this.usersEndpoint = usersEndpoint;
        this.postEndpoint = postEndpoint;
    }

    @After()
    public void tearDownData() {

        if (testContext.getReturnedPost() != null) {
            postEndpoint.deletePost(testContext.getReturnedPost().getId());
        }
        if (testContext.getPosts() != null) {
            testContext.getPosts().forEach(p -> postEndpoint.deletePost(p.getId()));
        }
        if (testContext.getUser() != null) {
            usersEndpoint.deleteUser(testContext.getUser().getId());
        }
    }
}
