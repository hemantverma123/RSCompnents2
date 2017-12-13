package cs.curriculum.management.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import cs.peam.pages.PS_HomePage;

import java.util.Iterator;

import org.apache.commons.lang3.time.StopWatch;

import uiAutomation.testBase.TestBase;

/**
 * 
 * @author Hemant Verma
 *
 */

public class StudentSupport extends TestBase {

//	@FindBy(id="UN_PRG_CAL_SRCH_EMPLID")
//	WebElement EmplID;
//	@FindBy(id="#ICSearch")
//	WebElement btn_Search;
//
//	@FindBy(xpath="//*[text()[contains(.,'Student Program Details')]]")
//	WebElement StudentCalculationResultPage;

	
	WebDriver driver;
	PS_HomePage homepage;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(StudentSupport.class.getName());
	
		
	public StudentSupport(WebDriver driver) {
		this.driver = driver;
		homepage = new PS_HomePage(driver);
		log.info("StudentSupport cosntructor called");		
	}


	public String getExtenuatingCircumstancesCase(){
		String casenum;
		
		waitForElement(driver,10,By.id("uonhomeText")).click();
		waitForPageLoad(driver, timeOutInSeconds);
		waitForElement(driver,10,By.xpath("//img[@alt='Jump to support section']")).click();
		waitForPageLoad(driver, timeOutInSeconds);
		pause(3);
		this.waitForElement(driver, timeOutInSeconds, By.id("UN_SUPPORT_1")).click();

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		casenum = this.waitForElement(driver, timeOutInSeconds, By.id("EOTL_SS_HDR_TITLE#EOTL_SS_TITLE")).getText();
		
		driver.switchTo().defaultContent();
		return casenum;
	}
	
