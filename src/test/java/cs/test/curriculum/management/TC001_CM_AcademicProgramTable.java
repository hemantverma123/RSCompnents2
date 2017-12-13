package cs.test.curriculum.management;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cs.curriculum.management.pages.AcademicProgram;
import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_Login;
import uiAutomation.testBase.TestBase;

public class TC001_CM_AcademicProgramTable extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_CM_AcademicProgramTable.class.getName());	

	PS_HomePage homepage;
	PS_Login loginpage;
	AcademicProgram ap;
	
	@DataProvider(name="loginData")
	public String[][] getLoginData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}
	
	@DataProvider(name="ApplicantData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "AcademicProgramTable");
		return testRecords;
	}

	
	@BeforeClass
	public void setUp() throws IOException{
    init();
	homepage = new PS_HomePage(driver);
	ap = new AcademicProgram(driver);
	
		log.info("=========== Starting Create AcademicProgram Test=============");
	}
	

	@Test (dataProvider="loginData")
	public void Login(String userid, String encryptedPassword, String runMode, String Env){
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the user "+ userid + " record as no run");

			homepage.login(userid,encryptedPassword,runMode);
		}catch (SkipException se) {System.out.println("Skipping the user "+ userid + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as loginData for the user " + userid);
			getScreenShot("loginData");
		}
	}

	
	@Test (dependsOnMethods={"Login"},dataProvider="ApplicantData")
	public void CreateAcademicProgram(String runMode, String  AcademicProgram, String  Description, String  ShortDescription, String  AcademicCareer, String  GradingScheme, String  GBDefaultforTransferCredit, String  AcademicGroup, String  AcademicLevelRule, String  AcademicCalendar, String  TranscriptLevel){
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("AcademicProgram "+ AcademicProgram + " marked as no run...");
			ap.OpenCurriculumManagementCentre();
			ap.SetAcademicProgram(AcademicProgram, Description, ShortDescription, AcademicCareer, GradingScheme, GBDefaultforTransferCredit, AcademicGroup, AcademicLevelRule, AcademicCalendar, TranscriptLevel);
		}catch (SkipException se) {System.out.println("Skipping the AcademicProgram "+ AcademicProgram + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as  AcademicProgramTest for the AcademicProgram " + AcademicProgram);
			getScreenShot("AcademicProgramTest_Exception");
		}
	}
	

	@AfterClass
	public void endTest(){
		log.info("=========== Finished Create AcademicProgram Test=============");
		//driver.close();
	}

}
