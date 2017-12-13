package cs.test.admissions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import admissions.TestManager;
import admissions.UonCountries;
import admissions.UonCountryEnvironments;
import admissions.UonTestEnviroments;

//import com.google.gson.JsonObject;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DBObject;

import cs.peam.pages.PS_HomePage;

//import helpercode.MongoInterface;
import cs.admissions.MaintainApplicationsPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;
import uiAutomation.uiActions.MyNottingham.MyNottingham_Login;

public class TC007_MaintainApplicatons extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC007_MaintainApplicatons.class.getName());	
//added one comment
	//FrameWork Parameters
	String excelname, sheetname, msg;
	int data_row=1;
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");

	
	MyNottingham_Login myNottLogin;
	MaintainApplicationsPage map;
	//MongoInterface mi;
	PS_HomePage homepage;
	
//	static StudentJsonHandler jsHandler;
//	static String studentData;
	
	//Student Requirements
	String myNottsURL,campusSolutionsURL;

//Campus level parameters
	String courseLvl;
	String country = UonCountries.UNITED_KINGDOM.toString();
	String nationCode = UonCountries.UNITED_KINGDOM.nationCode();
	String countryCode = UonCountries.UNITED_KINGDOM.countryCode();
	
	@DataProvider(name="loginData")
	public String[][] getLoginData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}

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
	myNottLogin = new MyNottingham_Login(driver);
	map = new MaintainApplicationsPage(driver);

	//	mi = new MongoInterface("students");
	//	cd = new CountryData();

	//UonCountryEnvironments.setEnv(UonTestEnviroments.UATA.toString()); 
	UonCountryEnvironments.setEnv(UonTestEnviroments.UATB.toString());
	TestManager.setupEnvironmentsAndBrowserForTests(countryCode);
	myNottsURL = TestManager.getMyNottsTestEnvUrl();
	campusSolutionsURL = TestManager.getCsTestEnvUrl();
	getUrl(myNottsURL);

	writeToDataFile("student_data.txt",	"InsertDate :" + formater.format(calendar.getTime()));
	
	log.info("=========== Starting Application Maintenance=============");

	}

	@Test (dataProvider="loginData")
	public void Login(String userid, String Password, String runMode, String Env){
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("skipping the userid " + userid);
			
			myNottLogin.loginToApplication(userid,Password);
			map.OpenMaintainApplications();			
			
		}catch (SkipException se) {System.out.println("Skipping the user "+ userid + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as loginData_Exception for the user " + userid);
			getScreenShot("loginData_Exception");
			e.printStackTrace();
		}
	}

	
	@Test (dependsOnMethods={"Login"},dataProvider="ApplicantData")
	public void MaintainApplications(String recordId, String Career, String runMode, String studentid, String ResidencyCategory, String StudentCountry, String academicProg, String academicPlan){
		data_row++;
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("record "+ recordId + " marked as no run...");


			log.info(recordId);
			log.info(studentid);
			log.info(academicProg);
			log.info(academicPlan);

			map.SearchStudent(studentid);
			map.updateApplicationProgramData(academicProg,academicPlan);

			getScreenShot(Career+"_"+studentid);
			
			msg = recordId +" "+  Career +" "+ runMode +" "+ studentid +" "+ ResidencyCategory +" "+ StudentCountry +" "+ academicProg +" "+ academicPlan;
			writeToDataFile("student_data.txt", msg);
			
			//writing to the mongodb
			
//			JsonObject student = jsHandler.getStudentObject();
//			BasicDBObject doc = BasicDBObject.parse(student.toString());
//			
//			doc.append("recordId", recordId).append("courseName", Career).append("Env", UonTestEnviroments.UATA.toString()).append("InsertDate", formater.format(calendar.getTime()));
//			System.out.println("Writing to MongoDB: " + doc.toString());
//			mi.addDataToMongo(doc);
			
			System.out.println("updating runmode for row="+data_row);
			String datawritten = writeTestData(excelname, sheetname, "runMode", data_row, "N");
			System.out.println(datawritten);
			
			System.out.println("\nrecordId: " + recordId);
			System.out.println("StudentID: "+ studentid);
		}catch (SkipException se) {System.out.println("Skipping the record "+ recordId + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as  "+country+"_"+Career+" for the user " + recordId);
			getScreenShot(country+"_"+Career+studentid+"_Exception");
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void endTest(){
		log.info("=========== Finished Application Maintenance=============");

		//driver.close();
	}

}
