package stepDefs;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"html:target/cucumberHtmlReport"},
		//pretty:target/cucumber-json-report.json
		features = {"classpath:features"},
		glue = {"stepDefs"},
		tags = {"@E2ETest1"}
		//tags = {"@manyapplicants","@datatableapplicant"}
		//tags = {"~@SmokeTest"}  --will inverse the choice of tags, will exeucte all tags other than @somketest 
)

public class RunRSTests {

}
