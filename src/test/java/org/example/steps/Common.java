package org.example.steps;

import com.google.inject.Inject;
import io.cucumber.java.en.Then;
import org.example.context.TestContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Common {

    private final TestContext testContext;

    @Inject
    public Common(TestContext testContext) {
        this.testContext = testContext;
    }

    @Then("a {int} response is returned")
    public void iGetAResponse(int responseCode) {
        assertThat(testContext.getResponse().getStatusCode()).isEqualTo(responseCode);
    }
}
