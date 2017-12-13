package cs.peam.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cs.peam.pages.PS_Login;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.commons.lang3.time.StopWatch;

import uiAutomation.testBase.TestBase;


/**
 * 
 * @author Hemant Verma
 *
 */

public class PS_IAM extends TestBase {


	@FindBy(id="SSR_IAM_ACRT2VW_EMPLID")
	WebElement ID;
	@FindBy(id="#ICSearch")
	WebElement btn_Search;

	List <String> lCourseRootActivityIDs = new ArrayList<>();
	
	String text;
	WebDriver driver;

	PS_HomePage homepage;

	
	private final static Logger log = Logger.getLogger(PS_IAM.class.getName());

	
		
	public PS_IAM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		homepage = new PS_HomePage(driver);
		log.info("PS_IAM called");		
	}

	public void OpenIAM()
	{
		homepage.OpenIAM();
	}
	
	
	public String homePageHeaderValidation()
	{
		text = homepage.homePageHeaderValidation();
		return text;
	}
	

	public void searchStudentOnIAM(String student)
	{
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,ID).sendKeys(student);
		waitForElement(driver,10,btn_Search).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		setCourseRootActivityIDs();
		driver.switchTo().defaultContent();
	}

	public void setCourseRootActivityIDs(){
		//lCourseRootActivityIDs
		
		String CourseRootActivityID = "";

		//String str = driver.findElement(By.xpath(".//*[@id='win0divSEARCHRESULT']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span[2]")).getText();
		String str = waitForElement(driver,10, By.xpath(".//*[@id='win0divSEARCHRESULT']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span[2]")).getText();
		int countCourseRootActivityID = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
		
		int i=0;  
	    while(i<countCourseRootActivityID){   
	    	
	    	CourseRootActivityID = waitForElement(driver,10,By.id("RESULT2$"+i)).getText();
	    	//System.out.println(i + ". CourseRootActivityID = " + CourseRootActivityID);
	    	lCourseRootActivityIDs.add(CourseRootActivityID);
	    	i++;
	    }
	}
	
	
	public boolean verifyMandatoryCourse(String student)
	{
		String CourseRootActivityID = "";
		List<WebElement> lweActivityID;
		boolean verifyMandatoryCourse_outcome = true;
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		String str = driver.findElement(By.xpath(".//*[@id='win0divSEARCHRESULT']/table/tbody/tr[1]/td/table/tbody/tr/td[2]/span[2]")).getText();
		int iCourseRootActivityID = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
		
		int i=0;  
	    while(i<iCourseRootActivityID){   
	    	waitForElement(driver,10,By.id("RESULT2$"+i));
	    	CourseRootActivityID = waitForElement(driver,10,By.id("RESULT2$"+i)).getText();
	    	System.out.println(i + ". CourseRootActivityID = " + CourseRootActivityID);
	    	
	    	waitForElement(driver,10,By.id("RESULT2$"+i)).click();
	    	ProcessingImgInvisible(driver, 10, By.id("processing"));
	        
	    	waitForElement(driver,10,By.id("SSR_RESULTS$0"));
	    	lweActivityID = driver.findElements(By.xpath("//a[text()[contains(.,'ACT')]]"));
	    	    	
	    	List <String> ListActIDs = new ArrayList<>();
	    	for (WebElement we : lweActivityID) {
	    		//System.out.println("AcitivityID attributes :  text=" + we.getText() +"  id="+ we.getAttribute("id") +"  value="+ we.getAttribute("value") + "   tagName=" + we.getTagName() +"  class="+ we.getClass() );
	    		ListActIDs.add(we.getAttribute("id"));
//				JavascriptExecutor js = (JavascriptExecutor) driver;
//				// Execute javascript
//				Object items = js.executeScript("var items = {}; for (index = 0;s index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", driver.findElement(By.id(we.getAttribute("id"))));
//				System.out.println("JS script value:  "+items.toString());
	    	}
	    	
	    	
	    	int j=0;
	    	for (String s : ListActIDs) {
 
	    		waitForPageLoad(driver, 30);
	    		waitForElement(driver,10,By.id("SSR_RESULTS$"+j)).click();;
	    		ProcessingImgInvisible(driver, 10, By.id("processing"));
	    		//System.out.println("AcitivityID attributes :  text=" + we.getText() +"  id="+ we.getAttribute("id") +"  value="+ we.getAttribute("value") + "   tagName=" + we.getTagName() +"  class="+ we.getClass() );
	    		
	    		//waitForElement(driver,20,By.id(s));
	    		//waitForElementClickable(driver,20,driver.findElement(By.id(s)));
				//driver.findElement(By.id("SSR_RESULTS$"+j)).click();  //activity id's results
				//waitForElement(driver,10,By.id("SSR_IAMRTRSLTVW_SSR_IAM_OUTCOME$0"));
				String outcome = waitForElement(driver,10,By.id("SSR_IAMRTRSLTVW_SSR_IAM_OUTCOME$0")).getText();

				waitForElement(driver,10,By.id("SSR_IAM_ACT2DER_SSR_GOTO$0")).click();  //return on Individual Activity Manager - Activity Result  
				ProcessingImgInvisible(driver, 10, By.id("processing"));  
				System.out.println("student:" +student+ "  " + driver.findElement(By.id(s)).getText() + "=" +outcome);
				
				if(!outcome.equals("Pass")){
				 //verify the mandatory course if student fails in the course
					
					System.out.println("Student failed Course: "+ waitForElement(driver,10,By.id(s)).getText());
					waitForElement(driver,10,By.id(s)).click();
					ProcessingImgInvisible(driver, 10, By.id("processing"));
					
					if (isElementPresent(driver, By.id("SSR_ACMMAIN_INC_SSR_CW_REQUIRED$0"))){		//mandatory course??
						//System.out.println(driver.findElement(By.id("SSR_ACMMAIN_INC_SSR_CW_REQUIRED$0")).getAttribute("checked")); returns true

						if (driver.findElement(By.id("SSR_ACMMAIN_INC_SSR_CW_REQUIRED$0")).isSelected())
						{
							waitForElement(driver,10,By.id("SSR_IAM_ACT0DER_SSR_GOTO")).click();
							ProcessingImgInvisible(driver, 10, By.id("processing"));
							System.out.println("Course "+ driver.findElement(By.id(s)).getText() +" is mandatory and Failed.. Mandatory course rule is failed");
							driver.switchTo().defaultContent();
							return verifyMandatoryCourse_outcome = false;
						}
						else {
							waitForElement(driver,10,By.id("SSR_IAM_ACT0DER_SSR_GOTO")).click();
							ProcessingImgInvisible(driver, 10, By.id("processing"));
							System.out.println("Course "+ driver.findElement(By.id(s)).getText() +" is not mandatory");
						}
					}
					else {
						waitForElement(driver,10,By.id("SSR_IAM_ACT0DER_SSR_GOTO")).click();
						ProcessingImgInvisible(driver, 10, By.id("processing"));
						System.out.println("Course "+ driver.findElement(By.id(s)).getText() +" does not have associated mandatory checkbox on page");
					}
				}
				
				if(j == lweActivityID.size()-1) {
					waitForElement(driver,10,By.id("#ICList")).click();
					ProcessingImgInvisible(driver, 10, By.id("processing"));
					//System.out.println(driver.findElement(By.id("#ICList")).getAttribute("value") + " Clicked...");
				}
				
				//System.out.println(j + " round complete");
				j++;
				
			}
	    	
	    i++;  
	    }  

		driver.switchTo().defaultContent();
		return verifyMandatoryCourse_outcome;
	}
	
	public int getTableHeaderIndex(String str) { //todo move this to base class
		 int i = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
		 return i;
	}

	public int getElementIndex(String str) { //todo move this to base class
		 int countCourseRootActivityID = Integer.parseInt(str.substring(str.indexOf("$")+1).trim());
		 return countCourseRootActivityID;
	}
	
	public boolean VerifyResitCandidateForFailedCourses(boolean isFinalYearStudent, List<String> lFailedCourses) {
		boolean rule13PassFlag = false;
		
		System.out.println("Verifying Failed courses for rule 13...");

		if(isFinalYearStudent) {
			System.out.println("Student is a final year student...");
		}

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		for(String failedCourse:lFailedCourses) {
			System.out.println("Failed course: " + failedCourse);
			
			WebElement CourseRootActivityID = waitForElement(driver,10,By.xpath("//a[text()[contains(.,'" +failedCourse+ "')]]"));
			CourseRootActivityID.click();
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			waitForElement(driver,10,By.xpath("//*[text()[contains(.,'Most Recent Result')]]")).click();
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			//waitForElement(driver,10,By.xpath("//*[text()[contains(.,'Most Recent Result')]]/parent::*")).click();
			
			List<WebElement> lweActivityIDs = driver.findElements(By.xpath("//a[text()[contains(.,'ACT')]]"));
			List<String> lActivityIDs = new ArrayList<String>();
			
			for (WebElement we : lweActivityIDs) {
				lActivityIDs.add(we.getAttribute("id"));
			}
			
			for (String activityId : lActivityIDs) {

				String courseActID = waitForElement(driver,10,By.id(activityId)).getText();
				
				int i = getElementIndex(activityId);
	    		//System.out.println(activityId + "  index " +i );
	    		//System.out.println("SSR_IAM_RSL2DER_SSR_IAM_OUTCOME$"+i);
	    		
				String outcome = waitForElement(driver,10,By.id("SSR_IAM_RSL2DER_SSR_IAM_OUTCOME$"+i)).getText();
	    		//System.out.println("Activity outcome " + outcome);	    		
	    		//String activityStatus = waitForElement(driver,10,By.id("SSR_IAM_ROOTVW_SSR_IAM_ACT_STATUS$"+i)).getText();
//	    		Dimension dim = waitForElement(driver,10,By.id("SSR_IAM_ROOTVW_SSR_IAM_ACT_STATUS$"+i)).getSize();
//	    		System.out.println("Size is :" + dim+" hight" + dim.height + " width:" + dim.width);

	    		String activityStatusClass = waitForElement(driver,10,By.id("SSR_IAM_ROOTVW_SSR_IAM_ACT_STATUS$"+i)).getAttribute("class");
//	    		System.out.println("Class is :" + elemntClass);

	    		String activityStatus;
	    		if(activityStatusClass.equals("PSDROPDOWNLIST_DISPONLY")) {
		    		activityStatus = waitForElement(driver,10,By.id("SSR_IAM_ROOTVW_SSR_IAM_ACT_STATUS$"+i)).getText();
		    		//System.out.println("activityStatus " + activityStatus);
	    			
	    		}
	    		else {
	    			
	    			Select s = new Select(waitForElement(driver,10,By.id("SSR_IAM_ROOTVW_SSR_IAM_ACT_STATUS$"+i)));
	    			activityStatus = s.getFirstSelectedOption().getText();
		    		//System.out.println("activityStatus " + activityStatus);
	    		}
	    		
//	    		if(isFinalYearStudent && i==0 && outcome.equals("Fail")) {
//	    			System.out.println("Student is Final year and should be allowed to resit....");
//	    			rule13PassFlag = true;
//	    			//break;  //final year student still can have resit
//	    		}
	    		
	    		if(i==0 && outcome.equals("Pass")) { //Course should not be fail while its component can be either pass or fail
	    			System.out.println("Data Error: Activity should have outcome as Fail for Course: " + courseActID);
	    			rule13PassFlag = false;
	    			break;
	    		}
	    		else {

	    			if(outcome.equals("Pass")) {
	    				System.out.println("Ignoring failed course's activity courseActivityId:" +courseActID+" :"+ outcome);
	    				rule13PassFlag = true;
	    			}
	    			else {
		    			if(activityStatus.equals("Enrolled") || activityStatus.equals("Resit Candidate")) {
		    				rule13PassFlag = true;
		    			}
		    			else {
		    				System.out.println("Failed for courseActID: "+courseActID + "  activityStatus:"+ activityStatus);
		    				rule13PassFlag = false;
		    			}
	    			}
	    		}
	    		//System.out.println("rule13PassFlag: " +rule13PassFlag );
			} //end of for lweActivityIDs 
    		waitForElement(driver,10,By.id("#ICList")).click();
    		ProcessingImgInvisible(driver, 10, By.id("processing"));
		} //end of for failedCourse
		driver.switchTo().defaultContent();
		return rule13PassFlag;
	}
}
