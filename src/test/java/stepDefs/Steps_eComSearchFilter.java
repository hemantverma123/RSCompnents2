package stepDefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import framework.MongoInterface;
import framework.TestBase;
import framework.envspecific.TestManager;
import framework.envspecific.UonCountries;
import framework.envspecific.UonCountryEnvironments;
import framework.envspecific.UonTestEnviroments;
import rs.ecom.pages.BasketSummaryPage;
import rs.ecom.pages.HomePage;
import rs.ecom.pages.LoginPage;
import rs.ecom.pages.ProductPage;
import rs.ecom.pages.SearchResultPage;

//import cucumber.api.PendingException;

public class Steps_eComSearchFilter extends TestBase implements En {

	public static final Logger log = Logger.getLogger(Steps_eComSearchFilter.class.getName());	

	MongoInterface mi;
	HomePage homepage;
	LoginPage loginpage;
	ProductPage prodpage;
	BasketSummaryPage basket;
	SearchResultPage srp;
	
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");
	
	String username, password,myTestURL;
	String country = UonCountries.UNITED_KINGDOM.toString();
	String nationCode = UonCountries.UNITED_KINGDOM.nationCode();
	String countryCode = UonCountries.UNITED_KINGDOM.countryCode();
	String excelname, sheetname;
	int data_row=1;


@Before
public void BeforeScenario(Scenario s) {
	System.out.println("Should execute before the scenario : " + s.getName());
	log.info("Executing scenario - " + s.getName());
}
	

public Steps_eComSearchFilter() {

	Given("^initial setup completed for SearchFilter$", () -> {
	    System.out.println("inside the initial setup for search filter");

		init();
		mi = new MongoInterface("students");
		
		//commonSteps = new Steps_eComCommon(driver);
		homepage = new HomePage(driver);
		prodpage = new ProductPage(driver);
		basket = new BasketSummaryPage(driver);
		srp = new SearchResultPage(driver);
		
		//Assuming that test url is deployed in PUAT env
		UonCountryEnvironments.setEnv(UonTestEnviroments.PUAT.toString()); 
		//UonCountryEnvironments.setEnv(UonTestEnviroments.UATB.toString());
		TestManager.setupEnvironmentsAndBrowserForTests(countryCode);
		myTestURL = TestManager.getMyTestEnvUrl();
		
		getUrl(myTestURL);  //it will override the default url specified in the config file

		writeToDataFile("product_data.txt",	"InsertDate :" + formater.format(calendar.getTime()));
		
		log.info("=========== initial setup done=============");

	});

	Given("^user clicks on Home button for search$", () -> {
	    homepage.clickHomeBtn();

	});
	
	When("^user search \"([^\"]*)\"$", (String product) -> {
		homepage.searchProduct(product);
	});

	When("^user clicks search button$", () -> {
	   homepage.ClickSearch();
	});

	//Then("^user should see (.*)$", (String result) -> {
	Then("^user should see \"([^\"]*)\"$", (String result) -> {
		srp.verifysearch(result);
	});

	System.out.println("End of search filter step class");
}


	@After
	public void afterScenario() {
		System.out.println("inside after scenario hookup");
		log.info("=========== Finished Create UK Applicants Test=============");
	}

}
