package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class EditAddressLinkPage extends TestBase {

	WebDriver driver;
	StudentJsonHandler jsHandler;
	UtilityMethods UtilMeth = new UtilityMethods();

	By addressLine1 = By.id("DERIVED_ADDRESS_ADDRESS1");
	By addressLine2 = By.id("DERIVED_ADDRESS_ADDRESS2");
	By addressLine3 = By.id("DERIVED_ADDRESS_ADDRESS3");
	By addressCity = By.id("DERIVED_ADDRESS_CITY");
	By addressState = By.id("DERIVED_ADDRESS_STATE");
	By addressPostal = By.id("DERIVED_ADDRESS_POSTAL");

	By okBtn = By.name("DERIVED_ADDRESS_OK_PB$IMG");

	public EditAddressLinkPage(WebDriver driver,StudentJsonHandler jsHandler) {
		this.driver = driver;
		this.jsHandler = jsHandler;

	}

	public EditAddressLinkPage TypeAddressLine1(String addressLine) {
		driver.findElement(addressLine1).sendKeys(addressLine);
		return this;
	}

	public EditAddressLinkPage TypeAddressLine2(String addressLine) {
		driver.findElement(addressLine2).sendKeys(addressLine);
		return this;
	}

	public EditAddressLinkPage TypeAddressLine3(String addressLine) {
		driver.findElement(addressLine3).sendKeys(addressLine);
		return this;
	}

	public EditAddressLinkPage TypeCity(String city) {
		driver.findElement(addressCity).sendKeys(city);
		return this;
	}

	public EditAddressLinkPage TypeCounty() {
		driver.findElement(addressState).sendKeys("ABS");
		return this;
	}
	
	public EditAddressLinkPage TypeCounty(String nation) {
		switch (nation) {
		case "GBR":
			driver.findElement(addressState).sendKeys("ABS");
			break;
		case "CHN":
			driver.findElement(addressState).sendKeys("12");
			break;
		case "MYS":
			driver.findElement(addressState).sendKeys("MYL");
			break;

		default:
			break;
		}

		
		return this;
	}

	public EditAddressLinkPage TypePostcode(String postcode) {
		driver.findElement(addressPostal).sendKeys(postcode);
		UtilMeth.PauseForJS(driver, 10, true);
		return this;
	}

	public EditAddressLinkPage ClickOkBtn() {
		driver.findElement(okBtn).click();
		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}

	
	public void IgnoreErrorOnInvalidAddress() {
		
		if(isElementPresent(driver, By.id("#ICCancel"))) {
			waitForElement(driver, timeOutInSeconds, By.id("#ICCancel")).click();
		}
	}
	
	public void EditAddressDetails(String nationCode) {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		TypeAddressLine1(jsHandler.getStudentStreet()).TypeAddressLine2(jsHandler.getStudentStreet())
		.TypeAddressLine3(jsHandler.getStudentStreet()).TypeCity(jsHandler.getStudentCity())
		.TypeCounty(jsHandler.getStudentNationCode()).TypePostcode(jsHandler.getStudentPostcode()).ClickOkBtn().IgnoreErrorOnInvalidAddress();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		driver.switchTo().defaultContent();
	}
}
