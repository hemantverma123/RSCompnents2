package admissions.helper;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.StudentJsonHandler;
import admissions.UonCountryEnvironments;
import admissions.UonCourseLevels;
import admissions.components.MenuComponent;
import admissions.pages.LandingPageMyNottingham;
import admissions.pages.MaintainApplicationsStaffMyNottingham;
import admissions.pages.StaffHomeMyNottingham;
import admissions.pages.StudentDataStaffMyNottingham;

public class AgentTask {

	WebDriver driver;
	String env;

	LandingPageMyNottingham landingPage;
	UonCountryEnvironments uonEnv;

	public AgentTask() {
		// TODO Auto-generated constructor stub
	}

	public WebDriver acceptStudent(WebDriver driver, String studentID, String courseLvl) {
		driver = loadMyNottingham(driver);
		driver = gotoMaintainApplication(driver, studentID);
		acceptStudentApp(driver, courseLvl);

		MenuComponent menu = new MenuComponent(driver);
		menu.uonSignOut();

		return driver;
	}

	public WebDriver acceptStudent(WebDriver driver, String studentID, String courseLvl, boolean signout) {
		driver = loadMyNottingham(driver);
		driver = gotoMaintainApplication(driver, studentID);
		acceptStudentApp(driver, courseLvl);

		if (signout) {
			MenuComponent menu = new MenuComponent(driver);
			menu.uonSignOut();
		}

		return driver;
	}

	public WebDriver verifyApplication(WebDriver driver, String courselvl, String studentID,
			StudentJsonHandler jsHandler) {
		loadMyNottingham(driver);
		gotoMaintainApplication(driver, studentID);

		WebElement displayName = driver.findElement(By.id("HCR_PERSON_NM_I_NAME_DISPLAY"));
		System.out.println("DisplayName => " + displayName.getText());
		Assert.assertEquals("Display Name is Wrong .....",
				jsHandler.getStudentFirstName() + " " + jsHandler.getStudentLastname(), displayName.getText());

		WebElement dob = driver.findElement(By.id("DERIVED_PERDATA_BIRTHDATE"));
		System.out.println("dob => " + dob.getAttribute("value"));
		Assert.assertEquals("BirthDate is Wrong ...", jsHandler.getStudentDob(true), dob.getAttribute("value"));

		WebElement phoneNumber = driver.findElement(By.id("SCC_PERS_PHN_H_PHONE$0"));
		System.out.println("phoneNumber => " + phoneNumber.getAttribute("value"));
		Assert.assertEquals("PhoneNumber is Wrong ...", jsHandler.getStudentPhone(true),
				phoneNumber.getAttribute("value"));

		WebElement email = driver.findElement(By.id("SCC_EMAIL_H_EMAIL_ADDR$0"));
		System.out.println("DisplayName3 => " + email.getAttribute("value"));
		Assert.assertEquals("Email is Wrong ...", jsHandler.getStudentEmail(), email.getAttribute("value"));

		WebElement addressLnk = driver.findElement(By.name("#ICPanel1"));
		addressLnk.click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SCC_ADDR_H$srt2$0")));
		wait.until(ExpectedConditions.textToBe(By.id("SCC_ADDR_H$srt2$0"), "Address"));
		System.out.println(
				"This Is The Heading Check!!!!!!!!!!! " + driver.findElement(By.id("SCC_ADDR_H$srt2$0")).getText());

		// Address off WebPage
		WebElement LongAddress = driver.findElement(By.id("DERIVED_SCC_AD_ADDRESSLONG$0"));
		String addressOffWeb = LongAddress.getText();
		addressOffWeb = addressOffWeb.replaceAll("\\s+", "");

		// Address From Json Message
		String addressOffJson = jsHandler.getStudentStreet() + "\r" + jsHandler.getStudentStreet() + "\r"
				+ jsHandler.getStudentStreet() + "\r" + jsHandler.getStudentCity() + "\r" + "Aberdeenshire "
				+ jsHandler.getStudentPostcode();
		addressOffJson = addressOffJson.replaceAll("\\s+", "");
		Assert.assertEquals("LongAddress is Wrong ...", addressOffJson, addressOffWeb);

		WebElement appProgData = driver.findElement(By.name("#ICPanel3"));
		appProgData.click();

		wait.until(ExpectedConditions.textToBe(By.id("INSTITUTION_TBL_DESCR"), "The University of Nottingham"));

		WebElement uni = driver.findElement(By.id("INSTITUTION_TBL_DESCR"));
		WebElement acCareer = driver.findElement(By.id("ACAD_CAR_TBL_DESCR"));

		Assert.assertEquals("University is Wrong ...", "The University of Nottingham", uni.getText());

		switch (courselvl) {
		case "UG":
			Assert.assertEquals("Academic Career is Wrong ...", "Undergraduate", acCareer.getText());
			System.out.println("ONE------------------------ Undergraduate");
			break;
		case "LLL":
			Assert.assertEquals("Academic Career is Wrong ...", "", acCareer.getText()); //ADD String
			System.out.println("TWO------------------------");
			break;
		case "PGT":
			Assert.assertEquals("Academic Career is Wrong ...", "Postgraduate Taught", acCareer.getText());
			System.out.println("Three------------------------ Postgraduate Taught");
			break;
		case "PGR":
			Assert.assertEquals("Academic Career is Wrong ...", "Postgraduate Research", acCareer.getText());
			System.out.println("Four------------------------ Postgraduate Research");
			break;

		default:
			break;
		}

		//Assert.assertEquals("Academic Career is Wrong ...", "Postgraduate Taught", acCareer.getText());

		return driver;
	}