	public void CreateExtenuatingCircumstancesCase(String summary) {
		
		waitForElement(driver,10,By.id("uonhomeText")).click();
		waitForPageLoad(driver, timeOutInSeconds);
		waitForElement(driver,10,By.xpath("//img[@alt='Jump to support section']")).click();
		waitForPageLoad(driver, timeOutInSeconds);
		pause(3);
		System.out.println("click1");
		waitForElement(driver,10,By.id("UN_LIST1_1")).click();
		
		WebElement elementExtenuating = waitForElement(driver,10,By.xpath("//*[text()[contains(.,'Extenuating Circumstances (FOB)')]]"));
		
		String extenuatingID = elementExtenuating.getAttribute("id");  //UN_CASE_LIST1_16.00
		int k = Integer.parseInt(extenuatingID.substring(extenuatingID.indexOf(".")-2,extenuatingID.indexOf(".")).trim());
		
		Actions action = new Actions(driver);
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('two')");		
		
		System.out.println("before loop");
		//String supportid;
		for(int i=1;i<k+5;i++) {  //there are 7 elements with different element id
		System.out.println("Clicking down arrow keys: "+i);
//			supportid = "UN_LIST1_"+i;
//			waitForElement(driver,10,By.id(supportid)).click();
//			String supporttype = waitForElement(driver,10,By.id("UN_LIST1_"+i)).getText();
//			
//			if(supporttype.trim().equals("Extenuating Circumstances (FOB)")) {
//				System.out.println("Extenuating Circumstances (FOB) - found for element: "+UN_LIST1_"+i);
//				break;
//			}
			
			action.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		//Extenuating Circumstances (FOB)
		action.moveToElement(waitForElement(driver,10,By.id(extenuatingID))).click().build().perform();
		//action.moveToElement(waitForElement(driver,10,By.id("UN_CASE_LIST1_16.00"))).click().build().perform();
		
		waitForElement(driver,10,By.id(extenuatingID)).click();  //.//*[@id='UN_IH_SRVC_CAT_REQ_LIST1']
		pause(2);
		waitForElement(driver,10,By.id("UN_CASE_LIST2_1.00")).click();
		waitForElement(driver,10,By.id("UN_IH_CONTINUE")).click();
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,By.xpath("//*[text()[contains(.,'tachments')]]/parent::*[@role='tab']/span")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver,10,By.id("FORM_FORM_SUBJECT")).sendKeys("AttachementSummary");
		waitForElement(driver,10,By.id("FORM_WRK_EOFM_ATTACH_PB$0")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		Iterator<String> windows;
		
		getFrames(driver);
		
		String parentWindowHandler = driver.getWindowHandle();
		windows = getAllWindows(driver);
		
		String subWindowHandler = null;
		while (windows.hasNext()){	//13=parent, 20=uploadwindow
		    subWindowHandler = windows.next();
		    driver.switchTo().window(subWindowHandler);
		    System.out.println("subWindowHandler: " + subWindowHandler + " Window Title: "+ driver.getTitle() + " Window current URL: " + driver.getCurrentUrl());// + " Window page source: " + driver.getPageSource());
		}
		System.out.println("Finally - subWindowHandler: " + subWindowHandler + " Window Title: "+ driver.getTitle() + " Window current URL: " + driver.getCurrentUrl());// + " Window page source: " + driver.getPageSource());
		
//		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
//		waitForElement(driver,10,By.id("UN_CASETYPE_WRK_ATTACHADD$0")).click();
		
		driver.switchTo().defaultContent();
		waitAndSwitchtoframe(driver,10,By.id("ptModFrame_0"));
		
		waitForElement(driver,10,By.name("#ICOrigFileName")).clear();
		waitForElement(driver,10,By.name("#ICOrigFileName")).sendKeys("C:\\Users\\brahv\\Documents\\testuploadDoc.docx");
		
		pause(1);
		waitForElement(driver,10,By.name("Upload")).click();
		pause(2);

		driver.switchTo().defaultContent();
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,By.xpath("//*[text()[contains(.,'Affected Courses')]]/parent::*[@role='tab']/span")).click();
		
//		SelectElement(driver,By.id("UN_STUD_CRSE_VW_UN_ASSESSMENT_TYPE$0"),"Course Work");
//		SelectDate(driver,By.id("#UN_STUD_CRSE_VW_ACTIVITY_DATE$prompt$img$0"));

		waitForElement(driver,10,By.xpath("//*[text()[contains(.,'orm')]]/parent::*[@role='tab']/span")).click();
		this.waitForElement(driver, timeOutInSeconds, By.id("FORM_FORM_SUBJECT")).sendKeys(summary);;
		this.waitForElement(driver, timeOutInSeconds, By.id("UN_CASETYPE_WRK_BUTTON1")).click();

		driver.switchTo().defaultContent();
		String msg =  this.waitForElement(driver, timeOutInSeconds, By.xpath(".//*[@id='alertmsg']/span")).getText();
		System.out.println(msg);
		waitForElement(driver, timeOutInSeconds, By.id("#ICOK")).click();
		
		//Defect error handling
		if(this.isElementPresent(driver, By.id("#ICOK"))) {
		waitForElement(driver, timeOutInSeconds, By.id("#ICOK")).click();
		}

		if(this.isElementPresent(driver, By.id("#ICOK"))) {
		waitForElement(driver, timeOutInSeconds, By.id("#ICOK")).click();
		}
		
		driver.switchTo().defaultContent();
	}
	
