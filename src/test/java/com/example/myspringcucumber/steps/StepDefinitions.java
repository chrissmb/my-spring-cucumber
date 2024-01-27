package com.example.myspringcucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class StepDefinitions {

    @LocalServerPort
    private String port;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> responseEntityString;

    private final static String HOST = "http://localhost";

    private String getHostPort() {
        return HOST + ":" + port;
    }

    @When("the client calls version")
    public void the_client_calls_version() {
        responseEntityString = restTemplate.getForEntity(getHostPort() + "/version", String.class);
    }

    @When("the client add {double} and {double}")
    public void the_client_add_and(Double x, Double y) {
        String endpoint = String.format("/calc/sum/%s/%s", x, y);
        responseEntityString = restTemplate.getForEntity(getHostPort() + endpoint, String.class);
    }

    @When("the client divide {double} and {double}")
    public void the_client_divide_and(Double x, Double y) {
        String endpoint = String.format("/calc/divide/%s/%s", x, y);
        responseEntityString = restTemplate.getForEntity(getHostPort() + endpoint, String.class);
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(int expected) {
        assertEquals(expected, responseEntityString.getStatusCode().value());
    }

    @Then("the client receives a body {string}")
    public void the_client_receives_a_body(String expected) {
        assertEquals(expected, responseEntityString.getBody());
    }
}
