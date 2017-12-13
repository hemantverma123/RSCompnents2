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

public class TC001_PS_PEAM_UG_Regulation_10 extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_UG_Regulation_10.class.getName());
	
	int CurrentInProgressValue = 0,FAILED_CREDITS=0,INPROGRESS_CREDITS=0,PASSED_CREDITS=0,PRG_ATTEMPT=0,TOTAL_CREDITS=0,WEIGHTED_AVERAGE=0;
	List<String> LcompCourses = new ArrayList<>();
	List<String> LnonCompCourses = new ArrayList<>();
	HashMap<String, Integer> hmAllCoursesResult = new HashMap<>();
	boolean rule10aflag = false;


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
	public void UG_Regulation_10(String student, String runMode) {
		//Main Menu->Campus Solutions>Curriculum Management> Academic Item Registry> Academic Item Registry (AIR)
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting UG Regulation 10 Test=============");
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
			if(AllnonCompCoursesPass){ //rules 10 b, a and c should be triggered here
				System.out.println("Student Passed all Non Compensatory course hence rule 11 Pass");
				for(String CompCourse:LcompCourses) {
					System.out.println("CompCourse: " + CompCourse);
					}
					//10(b)	
					//•	passed modules worth at least 100 credits
					//•	have a weighted average for the stage of at least 50%

				apt.OpenAPT();	//TODO need to optimize to read data at once in the bigining
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
					
					if (PASSED_CREDITS >=100 && WEIGHTED_AVERAGE >=50){
							System.out.println("Rule 10B Pass with PASSED_CREDITS=" + PASSED_CREDITS + "and WEIGHTED_AVERAGE=" + WEIGHTED_AVERAGE);
					}
					else{
					 	System.out.println("Rule 10B Fail with PASSED_CREDITS=" + PASSED_CREDITS + "and WEIGHTED_AVERAGE=" + WEIGHTED_AVERAGE);
					 	//Rule 10A
					 	//•	passed modules worth at least 80 credits 
					 	//•	have a weighted average for the stage of at least 40% 
					 	//•	with no module marks of less than 30%
					 	
						if (PASSED_CREDITS >=80 && WEIGHTED_AVERAGE >=40){
							//verify if final grade is less than 30
							hmAllCoursesResult = scr.AllCoursesFinalGrades2();
								
							for(Map.Entry<String, Integer> hm:hmAllCoursesResult.entrySet()) {
								if(hm.getValue()<30) {
									System.out.println("Rule10A for course " + hm.getKey() + " with final grade =" + hm.getValue());
									rule10aflag = false;
									break;
								
								}
								else {
									rule10aflag = true;
									System.out.println("Rule10A for course " + hm.getKey() + " with final grade =" + hm.getValue());
								}
							}
								
							if(rule10aflag) {
								System.out.println("Rule10A Pass");
							}
							else { 	
									System.out.println("Rule10A Fail, Triggering rule 10C...");
									//trigger rule 10C
									//•	passed modules worth at least 90 credits
									//•	have marks of 30% or more in modules worth at least 110* credits, 
									//•	have a weighted average for the stage of at least 45%.
		
								if (PASSED_CREDITS >=90 && WEIGHTED_AVERAGE >=45 && TOTAL_CREDITS>=110){
									System.out.println("Rule 10C Pass");
								}
								else {
									System.out.println("Rule10C Fail");
								}
								
							}
					
						}
					}
			}
			else {
				System.out.println("Student failed Non Compensatory course hence rule 11 failed");
			}
			
			log.info("=========== Finished UG Regulation 10 Test=============");			
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as UGRegulation10 for the user " + student);
			getScreenShot("UGRegulation10");
		}
	}
	
	
	@AfterClass
	public void endTest(){
	driver.close();
	}

}
