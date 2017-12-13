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

import cs.peam.pages.PS_Login;

import org.apache.commons.lang3.time.StopWatch;

import uiAutomation.testBase.TestBase;

/**
 * 
 * @author Hemant Verma
 *
 */

public class PS_HomePage extends TestBase {
	
	
	WebDriver driver;
	PS_Login loginpage;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(PS_HomePage.class.getName());


	@FindBy(id="pthnavbca_PORTAL_ROOT_OBJECT")
	WebElement main_mnu;
	@FindBy(xpath="//*[text()[contains(.,'Campus Solutions')]][@class='ptntop']")
	WebElement m_CampusSol;
	//@FindBy(id="fldra_HCSR_RECORDS_AND_REGISTRATION")
	@FindBy(xpath="//*[text()[contains(.,'Records and Enrolment')]]/parent::*[@portal='EMPLOYEE']/a")
	WebElement m_RecEnrol;
	
	//IAM elements	
	//@FindBy(id="fldra_HC_SSR_IAM_FOLDER_GBL")
	@FindBy(xpath="//*[text()[contains(.,'Individual Activity Manager')]]/parent::*[@portal='EMPLOYEE']/a")
	WebElement mnu_iam;
	//@FindBy(id="crefli_HC_SSR_IAM_ACT_DATA_GBL")
	
	@FindBy(xpath="//*[@folderid=\'crefli_HC_SSR_IAM_ACT_DATA_GBL']")	
	WebElement mnu_iam_page;

	//Stage Validation
	//@FindBy(id="fldra_UN_BOLTONS")  University of Nottingham
	@FindBy(xpath="//a[text()[contains(.,'University of Nottingham')]]/parent::*[@portal='EMPLOYEE']/a")
	WebElement mnu_UoN;
	@FindBy(xpath="//a[text()[contains(.,'Progression & Award')]]/parent::*[@portal='EMPLOYEE']/a")
	WebElement mnu_ProgAward;
	//@FindBy(xpath="//*[@parent=\"UN_PROGRESSION\"]/a")
	@FindBy(xpath="//li[@parent=\"UN_PROGRESSION\" and @folderid=\"UN_PROGRESSION\"]/a")
	WebElement mnu_progression;
	@FindBy(xpath="//a[text()[contains(.,'Student Calculation Results')]]/parent::*[@portal='EMPLOYEE']/a")  
	WebElement mnu_SCRpage; //student calculation results
	
	//Page Validations 
	@FindBy(id="app_label")
	WebElement homePageHeader;
	
	@FindBy(id="btn-manageDocs")
	WebElement logincheck;
	
	//Regulation 9 - APT
	@FindBy(xpath="//*[text()[contains(.,'Programme Enrolment')]]/parent::*[@portal='EMPLOYEE']/a")
	WebElement m_ProgEnrol;
	@FindBy(xpath="//*[text()[contains(.,'Academic Progress Tracker')]]/parent::*[@portal='EMPLOYEE']/a")
	WebElement m_APTpage;

	@FindBy(xpath="//*[text()[contains(.,'Student Admissions')]]")
	WebElement m_studentAdmissions;
	@FindBy(xpath="//*[text()[contains(.,'Application Maintenance')]]")
	WebElement m_ApplicationMainteance;
	@FindBy(xpath="//*[text()[contains(.,'Maintain Applications')]]")
	WebElement m_MaintainApplications;
	
		
	public PS_HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		loginpage = new PS_Login(driver);
		//loginpage.loginToApplication();
		
	}

public void login(String userid, String encryptedPassword, String runMode) {
	loginpage.loginToApplication(userid,encryptedPassword,runMode);
}

public String verifyLogin() {
	String str = loginpage.verifylogin();
	return str;
}

public void OpenIAM() 
{
	//Records and Enrolment>Individual Activity Manager>Individual Activity Manager
	
	waitForElement(driver, 30,main_mnu).click();
	waitForElement(driver, 10,m_CampusSol).click();
	waitForElement(driver, 10,m_RecEnrol).click();
	waitForElement(driver, 10,mnu_iam).click();
	waitForElement(driver, 10,mnu_iam_page).click();
	
//	timer.start();
//	Thread.sleep(500);
//	timer.stop();
//	double pageLoadTime_ms = timer.getTime();
//    double pageLoadTime_Seconds = pageLoadTime_ms / 1000;
//    System.out.println("Total Page Load Time in secs: " + pageLoadTime_Seconds + " seconds");
    
    Long loadtime = (Long)((JavascriptExecutor)driver).executeScript(
            "return performance.timing.loadEventEnd - performance.timing.navigationStart;");        
        System.out.println("Individual Activity Manager loadtime: "+loadtime);
        
}
	

	public String homePageHeaderValidation()
	{
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement(driver, 10, homePageHeader);
		String text = homePageHeader.getText();
		driver.switchTo().defaultContent();
		return text;
	}
	
	
