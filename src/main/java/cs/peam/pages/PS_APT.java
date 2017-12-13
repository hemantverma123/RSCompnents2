package cs.peam.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import uiAutomation.testBase.TestBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.StopWatch;


/**
 * 
 * @author Hemant Verma
 *
 */

public class PS_APT extends TestBase {

	@FindBy(id="SSR_APT_SRCH_EMPLID")
	WebElement ID_APTpage;
	
	//@FindBy(id="ICTAB_3")
	@FindBy(xpath=".//*[text()[contains(.,'APT Items')]]/parent::*[@role='tab']")
	WebElement tab_APTitems;
	
	@FindBy(xpath=".//*[text()[contains(.,'APT Tree')]]/parent::*[@role='tab']")
	WebElement tab_APTree;
	
	//@FindBy(xpath=".//*[text()[contains(.,'Programme of Study')]]")
	@FindBy(xpath="//*[@id='SSR_APT_WRK_GROUPBOX1$0']")
	WebElement ProgramOfStudy;
//	@FindBy(id="SSR_APT_RSLT_SSR_RSLT_NVAL$6")
//	WebElement InProgCredit;
	@FindBy(id="#ICSearch")
	WebElement btn_Search;
	
	HashMap<String,String> APT_Elements = new HashMap<String,String>();
	
	WebDriver driver;
	
	PS_HomePage homepage;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(PS_APT.class.getName());
		
	public PS_APT(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		homepage = new PS_HomePage(driver);
		log.info("PS_APT called");		
	}
	
	public void OpenAPT()
	{
		homepage.OpenAPT();
	}

	public void OpenAIR()
	{
		homepage.OpenAIR();
	}

