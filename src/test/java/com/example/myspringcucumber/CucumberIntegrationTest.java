package com.example.myspringcucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        plugin = {
                "pretty",
                "json:target/cucumber-report.json",
                "html:target/cucumber-report.html",
        },
        glue = {"com.example.myspringcucumber.steps"})
public class CucumberIntegrationTest {
}
