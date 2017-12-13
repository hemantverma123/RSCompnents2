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

import cs.peam.pages.PS_AIR;
import cs.peam.pages.PS_APT;
import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_IAM;
import cs.peam.pages.PS_StudentCalcResult;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;


public class TC001_PS_PEAM_UG_RegulationsFlow extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_PEAM_UG_RegulationsFlow.class.getName());
	int FAILED_CREDITS=0,INPROGRESS_CREDITS=0,PASSED_CREDITS=0,PRG_ATTEMPT=0,TOTAL_CREDITS=0,WEIGHTED_AVERAGE=0;
	boolean isFinalYearStudent;
	
	LinkedHashMap<String,String> hmAllRulesOutcome = new LinkedHashMap<>();
	HashMap<String, Integer> hmAllCoursesResult = new HashMap<>();

	PS_HomePage homepage;
	PS_StudentCalcResult scr;
	PS_APT apt;
	PS_IAM iam;
	PS_AIR air;
	
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
 	 apt = new PS_APT(driver);
 	 iam = new PS_IAM(driver);
 	 air = new PS_AIR(driver);
 	 
	}
	

	@Test (dataProvider="loginData")
	public void Login(String userid, String encryptedPassword, String runMode, String env){
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
	public void UG_Regulations_Flow(String student, String runMode){
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");
		
		log.info("=========== Starting UG_Regulations Flow Test=============");
	    scr.OpenStudentCalculationResults();
	    Assert.assertEquals(scr.SCRPageValidation(),"Student Calculation Details");
	    scr.searchStudent(student);
	    Assert.assertEquals(scr.SCRsearchStudentPageValidation(),"Student Program Details");
	    
	    hmAllCoursesResult = scr.AllCoursesFinalGrades2();
		hmAllRulesOutcome = scr.GetAllRulesOutcome();
		
		for(Map.Entry<String, String> hmRule:hmAllRulesOutcome.entrySet()) {
			String ruleName, ruleOutcome;
			ruleName = hmRule.getKey();
			ruleOutcome = hmRule.getValue();
			
			System.out.println("\n\nSCR Page:	"+ruleName + ": "+ruleOutcome );
			
			boolean ManagingMandatoryCoursesInIAMRuleStatus = false;
			if(ruleName.equals("UN Progression - Managing Mandatory Courses in IAM")) {
				System.out.println("=========== Executing UN Progression - Managing Mandatory Courses in IAM - Test=============");
				log.info("=========== Executing UN Progression - Managing Mandatory Courses in IAM - Test=============");
				iam.OpenIAM();
			    iam.searchStudentOnIAM(student);
			    
			    ManagingMandatoryCoursesInIAMRuleStatus = iam.verifyMandatoryCourse(student);

			    if(ManagingMandatoryCoursesInIAMRuleStatus)
			    	System.out.println("UN Progression - Managing Mandatory Courses in IAM Test: should be : Pass" );
			    else
			    	System.out.println("UN Progression - Managing Mandatory Courses in IAM Test: should be : Fail" );
			    
			    if(ManagingMandatoryCoursesInIAMRuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - Managing Mandatory Courses in IAM Test: " + "PASS");
			    }
			    else if(!ManagingMandatoryCoursesInIAMRuleStatus && ruleOutcome.equals("Fail")) {
			    	System.out.println("UN Progression - Managing Mandatory Courses in IAM Test:  Both failed hence Test is PASS");
			    }
			    else {
			    	System.out.println("UN Progression - Managing Mandatory Courses in IAM Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    }
				
				log.info("=========== Finished TC001_PS_ManagingMandatoryCoursesInIAM Test=============");
			}
			
			boolean StageValidationRuleStatus = false;  
			if(ruleName.equals("UN Progression - Stage Validation")) {
				
				log.info("=========== Starting stageValidation_Regulation Test=============");
				//Main Menu->Records and Enrolments->Programme Enrolment-> Academic Progress Tracker

				apt.OpenAPT();
			    apt.searchStudentOnAPT(student);
			    isFinalYearStudent = apt.IsStudentFinalYear(); //getting this info from header tab
			    
			    apt.setAPTElements();
			    String currentStage = apt.GetAPTElement("currentStage");
			    System.out.println("TestFlow: Current stage : " + currentStage);
			    
			    //apt.CurrentCourseList();
			    
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
					StageValidationRuleStatus = true;
				else
					StageValidationRuleStatus=false;

				//Assert.assertEquals(INPROGRESS_CREDITS,120);
				
				if(StageValidationRuleStatus) {
			    	System.out.println("UN Progression - stageValidation_Regulation Test: should be: " + "PASS");
			    }
			    else {
			    	System.out.println("UN Progression - stageValidation_Regulation Test: should be: " + "Fail");
			    }
				
				if(StageValidationRuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - stageValidation_Regulation Test: " + "PASS");
			    }
				else if(!StageValidationRuleStatus && ruleOutcome.equals("Fail")) {
					System.out.println("UN Progression - stageValidation_Regulation Test: Both failed hence Test is PASS");
				}
			    else {
			    	System.out.println("UN Progression - stageValidation_Regulation Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    }
					
				log.info("=========== Finished stageValidation_Regulation Test=============");
			}
			
			
			boolean UGRegulation12RuleStatus = false;  
			if(ruleName.equals("UN Progression - UG - Regulation 12")) {
				log.info("=========== Starting UG_Regulation_12 Test=============");
				String currentStage = apt.GetAPTElement("currentStage");
				System.out.println("Test flow: current stage: " + currentStage);
				air.OpenAIR();
				
				int special_weighted_average = air.SpecialWeightedAverageScore(currentStage);

				if (WEIGHTED_AVERAGE >special_weighted_average){
					System.out.println("Student should be Passsed with weighted_average: " + WEIGHTED_AVERAGE + " and special_weighted_average is: " + special_weighted_average);
					UGRegulation12RuleStatus = true;
				}
				else{
					System.out.println("Student should be Failed with weighted_average: " + WEIGHTED_AVERAGE + " and special_weighted_average is: " + special_weighted_average);
					UGRegulation12RuleStatus = false;
				}

				//Assert.assertEquals(WEIGHTED_AVERAGE,0);

				if(UGRegulation12RuleStatus) {
			    	System.out.println("UN Progression - UG - Regulation 12 Test: should be " + "PASS");
			    }
			    else {
			    	System.out.println("UN Progression - UG - Regulation 12 Test: should be " + "Fail");
			    }

				
				if(UGRegulation12RuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - UG - Regulation 12 Test: " + "PASS");
			    }
				else if(!UGRegulation12RuleStatus && ruleOutcome.equals("Fail")) {
			    	System.out.println("UN Progression - UG - Regulation 12 Test: Both failed hence Test is PASS");
			    }
				
			    else {
			    	System.out.println("UN Progression - UG - Regulation 12 Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    }

				log.info("=========== Finished UG_Regulation_12 Test=============");

			}

			//must pass all Modules within a Programme of Study at the end of a Stage to Progress to progress to the next Stage. 		
			boolean UGRegulation9RuleStatus = false;  
			if(ruleName.equals("UN Progression - UG - Regulation 9")) {  
				log.info("=========== Starting UG_Regulation_9 Test=============");

				//ruleOutcome = scr.GetRuleOutcome("UN Progression - UG - Regulation 9");
				//System.out.println("Regulation 9 outcome for the student-" +student+" is "+ ruleOutcome );
				
				scr.OpenStudentCalculationResults();
				scr.searchStudent(student);
				boolean bAllCoursesResult = scr.AllCoursesFinalGrades();
				
				if (bAllCoursesResult) {
					UGRegulation9RuleStatus = true;
				}
				
				
				if(UGRegulation9RuleStatus) 
					System.out.println("UN Progression - UG - Regulation 9 Test: should be : " + "PASS");
				else
					System.out.println("UN Progression - UG - Regulation 9 Test: should be : " + "Fail");
				
				if(UGRegulation9RuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - UG - Regulation 9 Test: " + "PASS");
			    	System.out.println("Regulation 11 will not be executed...");
			    }
				else if(!UGRegulation9RuleStatus && ruleOutcome.equals("Fail")) {
			    	System.out.println("UN Progression - UG - Regulation 9 Test: Both Failed hence Test : " + "PASS");
			    	System.out.println("Regulation 11 will be executed...");
			    }

				else {
			    	System.out.println("UN Progression - UG - Regulation 9 Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    	System.out.println("Regulation 11 will be triggered...");
			    }
				
				log.info("=========== Finished UG_Regulation_9 Test=============");
			}


			List<String> LcompCourses = new ArrayList<>();
			List<String> LnonCompCourses = new ArrayList<>();
			List <String> LCourseListAIRItemIDs = new ArrayList<>();
			boolean UGRegulation11RuleStatus = false;  
			if(ruleName.equals("UN Progression - UG - Regulation 11")) {
				log.info("=========== Starting UG_Regulation_11 Test=============");

			    apt.OpenAPT();
			    apt.searchStudentOnAPT(student);
			    
			    LCourseListAIRItemIDs = apt.getAIRItemIDsForCourseList();
						
//			    for(String course:LCourseListAIRItemIDs) {
//			    	System.out.println("courselist is: "+course);
//			    }
			    
			    air.OpenAIR();

				HashMap <String,String> hm_CompCourses = air.verifyCompensatableCourses(LCourseListAIRItemIDs);
			    
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
					System.out.println("Student Passed all Non Compensatory course hence rule 11 should be Passsed");
					UGRegulation11RuleStatus = true;
					
//					for(String CompCourse:LcompCourses) {
//						System.out.println("CompCourse: " + CompCourse);
//					}
				}
				else { //rule 10 will not be triggered
					System.out.println("Student failed Non Compensatory course hence rule 11 should be failed");
					UGRegulation11RuleStatus = false;
				}

				
				if(UGRegulation11RuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - UG - Regulation 11 Test: " + "PASS");
			    	System.out.println("Regulation 10 will be executed...");
			    	
			    }
				else if((!UGRegulation11RuleStatus && ruleOutcome.equals("Fail"))) {
			    	System.out.println("UN Progression - UG - Regulation 11 Test: Both failed hence Test is " + "PASS");
			    	System.out.println("Regulation 10 will not be executed...");
				}
			    else {
			    	System.out.println("UN Progression - UG - Regulation 11 Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    	System.out.println("Regulation 10 will not be triggered...");
			    }
				
				log.info("=========== Finished UG_Regulation_11 Test=============");
			}
			
			
			boolean UGRegulation10bRuleStatus = false;
			//if(UGRegulation11RuleStatus && ruleName.equals("UN Progression - UG - Regulation 10b")) {
			if(ruleName.equals("UN Progression - UG - Regulation 10b")) {
				log.info("=========== Starting UG_Regulation_10b Test=============");
				
				if (PASSED_CREDITS >=100 && WEIGHTED_AVERAGE >=50){
					System.out.println("Rule 10B should be Passed with PASSED_CREDITS=" + PASSED_CREDITS + "and WEIGHTED_AVERAGE=" + WEIGHTED_AVERAGE);
					UGRegulation10bRuleStatus = true;
				}
				else{
				 	System.out.println("Rule 10B should be Failed with PASSED_CREDITS=" + PASSED_CREDITS + "and WEIGHTED_AVERAGE=" + WEIGHTED_AVERAGE);
				 	UGRegulation10bRuleStatus = false;
				}

				if(UGRegulation10bRuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - UG - Regulation 10B Test: " + "PASS");
			    	System.out.println("Regulation 10A will not be executed...");
			    	
			    }
				else if((!UGRegulation10bRuleStatus && ruleOutcome.equals("Fail"))) {
			    	System.out.println("UN Progression - UG - Regulation 10b Test: Both failed hence Test is " + "PASS");
			    	System.out.println("Regulation 10A will be executed...");
				}
			    else {
			    	System.out.println("UN Progression - UG - Regulation 10B Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    	if(ruleOutcome.equals("Pass"))
			    			System.out.println("Regulation 10A will not be triggered...");
			    	else
			    			System.out.println("Regulation 10A will be triggered...");
			    }
				log.info("=========== Finished UG_Regulation_10B Test=============");
			}
	
			//Rule 10A
		 	//•	passed modules worth at least 80 credits 
		 	//•	have a weighted average for the stage of at least 40% 
		 	//•	with no module marks of less than 30%
			boolean UGRegulation10ARuleStatus = false;
			
			//if(!UGRegulation10bRuleStatus && ruleName.equals("UN Progression - UG - Regulation 10a")) {
			if(ruleName.equals("UN Progression - UG - Regulation 10a")) {
				log.info("=========== Starting UG_Regulation_10A Test=============");
				if (PASSED_CREDITS >=80 && WEIGHTED_AVERAGE >=40){
					//verify if final grade is less than 30
//					scr.OpenStudentCalculationResults();
//					scr.searchStudent(student);
//					hmAllCoursesResult = scr.AllCoursesFinalGrades2();
						
					for(Map.Entry<String, Integer> hm:hmAllCoursesResult.entrySet()) {
						if(hm.getValue()<30) {
							System.out.println("Rule10A should Fail for course " + hm.getKey() + " with final grade =" + hm.getValue());
							UGRegulation10ARuleStatus = false;
							break;
						
						}
						else {
							UGRegulation10ARuleStatus = true;
							System.out.println("Rule10A should Pass for course " + hm.getKey() + " with final grade =" + hm.getValue());
						}
					}
						
					
					if(UGRegulation10ARuleStatus && ruleOutcome.equals("Pass")) {
				    	System.out.println("UN Progression - UG - Regulation 10A Test: " + "PASS");
				    	System.out.println("Regulation 10C will not be executed...");
				    	
				    }
					else if((!UGRegulation10ARuleStatus && ruleOutcome.equals("Fail"))) {
				    	System.out.println("UN Progression - UG - Regulation 10b Test: Both failed hence Test is " + "PASS");
				    	System.out.println("Regulation 10C will be executed...");
					}
				    else {
				    	System.out.println("UN Progression - UG - Regulation 10A Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
				    	if(ruleOutcome.equals("Pass"))
				    			System.out.println("Regulation 10C will not be triggered...");
				    	else
				    			System.out.println("Regulation 10C will be triggered...");
				    }
					log.info("=========== Finished UG_Regulation_10A Test=============");
				}
			}
			
			boolean UGRegulation10CRuleStatus = false;
			//if(!UGRegulation10ARuleStatus && ruleName.equals("UN Progression - UG - Regulation 10c")) {
			if(ruleName.equals("UN Progression - UG - Regulation 10c")) {
				log.info("=========== Starting UG_Regulation_10C Test=============");
				
				if (PASSED_CREDITS >=90 && WEIGHTED_AVERAGE >=45 && TOTAL_CREDITS>=110){
					System.out.println("Rule 10C should be Passed");
					UGRegulation10CRuleStatus = true;
				}
				else {
					System.out.println("Rule 10C should be failed ");
					UGRegulation10CRuleStatus = false;
				}

				if(UGRegulation10CRuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - UG - Regulation 10C Test: " + "PASS");
			    	System.out.println("Regulation 13 will not be executed...");
			    	
			    }
				else if((!UGRegulation10CRuleStatus && ruleOutcome.equals("Fail"))) {
			    	System.out.println("UN Progression - UG - Regulation 10C Test: Both failed hence Test is " + "PASS");
			    	System.out.println("Regulation 13 will be executed...");
				}
			    else {
			    	System.out.println("UN Progression - UG - Regulation 10C Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    	if(ruleOutcome.equals("Pass"))
			    			System.out.println("Regulation 13 will not be triggered...");
			    	else
			    			System.out.println("Regulation 13 will be triggered...");
			    }
				log.info("=========== Finished UG_Regulation_10C Test=============");
			}


			boolean UGRegulation13RuleStatus = false;
			if(ruleName.equals("UN Progression - UG - Regulation 13")) {
				log.info("=========== Starting UG_Regulation_13 Test=============");
				System.out.println(UGRegulation13RuleStatus);
				//Get all the failed courses only and check if their activity status is resit candidate. If resit allowed check box is no then rule will fail. If course is failed and activity status is not resit candidate then also rule will fail.
				
//				apt.OpenAPT();
//				apt.searchStudentOnAPT(student);  //getting stage info from header tab

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

				iam.OpenIAM();
				iam.searchStudentOnIAM(student);
				UGRegulation13RuleStatus = iam.VerifyResitCandidateForFailedCourses(isFinalYearStudent,failedCourses);
				
				if(UGRegulation13RuleStatus == true) {
					System.out.println("Rule 13 should be Pass");
				}
				else {
					System.out.println("Rule 13 should be Fail");
				}
				
				if(UGRegulation13RuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - UG - Regulation 13 Test: " + "PASS");
			    }
				else if((!UGRegulation13RuleStatus && ruleOutcome.equals("Fail"))) {
			    	System.out.println("UN Progression - UG - Regulation 13 Test: Both failed hence Test is " + "PASS");
				}
			    else {
			    	System.out.println("UN Progression - UG - Regulation 13 Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    }
				
				log.info("=========== Finished UG_Regulation_13 Test=============");
			}


			boolean UGRegulation20RuleStatus = false;
			if(ruleName.equals("UN Progression - UG - Regulation 20")) {
				log.info("=========== Starting UG_Regulation_20 Test=============");
				System.out.println(UGRegulation20RuleStatus);
				
				if(FAILED_CREDITS>20 || INPROGRESS_CREDITS>20) {
					System.out.println("Rule 20 should be Failed" + " FAILED_CREDITS=" + FAILED_CREDITS+" INPROGRESS_CREDITS="+INPROGRESS_CREDITS);
					UGRegulation20RuleStatus = false;
				}
				else {
					System.out.println("Rule 20 should be Pass" + " FAILED_CREDITS=" + FAILED_CREDITS+" INPROGRESS_CREDITS="+INPROGRESS_CREDITS);
					UGRegulation20RuleStatus = true;
				}

				if(UGRegulation20RuleStatus && ruleOutcome.equals("Pass")) {
			    	System.out.println("UN Progression - UG - Regulation 20 Test: " + "PASS");
			    }
				else if((!UGRegulation20RuleStatus && ruleOutcome.equals("Fail"))) {
			    	System.out.println("UN Progression - UG - Regulation 20 Test: Both failed hence Test is " + "PASS");
				}
			    else {
			    	System.out.println("UN Progression - UG - Regulation 20 Test: " + "Fail - Results Mismatch<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			    }
				
				log.info("=========== Finished UG_Regulation_20 Test=============");
			}
		}  //end of rules for loop
		log.info("=========== Finished UG_Regulations Flow Test=============");
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
		 catch (Exception e) {
			System.out.println("Taking screenshot as FlowError for the user " + student);
			getScreenShot("FlowError");
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void endTest(){
	driver.close();
	}

}