	public String CreateDisabilitySupportCase(String student, String summary, String details){		
		//getFrames(driver);
		
		waitForElement(driver,10,By.id("uonhomeText")).click();
		
		waitForPageLoad(driver, timeOutInSeconds);
		waitForElement(driver,10,By.xpath("//img[@alt='Jump to support section']")).click();
		waitForPageLoad(driver, timeOutInSeconds);
		
		pause(3);
		
		waitForElement(driver,10,By.id("UN_CASE_LIST1_1.00")).click();
		
		Actions action = new Actions(driver);
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('two')");		
		
		for(int i=1;i<8;i++) {
		//System.out.println("Clicking down arrow keys: "+i);
		action.sendKeys(Keys.ARROW_DOWN).build().perform();
		}
		
		action.moveToElement(waitForElement(driver,10,By.id("UN_CASE_LIST1_10.00"))).click().build().perform();
		
		waitForElement(driver,10,By.id("UN_CASE_LIST1_10.00")).click();  //.//*[@id='UN_IH_SRVC_CAT_REQ_LIST1']
		waitForElement(driver,10,By.id("UN_CASE_LIST2_1.00")).click();
		waitForElement(driver,10,By.id("UN_CASE_SMUMMARY")).clear();
		waitForElement(driver,10,By.id("UN_CASE_SMUMMARY")).sendKeys(summary);
		waitForElement(driver,10,By.id("UN_SRVC_REQ_COMMENTS")).clear();
		waitForElement(driver,10,By.id("UN_SRVC_REQ_COMMENTS")).sendKeys(details);
		waitForElement(driver,10,By.id("addAttachment")).click();
		
		//TODO Scenario - Handle scenario where attachement opens a new window and new window gets closed on pressing done button. To handle this I used javascript as webdriver session becomes invalid on clicking done button.
		
		Iterator<String> windows;
		
		getFrames(driver);
		
		String parentWindowHandler = driver.getWindowHandle();
		windows = getAllWindows(driver);
		
		String subWindowHandler = null;
		while (windows.hasNext()){	//13=parent, 20=uploadwindow
		    subWindowHandler = windows.next();
		    driver.switchTo().window(subWindowHandler);
		    System.out.println("subWindowHandler: " + subWindowHandler + " Window Title: "+ driver.getTitle() + " Window current URL: " + driver.getCurrentUrl());// + " Window page source: " + driver.getPageSource());
		}
		System.out.println("Finally - subWindowHandler: " + subWindowHandler + " Window Title: "+ driver.getTitle() + " Window current URL: " + driver.getCurrentUrl());// + " Window page source: " + driver.getPageSource());
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,By.id("UN_CASETYPE_WRK_ATTACHADD$0")).click();
		
		driver.switchTo().defaultContent();
		waitAndSwitchtoframe(driver,10,By.id("ptModFrame_0"));
		
		waitForElement(driver,10,By.name("#ICOrigFileName")).clear();
		waitForElement(driver,10,By.name("#ICOrigFileName")).sendKeys("C:\\Users\\brahv\\Documents\\testuploadDoc.docx");
		
		pause(1);
		waitForElement(driver,10,By.name("Upload")).click();
		pause(2);

		driver.switchTo().defaultContent();
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		try {
			//driver.close();
			//waitForElement(driver,10,By.id("UN_CASETYPE_WRK_DONE")).click();
			
			JavascriptExecutor js = (JavascriptExecutor) driver;

			//js.executeScript("arguments[0].style.border='4px solid yellow'", element);
			pause(3);
			js.executeScript("document.getElementById('UN_CASETYPE_WRK_DONE').click()");

			driver.switchTo().window(parentWindowHandler);
		}catch(Exception e) {
			System.out.println("Switching to Parent window...exception");
			driver.switchTo().window(parentWindowHandler);
			e.printStackTrace();
		}
		
		pause(1);
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]

		//driver.switchTo().window(parentWindowHandler);
		System.out.println("Last Window Title: "+ driver.getTitle());
		getFrames(driver);
		
		waitForElement(driver,10,By.id("saveSrvcReq")).click();
		
		String uploadSuccess = waitForElement(driver,10,By.id("UN_IH_SRVC_REQ_STATUS")).getText();
		Assert.assertEquals(uploadSuccess,"Support request saved successfully.");		
		
		waitForElement(driver,10,By.id("UN_SUPPORT_1")).click();   //click the case number
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		String caseNumber = waitForElement(driver,10,By.id("EOTL_SS_HDR_TITLE#EOTL_SS_TITLE")).getText();
		System.out.println("Student Support created with Case Number: " + caseNumber);
		
		driver.switchTo().defaultContent();
		return caseNumber;
		
	}
	
}
