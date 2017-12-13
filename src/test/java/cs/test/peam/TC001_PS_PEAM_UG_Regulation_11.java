package cs.test.peam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import cs.peam.pages.PS_StudentCalcResult;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;


public class TC001_PS_PEAM_UG_Regulation_11 extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_UG_Regulation_11.class.getName());
	List<String> LcompCourses = new ArrayList<>();
	List<String> LnonCompCourses = new ArrayList<>();


	PS_AIR air;
	PS_StudentCalcResult scr;
	PS_APT apt;
	PS_HomePage homepage;
	
	String ruleOutcome;
	boolean bruleOutcome;
	List <String> LAIRItemIDs = new ArrayList<>();
	
	
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
     //scr = new PS_StudentCalcResult(driver);
     apt = new PS_APT(driver);
     air = new PS_AIR(driver);
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

	@Test(dependsOnMethods={"Login"},dataProvider="APTData")
	public void UG_Regulation_11(String student, String runMode) {
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting UG Regulation 11 Test=============");
		    apt.OpenAPT();
		    apt.searchStudentOnAPT(student);
		    LAIRItemIDs = apt.getAIRItemIDsForCourseList();
			air.OpenAIR();
			  
			HashMap <String,String> hm_CompCourses = air.verifyCompensatableCourses(LAIRItemIDs);
		    
			for(Map.Entry<String, String> m:hm_CompCourses.entrySet()) {
				if(m.getValue().equals("Comp")) LcompCourses.add(m.getKey());	
		    	else LnonCompCourses.add(m.getKey());
		    }
			
			//Verify if nonCompensation courses have been passed
				scr.OpenStudentCalculationResults();
				scr.searchStudent(student);
				boolean AllnonCompCoursesPass = scr.NoncompCourseResult(LnonCompCourses);
				System.out.println("AllnonCompCoursesPass " + AllnonCompCoursesPass);

				if(AllnonCompCoursesPass) { //rules 10 b, a and c should be triggered here
					System.out.println("Student Passed all Non Compensatory course hence rule 11 Pass");
//					for(String CompCourse:LcompCourses) {
//						System.out.println("CompCourse: " + CompCourse);
//					}
				}
				else
					System.out.println("Student failed Non Compensatory course hence rule 11 failed");
				log.info("=========== Finished UG Regulation 11 Test=============");			
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as UGRegulation11 for the user " + student);
			getScreenShot("UGRegulation11");
		}
	}

	@AfterClass
	public void endTest(){
	driver.close();
	}

}
