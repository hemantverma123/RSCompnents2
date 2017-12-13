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

import cs.peam.pages.PS_AIR;
import cs.peam.pages.PS_APT;
import cs.peam.pages.PS_HomePage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;


public class TC001_PS_PEAM_UG_Regulation_12 extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_UG_Regulation_12.class.getName());
	int FAILED_CREDITS=0,INPROGRESS_CREDITS=0,PASSED_CREDITS=0,PRG_ATTEMPT=0,TOTAL_CREDITS=0,WEIGHTED_AVERAGE=0;

	PS_APT apt;
	PS_HomePage homepage;
	PS_AIR air;
	
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
     homepage = new PS_HomePage(driver);
     apt = new PS_APT(driver);
     air = new PS_AIR(driver);
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
	public void UG_Regulation_12(String student, String runMode) {
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting UG_Regulation_12 Test=============");
			apt.OpenAPT(); 		//Main Menu->Records and Enrolments->Programme Enrolment-> Academic Progress Tracker
			
//			getScreenShot("StudentAcademicProgressTracker");
//			Assert.assertEquals(apt.APTPageValidation(),"Academic Progress Tracker");

			apt.searchStudentOnAPT(student);

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

			System.out.println("Calculating speical weighted average...");
//			String str_special_weighted_average = apt.SpecialWeightedAverageScore();
//			int special_weighted_average = 0;
//			if(str_special_weighted_average.equals(""))
//				special_weighted_average =0;
//			else special_weighted_average = Integer.parseInt(str_special_weighted_average);

			String currentStage = apt.GetAPTElement("currentStage");
			System.out.println("Test flow: current stage: " + currentStage);
			air.OpenAIR();
			
			int special_weighted_average = air.SpecialWeightedAverageScore(currentStage);
			if (WEIGHTED_AVERAGE >special_weighted_average)
			{ System.out.println("Student Pass with weighted_average: " + WEIGHTED_AVERAGE + " and special_weighted_average is: " + special_weighted_average);}
			else
			{System.out.println("Student Fail with weighted_average: " + WEIGHTED_AVERAGE + " and special_weighted_average is: " + special_weighted_average);}

			//Assert.assertEquals(WEIGHTED_AVERAGE,0);
			log.info("=========== Finished UG_Regulation_12 Test=============");
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as UGRegulation12 for the user " + student);
			getScreenShot("UGRegulation12");
		}
	}

	@AfterClass
	public void endTest(){
	driver.close();
	}

}