	public String APTPageValidation()
	{
		String text = homepage.homePageHeaderValidation();
		return text;
	}
	
	
	public void searchStudentOnAPT(String student)
	{
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,ID_APTpage).clear();
		waitForElement(driver,10,ID_APTpage).sendKeys(student);
		waitForElement(driver,10,btn_Search).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		driver.switchTo().defaultContent();
	}
	

	public List<String> getAllStagesOnAPTItems() {
		List<String> LallStages = new ArrayList<>();
		List<WebElement> stages ;
		stages = driver.findElements(By.xpath("//*[text()[contains(.,'Stage')]]/a"));
		//System.out.println("First Stage name is: " + stages.get(0).getAttribute("id"));
		for(WebElement wb:stages) {
			//System.out.println("Adding Stage to the list: " + wb.getAttribute("id"));
			LallStages.add(wb.getAttribute("id"));
		}
		
		return LallStages;
	}
	
	public List<String> getAllStagesOnAPTree() {
		List<String> LallStages = new ArrayList<>();
		List<WebElement> stages ;
		
		stages = driver.findElements(By.xpath("//*[text()[contains(.,'Stage')]]"));
		
		for(WebElement wb:stages) {
			//System.out.println("Adding Stage to the list: " + wb.getAttribute("id"));
			LallStages.add(wb.getAttribute("id"));
		}
		
		return LallStages;
	}

	
	public String GetAPTElement(String element){
		//Academic Item ID, currentStage
		
		for(Map.Entry<String, String> hmElement: APT_Elements.entrySet()) {
			//System.out.println("Inside GetAPTElement: " + hmElement.getKey() +"  "+ hmElement.getValue());
			if(element.equals(hmElement.getKey())) {
				return hmElement.getValue();
			}
		}
	return  "no such element on APT";
	}
	
	
	public HashMap<String,Integer> AllResultTypeScores()    
	{
		WebElement Bstage;
		WebElement ResultValueField, BacademicItemAttempt;
		List<WebElement> resultTypes;
		List<String> Lstages = new ArrayList<>();
		int stageindex,loopcount;
		//String FAILED_CREDITS="",INPROGRESS_CREDITS="",PASSED_CREDITS="",PRG_ATTEMPT="",TOTAL_CREDITS="",WEIGHTED_AVERAGE="";
		String Academic_Item_ID;
		
		HashMap<String,Integer> hmAcademicItemAttemptResults = new HashMap<String,Integer>();

		//try {
			waitForPageLoad(driver, 30);
			waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
			waitForElement(driver,10,tab_APTitems).click();
			//new Actions(driver).moveToElement(tab_APTitems).perform();  //move focus
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			
			Lstages = getAllStagesOnAPTItems();

			loopcount=1;
			for(String stageElementId:Lstages) {
				
				//System.out.println("loop count: "+loopcount);
				String BacademicItemAttemptElementId;
				
				Bstage = waitForElement(driver,10,By.id(stageElementId));
				
				String strBstage = Bstage.getAttribute("id");
				stageindex = Integer.parseInt(strBstage.substring(strBstage.indexOf("$")+1).trim());
				
				String stagetext = "//*[@id='"+"win0divSSR_APT_WRK_GROUPBOX1GP$"+stageindex+"']";
				WebElement stage = waitForElement(driver,10,By.xpath(stagetext));
				stagetext = stage.getText();
				BacademicItemAttemptElementId = "SSR_APT_WRK_GROUPBOX1$" + stageindex;
				
				//System.out.println("stage button is: " + Bstage.getAttribute("id")+"aria-expanded :" + Bstage.getAttribute("aria-expanded"));
				Bstage.click();	
				ProcessingImgInvisible(driver, 10, By.id("processing"));

				String Academic_Item_ID_ElementID = "SSR_APT_ITEM_SSR_ITEM_ID$" + stageindex;
				WebElement F_Academic_Item_ID = waitForElementVisible(driver,10,By.id(Academic_Item_ID_ElementID));
				Academic_Item_ID =F_Academic_Item_ID.getText();
				APT_Elements.put("Academic Item ID",Academic_Item_ID);  //this is current stage actually so will not be used
				
				//*[@id="SSR_APT_ITEM_SSR_ITEM_ID$1"]
				
				BacademicItemAttempt = waitForElement(driver, 10,By.id(BacademicItemAttemptElementId));
				//System.out.println("Academic item attempt button is: " + BacademicItemAttempt.getAttribute("id")+"aria-expanded :" + BacademicItemAttempt.getAttribute("aria-expanded"));
				
				if(BacademicItemAttempt.getAttribute("aria-expanded").equals("False")) {
					BacademicItemAttempt.click();
				}
				
				resultTypes = driver.findElements(By.xpath("//input[@id[contains(.,'SSR_APT_RSLT_SSR_RSLT_TYPE$')]]"));
				for(WebElement resultType:resultTypes) {
					//System.out.println("result types are: " + resultType.getAttribute("name		") + "Values :" + resultType.getAttribute("value"));

					String str = resultType.getAttribute("id");
					int index = Integer.parseInt(str.substring(str.indexOf("$")+1).trim());
					ResultValueField = waitForElement(driver, 10, By.id("SSR_APT_RSLT_SSR_RSLT_NVAL$" + index));
					
					hmAcademicItemAttemptResults.put(resultType.getAttribute("value"),Integer.parseInt(ResultValueField.getAttribute("value"))); 
					
				}
				
				
				Bstage = waitForElement(driver, 10,By.id(stageElementId));  //staleElement issue
				if(Bstage.getAttribute("aria-expanded").equals("true")) {
					Bstage.click();  //closing the stage
				}
				

				loopcount++;
				if(loopcount>1) break;  //todo APT Items should always have one stage so no point to carry on for other stages
			}
			
    		//((JavascriptExecutor)driver).executeScript("document.getElementById('SSR_APT_WRK_GROUPBOX1$1').focus();");
			//new Actions(driver).moveToElement(StageOfStudy).perform();   //send focus to the element
			
			driver.switchTo().defaultContent();
			
			return hmAcademicItemAttemptResults;
		
	}

	
	
	public String setAPTElements()    //getting current stage via  APT Tree - APT tree should always have only one current stage
	{
		int stageindex,loopcount;
		//String statusTitle;
		String currentStage = ""; //nextStage = "";
		
		List<String> LallStages = new ArrayList<>();
		
		WebElement stageid;
		
		//System.out.println("Finding the current stage of the student...");
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,tab_APTree).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		LallStages = getAllStagesOnAPTree();
		//*[@id='win0divSSR_APT_WRK_SSR_ACAD_ITEM_STAT$10']
		
		loopcount=0;
		for(String stageElementId:LallStages) {
			
			//System.out.println("loop count: "+loopcount);
			
			stageid = waitForElement(driver,10,By.id(stageElementId));
			
			String strStage = stageid.getAttribute("id");
			stageindex = Integer.parseInt(strStage.substring(strStage.indexOf("$")+1).trim());
			
			String StageDescription = waitForElement(driver,10,By.id("ITEM_DESCR$"+stageindex)).getText();
			System.out.println("Current stage description :" + StageDescription);
			
			currentStage = waitForElement(driver,10,By.id("AIR_ITEM$"+stageindex)).getText();
			APT_Elements.put("currentStage", currentStage);
			//System.out.println("StudentCurrentStage: Current stage:" + currentStage);
			
//			String StatusElementID = "//*[@id='"+"win0divSSR_APT_WRK_SSR_ACAD_ITEM_STAT$"+stageindex+"']/img";
//			WebElement status = waitForElement(driver,10,By.xpath(StatusElementID));
//			statusTitle = status.getAttribute("title");
//			//System.out.println("Stage : " + StageDescription + "Status :"+ statusTitle);
//			
//			if(statusTitle.equals("Satisfied")) {
//				currentStage = LallStages.get(loopcount);
//				APT_Elements.put("currentStage", currentStage);
//			}
//			else {
//				nextStage = LallStages.get(loopcount);
//				APT_Elements.put("nextStage", nextStage);
//			}
				
			loopcount++;
		}
		driver.switchTo().defaultContent();
		return currentStage;	
	}
	
	public List<String> getAllCourseListOnAPTree() {  //these courses are active course for the current stage
		List<String> LallCourseList = new ArrayList<>();
		List<WebElement> Lwecourselist ;
		
		
		Lwecourselist = driver.findElements(By.xpath("//*[text()[contains(.,'Course List')]]"));
		
		for(WebElement wb:Lwecourselist) {
			//System.out.println("Adding CourseList to the list: " + wb.getAttribute("id"));
			LallCourseList.add(wb.getAttribute("id"));
		}
		
		return LallCourseList;
	}

	
	public List<String> CurrentCourseList() {  //this function is not required now
		//get all the course lists having index GT current stage and LT the last stage with status title not satisfied
		List<String> LallCourseList,LcourseList = new ArrayList<>();
		int courseListindex;
		String statusTitle;
		
		//System.out.println("Finding the current course lists of the student...");
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,tab_APTree).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		LallCourseList = getAllCourseListOnAPTree();
		//*[@id='win0divSSR_APT_WRK_SSR_ACAD_ITEM_STAT$10']
		
		for(String courseListElementId:LallCourseList) {
			//System.out.println("all Course list: " + courseListElementId);
			
			WebElement weCourseList = waitForElement(driver,10,By.id(courseListElementId));
			
			String strCourseListID = weCourseList.getAttribute("id");
			courseListindex = Integer.parseInt(strCourseListID.substring(strCourseListID.indexOf("$")+1).trim());

			String StatusElementID = "//*[@id='"+"win0divSSR_APT_WRK_SSR_ACAD_ITEM_STAT$"+courseListindex+"']/img";
			WebElement status = waitForElement(driver,10,By.xpath(StatusElementID));
			statusTitle = status.getAttribute("title");
			//String courseListDescription = waitForElement(driver,10,By.id("ITEM_DESCR$"+courseListindex)).getText();
			//System.out.println("courseListDescription : " + courseListDescription + "Status :"+ statusTitle);
			
			if(statusTitle.equals("Satisfied"))
				LcourseList.add(strCourseListID);  //if more than one stages then need  to apply the logic for the courselist having index GT previous stage
			else break;
			
		}
		
		driver.switchTo().defaultContent();
		return LcourseList;
	}
	
	public List<String> getAIRItemIDsForCourseList() {
		//System.out.println("Getting AIR ids...");
		
		List<String> LcourseList = new ArrayList<>();
		List <String> lCourseAIRItemIDs = new ArrayList<>();
		
		int courseListindex;		
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,tab_APTree).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));

		LcourseList = getAllCourseListOnAPTree();
		

