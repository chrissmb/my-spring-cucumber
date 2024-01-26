package com.example.myspringcucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {

    @LocalServerPort
    private String port;

    private RestTemplate restTemplate = new RestTemplate() {{
        setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return false;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {

            }
        });
    }};

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