	private WebDriver loadMyNottingham(WebDriver driver) {
		landingPage = new LandingPageMyNottingham(driver);

		//uonEnv = new UonCountryEnvironments();

		env = System.getProperty("testEnvironment");

		if (env == null) {
			env = UonCountryEnvironments.getEnv();
		}

		System.out.println("Env equals --------------> " + env);

		if (env.equals("UATA")) {
			landingPage.TypeUserName("UN_CS_BATCH").TypePassword("uataPassword1").ClickSignInBtn();
		} else {
			landingPage.TypeUserName("UN_CS_BATCH").TypePassword("uatbPassword1").ClickSignInBtn();
		}
		return driver;

	}

	private WebDriver gotoMaintainApplication(WebDriver driver, String studentID) {
		// Staff Landing Page
		StaffHomeMyNottingham staffHome = new StaffHomeMyNottingham(driver);
		staffHome.gotoMaintainApplications();

		// Maintain Applications Page
		MaintainApplicationsStaffMyNottingham maintainApp = new MaintainApplicationsStaffMyNottingham(driver,
				studentID);
		maintainApp.enterID().clickSearchBtn();
		return driver;

	}

	private void acceptStudentApp(WebDriver driver, String courseLvl) {
		if (courseLvl.equals(UonCourseLevels.PGR.toString()) || courseLvl.equals(UonCourseLevels.PGT.toString())
				|| courseLvl.equals("UG")) {

			System.out.println("CouseLvl equals -------------------->>>" + courseLvl);
			// Student Data Page Staff My Nottingham
			StudentDataStaffMyNottingham studentData = new StudentDataStaffMyNottingham(driver);
			studentData.clickCorrectHistoryBtn().applicationProgramDataLnk().programActionBx("admit")
					.actionReasonBx("admit", "(AD) Unconditional").clickSaveBtn();

		} else if (courseLvl.equals("LLL")) {

			System.out.println("CouseLvl !!!  equals !!! -------------------->>>" + courseLvl);
			StudentDataStaffMyNottingham studentData = new StudentDataStaffMyNottingham(driver);
			studentData.clickCorrectHistoryBtn().applicationProgramDataLnk().programActionBx("admit")
					.actionReasonBx("admit", "(AD) Unconditional").clickSaveBtn();
		}

	}

}