//		for(String str:LcourseList) {
//			System.out.println("Current course list in AIR items :" + str);
//		}

		
		for(String courseListElementId:LcourseList) {
			//System.out.println("Working on Course-list item:"  + courseListElementId);
			String strCourseList = waitForElement(driver,10,By.id(courseListElementId)).getAttribute("id");
			courseListindex = Integer.parseInt(strCourseList.substring(strCourseList.indexOf("$")+1).trim());
			lCourseAIRItemIDs.add(waitForElement(driver,10,By.id("AIR_ITEM$"+courseListindex)).getText());
		}
		
//		for(String str:lCourseAIRItemIDs) {
//			System.out.println("CousrList AIR: " + str);
//		}
		
		driver.switchTo().defaultContent();
		return lCourseAIRItemIDs;
	}
	
	public boolean IsStudentFinalYear(){
		System.out.println("Calcualting the year of the student...");
		
		boolean isStudnetFinalYear = false;
				
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		String formatID = waitForElement(driver,10,By.id("SSR_AIR_PRG_VW_SSR_FORMAT_ID")).getText();
		String cohartTag = waitForElement(driver,10,By.id("SSR_COHORT_TBL_DESCR")).getText();

		int formatIDYear = Integer.parseInt(formatID.substring(formatID.indexOf("_")+1,formatID.indexOf("_")+2).trim());
		int stageYear = Integer.parseInt(cohartTag.substring(cohartTag.length()-2).trim());
		
		if(formatIDYear==stageYear) isStudnetFinalYear = true;
		else isStudnetFinalYear = false;

		driver.switchTo().defaultContent();
		return isStudnetFinalYear;
	}
}
