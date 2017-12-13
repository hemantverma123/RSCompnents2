package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusResidency extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	UtilityMethods UtilMeth = new UtilityMethods();
	String country;
	String course;

	By residencyMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$2");
//	By residencyMalUGLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$2");
//	By residencyMalLLLLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$2");
//	By residencyMalPGTLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$2");
//	By residencyMalPGRLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$2");
	By residencyChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By residencyChnPGRLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$2");
	By residencyChnPGTLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By residencyChnLLLLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By residencyChnUGLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By residencyUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By residencyUKLnkEL1 = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$3");
	//	By residencyUKPGTLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$3");
//	By residencyUKPGRLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$3");
//	By residencyUKLLLLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$3");
//	By residencyUKUGLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$3");
	By nricYesEL = By.id("CIBAA_RES_2_STG_YESNO_DROPDOWN$0");
	By nricNoEL = By.id("CIBAA_RES_2_STG_YESNO_DROPDOWN$75$$0");
	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");

	By firstEntryDateEL = By.id("CIBAA_SUPQ2_STG_DATE1$0");
	By nationalitySelectEL = By.id("CIBAA_SUPQ2_STG_TEXT1$1");
	By permResidenceSelectEL = By.id("CIBAA_SUPQ2_STG_TEXT1$2");
	By residCategorySelectEL = By.id("CIBAA_SUPQ2_STG_TEXT1$3");

	public FormStatusResidency(WebDriver driver,StudentJsonHandler jsHandler, String country, String course) {
		this.driver = driver;
		this.jsHandler = jsHandler;
		this.country = country;
		this.course = course;

//		switch (country) {
//		case "Malaysia":
//			if (course.equals("PGR")) {
//				WebElement residencyMalPGRLnk = this.driver.findElement(residencyMalPGRLnkEL);
//				residencyMalPGRLnk.click();
//			} else if (course.equals("PGT")) {
//				WebElement residencyMalPGTLnk = this.driver.findElement(residencyMalPGTLnkEL);
//				residencyMalPGTLnk.click();
//			} else if (course.equals("UG")) {
//				WebElement residencyMalPGTLnk = this.driver.findElement(residencyMalUGLnkEL);
//				residencyMalPGTLnk.click();
//			}else if (course.equals("LLL")) {
//				WebElement residencyMalPGTLnk = this.driver.findElement(residencyMalLLLLnkEL);
//				residencyMalPGTLnk.click();
//			} else {
//				WebElement residencyLnk = this.driver.findElement(residencyChnLnkEL);
//				residencyLnk.click();
//			}
//
//			break;
//
//		case "China":
//			if (course.equals("PGR")) {
//				WebElement residencyChnPGRLnk = this.driver.findElement(residencyChnPGRLnkEL);
//				residencyChnPGRLnk.click();
//			} else if (course.equals("PGT")) {
//				WebElement residencyChnPGTLnk = this.driver.findElement(residencyChnPGTLnkEL);
//				residencyChnPGTLnk.click();
//			} else if (course.equals("UG")) {
//				WebElement residencyChnPGTLnk = this.driver.findElement(residencyChnUGLnkEL);
//				residencyChnPGTLnk.click();
//			}else if (course.equals("LLL")) {
//				WebElement residencyChnPGTLnk = this.driver.findElement(residencyChnLLLLnkEL);
//				residencyChnPGTLnk.click();
//			}else {
//				WebElement residencyLnk = this.driver.findElement(residencyChnLnkEL);
//				residencyLnk.click();
//			}
//
//			break;
//
//		case "United Kingdom":
//			if (course.equals("PGR")) {
//				WebElement residencyUKPGRLnk = this.driver.findElement(residencyUKPGRLnkEL);
//				residencyUKPGRLnk.click();
//			} else if (course.equals("PGT")) {
//				WebElement residencyUKPGTLnk = this.driver.findElement(residencyUKPGTLnkEL);
//				residencyUKPGTLnk.click();
//			} else if (course.equals("LLL")) {
//				WebElement residencyUKPGTLnk = this.driver.findElement(residencyUKLLLLnkEL);
//				residencyUKPGTLnk.click();
//			}else if (course.equals("UG")) {
//				WebElement residencyUKUGLnk = this.driver.findElement(residencyUKUGLnkEL);
//				residencyUKUGLnk.click(); 
//			}else {
//				WebElement residencyLnk = this.driver.findElement(residencyUKLnkEL);
//				residencyLnk.click();
//			}
//			break;
//
//		default:
//			break;
//		}
//
//		UtilMeth.PauseForJS(driver, 60, true);
	}

	public FormStatusResidency NricRadio(Boolean nric) {
		if (nric == true) {
			WebElement residencyYesRadio = driver.findElement(nricYesEL);
			residencyYesRadio.click();
		} else {
			WebElement residencyNoRadio = driver.findElement(nricNoEL);
			residencyNoRadio.click();
		}
		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}

	public FormStatusResidency saveBtn() {
		WebElement saveBtn = waitForElement(driver, timeOutInSeconds, saveBtnEL);
		saveBtn.click();

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}

	public FormStatusResidency dateOfEntry() {
		String date = "20/08/1997";
		
//		driver.findElement(By.id("CIBAA_SUPQ2_STG_DATE1$0")).click();
//		driver.findElement(By.id("CIBAA_SUPQ2_STG_DATE1$0")).clear();
//		driver.findElement(By.id("CIBAA_SUPQ2_STG_DATE1$0")).sendKeys("13/11/2017");
		
		WebElement dateBx = waitForElement(driver, 10, firstEntryDateEL);

//		JavascriptExecutor jse = (JavascriptExecutor) driver;
//		jse.executeScript("document.getElementById(dateBx).focus();");

		dateBx.click();
		dateBx.clear();
		dateBx.sendKeys(date);
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public FormStatusResidency nationalitySelect(String countryCode) {  //TODO remove countrycode

		Select natSelect = new Select(driver.findElement(nationalitySelectEL));
		//natSelect.selectByValue(countryCode);
		natSelect.selectByVisibleText(jsHandler.getStudentCountry());

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public FormStatusResidency permResidencySelect(String countryCode) { //TODO remove countrycode

		Select permResSelect = new Select(driver.findElement(permResidenceSelectEL));
		//permResSelect.selectByValue(countryCode);
		permResSelect.selectByVisibleText(jsHandler.getStudentCountry());

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public FormStatusResidency resCategorySelect(String countryCode) {  //TODO remove countryCode

		Select resCatSelect = new Select(driver.findElement(residCategorySelectEL));
		//resCatSelect.selectByValue("P");

//		British citizen – British Overseas Territories
//		British citizen – Channel Islands and Isle of Man
//		Child of a Turkish worker
//		EEA or Swiss national
//		EU national (non-UK citizen)
//		Humanitarian Protection or similar
//		Other
//		Refugee
//		Settled in the UK
//		UK citizen – England
//		UK citizen – Northern Ireland
//		UK citizen – Scotland
//		UK citizen – Wales
		
		switch(jsHandler.getStudentResidencyCategory()) {
		case "INTUK - International":
			resCatSelect.selectByVisibleText("Other");
			break;
		case "EUUK - EU Country":
			resCatSelect.selectByVisibleText("EU national (non-UK citizen)");
			break;
		case "HOMUK - GBR":
			resCatSelect.selectByVisibleText("UK citizen – England");
			break;
		case "ILSUK - Channel Islands":
			resCatSelect.selectByVisibleText("British citizen – Channel Islands and Isle of Man");
			break;
		case "UKFSA - Swiss National":
			resCatSelect.selectByVisibleText("EEA or Swiss national");
			break;
			
		case "UKFSQ - Refugee":
			resCatSelect.selectByVisibleText("Refugee");
			break;

		default:
			System.out.println("Invalid Residency Category : " + jsHandler.getStudentResidencyCategory());
		}

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public void SetResidencey(String nationCode) {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		switch (nationCode) {
		case "MYS":
			WebElement residencylink = this.driver.findElement(residencyMalLnkEL);
			residencylink.click();
			UtilMeth.PauseForJS(driver, 20, true);
			NricRadio(true).saveBtn();
			break;

		case "CHN":
			if (course.equals("PGR")) {
				WebElement residencyChnPGRLnk = this.driver.findElement(residencyChnPGRLnkEL);
				residencyChnPGRLnk.click();
			} else if (course.equals("PGT")) {
				WebElement residencyChnPGTLnk = this.driver.findElement(residencyChnPGTLnkEL);
				residencyChnPGTLnk.click();
			} else if (course.equals("UG")) {
				WebElement residencyChnPGTLnk = this.driver.findElement(residencyChnUGLnkEL);
				residencyChnPGTLnk.click();
			}else if (course.equals("LLL")) {
				WebElement residencyChnPGTLnk = this.driver.findElement(residencyChnLLLLnkEL);
				residencyChnPGTLnk.click();
			}else {
				WebElement residencyLnk = this.driver.findElement(residencyChnLnkEL);
				residencyLnk.click();
			}

			break;

		case "GBR":
			if (course.equals("PGR")||course.equals("PGT")||course.equals("LLL")||course.equals("UG")) {
				WebElement residencyUKLnkEL2 = this.driver.findElement(residencyUKLnkEL1);
				residencyUKLnkEL2.click();
			}else {
				WebElement residencyLnk = this.driver.findElement(residencyUKLnkEL);
				residencyLnk.click();
			}
			UtilMeth.PauseForJS(driver, 20, true);			
			dateOfEntry().nationalitySelect(nationCode).permResidencySelect(nationCode).resCategorySelect("P").saveBtn();
			break;
		default:
			break;
		}

		driver.switchTo().defaultContent();
		System.out.println("SetResidency done for "+country);
	}
}
