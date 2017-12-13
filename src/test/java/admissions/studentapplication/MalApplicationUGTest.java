package admissions.studentapplication;

import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import admissions.BrowserManager;
import admissions.Browsers;
import admissions.StudentJsonHandler;
import admissions.TestManager;
import admissions.UonCountries;
import admissions.UonCountryEnvironments;
import admissions.UonCourseLevels;
import admissions.UonTestEnviroments;
import admissions.pages.ApplicationSummaryMyNottingham;
import admissions.pages.EditAddressLinkPage;
import admissions.pages.FormStatusAdditionalQuestions;
import admissions.pages.FormStatusContactAndAddress;
import admissions.pages.FormStatusPersonalDetails;
import admissions.pages.FormStatusPersonalStatement;
import admissions.pages.FormStatusResidency;
import admissions.pages.FormStatusSupportingDocuments;
import admissions.pages.LandingPageMyNottingham;
import admissions.pages.MalChinaApplicationAccountSignup;
import admissions.pages.NewApplicationMyNottingham;
import admissions.pages.RegistrationCompletePageMyNottingham;
import admissions.pages.StudentHomeMyNottingham;
import admissions.pages.SubmitApplicationMyNottingham;

public class MalApplicationUGTest {

	WebDriver driver;
	StudentJsonHandler jsHandler;

	// Student Requirements
	String studentID;
	String country = UonCountries.MALAYSIA.toString();
	String courseLvl = UonCourseLevels.UG.toString();
	String nationCode = UonCountries.MALAYSIA.nationCode();
	String countryCode = UonCountries.MALAYSIA.countryCode();

	String myNottsURL;
	String campusSolutionsURL;
	String studentData;

	@Before
	public void setUp() throws Exception {
		//UonCountryEnvironments.setEnv(UonTestEnviroments.UATB.toString()); 
		UonCountryEnvironments.setEnv(UonTestEnviroments.PUAT.toString());
		BrowserManager.setBrowser(Browsers.FIREFOX.toString());// This sets the Browser While I'm running in Eclipse ..
																// Jenkins will override this..
		TestManager.setupEnvironmentsAndBrowserForTests(countryCode);

		myNottsURL = TestManager.getMyNottsTestEnvUrl();
		campusSolutionsURL = TestManager.getCsTestEnvUrl();
		studentData = TestManager.getStudentData(countryCode);
		driver = TestManager.getDriver();

		jsHandler = new StudentJsonHandler(studentData);

	}

	@After
	public void tearDown() throws Exception {
		// Thread.sleep(5000);
		//driver.quit();
	}

