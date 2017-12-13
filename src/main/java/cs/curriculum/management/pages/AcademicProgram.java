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

public class AcademicProgram extends TestBase {

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
	
	private final static Logger log = Logger.getLogger(AcademicProgram.class.getName());
	
		
	public AcademicProgram(WebDriver driver) {
		this.driver = driver;
//		PageFactory.initElements(driver, this);
		homepage = new PS_HomePage(driver);
		log.info("AcademicProgram cosntructor called");		
	}


	public void OpenCurriculumManagementCentre()
	{
		homepage.OpenCurriculumManagementCentre();
	}

	
	public void SetAcademicProgram(String  AcademicProgram, String  Description, String  ShortDescription, String  AcademicCareer, String  GradingScheme, String  GBDefaultforTransferCredit, String  AcademicGroup, String  AcademicLevelRule, String  AcademicCalendar, String  TranscriptLevel) throws InterruptedException{		
		getFrames(driver);
		waitAndSwitchtoframe(driver,10,By.id("ptalPgltAreaFrame"));
		//waitAndSwitchtoframe(driver,10,By.xpath("//*[text()[contains(.,'ptalPgltAreaFrame')]]"));
		waitForElement(driver, timeOutInSeconds, By.xpath("//*[text()[contains(.,'Academic Program Table')]]")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		driver.switchTo().defaultContent();
		//Thread.sleep(2000);
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
		//waitForElement(driver, timeOutInSeconds,By.cssSelector("#ICTAB_1 > span")).click();
		//waitForElement(driver, timeOutInSeconds, By.xpath("//*[text()[contains(.,'dd a New Value')]]/parent::*[@role='tab']")).click();
		waitForElement(driver, timeOutInSeconds,By.xpath(".//*[@id='ICTAB_1']/span")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_PROG")).clear();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_PROG")).sendKeys(AcademicProgram);
		ProcessingImgInvisible(driver, 10, By.id("processing"));
//		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_INSTITUTION$prompt$img")).click();
//		ProcessingImgInvisible(driver, 10, By.id("processing"));
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_0 | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("SEARCH_RESULT1")).click();
//		ProcessingImgInvisible(driver, 10, By.id("processing"));
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
		waitForElement(driver, timeOutInSeconds,By.id("#ICSearch")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_DESCR$0")).sendKeys(Description);
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_DESCRSHORT$0")).sendKeys(ShortDescription);
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CAREER$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CAREER$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CAREER$0")).sendKeys(AcademicCareer);
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_GRADE_TRANSFER$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_GROUP$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_GROUP$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_GROUP$0")).sendKeys(AcademicGroup);
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_LEVEL_LOAD_RULE$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_LEVEL_LOAD_RULE$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_LEVEL_LOAD_RULE$0")).sendKeys(AcademicLevelRule);
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CALENDAR_ID$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CALENDAR_ID$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CALENDAR_ID$0")).sendKeys(AcademicCalendar);
		//waitForElement(driver, timeOutInSeconds,By.id("win0divACAD_PLAN_TBL_DESCR$0")).click();

//		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CAREER$prompt$img$0")).click();
//		ProcessingImgInvisible(driver, 10, By.id("processing"));
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_1 | ]]
//
//		driver.switchTo().defaultContent();
//		waitAndSwitchtoframe(driver,10,By.id("ptModFrame_0"));
//		waitForElement(driver, timeOutInSeconds,By.id("SEARCH_RESULT1")).click();
//		ProcessingImgInvisible(driver, 10, By.id("processing"));
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
//		
//		driver.switchTo().defaultContent();
//		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
//
//		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_GROUP$prompt$img$0")).click();
//		ProcessingImgInvisible(driver, 10, By.id("processing"));
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_2 | ]]
//
//		driver.switchTo().defaultContent();
//		waitAndSwitchtoframe(driver,10,By.id("ptModFrame_1"));
//		
//		waitForElement(driver, timeOutInSeconds,By.id("SEARCH_RESULT1")).click();
//		ProcessingImgInvisible(driver, 10, By.id("processing"));
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
//		
//
//		driver.switchTo().defaultContent();
//		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
//
//		System.out.println("bhai aa gya");
//		
//		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_LEVEL_LOAD_RULE$prompt$img$0")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_3 | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("SEARCH_RESULT1")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_CALENDAR_ID$prompt$img$0")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_4 | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("SEARCH_RESULT1")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
		

