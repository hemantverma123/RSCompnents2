package cs.test.peam;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
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


public class TC001_PS_PEAM_UG_Regulation_20 extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_UG_Regulation_20.class.getName());
	int FAILED_CREDITS=0,INPROGRESS_CREDITS=0,PASSED_CREDITS=0,PRG_ATTEMPT=0,TOTAL_CREDITS=0,WEIGHTED_AVERAGE=0;
	LinkedHashMap<String,String> hmAllRulesOutcome = new LinkedHashMap<String,String>();
	String rule20Outcome = "";

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
	public void UG_Regulation_20(String student, String runMode) {
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting UG_Regulation_20 Test=============");
			scr.OpenStudentCalculationResults();
			scr.searchStudent(student);

			hmAllRulesOutcome = scr.GetAllRulesOutcome();
			HashMap <String,Integer> hmAllCoursesResult = scr.AllCoursesFinalGrades2();
			
			for(Map.Entry<String, String> hmRule:hmAllRulesOutcome.entrySet()) {
				String ruleName, ruleOutcome;
				ruleName = hmRule.getKey();
				ruleOutcome = hmRule.getValue();
				if(ruleName.equals("UN Progression - UG - Regulation 20")) {
					rule20Outcome =ruleOutcome; 
				}
				System.out.println("SCR Page:	"+ruleName + ": "+ruleOutcome );
			}
			System.out.println("\n\nSCR Page:	UN Progression - UG - Regulation 20: "+rule20Outcome );
			
			apt.OpenAPT(); 		//Main Menu->Records and Enrolments->Programme Enrolment-> Academic Progress Tracker
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
			
			if(FAILED_CREDITS>20 || INPROGRESS_CREDITS>20) {
				System.out.println("Rule 20 should be Failed" + " FAILED_CREDITS=" + FAILED_CREDITS+" INPROGRESS_CREDITS="+INPROGRESS_CREDITS);
			}
			else {
				System.out.println("Rule 20 should be Pass" + " FAILED_CREDITS=" + FAILED_CREDITS+" INPROGRESS_CREDITS="+INPROGRESS_CREDITS);
			}
			log.info("=========== Finished UG_Regulation_20 Test=============");
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as UGRegulation20 for the user " + student);
			getScreenShot("UGRegulation20");
		}
	}

	@AfterClass
	public void endTest(){
	driver.close();
	}

}
