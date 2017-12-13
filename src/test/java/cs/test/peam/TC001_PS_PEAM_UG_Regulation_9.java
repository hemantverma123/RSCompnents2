package cs.test.peam;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_StudentCalcResult;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;


public class TC001_PS_PEAM_UG_Regulation_9 extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_UG_Regulation_9.class.getName());	

	PS_HomePage homepage;
	PS_StudentCalcResult scr;
	String ruleOutcome;
	boolean bruleOutcome;
	
	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}

	@DataProvider(name="SCRData")
	public String[][] getIAMData(){
		String[][] testRecords = getData("TestData.xlsx", "SCR");
		return testRecords;
	}
	
	@BeforeClass
	public void setUp() throws IOException{
     init();
 	 scr = new PS_StudentCalcResult(driver);
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

	@Test(dependsOnMethods={"Login"},dataProvider="SCRData")
	public void UG_Regulation_9(String student, String runMode) {
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting UG_Regulation_9 Test=============");
			scr.OpenStudentCalculationResults();
			scr.searchStudent(student);

			ruleOutcome = scr.GetRuleOutcome("UN Progression - UG - Regulation 9");
			System.out.println("Regulation 9 outcome for the student-" +student+" is "+ ruleOutcome );
			
			boolean bAllCoursesResult = scr.AllCoursesFinalGrades();
			
			if (ruleOutcome.equalsIgnoreCase("pass")) bruleOutcome = true; else bruleOutcome = false;
			
			if (bruleOutcome && bAllCoursesResult) {
				System.out.println("UN Progression - UG - Regulation 9 is applied");
				System.out.println("Regulation 11 will not be executed...");
			
			try {
					if(bAllCoursesResult)
					Assert.assertEquals(ruleOutcome, "Pass");
					else
					Assert.assertEquals(ruleOutcome, "Fail");
			}catch (AssertionError e) {System.out.println("Funtional test issue..."); e.getMessage();}	
			}
			else {
				System.out.println("Regulation 11 will be triggered...");
				//call regulation 11 here
			}
			log.info("=========== Finished UG_Regulation_9 Test=============");
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as UGRegulation9 for the user " + student);
			getScreenShot("UGRegulation9");
		}
	}

	@AfterClass
	public void endTest(){
	driver.close();
	}

}
