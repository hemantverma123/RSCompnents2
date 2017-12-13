package cs.peam.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cs.peam.pages.PS_HomePage;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;

import uiAutomation.testBase.TestBase;

/**
 * 
 * @author Hemant Verma
 *
 */

public class PS_StudentCalcResult extends TestBase {

	//Search Stundent for the result
	@FindBy(id="UN_PRG_CAL_SRCH_EMPLID")
	WebElement EmplID;
	@FindBy(id="#ICSearch")
	WebElement btn_Search;

	@FindBy(xpath="//*[text()[contains(.,'Student Program Details')]]")
	WebElement StudentCalculationResultPage;

	
	WebDriver driver;
	PS_HomePage homepage;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(PS_StudentCalcResult.class.getName());

	
		
	public PS_StudentCalcResult(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		homepage = new PS_HomePage(driver);
		log.info("PS_StudentCalcResult called");		
	}


	public void OpenStudentCalculationResults()
	{
		homepage.OpenStudentCalculationResults();
	}
	
	public String SCRPageValidation() {
		String text = homepage.homePageHeaderValidation();
		return text;
	}
	
	public String SCRsearchStudentPageValidation()
	{
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver, 10, StudentCalculationResultPage);
		String text = StudentCalculationResultPage.getText();
		driver.switchTo().defaultContent();
		return text;
	}
	
	
	public void searchStudent(String student)
	{
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		waitForElement(driver,10,EmplID).sendKeys(student);
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver,10,btn_Search).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver,10,By.id("#ICSortCol0")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver,10,By.id("#ICSortCol0")).click();
//		try {  //no need of thread.sleep now
//			Thread.sleep(2000);
//			driver.findElement(By.id("#ICSortCol0")).click();
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver,10,By.id("SEARCH_RESULT1")).click();
		driver.switchTo().defaultContent();
	}

	public String GetRuleOutcome(String rule)
	{
		
		String ruleName;
		String ruleOutcome = "";

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));		

		String str = driver.findElement(By.xpath(".//*[@id='win0divUN_PRGCAL_DT_VWGP$0']/table/tbody/tr/td[2]/span[3]")).getText();
		int numOfrules = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
		
		int i=0;  
	    while(i<numOfrules){  //#to-do need to include the logic to get the total count of all the rules, one logic is to continue on NoSuchElementException in catch block 

	    	ruleName = driver.findElement(By.xpath("//*[@id='UN_PRG_RULE_VW_SCC_RULE_NAME$"+i+"']")).getText();
	        //System.out.println(ruleName);
	        
	        if(rule.equalsIgnoreCase(ruleName))
	        {
	        	ruleOutcome = driver.findElement(By.xpath("//*[@id='win0divUN_PRGCAL_DT_VW_UN_PRG_OUTCOME_CD$"+i+"']")).getText();
	        }
	        
	    i++;  
	    }  

	    driver.switchTo().defaultContent();
		return ruleOutcome;
	}

	public LinkedHashMap<String,String> GetAllRulesOutcome()
	{
		LinkedHashMap<String,String> hmAllRulesOutcome = new LinkedHashMap<String,String>();
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		String str = waitForElement(driver,10,By.xpath(".//*[@id='win0divUN_PRGCAL_DT_VWGP$0']/table/tbody/tr/td[2]/span[3]")).getText();
		
		int numOfrules = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
		
		int i=0;  
	    while(i<numOfrules){   

	    	String ruleName = driver.findElement(By.xpath("//*[@id='UN_PRG_RULE_VW_SCC_RULE_NAME$"+i+"']")).getText();
	    	String ruleOutcome = driver.findElement(By.xpath("//*[@id='win0divUN_PRGCAL_DT_VW_UN_PRG_OUTCOME_CD$"+i+"']")).getText();
	    	
	    	hmAllRulesOutcome.put(ruleName, ruleOutcome);
	    i++;  
	    }  

	    
	    driver.switchTo().defaultContent();
		return hmAllRulesOutcome;
		
	}


	
	
	public boolean AllCoursesFinalGrades()
	{
		boolean allCoursesResult = true;
		String strfinalgrade = "";
		int finalgrade;

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		String str = driver.findElement(By.xpath(".//*[@id='win0divUN_STD_DTL_VWGP$0']/table/tbody/tr/td[2]/span[3]")).getText();
		
		int numOfcourses = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
		
		int i=0;  
	    while(i<numOfcourses){  //#to-do need to include the logic to get the total count of all the rules, one logic is to continue on NoSuchElementException in catch block 

	    	strfinalgrade = driver.findElement(By.xpath("//*[@id='UN_STD_DTL_VW_UN_IAM_FINAL_GRADE$"+i+"']")).getText();
	    	finalgrade = Integer.parseInt(strfinalgrade.trim());
	    	
	        System.out.println(finalgrade);
	        
	        if(finalgrade < 40)  //It should go to test case area
	        {	allCoursesResult = false;
	             break;
	        }
	        else
	        	allCoursesResult = true;
	    i++;  
	    }  

	    driver.switchTo().defaultContent();
		return allCoursesResult;
		
	}

	
	public HashMap<String,Integer> AllCoursesFinalGrades2()
	{
		HashMap<String,Integer> hmAllCoursesResult = new HashMap<String,Integer>();
		
		
		String strfinalgrade = "",CourseAirID,CourseActivityID;
		int finalgrade;

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		String str = driver.findElement(By.xpath(".//*[@id='win0divUN_STD_DTL_VWGP$0']/table/tbody/tr/td[2]/span[3]")).getText();
		
		int numOfcourses = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
		
		int i=0;  
	    while(i<numOfcourses){  //#to-do need to include the logic to get the total count of all the rules, one logic is to continue on NoSuchElementException in catch block 

		    	try {
					CourseAirID = driver.findElement(By.id("UN_STD_DTL_VW_SSR_ITEM_ID$"+i)).getText();  //not being used currently
					CourseActivityID = driver.findElement(By.id("SSR_ACTIVITY_ID$"+i)).getText();
					
					strfinalgrade = driver.findElement(By.xpath("//*[@id='UN_STD_DTL_VW_UN_IAM_FINAL_GRADE$"+i+"']")).getText();
					finalgrade = Integer.parseInt(strfinalgrade.trim());
					hmAllCoursesResult.put(CourseActivityID, finalgrade);
				} catch (NoSuchElementException e) { //handling dropped course scenario where Course Activity ID will change id to win0divSSR_ACTIVITY_ID$8 (student: 18810843)
					// TODO Auto-generated catch block
					//e.printStackTrace();
					System.out.println("element SSR_ACTIVITY_ID$"+i + " not found... so ignoring....");
					i++;  
					continue;
				}
	    i++;  
	    }  

	    driver.switchTo().defaultContent();
		return hmAllCoursesResult;
	}

	
	public boolean NoncompCourseResult(List<String> LnonCompCoursesAIRIDs) {
		
		boolean AllnonCompCoursesPass = true;
		
		for(String nonCompCourse: LnonCompCoursesAIRIDs) {
			System.out.println("Getting the result of non comp course");
			
			waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
			WebElement we_CompCourseAIRID = waitForElement(driver, 10, By.xpath("//*[text()[contains(.,"+nonCompCourse+")]]"));			
			String str_CompCourseAIRID = we_CompCourseAIRID.getAttribute("id");
			
			int finalgradeIndex = Integer.parseInt(str_CompCourseAIRID.substring(str_CompCourseAIRID.indexOf("$")+1).trim());
			
			String finalgradetext = driver.findElement(By.xpath("//*[@id='UN_STD_DTL_VW_UN_IAM_FINAL_GRADE$"+finalgradeIndex+"']")).getText();
			System.out.println("NonCompCourse final grade: "+ finalgradetext);
			
			int finalgrade = Integer.parseInt(finalgradetext.trim());
	    	
	        System.out.println(finalgrade);
	        
	        if(finalgrade < 40)
	        {	AllnonCompCoursesPass = false;
	             break;
	        }
	        else
	        	AllnonCompCoursesPass = true;
			
		}
		
		driver.switchTo().defaultContent();
		return AllnonCompCoursesPass;
	}
}
