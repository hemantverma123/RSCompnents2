package cs.test.curriculum.management;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cs.curriculum.management.pages.AcademicPlan;
import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_Login;
import uiAutomation.testBase.TestBase;

public class TC002_CM_AcademicPlan extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC002_CM_AcademicPlan.class.getName());	

	PS_HomePage homepage;
	PS_Login loginpage;
	AcademicPlan ap;
	
	@DataProvider(name="loginData")
	public String[][] getLoginData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}
	
	@DataProvider(name="ApplicantData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "AcademicPlanTable");
		return testRecords;
	}

	
	@BeforeClass
	public void setUp() throws IOException{
    init();
	homepage = new PS_HomePage(driver);
	ap = new AcademicPlan(driver);
	
		log.info("=========== Starting Create AcademicPlan Test=============");
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
	public void CreateAcademicPlan(String runMode, String AcademicPlan, String AcademicProgram, String AcademicCareer, String AcademicPlanType, String Description, String ShortDescription, String TranscriptLevel, String DiplomaDescription, String TranscriptDescription){
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("AcademicPlan "+ AcademicPlan + " marked as no run...");
			ap.OpenCurriculumManagementCentre();
			ap.SetAcademicPlan(AcademicPlan,AcademicProgram,AcademicCareer,AcademicPlanType,Description,ShortDescription,TranscriptLevel,DiplomaDescription,TranscriptDescription);
		}catch (SkipException se) {System.out.println("Skipping the AcademicPlan "+ AcademicPlan + " as marked no run");} 
 		 catch (Exception e) {
 			 System.out.println("Taking screenshot as  AcademicPlanTest for the AcademicPlan " + AcademicPlan);
 			 getScreenShot("AcademicPlanTest_Exception");
 			System.out.println(e);
 			 System.err.println(e);
			 e.printStackTrace();
		}
	}
	

	@AfterClass
	public void endTest(){
		log.info("=========== Finished Create AcademicPlan Test=============");
		//driver.close();
	}

}
