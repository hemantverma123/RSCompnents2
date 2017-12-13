package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusReferences extends TestBase{
	
	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	String country;
	
	By referencesLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$9");
	By declarationChkBxEL = By.id("CIBAA_RECOMN_CIBAA_RECOM_INFO$0");
	
	By refFirstNameEL = By.id("CIBAA_RECOM_STG_FIRST_NAME$0");
	By refLastNameEL = By.id("CIBAA_RECOM_STG_LAST_NAME$0");
	By refRelationshipEL = By.id("CIBAA_RECOM_STG_CIBAA_REF_RELTN_CD$0");
	By refEmailEL = By.id("CIBAA_RECOM_STG_EMAILID$0");
	
	By saveBtn = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");
	By submitAppBtnEL = By.id("CIBAA_LNDNG_WRK_SUBMITTED");

	public FormStatusReferences(WebDriver driver) {
		this.driver = driver;
	}
	
	public FormStatusReferences clickDeclaration() {
		
		WebElement declaration = driver.findElement(declarationChkBxEL);
		declaration.click();
		
		UtilMeth.PauseForJS(driver, 20, true);
		
		return this;
	}
	
	public FormStatusReferences refDummyData() {
		
		WebElement firstName = driver.findElement(refFirstNameEL);
		firstName.sendKeys("FirstName");
		
		UtilMeth.PauseForJS(driver, 30, true);
		
		WebElement lastName = driver.findElement(refLastNameEL);
		lastName.click();
		UtilMeth.PauseForJS(driver, 30, true);
		lastName = driver.findElement(refLastNameEL);
		lastName.sendKeys("LastName");
		
		UtilMeth.PauseForJS(driver, 30, true);
		
		Select select = new Select(driver.findElement(refRelationshipEL));
		select.selectByIndex(1);
		
		UtilMeth.PauseForJS(driver, 30, true);
		
		WebElement email = driver.findElement(refEmailEL);
		email.sendKeys("email@email.com");
		
		UtilMeth.PauseForJS(driver, 30, true);	
		
		return this;
	}
	
	public FormStatusReferences clickSaveBtn() {
		
		WebElement save = driver.findElement(saveBtn);
		save.click();
		
		UtilMeth.PauseForJS(driver, 20, true);
		
		return this;
	}
	
	public FormStatusReferences submitAppBtn() {

		WebElement submitAppBtn = driver.findElement(submitAppBtnEL);
		submitAppBtn.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}
	
	public void SetReference() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		WebElement referencesLnk = waitForElement(driver, timeOutInSeconds, referencesLnkEL);
		referencesLnk.click();
		
		UtilMeth.PauseForJS(driver, 20, true);

		clickDeclaration().refDummyData().clickSaveBtn().submitAppBtn();
		System.out.println("SetReference done...");
		driver.switchTo().defaultContent();
	}

}
