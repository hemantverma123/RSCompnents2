package admissions.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class RegistrationCompletePageMyNottingham extends TestBase{
	
	WebDriver driver;
	StudentJsonHandler jsHandler;
	
	UtilityMethods UtilMeth = new UtilityMethods();
	
	By loginPageBtn = By.id("CIBAA_RGSTR_WRK_SIGN_ON");
	By registrationCompleteDivEL = By.id("win0div$ICField1"); // This is to find the "Your registration is complete" txt ... the <span> has no ID... 
	
	String regCompletetxt = "Your registration is complete";
	
	public RegistrationCompletePageMyNottingham(WebDriver driver,StudentJsonHandler jsHandler) {
		this.driver = driver;
		this.jsHandler = jsHandler;
	}
	
	public void VerifyRegistrationComplete() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		WebElement regCompleteDiv = driver.findElement(registrationCompleteDivEL);
		WebElement regCompleteSpan = regCompleteDiv.findElement(By.tagName("span"));
		
		assertEquals("Registration complete page text is wrong", regCompletetxt, regCompleteSpan.getText());
		System.out.println("Registration Complete Page Txt is => "+regCompleteSpan.getText());
		driver.switchTo().defaultContent();
	}
	
	public RegistrationCompletePageMyNottingham ClickGoToLoginPageBtn() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver, 10, loginPageBtn).click();
		driver.switchTo().defaultContent();
		return this;
	}

}
