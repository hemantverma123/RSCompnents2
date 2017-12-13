package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusSupportingDocuments extends TestBase {

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	String country;
	String courselvl;

	By supportingDocMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By supportingDocLLLMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By supportingDocPGRMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");
	By supportingDocPGTMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");
	By supportingDocUGMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");

	By supportingDocChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");
	By supportingDocUGChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");
	By supportingDocLLLChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");
	By supportingDocPGRChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");
	By supportingDocPGTChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");

	By supportingDocUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");
	By supportingDocPGRUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$9");
	By supportingDocPGTUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$8");
	By supportingDocUGUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");

	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");
	By submitAppBtnEL = By.id("CIBAA_LNDNG_WRK_SUBMITTED");

	public FormStatusSupportingDocuments(WebDriver driver, String country, String courselvl) {
		this.driver = driver;
		this.country = country;
		this.courselvl = courselvl;

	}

	public FormStatusSupportingDocuments saveBtn() {

		WebElement saveBtn = driver.findElement(saveBtnEL);
		saveBtn.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusSupportingDocuments submitAppBtn() {

		WebElement submitAppBtn = driver.findElement(submitAppBtnEL);
		submitAppBtn.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusSupportingDocuments selectReasonForNotProviding(int numDocs) {

		for (int i = 0; i < numDocs; i++) {

			WebElement reason = driver.findElement(By.id("CIBAA_UPLD_LVL1_REASON_CD$" + i));

			Select select = new Select(reason);
			select.selectByIndex(1);
		}

		return this;
	}

	public FormStatusSupportingDocuments selectDocumentType(int list) {

		WebElement reason = driver.findElement(By.id("CIBAA_UPLD_LVL1_CIBAA_UPLD_DOC_TYP$0"));

		Select select = new Select(reason);
		select.selectByIndex(list);

		return this;
	}
	
	public void setSupportDoc() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		switch (this.country) {
		case "Malaysia":
			if (this.courselvl.equals("PGR")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocPGRMalLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("PGT")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocPGTMalLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("UG")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocUGMalLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("LLL")) {
				WebElement supportingDocLnk = waitForElement(driver, timeOutInSeconds, supportingDocLLLMalLnkEL);
				supportingDocLnk.click(); 
			}else {
				System.out.println("Problems with Malaysian Links");
			}
			UtilMeth.PauseForJS(driver, 60, true);
			saveBtn().submitAppBtn();
			break;

		case "China":
			if (this.courselvl.equals("PGR")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocPGRChnLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("PGT")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocPGTChnLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("UG")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocUGChnLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("LLL")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocLLLChnLnkEL);
				supportingDocLnk.click();
			} else {
				WebElement supportingDocLnk = driver.findElement(supportingDocChnLnkEL);
				supportingDocLnk.click();
			}
			break;
		case "United Kingdom":  //not applicable for UK
			if (this.courselvl.equals("PGR")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocPGRUKLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("PGT")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocPGTUKLnkEL);
				supportingDocLnk.click();
			} else if (this.courselvl.equals("UG")) {
				WebElement supportingDocLnk = driver.findElement(supportingDocUGUKLnkEL);
				supportingDocLnk.click();
			} else {
				WebElement supportingDocLnk = driver.findElement(supportingDocUKLnkEL);
				supportingDocLnk.click();
			}
			break;

		default:
			break;
		}
		
		driver.switchTo().defaultContent();
		System.out.println("setSupportDoc done for " + country);
	}
}
