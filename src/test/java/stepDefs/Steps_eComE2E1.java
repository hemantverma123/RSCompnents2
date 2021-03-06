package stepDefs;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import framework.MongoInterface;
import framework.TestBase;
import framework.envspecific.CountryData;
import framework.envspecific.TestManager;
import framework.envspecific.UonCountries;
import framework.envspecific.UonCountryEnvironments;
import framework.envspecific.UonTestEnviroments;
import rs.ecom.pages.BasketSummaryPage;
import rs.ecom.pages.HomePage;
import rs.ecom.pages.LoginPage;
import rs.ecom.pages.ProductPage;

//import cucumber.api.PendingException;

public class Steps_eComE2E1 extends TestBase implements En {

	public static final Logger log = Logger.getLogger(Steps_eComE2E1.class.getName());	

	MongoInterface mi;
	HomePage homepage;
	LoginPage loginpage;
	ProductPage prodpage;
	BasketSummaryPage basket;
	Steps_eComCommon commonSteps;
	
	CountryData cd;
	
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");
	
	String username, password,myTestURL;
	String country = UonCountries.UNITED_KINGDOM.toString();
	String nationCode = UonCountries.UNITED_KINGDOM.nationCode();
	String countryCode = UonCountries.UNITED_KINGDOM.countryCode();
	String excelname, sheetname;
	int data_row=1;
	
@BeforeClass
public void setup() throws IOException{
// will be used if test runs from junit framework	
}

@Before
public void BeforeScenario(Scenario s) throws IOException {
	System.out.println("Should execute before scenario : " + s.getName());
	log.info("Executing scenario - " + s.getName());
}
	
@SuppressWarnings("deprecation")
public Steps_eComE2E1() {

	Given("^initial setup completed$", () -> {
	    System.out.println("inside the initial setup");

		init();
		mi = new MongoInterface("students");
		cd = new CountryData();

		//commonSteps = new Steps_eComCommon(driver);
		homepage = new HomePage(driver);
		prodpage = new ProductPage(driver);
		basket = new BasketSummaryPage(driver);

		//Assuming that test url is deployed in PUAT env
		UonCountryEnvironments.setEnv(UonTestEnviroments.PUAT.toString()); 
		//UonCountryEnvironments.setEnv(UonTestEnviroments.UATB.toString());
		TestManager.setupEnvironmentsAndBrowserForTests(countryCode);
		myTestURL = TestManager.getMyTestEnvUrl();
		
		getUrl(myTestURL);  //it will override the default url specified in the config file

		writeToDataFile("product_data.txt",	"InsertDate :" + formater.format(calendar.getTime()));
		
		log.info("=========== initial setup done=============");

	});

	Given("^get the url from config file$", () -> {
	    // will cover config file input here

	});

	Given("^user clicks on Home button$", () -> {
	    homepage.clickHomeBtn();

	});
	
	Given("^user selects New Products$", () -> {
	 
		homepage.clickNewProductsMenu();
	});

	Given("^user selects product \"([^\"]*)\" to buy$", (String product) -> {
		prodpage.selectproduct(product);

	});

	Given("^user selects product category \"([^\"]*)\"$", (String prodcat) -> {
		prodpage.selectproductcat(prodcat);
	    
	});
	
	Given("^user adds selected product to the basket$", () -> {
		prodpage.addToBasket();

	});

	Given("^user click basket button$", () -> {
	    prodpage.clickbasket();

	});

	Then("^\"([^\"]*)\" page is displayed with the product \"([^\"]*)\"$", (String title, String selectedProduct) -> {
	    basket.verifyPageTitle(title);
	    basket.verifyProdOnBasketPage(selectedProduct);

	});

	And("^user login with user \"([^\"]*)\" and password \"([^\"]*)\"$", (String userid, String pwd) -> {
		homepage.ApplicantLogin(userid, pwd);
		    
	});

	Then("^verify login success$", () ->{
		String logintext = homepage.verifyLogin();
		Assert.assertEquals(logintext, "Log Out");
	});
	
	Then("^take the screenshot as \"([^\"]*)\"$", (String name) -> {
		getScreenShot(name);
	});

	System.out.println("End of E2E step class");
}

	@After
	public void afterScenario() {
		System.out.println("inside after scenario hookup");
		log.info("=========== Finished E2E Test=============");
	}

}
