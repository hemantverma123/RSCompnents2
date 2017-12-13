package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusTestInformation extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	UtilityMethods UtilMeth = new UtilityMethods();
	String country;
	String courselvl;

	By testInformationLLLUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By testInformationLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By pageTitleEL = By.id("CIBAA_LNDNG_WRK_PAGELABEL");

	By typeTestEL = By.id("CIBAA_STDTS_STG_TEST_ID$0");
	By dateTestEL = By.id("CIBAA_STDTS_STG_EXPECT_DT$0");

	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");

	By testCompHeadingEL = By.id("CIBAA_TSTCM_STG2$srt0$0");
	By testCompSelectEL = By.id("CIBAA_TSTCM_STG_TEST_COMPONENT$0");
	By scoreBxEL = By.id("CIBAA_TSTCM_STG_SCORE$0");
	By gradeBxEL = By.id("CIBAA_TSTCM_STG_SCORE_LETTER$0");

	public FormStatusTestInformation(WebDriver driver,StudentJsonHandler jsHandler, String country, String courselvl) {
		this.driver = driver;
		this.jsHandler = jsHandler;
		this.country = country;
		this.courselvl = courselvl;

//		switch (this.country) {
//		case "United Kingdom":
//			if(this.courselvl.equals("LLL")) {
//				WebElement testInfoLnk = driver.findElement(testInformationLnkEL);
//				testInfoLnk.click();
//			}
//			break;
//		case "China":
//
//			break;
//		case "Malaysia":
//
//			break;
//
//		default:
//			WebElement testInfoLnk = driver.findElement(testInformationLnkEL);
//			testInfoLnk.click();
//			break;
//		}
//
//
//		WebDriverWait wait = new WebDriverWait(driver, 60);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitleEL));

	}

	public FormStatusTestInformation typeOfTest() {

		Select select = new Select(driver.findElement(typeTestEL));
		select.selectByVisibleText("Unified Exam Certificate");
		//select.selectByIndex(3);

		UtilMeth.PauseForJS(driver, 20, true);

		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(testCompHeadingEL));

		return this;
	}

	public FormStatusTestInformation dateOfTest() {

		UtilMeth.PauseForJS(driver, 20, true);

		WebElement dateType = driver.findElement(dateTestEL);
		dateType.sendKeys("01/01/2015");

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public FormStatusTestInformation clickSaveBtn() {

		UtilMeth.PauseForJS(driver, 20, true);

		WebElement dateType = driver.findElement(saveBtnEL);
		dateType.click();

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public FormStatusTestInformation typeScore() {

		// WebElement drop = driver.findElement(testCompSelectEL);
		// drop.click();
		// drop.sendKeys(Keys.TAB);
		//
		// WebElement score = driver.findElement(scoreBxEL);
		// score.click();
		// score.sendKeys("100.00", Keys.TAB);

		new WebDriverWait(driver, 30).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(scoreBxEL));
		
		driver.findElement(scoreBxEL).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//driver.findElement(scoreBxEL).sendKeys("9.00");
		driver.findElement(scoreBxEL).sendKeys("0.00");

		return this;
	}

	public FormStatusTestInformation typeGrade() {

		WebElement grade = driver.findElement(gradeBxEL);
		grade.click();
		grade.sendKeys("A");

		return this;
	}
 
	public void SetTestInfo() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		switch (this.country) {
		case "United Kingdom":
			if(this.courselvl.equals("LLL")) {
				WebElement testInfoLnk = driver.findElement(testInformationLnkEL);
				testInfoLnk.click();
			}
			break;
		case "China":

			break;
		case "Malaysia":

			break;

		default:
			WebElement testInfoLnk = driver.findElement(testInformationLnkEL);
			testInfoLnk.click();
			break;
		}


		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitleEL));
		
		typeOfTest().typeScore().typeGrade().dateOfTest().clickSaveBtn();
		System.out.println("SetTestInfo done...");
		driver.switchTo().defaultContent();
	}
}
