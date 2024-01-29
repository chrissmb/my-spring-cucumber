package com.example.myspringcucumber.steps;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class StepDefinitions {

    @LocalServerPort
    private String port;

    @Autowired
    private RestTemplate restTemplateTest;

    public ResponseEntity<String> responseEntityString;

    private final static String HOST = "http://localhost";

    private WireMockServer wireMockServer;

    private String getHostPort() {
        return HOST + ":" + port + "/";
    }

    @Before
    public void before() {
        wireMockServer = new WireMockServer(options()
                .port(8089)
                .notifier(new ConsoleNotifier(true))
                .usingFilesUnderClasspath("wiremock"));
        wireMockServer.start();
    }

    @After
    public void after() {
        wireMockServer.stop();
    }

    @When("the client calls version")
    public void the_client_calls_version() {
        responseEntityString = restTemplateTest.getForEntity(getHostPort() + "version", String.class);
        ResponseEntity<String> x = restTemplateTest.getForEntity("http://localhost:8089/body-file", String.class);
        System.out.println(x.getBody());
    }

    @When("the client add {double} and {double}")
    public void the_client_add_and(Double x, Double y) {
        String endpoint = String.format("calc/sum/%s/%s", x, y);
        responseEntityString = restTemplateTest.getForEntity(getHostPort() + endpoint, String.class);
    }

    @When("the client divide {double} and {double}")
    public void the_client_divide_and(Double x, Double y) {
        String endpoint = String.format("calc/divide/%s/%s", x, y);
        responseEntityString = restTemplateTest.getForEntity(getHostPort() + endpoint, String.class);
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(int expected) {
        assertEquals(expected, responseEntityString.getStatusCode().value());

    }

    @Then("the client receives a body {string}")
    public void the_client_receives_a_body(String expected) {
        assertEquals(expected, responseEntityString.getBody());
    }

    @When("the client calls users")
    public void the_client_calls_users() {
        responseEntityString = restTemplateTest.getForEntity(getHostPort() + "user", String.class);
    }

    @Then("the client receives a body file {string}")
    public void the_client_receives_a_body_file(String file) throws JSONException {
        URL url = getClass().getClassLoader().getResource("expected-json/" + file);
        String expected = getContentFile(url.getFile());
        JSONAssert.assertEquals(expected, responseEntityString.getBody(), true);
    }

    private String getContentFile(String filepath) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
