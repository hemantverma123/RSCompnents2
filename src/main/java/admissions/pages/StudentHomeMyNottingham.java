package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class StudentHomeMyNottingham extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	UtilityMethods UtilMeth = new UtilityMethods();

	By applyNowButtonEL = By.id("app_lnk");
	By acceptRowEL = By.id("UN_IH_APP_STAT");
	By acceptBtnEL = By.className("btn");
	By acceptLnkEL = By.id("DERIVED_SSP_DSP_SS_ADM_ACC_DEC_PB$0");
	By acceptAdmEL = By.id("DERIVED_SSP_DSP_SS_ADM_ACCEPT_PB");
	By confirmAccEL = By.id("DERIVED_SSP_DSP_SS_ADM_CON_ACC_PB");
	
	public StudentHomeMyNottingham(WebDriver driver,StudentJsonHandler jsHandler) {
		this.driver = driver;
		this.jsHandler = jsHandler;
		
	}
	
	
	public StudentHomeMyNottingham clickApplyNowBtn() {
		//driver.findElement(applyNowButtonEL).click();
		waitForElement(driver, timeOutInSeconds, applyNowButtonEL).click();
		UtilMeth.PauseForJS(driver, 1.1f, false);
		return this;

	}
	
	public StudentHomeMyNottingham clickAcceptBtn() {
		
		WebElement acceptRow = driver.findElement(acceptRowEL);
		WebElement acceptBtn = acceptRow.findElement(acceptBtnEL);
		acceptBtn.click();
		
		return this;
	}
	
	public StudentHomeMyNottingham clickAcceptDeclineAdmissionLnk() {
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		
		//UtilMeth.PauseForJS(driver, 5, true);
		WebElement acceptLnk = driver.findElement(acceptLnkEL);
		acceptLnk.click();
		UtilMeth.PauseForJS(driver, 5, true);
		
		
		return this;
	}
	
	public StudentHomeMyNottingham iAcceptAdmissionBtn() {
		
		WebElement acceptAdmissionBtn = driver.findElement(acceptAdmEL);
		acceptAdmissionBtn.click();
		UtilMeth.PauseForJS(driver, 1.1f, false);
		
		return this;
	}
	
public StudentHomeMyNottingham confirmAcceptBtn() {
		
		WebElement confirmAcceptBtn = driver.findElement(confirmAccEL);
		confirmAcceptBtn.click();
		UtilMeth.PauseForJS(driver, 5, true);
		
		return this;
	}
	
	
	
	

}
