package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions
(
features={".//Features/Login2.feature"},
glue="stepDefinitions",
dryRun=false,
monochrome=true,
plugin= {"pretty","json:target/cucumber-json/cucumber.json","html:test-output"}
)
public class TestRun {

}
