package rs.ecom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import framework.TestBase;

import org.apache.commons.lang3.time.StopWatch;

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

	
	@FindBy(id="pageHeader']/div[2]/a/i")
	WebElement homebutton1;
	
	@FindBy(xpath="//*[@title='Home']")
	WebElement homebutton;
	
	
	@FindBy(xpath="//*[text()[contains(.,'New Products')]]")
	WebElement newProducts;
	
	@FindBy(xpath="//*[text()[contains(.,'Log In')]]")
	WebElement login;

	//----------product page

	@FindBy(xpath="//*[text()[contains(.,'Solder Paste')]]")
	WebElement Solder;
	@FindBy(xpath="//*[text()[contains(.,'Solder Pastes')]]")
	WebElement SolderPastes;
	@FindBy(xpath="//*[text()[contains(.,'My basket')]]")
	WebElement basketTitle;
	@FindBy(xpath="//*[text()[contains(.,'Solder Paste')]]")
	WebElement Solder3;

	@FindBy(id="atbBtn-1")
	WebElement add;
	
	@FindBy(id="js-basketAmt")
	WebElement bCheckout;


	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		loginpage = new LoginPage(driver);
		//loginpage.loginToApplication();
		
	}

public void login(String userid, String encryptedPassword, String runMode) {
	loginpage.loginToApplication(userid,encryptedPassword,runMode);
}

public String verifyLogin() {
	String str = loginpage.verifylogin();
	return str;
}

	
	public void LogOut() {
		//waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'Sign Out')]]")).click();
		waitForElement(driver, 10,By.xpath("//*[@alt='Sign Out']")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
	}
	
	public void clickHomeBtn() {
		homebutton.click();
		//waitForElement(driver, 10,By.xpath("//*[@title='Home']")).click();
		//ProcessingImgInvisible(driver, 10, By.id("processing"));
	}

	public void clickNewProductsMenu() {
		newProducts.click();		
	}
	
	public void ApplicantLogin(String userid, String Password) {
		login.click();
		loginpage.ApplicantLogin(userid, Password);
	}
	

}
