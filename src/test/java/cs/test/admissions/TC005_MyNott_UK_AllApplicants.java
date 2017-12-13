package cs.test.admissions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.mongodb.BasicDBObject;

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
import admissions.pages.FormStatusReferences;
import admissions.pages.FormStatusResearchDetails;
import admissions.pages.FormStatusResidency;
import admissions.pages.FormStatusTestInformation;
import admissions.pages.MalChinaApplicationAccountSignup;
import admissions.pages.NewApplicationMyNottingham;
import admissions.pages.RegistrationCompletePageMyNottingham;
import admissions.pages.StudentHomeMyNottingham;
import admissions.pages.SubmitApplicationMyNottingham;
import cs.admissions.CountryData;
import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_Login;
import helpercode.MongoInterface;
import uiAutomation.testBase.TestBase;

public class TC005_MyNott_UK_AllApplicants extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC005_MyNott_UK_AllApplicants.class.getName());	

	MongoInterface mi;
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
	CountryData cd;
	
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");
	
	static StudentJsonHandler jsHandler;
	static String studentData;
	
	//Student Requirements
	String username, password,myNottsURL,campusSolutionsURL;

//Campus level parameters
	String courseLvl;
	String country = UonCountries.UNITED_KINGDOM.toString();
	String nationCode = UonCountries.UNITED_KINGDOM.nationCode();
	String countryCode = UonCountries.UNITED_KINGDOM.countryCode();
	String excelname, sheetname;
	int data_row=1;
	
	@DataProvider(name="ApplicantData")
	public String[][] getTestData(){
			String[][] testRecords;
			if(prop.getProperty("gridmode").equals("true")) {
				excelname = prop.getProperty("gridtestdata");
				sheetname = prop.getProperty("gridtestdatasheet");

			}else {
				//testRecords = getData("TestData.xlsx", "UKApplicant");
				excelname = prop.getProperty("testdata");
				sheetname = prop.getProperty("testdatasheet");
			}
			
			testRecords = getData(excelname, sheetname);
			return testRecords;
		}

	@BeforeClass
	public void setUp() throws IOException{
    init();
	homepage = new PS_HomePage(driver);
	mi = new MongoInterface("students");
	cd = new CountryData();

	UonCountryEnvironments.setEnv(UonTestEnviroments.UATA.toString()); 
	//UonCountryEnvironments.setEnv(UonTestEnviroments.UATB.toString());
	TestManager.setupEnvironmentsAndBrowserForTests(countryCode);
	myNottsURL = TestManager.getMyNottsTestEnvUrl();
	campusSolutionsURL = TestManager.getCsTestEnvUrl();
	getUrl(myNottsURL);

	writeToDataFile("student_data.txt",	"InsertDate :" + formater.format(calendar.getTime()));
	
	log.info("=========== Starting Create UK Applicants Test=============");

	}
	
	@Test (dataProvider="ApplicantData")
	public void CreateUKApplicants(String recordId, String Career, String runMode, String exForename, String Path, String Campus, String ResidencyCategory, String StudentCountry){
		data_row++;
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("user "+ recordId + " marked as no run...");

			studentData = TestManager.getStudentData(cd.getNationCode(StudentCountry));
			jsHandler = new StudentJsonHandler(studentData);

			jsHandler.setStudentFirstName(exForename.trim());
			jsHandler.setStudentCountry(StudentCountry.trim());
			jsHandler.setStudentNationCode(cd.getNationCode(StudentCountry.trim()));
			jsHandler.setStudentCountryCode(cd.getCountryCode(StudentCountry.trim()));
			jsHandler.setStudentPostcode(cd.getPostCode(StudentCountry.trim()));
			jsHandler.setStudentResidencyCategory(ResidencyCategory.trim());
			jsHandler.setStudentPassword("Password1!");
			jsHandler.setStudentEmail(data_row);  //fn.lastname123@fakemail.com
			
			username = jsHandler.getStudentUsername();
			password = jsHandler.getStudentPassword();

			boolean applicantCreate = true;
//			applicantCreate = false;
//			username =  "Helene_Ankunding";
//			password =  "S1BZxIBL2iOau_w*#1";
						
			log.info(recordId);
			log.info(username);
			log.info(password);
			log.info(Career);
			log.info(studentData);

			switch(Career) {
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

			switch(courseLvl) {
			case("UG"):
				System.out.println("No specific step for UG...");
			break;
			case("PGR"):
				researchDetails.setResearchDetails();
			break;
			case("PGT"):
				addQuestions.SetQuestion();
				refs.SetReference();
			break;
			case("LLL"):
				testInfo.SetTestInfo();
			break;
			default:
				System.out.println("invalild course...");
			}
			
			if(!Career.equals("PGT"))
			addQuestions.SetQuestion();
			
			submitApp.SubmitAppClick();
			appSummary.confirmSuccess();
			appSummary.GotoHomePage();
			getScreenShot(country+"_"+Career);
			
			//writeToFile("student_data.txt",jsHandler.getStudentID(),jsHandler.getApplicationNumber(), username,password,studentData,jsHandler.getStudentCountry()+"_"+Career);
			
			String msg = jsHandler.getStudentID()+", "+jsHandler.getApplicationNumber()+", "+ username+", "+password+", "+jsHandler.getStudentCountry()+", "+Career+", "+studentData;
			writeToDataFile("student_data.txt", msg);
			
			//writing to the mongodb
			
			JsonObject student = jsHandler.getStudentObject();
			BasicDBObject doc = BasicDBObject.parse(student.toString());
			
			doc.append("recordId", recordId).append("courseName", Career).append("Env", UonTestEnviroments.UATA.toString()).append("InsertDate", formater.format(calendar.getTime()));
			System.out.println("Writing to MongoDB: " + doc.toString());
			mi.addDataToMongo(doc);
			homepage.LogOut();
			
			System.out.println("updating runmode for row="+data_row);
			String datawritten = writeTestData(excelname, sheetname, "runMode", data_row, "N");
			System.out.println(datawritten);
			
			System.out.println("\nrecordId: " + recordId);
			System.out.println("StudentID: "+ jsHandler.getStudentID());
			System.out.println("username = \"" +username+"\";");
			System.out.println("password = \"" +password+"\";");			
		}catch (SkipException se) {System.out.println("Skipping the user "+ username + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as  "+country+"_"+Career+" for the user " + username);
			e.printStackTrace();
			getScreenShot(country+"_"+Career+"_Exception");
		}
	}
	
	@AfterClass
	public void endTest(){
		log.info("=========== Finished Create UK Applicants Test=============");

		//driver.close();
	}

}
