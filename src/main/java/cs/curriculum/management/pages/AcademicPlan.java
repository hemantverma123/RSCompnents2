package cs.curriculum.management.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import cs.peam.pages.PS_HomePage;

import org.apache.commons.lang3.time.StopWatch;

import uiAutomation.testBase.TestBase;

/**
 * 
 * @author Hemant Verma
 *
 */

public class AcademicPlan extends TestBase {

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
	
	private final static Logger log = Logger.getLogger(AcademicPlan.class.getName());
	
		
	public AcademicPlan(WebDriver driver) {
		this.driver = driver;
		homepage = new PS_HomePage(driver);
		log.info("AcademicPlan cosntructor called");		
	}


	public void OpenCurriculumManagementCentre()
	{
		homepage.OpenCurriculumManagementCentre();
	}

	
	public void SetAcademicPlan(String AcademicPlan, String AcademicProgram, String AcademicCareer, String AcademicPlanType, String Description, String ShortDescription, String TranscriptLevel, String DiplomaDescription, String TranscriptDescription) throws InterruptedException{		
		//getFrames(driver);
		waitAndSwitchtoframe(driver,10,By.id("ptalPgltAreaFrame"));
		waitForElement(driver, timeOutInSeconds, By.xpath("//*[text()[contains(.,'Academic Plan Table')]]")).click();
		driver.switchTo().defaultContent();

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver, timeOutInSeconds,By.xpath(".//*[@id='ICTAB_1']/span")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));

		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PLAN_TBL_ACAD_PLAN")).sendKeys(AcademicPlan);
		waitForElement(driver, timeOutInSeconds,By.id("#ICSearch")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));

		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PLAN_TBL_ACAD_PROG$0")).sendKeys(AcademicProgram);
		new Select(waitForElement(driver, timeOutInSeconds,By.id("ACAD_PLAN_TBL_ACAD_PLAN_TYPE$0"))).selectByVisibleText(AcademicPlanType);
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PLAN_TBL_DESCR$0")).sendKeys(Description);
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PLAN_TBL_DESCRSHORT$0")).sendKeys(ShortDescription);

		waitForElement(driver, timeOutInSeconds,By.xpath("//*[@id='ICTAB_1']/span")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PLAN_TBL_DIPLOMA_DESCR$0")).sendKeys(DiplomaDescription);
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PLAN_TBL_TRNSCR_DESCR$0")).sendKeys(TranscriptDescription);
		waitForElement(driver, timeOutInSeconds,By.id("#ICSave")).click();

		System.out.println("Plan Table created...");
		driver.switchTo().defaultContent();
	}
	
}
