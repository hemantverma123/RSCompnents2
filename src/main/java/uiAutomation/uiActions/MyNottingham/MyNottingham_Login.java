package uiAutomation.uiActions.MyNottingham;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import cs.peam.pages.PS_Login;
import uiAutomation.testBase.TestBase;
/**
 * 
 * @author Hemant Verma
 *
 */

public class MyNottingham_Login extends TestBase {
	
	
	WebDriver driver;
	
	private final static Logger log = Logger.getLogger(PS_Login.class.getName());

	@FindBy(id="userid")
	WebElement username;

//	@FindBy(id = "email")
//	WebElement emailAddress;

	@FindBy(id = "pwd")
	WebElement passowrd;

	@FindBy(xpath = "//*[@name='Submit']")
	WebElement SignIn;

	@FindBy(id = "psloginerror")
	WebElement psloginerror;
	
	@FindBy(xpath = ".//*[@id='btn-manageDocs']")
	WebElement manageDoc_btn; 
	
	public MyNottingham_Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnsignIn() {
		SignIn.click();
		log.info("click on sign in and object is:-" + SignIn.toString());
	}

	public void enterUsername(String username) {
		this.username.sendKeys(username);
	}

	public void enterPassword(String password) {
		this.passowrd.sendKeys(password);
	}


	public String getInvalidLoginText() {
		
		waitForElement(driver, 10, psloginerror);
		String text = psloginerror.getText();
		return text;
	}
	
	
	public String getHomePageText()
	{
		waitForElement(driver, 10, manageDoc_btn);
		String text = manageDoc_btn.getText();
		return text;
	}
	
	
	public void loginToApplication(String username, String password) {
		//clickOnsignIn();
		enterUsername(username);
		enterPassword(password);
		clickOnsignIn();
	}
	
	public void navigateTo(String data){
		String locator = "//a[contains(text(),'"+data+"')]";
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void mouseOver(String data) throws InterruptedException{
		String locator = "//a[contains(text(),'"+data+"')]";
		Actions select = new Actions(driver);
		select.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
		Thread.sleep(1000);
	}
	
	public void clickOnProduct(String data){
		String locator = "//a[contains(text(),'"+data+"')]";
		driver.findElement(By.xpath(locator)).click();
	}

}