	@Test
	public void test() throws IOException {
		driver.get(myNottsURL);
		//TestManager.checkTelephoneForCountry(countryCode);

		// Welcome To Nottingham Landing Page
		LandingPageMyNottingham landingPage = new LandingPageMyNottingham(driver);
		landingPage.clickApplyCourseBtn();

		// Malaysia Application Account Signup Page
		MalChinaApplicationAccountSignup AppSignUp = new MalChinaApplicationAccountSignup(driver,jsHandler);
		AppSignUp.TypeFirstName(jsHandler.getStudentFirstName()).TypeMiddleName(jsHandler.getStudentMiddlename())
				.TypeLastName(jsHandler.getStudentLastname()).TypeDob(jsHandler.getStudentDob(true))
				.TypeTelephone(jsHandler.getStudentPhone(true)).TypeEmailID(jsHandler.getStudentEmail())
				.TypeConfirmEmailID(jsHandler.getStudentEmail()).TypeuserID(jsHandler.getStudentUsername())
				.TypePassword(jsHandler.getStudentPassword()).TypeConfirmPassword(jsHandler.getStudentPassword())
				.SelectQuestion1(1, "q1Answer").SelectQuestion2(2, "q2Answer").SelectQuestion3(3, "q3Answer")
				.ClickEditAddress();

		// Edit Address Link
		EditAddressLinkPage EdAddress = new EditAddressLinkPage(driver,jsHandler);
		EdAddress.TypeAddressLine1(jsHandler.getStudentStreet()).TypeAddressLine2(jsHandler.getStudentStreet())
				.TypeAddressLine3(jsHandler.getStudentStreet()).TypeCity(jsHandler.getStudentCity()).TypeCounty()
				.TypePostcode(jsHandler.getStudentPostcode()).ClickOkBtn();

		// Malaysia Application Account Submit Button
		AppSignUp.ClickSubmitBtn();

		// Registration Complete Page Click GoTo Login
		RegistrationCompletePageMyNottingham RegPage = new RegistrationCompletePageMyNottingham(driver,jsHandler);
		RegPage.ClickGoToLoginPageBtn();

		// Student is Registered at this point!!
		System.out.println("Student has Registered...");

		// Goes Back to Landing Page .... Login with new Username & Password
		landingPage = new LandingPageMyNottingham(driver);
		landingPage.TypeUserName(jsHandler.getStudentUsername()).TypePassword(jsHandler.getStudentPassword())
				.ClickSignInBtn();

		// Student My Nottingham HomePage
		StudentHomeMyNottingham studentHomepage = new StudentHomeMyNottingham(driver,jsHandler);
		studentHomepage.clickApplyNowBtn();

		// New Application
		NewApplicationMyNottingham newApplication = new NewApplicationMyNottingham(driver,jsHandler, country);
		newApplication.SelectCampus().ClickNextBtn().SelectInternational("No").ClickNextBtn().SelectCourseLvl(courseLvl)
				.ClickNextBtn().SelectTerm().ClickNextBtn().ClickSearch().SelectSubject(1).ClickNextBtn()
				.SelectFullTime(true).ClickNextBtn().ClickNextBtn() // year of Entry is all ready pre-populated so can
																	// get away with click next....
				// .selectStartDate(1).ClickNextBtn()
				.ClickConfirmSelectionBtn();

		// Personal Details Page
		FormStatusPersonalDetails perDetails = new FormStatusPersonalDetails(driver,jsHandler);
		perDetails.titleSelect("Mr").longNameTxt(jsHandler.getStudentFirstName(), jsHandler.getStudentLastname())
				.genderSelect(jsHandler.getStudentSex()).maritalSelect("Single").nationalitySelect("MY")
				.disabledSelect("NO").convictionRadio("NO").religionSelect().raceSelect().saveBtn();

		// Contact & Address
		FormStatusContactAndAddress contactAddr = new FormStatusContactAndAddress(driver,jsHandler);
		contactAddr.countryCodeTxtBx().saveBtn(true);

		// Residency
		FormStatusResidency residency = new FormStatusResidency(driver,jsHandler, country, courseLvl);
		residency.NricRadio(true).saveBtn();

		// Personal Statement
		FormStatusPersonalStatement perStatement = new FormStatusPersonalStatement(driver, country, courseLvl);
		perStatement.bulkFillCommentBx().saveBtn();

		// Additional Questions
		FormStatusAdditionalQuestions addQuestions = new FormStatusAdditionalQuestions(driver,jsHandler, country, courseLvl);
		addQuestions.payForStudiesSelect().hearAboutUniSelect().agentHelpingSelect().saveBtn();

		// Supporting Documents
		FormStatusSupportingDocuments suppDocs = new FormStatusSupportingDocuments(driver, country, courseLvl);
		suppDocs.saveBtn();

		suppDocs.submitAppBtn();

		// Submit Application Page
		SubmitApplicationMyNottingham submitApp = new SubmitApplicationMyNottingham(driver,jsHandler);
		submitApp.acceptTermsChkBx().submitAppRedBtn();

		ApplicationSummaryMyNottingham appSummary = new ApplicationSummaryMyNottingham(driver,jsHandler);
		appSummary.getScreenShot("MalApplicationUGTTest");
		appSummary.confirmSuccess();
	}

}
