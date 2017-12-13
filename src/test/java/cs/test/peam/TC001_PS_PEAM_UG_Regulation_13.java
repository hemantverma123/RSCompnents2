package cs.test.peam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import cs.peam.pages.PS_IAM;
import cs.peam.pages.PS_StudentCalcResult;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;


public class TC001_PS_PEAM_UG_Regulation_13 extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_UG_Regulation_13.class.getName());
	LinkedHashMap<String,String> hmAllRulesOutcome = new LinkedHashMap<String,String>();
	String rule13Outcome = "";

	PS_HomePage homepage;
	PS_StudentCalcResult scr;
	PS_IAM iam;
	PS_APT apt;
	String ruleOutcome;
	boolean bruleOutcome;
	
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
 	 scr = new PS_StudentCalcResult(driver);
 	 homepage = new PS_HomePage(driver);
 	 iam = new PS_IAM(driver);
 	 apt = new PS_APT(driver);
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
	public void UG_Regulation_13(String student, String runMode) {
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting UG_Regulation_13 Test=============");
			scr.OpenStudentCalculationResults();
			scr.searchStudent(student);

			hmAllRulesOutcome = scr.GetAllRulesOutcome();
			HashMap <String,Integer> hmAllCoursesResult = scr.AllCoursesFinalGrades2();
			
			for(Map.Entry<String, String> hmRule:hmAllRulesOutcome.entrySet()) {
				String ruleName, ruleOutcome;
				ruleName = hmRule.getKey();
				ruleOutcome = hmRule.getValue();
				if(ruleName.equals("UN Progression - UG - Regulation 13")) {
					rule13Outcome =ruleOutcome; 
				}
				System.out.println("SCR Page:	"+ruleName + ": "+ruleOutcome );
			}
			System.out.println("\n\nSCR Page:	UN Progression - UG - Regulation 13: "+rule13Outcome );

			apt.OpenAPT();
			apt.searchStudentOnAPT(student);
			boolean isFinalYearStudent = apt.IsStudentFinalYear();
			//System.out.println("isFinalYearStudent: "+isFinalYearStudent);

			List<String> failedCourses = new ArrayList<String>();
			List<String> passedCourses = new ArrayList<String>();
			
			for(Map.Entry<String, Integer> hm:hmAllCoursesResult.entrySet()) {
				
				if(isFinalYearStudent) {
					if(hm.getValue()<50) failedCourses.add(hm.getKey());
					else passedCourses.add(hm.getKey());
				}else {
					if(hm.getValue()<40) failedCourses.add(hm.getKey());
					else passedCourses.add(hm.getKey());
				}
			}

			//check for the failed course for rule 13
//			for(String failCourse:failedCourses) {
//				System.out.println(failCourse);
//			}
			
			//getting the year of student
			iam.OpenIAM();
			iam.searchStudentOnIAM(student);
			boolean rule13PassFlag = iam.VerifyResitCandidateForFailedCourses(isFinalYearStudent,failedCourses);
			
			if(rule13PassFlag == true) {
				System.out.println("Rule 13 should be Pass");
			}
			else {
				System.out.println("Rule 13 should be Fail");
			}
			log.info("=========== Finished UG_Regulation_13 Test=============");
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as UGRegulation13 for the user " + student);
			getScreenShot("UGRegulation13");
		}
	}
	
	@AfterClass
	public void endTest(){
	driver.close();
	}

}
