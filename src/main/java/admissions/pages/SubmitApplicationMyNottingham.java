package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class SubmitApplicationMyNottingham extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	
	UtilityMethods UtilMeth = new UtilityMethods();

	By yesAcceptBxEL = By.id("CIBAA_SUBM_CONF_CIBAA_APPL_ACCEPT");
	By submitAppRedBtnEL = By.id("CIBAA_DERIVED_CIBAA_APPL_SUBMIT");
	
	public SubmitApplicationMyNottingham(WebDriver driver,StudentJsonHandler jsHandler) {
		this.driver = driver;
		this.jsHandler = jsHandler;
	}
	
	public SubmitApplicationMyNottingham acceptTermsChkBx() {
		
		WebElement yesAcceptBx = driver.findElement(yesAcceptBxEL);
		yesAcceptBx.click();
		
		UtilMeth.PauseForJS(driver, 60, true);
		
		return this;
	}
	
	public SubmitApplicationMyNottingham submitAppRedBtn() {
		WebElement submitAppRedBtn = driver.findElement(submitAppRedBtnEL);
		submitAppRedBtn.click();
		
		UtilMeth.PauseForJS(driver, 120, true);
		return this;
	}

	public void SubmitAppClick() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		acceptTermsChkBx().submitAppRedBtn();
		driver.switchTo().defaultContent();
		System.out.println("SubmitAppClick done...");
	}
	

}
