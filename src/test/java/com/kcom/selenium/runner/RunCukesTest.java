package com.kcom.selenium.runner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources",
                glue = {"com.kcom.selenium.stepDefinations"},
                format = {"pretty", "html:target/site/cucumber-pretty",
                "json:target/cucumber.json"})
public class RunCukesTest {
}
