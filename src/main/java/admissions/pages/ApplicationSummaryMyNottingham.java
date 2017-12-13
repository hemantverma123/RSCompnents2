package admissions.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class ApplicationSummaryMyNottingham extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	
	UtilityMethods UtilMeth = new UtilityMethods();

	String confirm = "Application submitted successfully";
	String malaysiaConfirm = "Please note that you are required to pay an application fee. Your application is still being processed and we will contact you when you are able to pay your fee. In the meantime please do not hesitate to contact us if you have any enquiries.";

	By makePaymentBtnEL = By.id("CIBAA_PAYM_WRK_SUBMIT_DT");
	By gotoHomePageBtnEL = By.id("CIBAA_LNDNG_WRK_CANCEL");
	By SignOut = By.xpath("//*[@alt='Sign Out']");
	By weStudentID = By.id("AGTEMPLIDS$0");
	By weApplicationNumber = By.id("CIBAA_APPL_S_VW_ADM_APPL_NBR$0");
	By weViewDetails = By.name("CIBAA_DERIVED_VIEW_FORM$IMG$0");   //for future use

	By successTxtEL = By.id("CIBAA_CVV_WRK_DESCR254");

	public ApplicationSummaryMyNottingham(WebDriver driver,StudentJsonHandler jsHandler) {
		this.driver = driver;
		this.jsHandler = jsHandler;
	}

	public ApplicationSummaryMyNottingham makePayment() {

		WebElement makePaymentBtn = driver.findElement(makePaymentBtnEL);
		makePaymentBtn.click();
		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public ApplicationSummaryMyNottingham gotoHomePage() {

		WebElement gotoHomePageBtn = driver.findElement(gotoHomePageBtnEL);
		gotoHomePageBtn.click();
		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public ApplicationSummaryMyNottingham confirmSuccess() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		WebElement successTxt = driver.findElement(successTxtEL);

		Assert.assertEquals("Application failed!!!!", confirm, successTxt.getText());
		System.out.println("End Of Test!!!!!!!!!!!!!!!");
		driver.switchTo().defaultContent();
		return this;

	}

	public ApplicationSummaryMyNottingham confirmSuccess(String country) {

		WebElement successTxt = driver.findElement(successTxtEL);

		if (country.equals("Malaysia")) {
			
			Assert.assertEquals("Application failed!!!!", malaysiaConfirm, successTxt.getText());
			System.out.println("End Of Test!!!!!!!!!!!!!!!");

		} else {

			Assert.assertEquals("Application failed!!!!", confirm, successTxt.getText());
			System.out.println("End Of Test!!!!!!!!!!!!!!!");
		}

		return this;
	}
	
	public void ViewDetails() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver, timeOutInSeconds, By.name("CIBAA_DERIVED_VIEW_FORM$IMG$0")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		driver.switchTo().defaultContent();
	}
	
	public void GotoHomePage() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver, timeOutInSeconds, By.id("CIBAA_LNDNG_WRK_CANCEL")).click();
		
		String studentID = waitForElement(driver, timeOutInSeconds, weStudentID).getText();
		String appNumber = waitForElement(driver, timeOutInSeconds, weApplicationNumber).getText();
		jsHandler.setStudentID(studentID);
		jsHandler.setApplicationNumber(appNumber);
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		driver.switchTo().defaultContent();
	}

}