//	public String searchStudentPageValidation()
//	{
//		driver.switchTo().frame("ptifrmtgtframe");
//		waitForElement(driver, 10, StudentCalculationResultPage);
//		String text = StudentCalculationResultPage.getText();
//		driver.switchTo().defaultContent();
//		return text;
//	}
	
	
	
	public void navigateTo(String data){
		String locator = "//a[contains(text(),'"+data+"')]";
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void mouseOver(String data) throws InterruptedException{
		String locator = "//a[contains(text(),'"+data+"')]";
		Actions select = new Actions(driver);
		select.moveToElement(driver.findElement(By.xpath(locator))).build().perform();
		Thread.sleep(1000);
	}
	
	public void clickOnItem(String data){
		String locator = "//a[contains(text(),'"+data+"')]";
		driver.findElement(By.xpath(locator)).click();
	}
	
	public void OpenStudentCalculationResults()
	{
	    //main menu>University of Nottingham>Progression & AwardProgression>Student Calculation Results
		
		waitForElement(driver, 10,main_mnu).click();
		waitForElement(driver, 10,m_CampusSol).click();
		waitForElement(driver, 10,mnu_UoN).click();
		waitForElement(driver, 10,mnu_ProgAward).click();
		waitForElement(driver, 10,mnu_progression).click();
		waitForElement(driver, 10,mnu_SCRpage).click();

		Long loadtime = (Long)((JavascriptExecutor)driver).executeScript(
	            "return performance.timing.loadEventEnd - performance.timing.navigationStart;");        
	        System.out.println("OpenStudentCalculationResults loadtime: "+loadtime);
		
	}
	
	public void OpenAPT()//*[@id='UN_PRG_RULE_VW_SCC_RULE_NAME$0']
	{

		waitForElement(driver, 10,main_mnu).click();
		waitForElement(driver, 10,m_CampusSol).click();
		waitForElement(driver, 10,m_RecEnrol).click();
		waitForElement(driver, 10,m_ProgEnrol).click();
		waitForElement(driver, 10,m_APTpage).click();
		
	}

	public void OpenAIR()  //Academic item registery
	{
		waitForElement(driver, 10,main_mnu).click();
		waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'Campus Solutions')]]")).click();
		waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'Curriculum Management')]]")).click();
		waitForElement(driver, 10,By.xpath("//a[@id[contains(.,'fldra_HCSR_ACADEMIC_ITEM_REGISTRY_PTUN')]]")).click(); 
		
		//waitForElement(driver, 10,By.xpath(".//*[@id='fldra_HCSR_ACADEMIC_ITEM_REGISTRY_PTUN_15124463700025788']")).click();
		waitForElement(driver, 10,By.xpath("//li[@id[contains(.,'crefli_HC_SSR_PE_AIR_SRCH_GBL_PTUN')]]/a")).click();
		
	}
	
	public void LogOut() {
		//waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'Sign Out')]]")).click();
		waitForElement(driver, 10,By.xpath("//*[@alt='Sign Out']")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
	}
	
	public void clickApplyCourseBtn() {
		waitForElement(driver, 10,By.id("sgnup")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
	}

	public void ApplicantLogin(String userid, String Password) {
		loginpage.ApplicantLogin(userid, Password);
	}
	
	public void OpenCurriculumManagementCentre() {
		System.out.println("opening academic program link");
		
		waitForElement(driver, 10,main_mnu).click();
		waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'Campus Solutions')]]")).click();
		waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'Curriculum Management')]]")).click();
		waitForElement(driver, 10,By.linkText("Curriculum Management Centre")).click();
		
//		driver.findElement(By.id("pthnavbca_PORTAL_ROOT_OBJECT")).click();
//		driver.findElement(By.id("fldra_PTUN_11261054800032305")).click();
//		driver.findElement(By.id("fldra_HCSR_CURRICULUM_MANAGEMENT_PTUN_11261054800032305")).click();
//		
	}
	
	public void OpenMaintainApplications() {
		System.out.println("opening application maintenance link");
		waitForElement(driver, 30,main_mnu).click();
		waitForElement(driver, 10,m_CampusSol).click();
		waitForElement(driver, 10,m_studentAdmissions).click();
		waitForElement(driver, 10,m_ApplicationMainteance).click();
		waitForElement(driver, 10,m_MaintainApplications).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
	}

}
