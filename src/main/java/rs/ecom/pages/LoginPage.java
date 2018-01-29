package rs.ecom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import framework.TestBase;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


/**
 * 
 * @author Hemant Verma
 *
 */

public class LoginPage extends TestBase {
	
	WebDriver driver;

	private final static Logger log = Logger.getLogger(LoginPage.class.getName());

	@FindBy(name="username")
	WebElement username;

//	@FindBy(id = "email")
//	WebElement emailAddress;

	@FindBy(name = "j_password")
	WebElement passowrd;

	@FindBy(name = "loginBtn")
	WebElement SignIn;

	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnsignIn() {
		waitForElement(driver, 10, SignIn).click();
		//log.info("click on sign in and object is:-" + SignIn.toString());
	}

	public void enterUsername(String userid) {
		waitForElement(driver, 10, username);
		this.username.sendKeys(userid);
	}

	public void enterPassword(String pwd) {
		waitForElement(driver, 10, passowrd);
		this.passowrd.sendKeys(pwd);
	}

	public String verifylogin()
	{	
		return waitForElement(driver,10,driver.findElement(By.xpath("//*[text()[contains(.,'"+"Log Out"+"')]]"))).getText(); 
	}

	public void ApplicantLogin(String userid, String Password) {
	    enterUsername(userid);
		enterPassword(Password);
		clickOnsignIn();
	}

}
