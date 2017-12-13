package admissions.pages;

import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class NewApplicationMyNottingham extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	UtilityMethods UtilMeth = new UtilityMethods();

	By nextBtnEL = By.name("CIBAA_RGSTR_WRK_NEXT$IMG$0");

	By chinaCampusRadioEL = By.id("CIBAA_SEL_VW$sels$2$$0");
	By malaysiaCampusRadioEL = By.id("CIBAA_SEL_VW$sels$1$$0");
	//By ukCampusRadioEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	
	
	By internationalYesRadioEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	By internationalNoRadioEL = By.id("CIBAA_SEL_VW$sels$1$$0");

	By couseLvlFoundationMalEL = By.id("ftrCIBAA_SEL_VW$0_row1");
	By couseLvlUGMalEL = By.id("ftrCIBAA_SEL_VW$0_row4");
	By couseLvlPGRMalEL = By.id("ftrCIBAA_SEL_VW$0_row2");
	By couseLvlPGTMalEL = By.id("ftrCIBAA_SEL_VW$0_row3");

	By couseLvlEngPrepChinaEL = By.id("CIBAA_SEL_VW$sels$2$$0");
	By couseLvlUGChinaEL = By.id("CIBAA_SEL_VW$sels$3$$0");
	By couseLvlPGRChinaEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	By couseLvlPGTChinaEL = By.id("CIBAA_SEL_VW$sels$1$$0");

	By couseLvlEngPrepUKEL = By.id("CIBAA_SEL_VW$sels$2$$0");
	By couseLvlUGUKEL = By.id("CIBAA_SEL_VW$sels$3$$0");
	By couseLvlPGRUKEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	By couseLvlPGTUKEL = By.id("CIBAA_SEL_VW$sels$1$$0");

	By termRowOneEL = By.id("CIBAA_SEL_VW$sels$0$$0");

	By subjectTableEL = By.id("CIBAA_SEL_VW$scroll$0");

	By subjectRowOneEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	By subjectRowTwoEL = By.id("CIBAA_SEL_VW$sels$1$$0");
	By subjectRowThreeEL = By.id("CIBAA_SEL_VW$sels$2$$0");

	By courseRowOneEL = By.id("CIBAA_SEL_VW$sels$0$$0");

	By fullTimeEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	
	By yearOfEntryOneEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	By yearOfEntryTwoEL = By.id("CIBAA_SEL_VW$sels$1$$0");

	By targetDegreeEL = By.id("CIBAA_SEL_VW$sels$0$$0");

	By confirmBtnEL = By.id("CIBAA_RGSTR_WRK_CONTINUE_PB");

	By alternativeYesRadioEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	By alternativeNoRadioEL = By.id("CIBAA_SEL_VW$sels$1$$0");

	By ukStudyYesRadioEL = By.id("CIBAA_SEL_VW$sels$0$$0");
	By ukStudyNoRadioEL = By.id("CIBAA_SEL_VW$sels$1$$0");

	By startDateRowOneEL = By.id("CIBAA_SEL_VW$sels$0$$0");

	By iframeEL = By.id("ptifrmtgtframe");

	By searchButtonEL = By.name("CIBAA_OAA_WRK_SEARCH_PB$IMG");

	By termSelectDivEL = By.id("win0divCIBAA_SEL_VW$0");

	String country;

	public NewApplicationMyNottingham(WebDriver driver,StudentJsonHandler jsHandler, String country) {
		this.driver = driver;
		this.jsHandler = jsHandler;
		this.country = country;

//		WebElement iframe = this.driver.findElement(iframeEL);
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame(iframe);
	}

	public NewApplicationMyNottingham SelectCampus() {

		switch (this.country) {
		case "Malaysia":
			WebElement radioBtnRow2 = driver.findElement(malaysiaCampusRadioEL);
			radioBtnRow2.click();
			UtilMeth.PauseForJS(driver, 20, true);
			break;

		case "China":
			WebElement radioBtnRow1 = driver.findElement(chinaCampusRadioEL);
			radioBtnRow1.click();
			UtilMeth.PauseForJS(driver, 20, true);
			break;

		case "United Kingdom":

			By elementid = By.xpath("//*[text()[contains(.,'United Kingdom')]]");
			String radiodID = driver.findElement(elementid).getAttribute("id");
			//DESCR254$2
			//CIBAA_SEL_VW$sels$2$$0
			int radionum = Integer.parseInt(radiodID.substring(radiodID.indexOf("$")+1).trim());
			By ukCampusRadioEL = By.xpath("//*[text()[contains(.,'United Kingdom')]]/ancestor::tr[3]//*[@id='CIBAA_SEL_VW$sels$"+radionum+"$$0']");
			WebElement radioBtnRow3 = driver.findElement(ukCampusRadioEL);
			radioBtnRow3.click();
			UtilMeth.PauseForJS(driver, 20, true);
			break;

		default:

			System.out.println("Wrong Country Was Selected");
			break;
		}

		return this;

	}

	public NewApplicationMyNottingham SelectInternational(String yesNo) {

		if (yesNo.equals("Yes")) {
			WebElement selectInternationalYes = driver.findElement(internationalYesRadioEL);
			selectInternationalYes.click();
			UtilMeth.PauseForJS(driver, 20, true);

		} else {

			WebElement selectInternationalNo = driver.findElement(internationalNoRadioEL);
			selectInternationalNo.click();
			UtilMeth.PauseForJS(driver, 20, true);

		}

		return this;
	}

	public NewApplicationMyNottingham ClickNextBtn() {

		WebElement nxtQuestionBtn = driver.findElement(nextBtnEL);
		nxtQuestionBtn.click();
		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public NewApplicationMyNottingham SelectCourseLvl(String course) {

		if (country.equals("Malaysia")) {

			switch (course) {
			case "LLL":
				WebElement foundation = driver.findElement(couseLvlFoundationMalEL);
				foundation.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "UG":
				WebElement underGrad = driver.findElement(couseLvlUGMalEL);
				underGrad.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "PGR":
				WebElement pgr = driver.findElement(couseLvlPGRMalEL);
				pgr.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "PGT":
				WebElement pgt = driver.findElement(couseLvlPGTMalEL);
				pgt.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;

			default:
				System.out.println("Fell thru the Switch Statment.............");
				break;
			}
		}

		if (country.equals("China")) {

			switch (course) {
			case "LLL":
				WebElement foundation = driver.findElement(couseLvlEngPrepChinaEL);
				foundation.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "UG":
				WebElement underGrad = driver.findElement(couseLvlUGChinaEL);
				underGrad.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "PGR":
				WebElement pgr = driver.findElement(couseLvlPGRChinaEL);
				pgr.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "PGT":
				WebElement pgt = driver.findElement(couseLvlPGTChinaEL);
				pgt.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			default:
				break;
			}

		}
		if (country.equals("United Kingdom")) {

			switch (course) {
			case "LLL":
				WebElement foundation = driver.findElement(couseLvlEngPrepUKEL);
				foundation.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "UG":
				WebElement underGrad = driver.findElement(couseLvlUGUKEL);
				underGrad.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "PGR":
				WebElement pgr = driver.findElement(couseLvlPGRUKEL);
				pgr.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			case "PGT":
				WebElement pgt = driver.findElement(couseLvlPGTUKEL);
				pgt.click();
				UtilMeth.PauseForJS(driver, 20, true);
				break;
			default:
				break;
			}

		}

		return this;
	}

	public NewApplicationMyNottingham SelectTerm() {

		if (driver.findElements(termSelectDivEL).size() < 1) {
			fail("There is no Term to Select");
		} else {
			WebElement termStart = driver.findElement(termRowOneEL);
			termStart.click();
		}

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public NewApplicationMyNottingham SelectSubject(int subjectrow) {

		if (UtilMeth.isElementPresent(driver, subjectTableEL)) {
			WebElement subjectTable = driver.findElement(subjectTableEL);
			List<WebElement> subjects = subjectTable.findElements(By.tagName("input"));

			System.out.println("The Number of Subjects in the Table => " + subjects.size());

		} else {
			fail("There are No Subjects available .............................................");
		}

		switch (subjectrow) {
		case 1:
			WebElement subject = driver.findElement(subjectRowOneEL);
			subject.click();
			UtilMeth.PauseForJS(driver, 20, true);
			break;
		case 2:
			WebElement subject1 = driver.findElement(subjectRowTwoEL);
			subject1.click();
			UtilMeth.PauseForJS(driver, 20, true);
			break;
		case 3:
			WebElement subject2 = driver.findElement(subjectRowThreeEL);
			subject2.click();
			UtilMeth.PauseForJS(driver, 20, true);
			break;

		default:
			System.out.println("Subject Selection Failed");
			break;
		}

		return this;
	}

	public NewApplicationMyNottingham SelectCourse(int courserow) {

		WebElement course = driver.findElement(courseRowOneEL);
		course.click();

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public NewApplicationMyNottingham SelectFullTime(boolean fulltime) {

		if (fulltime) {
			WebElement fulltimeRadio = driver.findElement(fullTimeEL);
			fulltimeRadio.click();

		} else {
			System.out.println("FullTime Failed");
		}
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public NewApplicationMyNottingham SelectTargetDegree() {
		WebElement targetdegree = driver.findElement(targetDegreeEL);
		targetdegree.click();
		UtilMeth.PauseForJS(driver, 1.1f, false);
		return this;
	}

	public NewApplicationMyNottingham ClickConfirmSelectionBtn() {

		WebElement confirmbtn = driver.findElement(confirmBtnEL);
		confirmbtn.click();
		UtilMeth.PauseForJS(driver, 120, true);

		return this;
	}

	public NewApplicationMyNottingham SelectAlternative(boolean alternative) {
		if (alternative == true) {
			WebElement alternativeYesRadio = driver.findElement(alternativeYesRadioEL);
			alternativeYesRadio.click();

		} else {
			WebElement alternativeNoRadio = driver.findElement(alternativeNoRadioEL);
			alternativeNoRadio.click();

		}
		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}

	public NewApplicationMyNottingham SelectUkStudy(boolean ukStudy) {
		if (ukStudy == true) {
			WebElement ukStudyYesRadio = driver.findElement(ukStudyYesRadioEL);
			ukStudyYesRadio.click();
		} else {
			WebElement ukStudyNoRadio = driver.findElement(ukStudyNoRadioEL);
			ukStudyNoRadio.click();
		}

		return this;
	}

	public NewApplicationMyNottingham ClickSearch() {

		UtilMeth.PauseForJS(driver, 0.5f, false);

		WebElement searchBtn = driver.findElement(searchButtonEL);
		searchBtn.click();

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public NewApplicationMyNottingham selectStartDate(int row) {
		switch (row) {
		case 1:
			WebElement rowOne = driver.findElement(startDateRowOneEL);
			rowOne.click();

			break;

		default:
			break;
		}
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public NewApplicationMyNottingham finalCourse() {
		
			WebElement rowOne = driver.findElement(By.id("CIBAA_SEL_VW$sels$0$$0"));
			rowOne.click();

		
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public void NewApplicationDetails(String courseLvl) {
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		//CHINA("China", "CN", "CHN"), MALAYSIA("Malaysia", "MY", "MYS"), UNITED_KINGDOM("United Kingdom", "UK", "GBR");
		
		switch(country) {
		case("United Kingdom"):
			System.out.println("New applicant for UK");
			SelectCampus().ClickNextBtn().SelectInternational("No").ClickNextBtn().SelectCourseLvl(courseLvl).ClickNextBtn().SelectTerm().ClickNextBtn().
			ClickSearch().SelectCourse(1).ClickNextBtn().SelectFullTime(true).ClickNextBtn();
			if(courseLvl.equals("PGR")) 
				selectStartDate(1).ClickNextBtn().ClickConfirmSelectionBtn();
			else 
				ClickConfirmSelectionBtn();
		break;
		case("China"):
			System.out.println("New applicant for China");
		break;
		case("Malaysia"):
			System.out.println("New applicant for Malasiya");
			
		   SelectCampus().ClickNextBtn().SelectInternational("No").ClickNextBtn().SelectCourseLvl(courseLvl).ClickNextBtn().SelectTerm().ClickNextBtn().ClickSearch();
		   
		   if(courseLvl.equals("LLL")||courseLvl.equals("UG")) {
			   SelectSubject(1).ClickNextBtn().SelectFullTime(true).ClickNextBtn();
			   if(courseLvl.equals("LLL"))
				  finalCourse().ClickNextBtn();
			   else 
				   ClickNextBtn();
		   }else {
			   SelectCourse(1).ClickNextBtn().SelectFullTime(true).ClickNextBtn();
		   }
		   
		   ClickConfirmSelectionBtn();
		break;
		default:
			System.out.println("invalild Country...");
		}

		System.out.println("NewApplicationDetails done...");
		
		driver.switchTo().defaultContent();
	}
}
