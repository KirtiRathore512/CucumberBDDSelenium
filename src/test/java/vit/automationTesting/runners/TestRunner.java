package vit.automationTesting.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features="classpath:features",
		glue="vit.automationTesting.stepdefs",
		tags="@YourList",
		plugin={"pretty", // to generate reports
	            "html:target/html/htmlreport.html",
	            "json:target/json/file.json",
	            },
		monochrome=true,
		publish=true,
		dryRun= false
	
)

public class TestRunner {

}
