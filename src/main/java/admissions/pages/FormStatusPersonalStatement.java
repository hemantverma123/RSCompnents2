package admissions.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusPersonalStatement extends TestBase {

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	String country;
	String courselvl;

	String lipsum = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?";

	By personalStatmentPGRMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");
	By personalStatmentPGTMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By personalStatmentUGMalLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");

	By personalStatmentPGRChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$9");
	By personalStatmentPGTChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");
	By personalStatmentUGChnLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");

	By personalStatmentPGRUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$11");
	By personalStatmentPGTUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$10");
	By personalStatmentUGUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$9");

	By personalStatmentLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$7");

	By rationaleApplyEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$0");
	By elementsCourseEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$1");
	By relevantExperienceEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$2");
	By demonstrateEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$3");
	By motivationEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$4");
	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");
	By submitAppBtnEL = By.id("CIBAA_LNDNG_WRK_SUBMITTED");

	By refusedVisa = By.id("CIBAA_SUPQ2_STG_YESNO_DROPDOWN$0");

	By textArea1Man = By.id("CIBAA_SUPQ2_STG_COMMENTS$0");

	By blockText1 = By.id("win0divCIBAA_DERIVED_HTMLAREA$0");
	By blockText2 = By.id("win0divCIBAA_DERIVED_HTMLAREA$1");
	By blockText3 = By.id("win0divCIBAA_DERIVED_HTMLAREA$2");
	By blockText4 = By.id("win0divCIBAA_DERIVED_HTMLAREA$3");
	By blockText5 = By.id("win0divCIBAA_DERIVED_HTMLAREA$4");
	By blockText6 = By.id("win0divCIBAA_DERIVED_HTMLAREA$5");
	By blockText7 = By.id("win0divCIBAA_DERIVED_HTMLAREA$6");

	public FormStatusPersonalStatement(WebDriver driver, String country, String courselvl) {
		this.driver = driver;
		this.country = country;
		this.courselvl = courselvl;

	}

	public FormStatusPersonalStatement rationalTxtBx() {

		WebElement ratioaleApplyTxtBx = driver.findElement(rationaleApplyEL);
		ratioaleApplyTxtBx.sendKeys("Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... ");
		UtilMeth.PauseForJS(this.driver, 20, true);
		return this;
	}

	public FormStatusPersonalStatement elementsTxtBx() {

		WebElement elementsCourseTxtBx = driver.findElement(elementsCourseEL);
		elementsCourseTxtBx.sendKeys("Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... ");
		UtilMeth.PauseForJS(this.driver, 20, true);
		return this;
	}

	public FormStatusPersonalStatement relevantTxtBx() {

		WebElement relevantExperienceTxtBx = driver.findElement(relevantExperienceEL);
		relevantExperienceTxtBx.sendKeys("Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... ");
		UtilMeth.PauseForJS(this.driver, 20, true);
		return this;
	}

	public FormStatusPersonalStatement demonstrateTxtBx() {

		WebElement demonstrateTxtBx = driver.findElement(demonstrateEL);
		demonstrateTxtBx.sendKeys("Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... ");
		UtilMeth.PauseForJS(this.driver, 20, true);
		return this;
	}

	public FormStatusPersonalStatement motivationTxtBx() {

		WebElement motivationTxtBx = driver.findElement(motivationEL);
		motivationTxtBx.sendKeys("Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... Blah Blah .... ");
		UtilMeth.PauseForJS(this.driver, 20, true);
		return this;
	}

	public FormStatusPersonalStatement saveBtn() {

		WebElement saveBtn = driver.findElement(saveBtnEL);
		saveBtn.click();
		UtilMeth.PauseForJS(this.driver, 40, true);
		return this;
	}

	public FormStatusPersonalStatement submitAppBtn() {

		WebElement submitAppBtn = driver.findElement(submitAppBtnEL);
		submitAppBtn.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusPersonalStatement bulkFillMalPGT() {

		List<WebElement> allSelectEle_text = driver.findElements(By.cssSelector("[id^='CIBAA_SUPQ2_STG_TEXT1$']"));
		System.out.println("(8) Number of elements Select-Text=>" + allSelectEle_text.size());
		Select select;
		
		for (WebElement element : allSelectEle_text) {	
			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOf(element));
			select = new Select(element);
			select.selectByIndex(1);
			UtilMeth.PauseForJS(driver, 20, true);
		}

		List<WebElement> allTextAreaEle_comment = driver
				.findElements(By.cssSelector("[id^='win0divCIBAA_SUPQ2_STG_COMMENTS$']"));
		System.out.println("(10) Number of elements TextArea-comment =>" + allTextAreaEle_comment.size());

		for (WebElement element : allTextAreaEle_comment) {
			element.click();
			UtilMeth.PauseForJS(driver, 1, true);
			element.sendKeys("Comment");
			UtilMeth.PauseForJS(driver, 1, true);
		}

		List<WebElement> allSelectEle_yesNo = driver
				.findElements(By.cssSelector("[id^='CIBAA_SUPQ2_STG_YESNO_DROPDOWN$']"));
		System.out.println("(4) Number of elements Select-yesNo =>" + allSelectEle_yesNo.size());

		for (WebElement element : allSelectEle_yesNo) {
			select = new Select(element);
			select.selectByIndex(1);
			UtilMeth.PauseForJS(driver, 20, true);
		}

		List<WebElement> allTextAreaEle_date = driver
				.findElements(By.cssSelector("[id^='win0divCIBAA_SUPQ2_STG_DATE1$']"));
		System.out.println("(1) Number of elements Text-Date =>" + allTextAreaEle_date.size());

		for (WebElement element : allTextAreaEle_date) {
			element.click();
			UtilMeth.PauseForJS(driver, 1, true);
			element.sendKeys("01/01/2017");
			UtilMeth.PauseForJS(driver, 1, true);
		}
		return this;
	}

	public FormStatusPersonalStatement box1() {

		WebElement box = driver.findElement(textArea1Man);
		box.click();
		UtilMeth.PauseForJS(driver, 1, true);
		box.sendKeys("Comment");
		UtilMeth.PauseForJS(driver, 1, true);

		return this;
	}

	public FormStatusPersonalStatement uKPgrApp() {
		UtilMeth.PauseForJS(driver, 2.1f, false);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText1));

		WebElement iframeDiv = driver.findElement(By.id("cke_343_contents"));
		WebElement iframe = iframeDiv.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe);

		WebElement resTopic = driver.findElement(By.tagName("p"));
		resTopic.click();
		resTopic.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText2));
		WebElement iframeDiv2 = driver.findElement(By.id("cke_344_contents"));
		WebElement iframe2 = iframeDiv2.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe2);

		WebElement resTopic2 = driver.findElement(By.tagName("p"));
		resTopic2.click();
		resTopic2.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText3));

		WebElement iframeDiv3 = driver.findElement(By.id("cke_345_contents"));
		WebElement iframe3 = iframeDiv3.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe3);

		WebElement resTopic3 = driver.findElement(By.tagName("p"));
		resTopic3.click();
		resTopic3.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText4));

		WebElement iframeDiv4 = driver.findElement(By.id("cke_346_contents"));
		WebElement iframe4 = iframeDiv4.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe4);

		WebElement resTopic4 = driver.findElement(By.tagName("p"));
		resTopic4.click();
		resTopic4.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText5));

		WebElement iframeDiv5 = driver.findElement(By.id("cke_347_contents"));
		WebElement iframe5 = iframeDiv5.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe5);

		WebElement resTopic5 = driver.findElement(By.tagName("p"));
		resTopic5.click();
		resTopic5.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText6));

		WebElement iframeDiv6 = driver.findElement(By.id("cke_348_contents"));
		WebElement iframe6 = iframeDiv6.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe6);

		WebElement resTopic6 = driver.findElement(By.tagName("p"));
		resTopic6.click();
		resTopic6.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText7));

		WebElement iframeDiv7 = driver.findElement(By.id("cke_349_contents"));
		WebElement iframe7 = iframeDiv7.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe7);

		WebElement resTopic7 = driver.findElement(By.tagName("p"));
		resTopic7.click();
		resTopic7.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

		UtilMeth.PauseForJS(driver, 2.1f, false);

		return this;
	}

	public FormStatusPersonalStatement uKPgtApp() {
		UtilMeth.PauseForJS(driver, 2.1f, false);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText1));

		WebElement iframeDiv = driver.findElement(By.id("cke_1_contents"));
		WebElement iframe = iframeDiv.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe);
		UtilMeth.PauseForJS(driver, 2.1f, false);

		WebElement resTopic = driver.findElement(By.tagName("p"));
		resTopic.click();
		resTopic.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText2));

		WebElement iframeDiv2 = driver.findElement(By.id("cke_2_contents"));
		WebElement iframe2 = iframeDiv2.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe2);
		UtilMeth.PauseForJS(driver, 2.1f, false);

		WebElement resTopic2 = driver.findElement(By.tagName("p"));
		resTopic2.click();
		resTopic2.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText3));

		WebElement iframeDiv3 = driver.findElement(By.id("cke_3_contents"));
		WebElement iframe3 = iframeDiv3.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe3);
		UtilMeth.PauseForJS(driver, 2.1f, false);

		WebElement resTopic3 = driver.findElement(By.tagName("p"));
		resTopic3.click();
		resTopic3.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText4));

		WebElement iframeDiv4 = driver.findElement(By.id("cke_4_contents"));
		WebElement iframe4 = iframeDiv4.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe4);
		UtilMeth.PauseForJS(driver, 2.1f, false);

		WebElement resTopic4 = driver.findElement(By.tagName("p"));
		resTopic4.click();
		resTopic4.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText5));

		WebElement iframeDiv5 = driver.findElement(By.id("cke_5_contents"));
		WebElement iframe5 = iframeDiv5.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe5);
		UtilMeth.PauseForJS(driver, 2.1f, false);

		WebElement resTopic5 = driver.findElement(By.tagName("p"));
		resTopic5.click();
		resTopic5.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText6));

		WebElement iframeDiv6 = driver.findElement(By.id("cke_6_contents"));
		WebElement iframe6 = iframeDiv6.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe6);
		UtilMeth.PauseForJS(driver, 2.1f, false);

		WebElement resTopic6 = driver.findElement(By.tagName("p"));
		resTopic6.click();
		resTopic6.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		////////////////////////////////////////////////////////////////////////////////////////
		UtilMeth.PauseForJS(driver, 2.1f, false);
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(blockText7));

		WebElement iframeDiv7 = driver.findElement(By.id("cke_7_contents"));
		WebElement iframe7 = iframeDiv7.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe7);
		UtilMeth.PauseForJS(driver, 2.1f, false);

		WebElement resTopic7 = driver.findElement(By.tagName("p"));
		resTopic7.click();
		resTopic7.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

		return this;
	}

	public FormStatusPersonalStatement clickSubmitBtn() {

		WebElement submit = driver.findElement(submitAppBtnEL);
		submit.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public FormStatusPersonalStatement bulkFillCommentBx() {
		List<WebElement> allCommentBx = driver.findElements(By.cssSelector("[id^='CIBAA_SUPQ2_STG_COMMENTS$']"));

		System.out.println("Number of Comment box's => " + allCommentBx.size());

		for (WebElement webElement : allCommentBx) {
			webElement.sendKeys(lipsum);
		}
		return this;
	}
	
	//Mal PGT
	
	By careerObjectivesEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$0");
	By majorStrengthsEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$1");
	By whyNowEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$2");
	By managingTeamsEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$3");
	By budgetsEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$4");
	By pastPresentEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$5");
	By workAfterGradEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$6");
	By aspectAppealEL = By.id("CIBAA_SUPQ2_STG_COMMENTS$8");
	By careerProgEL = By.id("CIBAA_SUPQ2_STG_TEXT1$9");
	By salaryAdvEL = By.id("CIBAA_SUPQ2_STG_TEXT1$10");
	By greaterBizEL = By.id("CIBAA_SUPQ2_STG_TEXT1$11");
	By buildConEL = By.id("CIBAA_SUPQ2_STG_TEXT1$12");
	By careerChangeEL = By.id("CIBAA_SUPQ2_STG_TEXT1$13");
	By generalBizEL = By.id("CIBAA_SUPQ2_STG_TEXT1$14");
	
	By selfFulfillEL = By.id("CIBAA_SUPQ2_STG_TEXT1$15");
	By whyDecideEL = By.id("CIBAA_SUPQ2_STG_TEXT1$16");
	By visitedNottsEL = By.id("CIBAA_SUPQ2_STG_YESNO_DROPDOWN$17");
	By metRepEL = By.id("CIBAA_SUPQ2_STG_YESNO_DROPDOWN$18");
	
	

	public FormStatusPersonalStatement refusedVisaSelect() {
		Select select = new Select(driver.findElement(refusedVisa));
		select.selectByValue("N");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement careerObjectives() {
		WebElement careerObjectivesTxt = driver.findElement(careerObjectivesEL);
		careerObjectivesTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement majorStrengths() {
		WebElement majorStrengthsTxt = driver.findElement(majorStrengthsEL);
		majorStrengthsTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement whyNow() {
		WebElement whyNowTxt = driver.findElement(whyNowEL);
		whyNowTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement managingTeams() {
		WebElement managingTeamsTxt = driver.findElement(managingTeamsEL);
		managingTeamsTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement budgets() {
		WebElement budgetsTxt = driver.findElement(budgetsEL);
		budgetsTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement pastPresent() {
		WebElement pastPresentTxt = driver.findElement(pastPresentEL);
		pastPresentTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement workAfterGrad() {
		WebElement workAfterGradTxt = driver.findElement(workAfterGradEL);
		workAfterGradTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement aspectAppeal() {
		WebElement aspectAppealTxt = driver.findElement(aspectAppealEL);
		aspectAppealTxt.sendKeys("Text.............");

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement careerProg() {
		Select select = new Select(driver.findElement(careerProgEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement salaryAdv() {
		Select select = new Select(driver.findElement(salaryAdvEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement greaterBiz() {
		Select select = new Select(driver.findElement(greaterBizEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement buildCon() {
		Select select = new Select(driver.findElement(buildConEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement careerChange() {
		Select select = new Select(driver.findElement(careerChangeEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement generalBiz() {
		Select select = new Select(driver.findElement(generalBizEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement selfFulfill() {
		Select select = new Select(driver.findElement(selfFulfillEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement whyDecide() {
		Select select = new Select(driver.findElement(whyDecideEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement visitedNotts() {
		Select select = new Select(driver.findElement(visitedNottsEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalStatement metRep() {
		Select select = new Select(driver.findElement(metRepEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public void setPersonalStatement() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		switch (this.country) {
		case "Malaysia":
			if (this.courselvl.equals("LLL")) {
				//bulkFillCommentBx().saveBtn();
			}else if (this.courselvl.equals("PGR")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGRMalLnkEL);
				perStatementLnk.click();
				// Personal Statement
//				FormStatusPersonalStatement perStatement = new FormStatusPersonalStatement(driver, country, courseLvl);
//				perStatement.careerObjectives().majorStrengths().whyNow().managingTeams().budgets().pastPresent()
//						.workAfterGrad().aspectAppeal().careerProg().salaryAdv().greaterBiz().buildCon().careerChange()
//						.generalBiz().selfFulfill().whyDecide().visitedNotts().metRep().saveBtn();

			} else if (this.courselvl.equals("PGT")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGTMalLnkEL);
				perStatementLnk.click();
				careerObjectives().majorStrengths().whyNow().managingTeams().budgets()
				.pastPresent().workAfterGrad().aspectAppeal().careerProg().salaryAdv().greaterBiz().buildCon()
				.careerChange().generalBiz().selfFulfill().whyDecide().visitedNotts().metRep().saveBtn();
			} else if (this.courselvl.equals("UG")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentUGMalLnkEL);
				perStatementLnk.click();
				bulkFillCommentBx().saveBtn();
			} else {
				System.out.println("Malaysia - FormStatusPersonalStatement, Switch Statement Failed");
			}

			break;

		case "China":
			if (this.courselvl.equals("LLL")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGRMalLnkEL);
				perStatementLnk.click();
			}if (this.courselvl.equals("PGR")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGRChnLnkEL);
				perStatementLnk.click();
			} else if (this.courselvl.equals("PGT")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGTChnLnkEL);
				perStatementLnk.click();
			} else if (this.courselvl.equals("UG")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentUGChnLnkEL);
				perStatementLnk.click();
			} else {
				System.out.println("China - FormStatusPersonalStatement, Switch Statement Failed");
			}
			break;
		case "United Kingdom": //not being used for UK so can be deleted
			if (this.courselvl.equals("LLL")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGRMalLnkEL);
				perStatementLnk.click();
			}if (this.courselvl.equals("PGR")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGRUKLnkEL);
				perStatementLnk.click();

			} else if (this.courselvl.equals("PGT")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentPGTUKLnkEL);
				perStatementLnk.click();
			} else if (this.courselvl.equals("UG")) {
				WebElement perStatementLnk = driver.findElement(personalStatmentUGUKLnkEL);
				perStatementLnk.click();
			}else {
				System.out.println("United Kingdom - FormStatusPersonalStatement, Switch Statement Failed");
			}
			break;
		default:
			System.out.println("FormStatusPersonalStatement, Switch Statement Failed");
			break;
		}
		UtilMeth.PauseForJS(this.driver, 60, true);
		driver.switchTo().defaultContent();

	}

}
