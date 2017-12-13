package cs.admissions;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cs.peam.pages.PS_HomePage;
import uiAutomation.testBase.TestBase;

/**
 * 
 * @author Hemant Verma
 *
 */

public class MaintainApplicationsPage extends TestBase {

	WebDriver driver;
	PS_HomePage homepage;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(MaintainApplicationsPage.class.getName());
	
		
	public MaintainApplicationsPage(WebDriver driver) {
		this.driver = driver;
		homepage = new PS_HomePage(driver);
		log.info("StudentSupport cosntructor called");		
	}


	public void OpenMaintainApplications()
	{
		homepage.OpenMaintainApplications();
	}

	
	public void SearchStudent(String studentid) {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		waitForElement(driver,10,By.id("ADM_MAINT_SCTY_ADM_APPL_NBR")).clear();
		waitForElement(driver,10,By.id("ADM_MAINT_SCTY_EMPLID")).clear();
		waitForElement(driver,10,By.id("ADM_MAINT_SCTY_EMPLID")).sendKeys(studentid);
		waitForElement(driver,10,By.id("ADM_MAINT_SCTY_INSTITUTION")).clear();
		waitForElement(driver,10,By.id("ADM_MAINT_SCTY_INSTITUTION")).sendKeys("UON");
		waitForElement(driver,10,By.id("ADM_MAINT_SCTY_ACAD_CAREER")).clear();
		waitForElement(driver,10,By.id("#ICSearch")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		driver.switchTo().defaultContent();
	}
	
	public void updateApplicationProgramData(String academicProg,String academicPlan) {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,20,By.xpath("//*[text()[contains(.,'Application Pro')]]/parent::*[@role='tab']/span")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		WebElement btncorrectHistory = waitForElement(driver,2,By.id("#ICCorrection"));
		if(btncorrectHistory!=null){
			btncorrectHistory.click();
		}
		
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver,10,By.id("ADM_APPL_PROG_ACAD_PROG$0")).clear();
		waitForElement(driver,10,By.id("ADM_APPL_PROG_ACAD_PROG$0")).sendKeys(academicProg);
		pause(3);
		waitForElement(driver,10,By.id("ADM_APPL_PLAN_ACAD_PLAN$0")).clear();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		driver.switchTo().defaultContent();
		if(this.isElementPresent(driver, By.id("#ICOK"))) {
			System.out.println("OK button was present");
			waitForElement(driver,10,By.id("#ICOK")).click();
		}

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		waitForElement(driver,10,By.id("ADM_APPL_PLAN_ACAD_PLAN$0")).sendKeys(academicPlan);
		pause(3);

		waitForElement(driver,10,By.id("#ICSave")).click();
		ProcessingImgInvisible(driver, 10, By.id("saveWait_win0"));

		driver.switchTo().defaultContent();
		if(this.isElementPresent(driver, By.id("#ICOK"))) {
			waitForElement(driver,10,By.id("#ICOK")).click();
		}
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver,10,By.id("#ICList")).click();
		driver.switchTo().defaultContent();
	}
	
}
