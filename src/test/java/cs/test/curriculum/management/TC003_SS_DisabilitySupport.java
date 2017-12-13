package cs.test.curriculum.management;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.mongodb.BasicDBObject;

import cs.curriculum.management.pages.StudentSupport;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;
import uiAutomation.uiActions.MyNottingham.MyNottingham_Login;
import helpercode.MongoInterface;

public class TC003_SS_DisabilitySupport extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC003_SS_DisabilitySupport.class.getName());	

	//PS_HomePage homepage;
	MyNottingham_Login myNottLogin;
	//PS_Login loginpage;
	StudentSupport sp;
	MongoInterface mi;
	
	
	String userid;
	
	@DataProvider(name="loginData")
	public String[][] getLoginData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}
	
	@DataProvider(name="ApplicantData")
	public String[][] getTestData(){
		String[][] testRecords;
		if(prop.getProperty("gridmode").equals("true")) {
			testRecords = getData("GridTestData.xlsx", prop.getProperty("gridtestdatasheet"));
		}else {
			testRecords = getData("TestData.xlsx", "StudentSupport");	
		}
		
		return testRecords;
	}

	
	@BeforeClass
	public void setUp() throws IOException{
    init();
    mi = new MongoInterface("students");
	sp = new StudentSupport(driver);
	myNottLogin = new MyNottingham_Login(driver);
	
		log.info("=========== Starting Create StudentSupport Test=============");
	}
	

	@Test (dataProvider="loginData")
	public void Login(String userid, String encryptedPassword, String runMode, String Env){
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the user "+ userid + " record as no run");

			this.userid = userid;
			myNottLogin.loginToApplication(userid,encryptedPassword);
			
		}catch (SkipException se) {System.out.println("Skipping the user "+ userid + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as loginData for the user " + userid);
			getScreenShot("loginData");
		}
	}

	
	@Test (dependsOnMethods={"Login"},dataProvider="ApplicantData")
	public void CreateStudentSupport(String runMode, String student, String summary, String details){
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("StudentSupport "+ student + " marked as no run...");
			//ap.OpenCurriculumManagementCentre();
			String casenum = sp.CreateDisabilitySupportCase(student, summary, details);
			
			String msg;
			BasicDBObject doc = new BasicDBObject();
			
			msg = "Userid: "+this.userid+", caseNumber: "+casenum;
			writeToDataFile("CaseNumbers.txt", msg);
			
			doc.append("userid", userid).append("casenumber", casenum);
			System.out.println("Writing to MongoDB: " + msg);
			mi.addDataToMongo(doc);
			
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
