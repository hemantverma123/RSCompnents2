package cs.test.admissions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
import admissions.pages.FormStatusResearchDetails;
import admissions.pages.FormStatusResidency;
import admissions.pages.FormStatusTestInformation;
import admissions.pages.MalChinaApplicationAccountSignup;
import admissions.pages.NewApplicationMyNottingham;
import admissions.pages.RegistrationCompletePageMyNottingham;
import admissions.pages.StudentHomeMyNottingham;
import admissions.pages.SubmitApplicationMyNottingham;
import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_Login;
import uiAutomation.testBase.TestBase;

public class TC004_MyNott_Applicant_UK_UG extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC004_MyNott_Applicant_UK_UG.class.getName());	

	PS_HomePage homepage;
	PS_Login loginpage;
	MalChinaApplicationAccountSignup AppSignUp;
	EditAddressLinkPage EdAddress;
	RegistrationCompletePageMyNottingham RegPage;
	StudentHomeMyNottingham studentHomepage; 
	NewApplicationMyNottingham newApplication;
	FormStatusPersonalDetails perDetails;
	FormStatusContactAndAddress contactAddr;
	FormStatusResidency residency;
	FormStatusResearchDetails researchDetails;
	FormStatusTestInformation testInfo;
	FormStatusAdditionalQuestions addQuestions;
	SubmitApplicationMyNottingham submitApp;
	ApplicationSummaryMyNottingham appSummary;
	
	static StudentJsonHandler jsHandler;
	static String studentData;
	
	//Student Requirements
	String username, password,myNottsURL,campusSolutionsURL;

	String country = UonCountries.UNITED_KINGDOM.toString();
	String courseLvl = UonCourseLevels.UG.toString();
	String nationCode = UonCountries.UNITED_KINGDOM.nationCode();
	String countryCode = UonCountries.UNITED_KINGDOM.countryCode();
	

	
	@DataProvider(name="ApplicantData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "ApplicantTestData");
		return testRecords;
	}

	
	@BeforeClass
	public void setUp() throws IOException{
    init();
	homepage = new PS_HomePage(driver);
	
	UonCountryEnvironments.setEnv(UonTestEnviroments.PUAT.toString()); 
	TestManager.setupEnvironmentsAndBrowserForTests(countryCode);
	myNottsURL = TestManager.getMyNottsTestEnvUrl();
	campusSolutionsURL = TestManager.getCsTestEnvUrl();
	getUrl(myNottsURL);
	log.info("=========== Starting Create UK UG Applicant Test=============");

	}
	
	
	@Test (dataProvider="ApplicantData")
	public void CreateApplicant_UK_PGT(String username, String password, String runMode){
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("user "+ username + " marked as no run...");

			//getUrl(myNottsURL);
			
			studentData = TestManager.getStudentData(countryCode);
			jsHandler = new StudentJsonHandler(studentData);	

			username = jsHandler.getStudentUsername();
			password = jsHandler.getStudentPassword();

			log.info(username);
			log.info(password);
			log.info(studentData);

			AppSignUp = new MalChinaApplicationAccountSignup(driver,jsHandler);
			EdAddress = new EditAddressLinkPage(driver,jsHandler);
			RegPage = new RegistrationCompletePageMyNottingham(driver,jsHandler);
			studentHomepage = new StudentHomeMyNottingham(driver,jsHandler);
			newApplication = new NewApplicationMyNottingham(driver,jsHandler, country);
			perDetails = new FormStatusPersonalDetails(driver,jsHandler);
			contactAddr = new FormStatusContactAndAddress(driver,jsHandler);
			residency = new FormStatusResidency(driver,jsHandler, country, courseLvl);
			researchDetails = new FormStatusResearchDetails(driver, countryCode);
			testInfo = new FormStatusTestInformation(driver,jsHandler, country, courseLvl);
			addQuestions = new FormStatusAdditionalQuestions(driver,jsHandler, country, courseLvl);
			submitApp = new SubmitApplicationMyNottingham(driver,jsHandler);
			appSummary = new ApplicationSummaryMyNottingham(driver,jsHandler);
			
			boolean applicantCreate = true;
			
			if(applicantCreate) {
				homepage.clickApplyCourseBtn();
				AppSignUp.SetApplicantDetail();
				EdAddress.EditAddressDetails(nationCode);
				AppSignUp.ClickSubmitBtn();
				RegPage.VerifyRegistrationComplete();
				RegPage.ClickGoToLoginPageBtn();
			}
			Thread.sleep(5000);
			homepage.ApplicantLogin(username, password);
			studentHomepage.clickApplyNowBtn();
			
			if(applicantCreate) 
			newApplication.NewApplicationDetails(courseLvl);
			else {
				appSummary.ViewDetails();
			}
			
			perDetails.SetPersonalDetails(nationCode);
			contactAddr.SetContactAddress();
			residency.SetResidencey(nationCode);
			//researchDetails.setResearchDetails();
			//testInfo.SetTestInfo();
			addQuestions.SetQuestion();
			//refs.SetReference();
			
			submitApp.SubmitAppClick();
			appSummary.confirmSuccess();
			
			//writeToFile("student_data.txt",username,password,studentData,"UKApplicationUGTest");

			String msg = username+", "+password+", "+studentData+", "+"UKApplicationUGTest";
			writeToDataFile("student_data.txt", msg);

			getScreenShot("UKApplicationUGTest");
			
			appSummary.GotoHomePage(); 
			homepage.LogOut();
			
		}catch (SkipException se) {System.out.println("Skipping the user "+ username + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as  CreateUKUGApplicantTest for the user " + username);
			getScreenShot("CreateUKUGApplicantTest_Exception");
		}
	}
	

	@AfterClass
	public void endTest(){
		log.info("=========== Finished Create UK UG Applicant Test=============");

		//driver.close();
	}

}