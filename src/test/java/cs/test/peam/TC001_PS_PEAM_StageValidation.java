package cs.test.peam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import cs.peam.pages.PS_APT;
import cs.peam.pages.PS_HomePage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;


public class TC001_PS_PEAM_StageValidation extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_StageValidation.class.getName());
	int FAILED_CREDITS=0,INPROGRESS_CREDITS=0,PASSED_CREDITS=0,PRG_ATTEMPT=0,TOTAL_CREDITS=0,WEIGHTED_AVERAGE=0;

	PS_APT apt;
	PS_HomePage homepage;

	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}

	@DataProvider(name="APTData")
	public String[][] getIAMData(){
		String[][] testRecords = getData("TestData.xlsx", "APT");
		return testRecords;
	}
	
	@BeforeClass
	public void setUp() throws IOException{
     init();
	apt = new PS_APT(driver);
	homepage = new PS_HomePage(driver);

	}
	
	
	@Test (dataProvider="loginData")
	public void Login(String userid, String encryptedPassword, String runMode){
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


	@Test(dependsOnMethods={"Login"},dataProvider="APTData")
	public void stageValidation(String student, String runMode) {
		//Main Menu->Records and Enrolments->Programme Enrolment-> Academic Progress Tracker
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting stageValidation_Regulation Test=============");
			apt.OpenAPT();

//			getScreenShot("StudentAcademicProgressTracker");
//			Assert.assertEquals(apt.APTPageValidation(),"Academic Progress Tracker");
//			String student = getStudentData("TestData.xlsx", "APT", "Student", 2);
			
		    System.out.println("student: " + student);

		    apt.searchStudentOnAPT(student);
		    
		    String currentStage = apt.setAPTElements();
		    System.out.println("Current stage : " + currentStage);
		    
		    apt.CurrentCourseList();
		    
			HashMap<String, Integer> hmAllResultTypeScores = apt.AllResultTypeScores();
				
				for(Map.Entry<String, Integer> hm:hmAllResultTypeScores.entrySet()) {
					
					switch(hm.getKey()) {
					case "FAILED_CREDITS": FAILED_CREDITS = hm.getValue();
						System.out.println("  FAILED_CREDITS :" + FAILED_CREDITS);
						break;
					case "INPROGRESS_CREDITS":INPROGRESS_CREDITS = hm.getValue();
						System.out.println("  INPROGRESS_CREDITS :" + INPROGRESS_CREDITS);
						break;
					case "PASSED_CREDITS":PASSED_CREDITS = hm.getValue();
						System.out.println("  PASSED_CREDITS :" + PASSED_CREDITS);
						break;
					case "PRG_ATTEMPT":PRG_ATTEMPT = hm.getValue();
						System.out.println("  PRG_ATTEMPT :" + PRG_ATTEMPT);
						break;
					case "TOTAL_CREDITS":TOTAL_CREDITS = hm.getValue();
						System.out.println("  TOTAL_CREDITS :" + TOTAL_CREDITS);
						break;
					case "WEIGHTED_AVERAGE":WEIGHTED_AVERAGE = hm.getValue();
						System.out.println("  WEIGHTED_AVERAGE :" + WEIGHTED_AVERAGE);
						break;
					default: break;
					}
				}
		
			
			if (INPROGRESS_CREDITS >=120)
			{ System.out.println("Student Pass with InProgCreditScore: " + INPROGRESS_CREDITS);}
			else
			{System.out.println("Student Fail with InProgCreditScore: " + INPROGRESS_CREDITS);}
			log.info("=========== Finished stageValidation_Regulation Test=============");			
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as StageValidation for the user " + student);
			getScreenShot("StageValidation");
		}
	}

	
	@AfterClass
	public void endTest(){
	driver.close();
	}

}