//		waitForElement(driver, timeOutInSeconds,By.id("#ICSave")).click();
//		waitForElement(driver, timeOutInSeconds,By.cssSelector("#ICTAB_1 > span")).click();
//		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_ACAD_STDNG_RULE$12$$prompt$img$0")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_5 | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("#ICSearch")).click();
//		waitForElement(driver, timeOutInSeconds,By.id("#ICSearch")).click();
//		waitForElement(driver, timeOutInSeconds,By.id("#ICCancel")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]

		waitForElement(driver, timeOutInSeconds,By.xpath(".//*[@id='ICTAB_2']/span")).click();
		//waitForElement(driver, timeOutInSeconds,By.cssSelector("#ICTAB_2 > span")).click();
		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_CAMPUS$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_CAMPUS$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_CAMPUS$0")).sendKeys("U");
		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_BUSINESS_UNIT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_BUSINESS_UNIT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_BUSINESS_UNIT$0")).sendKeys("UNUK1");
		//waitForElement(driver, timeOutInSeconds,By.id("win0divCAMPUS_TBL_DESCR$28$$0")).click();

//		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_CAMPUS$prompt$img$0")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | null | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("pt_modalMask")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectFrame | modWin_6 | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("SEARCH_RESULTLAST")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("HOME_CAMPUS_TBL_BUSINESS_UNIT$prompt$img$0")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_7 | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("SEARCH_RESULT1")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]

		
//		waitForElement(driver, timeOutInSeconds,By.cssSelector("#ICTAB_3 > span")).click();
//		waitForElement(driver, timeOutInSeconds,By.id("ACAD_PROG_TBL_REPEAT_RULE$prompt$img$0")).click();
//		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=modWin_8 | ]]
//		waitForElement(driver, timeOutInSeconds,By.id("#ICCancel")).click();
		// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=TargetContent | ]]

		waitForElement(driver, timeOutInSeconds,By.xpath(".//*[@id='ICTAB_4']/span")).click();
		new Select(waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_TERM_CATEGORY$0"))).selectByVisibleText("Regular");
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_FT_MAX_TOTAL_UNIT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_FT_MAX_TOTAL_UNIT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_FT_MAX_TOTAL_UNIT$0")).sendKeys("100");
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_PT_MAX_TOTAL_UNIT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_PT_MAX_TOTAL_UNIT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_PT_MAX_TOTAL_UNIT$0")).sendKeys("80");
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_FT_PROJ_BILL_UNT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_FT_PROJ_BILL_UNT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_FT_PROJ_BILL_UNT$0")).sendKeys("20");
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_PT_PROJ_BILL_UNT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_PT_PROJ_BILL_UNT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("ENRL_LIMITS_TBL_PT_PROJ_BILL_UNT$0")).sendKeys("20");
		new Select(waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_SESSION_CODE$0"))).selectByVisibleText("April Mala");
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_FT_MAX_TOTAL_UNIT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_FT_MAX_TOTAL_UNIT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_FT_MAX_TOTAL_UNIT$0")).sendKeys("100");
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_PT_MAX_TOTAL_UNIT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_PT_MAX_TOTAL_UNIT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_PT_MAX_TOTAL_UNIT$0")).sendKeys("100");
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_FT_MAX_WAIT_UNIT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_FT_MAX_WAIT_UNIT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_FT_MAX_WAIT_UNIT$0")).sendKeys("10");
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_PT_MAX_WAIT_UNIT$0")).click();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_PT_MAX_WAIT_UNIT$0")).clear();
		waitForElement(driver, timeOutInSeconds,By.id("SESSN_ENRLIMTBL_PT_MAX_WAIT_UNIT$0")).sendKeys("10");
		waitForElement(driver, timeOutInSeconds,By.cssSelector("img[alt=\"Show following tabs\"]")).click();
		waitForElement(driver, timeOutInSeconds,By.cssSelector("img[alt=\"Show following tabs\"]")).click();
		waitForElement(driver, timeOutInSeconds,By.cssSelector("#ICTAB_14 > span")).click();
		waitForElement(driver, timeOutInSeconds,By.id("#ICSave")).click();
		
		System.out.println("FINALLY DONE");
		driver.switchTo().defaultContent();
	}
	
}
