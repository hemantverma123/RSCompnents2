package rs.ecom.pages;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.TestBase;

/**
 * 
 * @author Hemant Verma
 *
 */

public class HomePage extends TestBase {
	
	
	WebDriver driver;
	LoginPage loginpage;
	
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(HomePage.class.getName());

	@FindBy(id="searchTerm")
	WebElement searchFilter;

	@FindBy(id="btnSearch")
	WebElement bSearch;
	
	@FindBy(id="pageHeader']/div[2]/a/i")
	WebElement homebutton1;
	
	@FindBy(xpath="//*[@title='Home']")
	WebElement homebutton;
	
	
	@FindBy(xpath="//*[text()[contains(.,'New Products')]][@class='menu']")
	WebElement newProducts;
	
	@FindBy(xpath="//*[text()[contains(.,'Log In')]]")
	WebElement login;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		loginpage = new LoginPage(driver);
		//loginpage.loginToApplication();
		
	}

	public String verifyLogin() {
		String str = loginpage.verifylogin();
		return str;
	}

	public void LogOut() {
		//waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'Sign Out')]]")).click();
		waitForElement(driver, 10,By.xpath("//*[@alt='Sign Out']")).click();
		
	}
	
	public void clickHomeBtn() {
		homebutton.click();
		//waitForElement(driver, 10,By.xpath("//*[@title='Home']")).click();
		//ProcessingImgInvisible(driver, 10, By.id("processing"));
	}

	public void clickNewProductsMenu() {
		newProducts.click();
		//waitForElement(driver, timeOutInSeconds, By.xpath("//*[text()[contains(.,'New Products')]][@class='menu']"));
	}
	
	public void ApplicantLogin(String userid, String Password) {
		login.click();
		loginpage.ApplicantLogin(userid, Password);
	}
	
	public void searchProduct(String prod) {
		searchFilter.sendKeys(prod);
	}
	
	public void ClickSearch() {
		bSearch.click();
	}
	
	
}
