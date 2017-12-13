package cs.test.curriculum.management;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

//import com.mongodb.BasicDBObject;

import cs.curriculum.management.pages.StudentSupport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;
import uiAutomation.uiActions.MyNottingham.MyNottingham_Login;
import helpercode.MongoInterface;

public class TC004_SS_ExtenuatingCircumstanaces extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC004_SS_ExtenuatingCircumstanaces.class.getName());	

	//FrameWork Parameters
	String excelname, sheetname, msg;
	int data_row=1;
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");


	//PS_HomePage homepage;
	MyNottingham_Login myNottLogin;
	//PS_Login loginpage;
	StudentSupport sp;
	MongoInterface mi;
	
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
    mi = new MongoInterface("students");
	sp = new StudentSupport(driver);
	myNottLogin = new MyNottingham_Login(driver);
	
	writeToDataFile("SS_ExtenuatingCases.csv",	"InsertDate :" + formater.format(calendar.getTime()));
	msg = "Student, caseNumber, Summary, details";
	writeToDataFile("SS_ExtenuatingCases.csv", msg);

		log.info("=========== Starting Create StudentSupport Test=============");
	}
	

	@Test (dataProvider="loginData")
	public void Login(String userid, String encryptedPassword, String runMode, String Env){
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the user "+ userid + " record as no run");

			userid = userid;
			myNottLogin.loginToApplication(userid,encryptedPassword);
			
		}catch (SkipException se) {System.out.println("Skipping the user "+ userid + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as loginData for the user " + userid);
			getScreenShot("loginData");
		}
	}

	
	@Test (dependsOnMethods={"Login"},dataProvider="ApplicantData")
	public void CreateExtenuatingCase(String recordId,String student, String runMode, String summary, String details){
		data_row++;
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("StudentSupport "+ student + " marked as no run...");
			//ap.OpenCurriculumManagementCentre();
			//String casenum = sp.SetCaseNumber(student, summary, details);
			
			sp.CreateExtenuatingCircumstancesCase(summary);
			String casenum = sp.getExtenuatingCircumstancesCase();
			
			msg = student+", "+casenum+", "+summary+", "+details;
			System.out.println(msg);
			writeToDataFile("SS_ExtenuatingCases.csv", msg);

//			BasicDBObject doc = new BasicDBObject();
//			doc.append("userid", student).append("casenumber", casenum);
//			System.out.println("Writing to MongoDB: " + msg);
//			mi.addDataToMongo(doc);
			
			System.out.println("updating runmode for row="+data_row);
			String datawritten = writeTestData(excelname, sheetname, "runMode", data_row, "N");
			System.out.println(datawritten);
			
			System.out.println("\nrecordId: " + recordId);
			System.out.println("summary: "+ summary);
			
		}catch (SkipException se) {System.out.println("Skipping the Student "+ student + " as marked no run");} 
 		 catch (Exception e) {
 			 System.out.println("Taking screenshot as  StudentSupportTest for the AcademicPlan " + student);
 			 getScreenShot("StudentSupportTest_Exception");
 			System.out.println(e);
 			 System.err.println(e);
			 e.printStackTrace();
		}
	}
	

	@AfterClass
	public void endTest(){
		log.info("=========== Finished Create StudentSupport Test=============");
		//driver.close();
	}

}
