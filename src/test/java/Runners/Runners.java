package Runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, monochrome = true, plugin = {"json:target/cucumber.json", "html:target/cucumber"},
        features = "src/test/resources/", format = "html:target/site/cucumber-pretty", glue = "classpath:", tags = {}, dryRun = false)
public class Runners {
}
