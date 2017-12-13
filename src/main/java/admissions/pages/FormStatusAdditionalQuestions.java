package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusAdditionalQuestions extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	
	UtilityMethods UtilMeth = new UtilityMethods();
	String country;
	String courselvl;

	By addQuestionsPGRMMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");
	By addQuestionsPGTMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");
	By addQuestionsUGMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");

	By addQuestionsPGRChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By addQuestionsPGTChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By addQuestionsLLLChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");

	By addQuestionsPGRUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$8");
	By addQuestionsPGTUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");
	By addQuestionsLLLUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By addQuestionsUGUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");

	By addQuestionsLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By payForStudiesSelectEL = By.id("CIBAA_SUPQ2_STG_TEXT1$0");
	By hearAboutUniSelectEL = By.id("CIBAA_SUPQ2_STG_TEXT1$1");
	By agentHelpingSelectEL = By.id("CIBAA_SUPQ2_STG_YESNO_DROPDOWN$2");
	By agentHelpingSelectUKEL = By.id("CIBAA_SUPQ2_STG_YESNO_DROPDOWN$0");
	By intApplicantEL = By.id("CIBAA_SUPQ2_STG_YESNO_DROPDOWN$1");
	By overallScoreEL = By.id("CIBAA_SUPQ2_STG_TEXT1$0");
	By speakingScoreEL = By.id("CIBAA_SUPQ2_STG_TEXT1$1");
	By listeningScoreEL = By.id("CIBAA_SUPQ2_STG_TEXT1$2");
	By writingScoreEL = By.id("CIBAA_SUPQ2_STG_TEXT1$3");
	By readingScoreEL = By.id("CIBAA_SUPQ2_STG_TEXT1$4");

	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");

	By submitAppBtnEL = By.id("CIBAA_LNDNG_WRK_SUBMITTED");
	
	

	public FormStatusAdditionalQuestions(WebDriver driver,StudentJsonHandler jsHandler, String country, String courselvl) {
		this.driver = driver;
		this.jsHandler = jsHandler;
		this.country = country;
		this.courselvl = courselvl;

//		switch (this.country) {
//		case "Malaysia":
//			if (courselvl.equals("PGR")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGRMMalLnkEL);
//				additionalQuestionsLnk.click();
//			} else if (courselvl.equals("PGT")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGTMalLnkEL);
//				additionalQuestionsLnk.click();
//			} else if (courselvl.equals("UG")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsUGMalLnkEL);
//				additionalQuestionsLnk.click();
//			} else {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLnkEL);
//				additionalQuestionsLnk.click();
//			}
//			break;
//		case "China":
//			if (courselvl.equals("PGR")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGRChnLnkEL);
//				additionalQuestionsLnk.click();
//			} else if (courselvl.equals("PGT")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGTChnLnkEL);
//				additionalQuestionsLnk.click();
//			} else if (courselvl.equals("LLL")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLLLChnLnkEL);
//				additionalQuestionsLnk.click();
//			} else {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLnkEL);
//				additionalQuestionsLnk.click();
//			}
//			break;
//		case "United Kingdom":
//			if (courselvl.equals("PGR")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGRUKLnkEL);
//				additionalQuestionsLnk.click();
//			} else if (courselvl.equals("PGT")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGTUKLnkEL);
//				additionalQuestionsLnk.click();
//			} else if (courselvl.equals("LLL")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLLLUKLnkEL);
//				additionalQuestionsLnk.click();
//			} else if (courselvl.equals("UG")) {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsUGUKLnkEL);
//				additionalQuestionsLnk.click();
//			}else {
//				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLnkEL);
//				additionalQuestionsLnk.click();
//			}
//			break;
//
//		default:
//			break;
//		}
//
//		UtilMeth.PauseForJS(driver, 60, true);
	}

	public FormStatusAdditionalQuestions payForStudiesSelect() {
		Select select = new Select(driver.findElement(payForStudiesSelectEL));
		select.selectByValue("1");

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusAdditionalQuestions hearAboutUniSelect() {

		Select select = new Select(driver.findElement(hearAboutUniSelectEL));
		select.selectByValue("ADVE");

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusAdditionalQuestions agentHelpingSelect() {

		if (this.country.equals("United Kingdom")) {
			Select select = new Select(driver.findElement(agentHelpingSelectUKEL));
			select.selectByValue("N");
		} else {
			Select select = new Select(driver.findElement(agentHelpingSelectEL));
			select.selectByValue("N");
		}

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusAdditionalQuestions intApplicant() {

		Select select = new Select(driver.findElement(intApplicantEL));
		select.selectByValue("N");

		return this;
	}

	public FormStatusAdditionalQuestions overallScore() {

		Select select = new Select(driver.findElement(overallScoreEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusAdditionalQuestions saveBtn() {

		WebElement saveBtn = driver.findElement(saveBtnEL);
		saveBtn.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusAdditionalQuestions clickSubmitBtn() {

		WebElement submit = driver.findElement(submitAppBtnEL);
		submit.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusAdditionalQuestions speakingScore() {

		Select select = new Select(driver.findElement(speakingScoreEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);

		return this;

	}

	public FormStatusAdditionalQuestions listeningScore() {

		Select select = new Select(driver.findElement(listeningScoreEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);

		return this;

	}

	public FormStatusAdditionalQuestions writingScore() {

		Select select = new Select(driver.findElement(writingScoreEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);

		return this;

	}

	public FormStatusAdditionalQuestions readingScore() {

		Select select = new Select(driver.findElement(readingScoreEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);

		return this;

	}
	
	public FormStatusAdditionalQuestions setAgency() {
		WebElement agency = waitForElement(driver, timeOutInSeconds, By.id("CIBAA_SUPQ3_STG_COMMENTS$0"));
		agency.click();
		agency.clear();
		agency.sendKeys("Agency");
		return this;
	}

	public void SetQuestion() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		switch (this.country) {
		case "Malaysia":
			if (courselvl.equals("PGR")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGRMMalLnkEL);
				additionalQuestionsLnk.click();
			} else if (courselvl.equals("PGT")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGTMalLnkEL);
				additionalQuestionsLnk.click();
			} else if (courselvl.equals("UG")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsUGMalLnkEL);
				additionalQuestionsLnk.click();
			} else {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLnkEL);
				additionalQuestionsLnk.click();
			}
			
			UtilMeth.PauseForJS(driver, 20, true);
			payForStudiesSelect().hearAboutUniSelect().agentHelpingSelect().saveBtn();
			break;
		case "China":
			if (courselvl.equals("PGR")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGRChnLnkEL);
				additionalQuestionsLnk.click();
			} else if (courselvl.equals("PGT")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGTChnLnkEL);
				additionalQuestionsLnk.click();
			} else if (courselvl.equals("LLL")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLLLChnLnkEL);
				additionalQuestionsLnk.click();
			} else {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLnkEL);
				additionalQuestionsLnk.click();
			}
			break;
		case "United Kingdom":
			if (courselvl.equals("PGR")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGRUKLnkEL);
				additionalQuestionsLnk.click();
				UtilMeth.PauseForJS(driver, 20, true);
				agentHelpingSelect().saveBtn().clickSubmitBtn(); //.setAgency().intApplicant()
				
			} else if (courselvl.equals("PGT")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsPGTUKLnkEL);
				additionalQuestionsLnk.click();
				agentHelpingSelect().intApplicant().saveBtn();

			} else if (courselvl.equals("LLL")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLLLUKLnkEL);
				additionalQuestionsLnk.click();
				ProcessingImgInvisible(driver, 10, By.id("processing"));
				overallScore().saveBtn().clickSubmitBtn();
			} else if (courselvl.equals("UG")) {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsUGUKLnkEL);
				additionalQuestionsLnk.click();
				agentHelpingSelect().saveBtn().clickSubmitBtn();
			}else {
				WebElement additionalQuestionsLnk = this.driver.findElement(addQuestionsLnkEL);
				additionalQuestionsLnk.click();
			}
			break;

		default:
			break;
		}
		UtilMeth.PauseForJS(driver, 20, true);
		driver.switchTo().defaultContent();
		System.out.println("SetQuestion done...");
	}

}
