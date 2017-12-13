package cs.test.admissions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
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
import admissions.pages.FormStatusPersonalStatement;
import admissions.pages.FormStatusReferences;
import admissions.pages.FormStatusResearchDetails;
import admissions.pages.FormStatusResidency;
import admissions.pages.FormStatusSupportingDocuments;
import admissions.pages.FormStatusTestInformation;
import admissions.pages.FormStatusWorkExperience;
import admissions.pages.MalChinaApplicationAccountSignup;
import admissions.pages.NewApplicationMyNottingham;
import admissions.pages.RegistrationCompletePageMyNottingham;
import admissions.pages.StudentHomeMyNottingham;
import admissions.pages.SubmitApplicationMyNottingham;
import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_Login;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;

public class TC006_MyNott_Malasiya_AllApplicants extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC006_MyNott_Malasiya_AllApplicants.class.getName());	

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
	FormStatusReferences refs;
	FormStatusPersonalStatement perStatement;
	FormStatusWorkExperience workExperience;
	FormStatusSupportingDocuments suppDocs;
	
	static StudentJsonHandler jsHandler;
	static String studentData;
	
	//Student Requirements
	String username, password,myNottsURL,campusSolutionsURL;
	
	String country = UonCountries.MALAYSIA.toString();
	String nationCode = UonCountries.MALAYSIA.nationCode();
	String countryCode = UonCountries.MALAYSIA.countryCode();
	String courseLvl;
	
	@DataProvider(name="ApplicantData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "MALAYSIA");
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
	log.info("=========== Starting Create MALAYSIA Applicants Test=============");

	}
	
	@Test (dataProvider="ApplicantData")
	public void CreateMALAYSIAApplicants(String userid, String courseName, String runMode){
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("user "+ userid + " marked as no run...");

			studentData = TestManager.getStudentData(countryCode);
			jsHandler = new StudentJsonHandler(studentData);	
			username = jsHandler.getStudentUsername();
			password = jsHandler.getStudentPassword();

			log.info(username);
			log.info(courseName);
			log.info(studentData);
			
			switch(courseName) {
			case("UG"):
				System.out.println("Creating UG applicant for " + country);
			courseLvl = UonCourseLevels.UG.toString();
			break;
			case("PGR"):
				System.out.println("Creating PGR applicant for" + country);
			courseLvl = UonCourseLevels.PGR.toString();
			break;
			case("PGT"):
				System.out.println("Creating PGT applicant for " + country);
			courseLvl = UonCourseLevels.PGT.toString();
			break;
			case("LLL"):
				System.out.println("Creating LLL applicant for " + country);
			courseLvl = UonCourseLevels.LLL.toString();
			break;
			default:
				System.out.println("invalild course...");
			}
			
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
			refs = new FormStatusReferences(driver);
			perStatement = new FormStatusPersonalStatement(driver, country, courseLvl);
			workExperience = new FormStatusWorkExperience(driver);
			suppDocs = new FormStatusSupportingDocuments(driver, country, courseLvl);
			
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
			
			if(courseLvl.equals("PGT"))
			workExperience.setWorkExperience();
			
			perStatement.setPersonalStatement();
			addQuestions.SetQuestion();
			
			suppDocs.setSupportDoc();
			
			submitApp.SubmitAppClick();
			appSummary.confirmSuccess();
			appSummary.GotoHomePage();
			getScreenShot(country+"_"+courseName);
			writeToFile("student_data.txt",jsHandler.getStudentID(),jsHandler.getApplicationNumber(), username,password,studentData,country+"_"+courseName);
			
			homepage.LogOut();
			
		}catch (SkipException se) {System.out.println("Skipping the user "+ username + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as  "+country+"_"+courseName+" for the user " + username);
			getScreenShot(country+"_"+courseName+"_Exception");
		}
	}
	
	@AfterClass
	public void endTest(){
		log.info("=========== Finished Create MALAYSIA Applicants Test=============");

		//driver.close();
	}

}
