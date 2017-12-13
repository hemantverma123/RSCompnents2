package admissions.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import admissions.UtilityMethods;

public class StudentDataStaffMyNottingham {

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();

	By correctHistoryBtnEL = By.id("#ICCorrection");
	By nationalIDTxtBxEL = By.id("SCC_PERS_NID_H_NID_SPECIAL_CHAR$0");
	By saveBtnEL = By.id("#ICSave");
	By academicShiftEL = By.id("ADM_APPL_PROG_SSR_SHIFT$0");
	By applicationProgDataEL = By.name("#ICPanel3");
	By candidatureDetailsEL = By.name("#ICPanel10");
	By bioDetailsEL = By.name("#ICPanel0");

	public StudentDataStaffMyNottingham(WebDriver driver) {
		this.driver = driver;

		this.driver.switchTo().defaultContent();
		this.driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

	}

	public StudentDataStaffMyNottingham clickCorrectHistoryBtn() {

		WebElement CorrectHistoryBtn = driver.findElement(correctHistoryBtnEL);
		CorrectHistoryBtn.click();
		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public StudentDataStaffMyNottingham nationalIDTxtBx() {

		WebElement nationalID = driver.findElement(nationalIDTxtBxEL);
		nationalID.clear();
		nationalID.sendKeys("aa999999");
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public StudentDataStaffMyNottingham clickSaveBtn() {
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

		WebElement saveBtn = driver.findElement(saveBtnEL);
		saveBtn.click();
		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}

	public StudentDataStaffMyNottingham academicShiftSelect() {

		Select select = new Select(driver.findElement(academicShiftEL));
		select.selectByValue("SEPTEMBER");

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public StudentDataStaffMyNottingham applicationProgramDataLnk() {

		WebElement AppProgData = driver.findElement(applicationProgDataEL);
		AppProgData.click();

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public StudentDataStaffMyNottingham candidatureDetailsLnk() {

		WebElement candidature = driver.findElement(candidatureDetailsEL);
		candidature.click();

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public StudentDataStaffMyNottingham bioDetailsLnk() {

		WebElement bioDetails = driver.findElement(bioDetailsEL);
		bioDetails.click();

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public StudentDataStaffMyNottingham programActionBx(String action) {
		

		WebElement progActionMagni = driver.findElement(By.id("ADM_APPL_PROG_PROG_ACTION$prompt$img$0"));
		progActionMagni.click();
		UtilMeth.PauseForJS(driver, 60, true);
		

		driver.switchTo().defaultContent();
		List<WebElement> frame = driver.findElements(By.cssSelector("[id^='ptModFrame']"));
		System.out.println("Size of Frame Modals --->>>>"+frame.size()+" <<<<----");
		driver.switchTo().frame(driver.findElement(By.cssSelector("[id^='ptModFrame']")));
		
		switch (action) {
		case "admit":
			WebElement admit = driver.findElement(By.id("RESULT4$0"));
			admit.click();

			break;
		case "deny":
			WebElement deny = driver.findElement(By.id("RESULT4$8"));
			deny.click();

			break;
		case "conditional":
			WebElement cond = driver.findElement(By.id("RESULT4$3"));
			cond.click();

			break;
		case "matriculation":
			WebElement matric = driver.findElement(By.id("RESULT4$9"));
			matric.click();

			break;
		default:
			break;
		}
		
		UtilMeth.PauseForJS(driver, 60, true);


		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));


		return this;
	}
	
	public StudentDataStaffMyNottingham actionReasonBx(String programAction, String actionReason) {
		
		WebElement actReasonMagni = driver.findElement(By.id("ADM_APPL_PROG_PROG_REASON$prompt$img$0"));
		actReasonMagni.click();
		
		UtilMeth.PauseForJS(driver, 60, true);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.cssSelector("[id^='ptModFrame']")));
		
		if (programAction.equals("admit")) {
			switch (actionReason) {
			case "unconditional":
				WebElement reasonUnc = driver.findElement(By.id("RESULT5$19"));
				reasonUnc.click();
				break;
			case "(AD) Unconditional":
				WebElement reasonADUnc = driver.findElement(By.id("RESULT5$4"));
				reasonADUnc.click();
				break;

			default:
				break;
			}
			
		}
		
		if (programAction.equals("deny")) {
			switch (actionReason) {
			case "rejection":
				WebElement reason = driver.findElement(By.id("RESULT5$16"));
				reason.click();
				
				break;

			default:
				break;
			}
			
		}
		
		if (programAction.equals("conditional")) {
			switch (actionReason) {
			case "conditional":
				WebElement reason = driver.findElement(By.id("RESULT5$2"));
				reason.click();
				
				break;

			default:
				break;
			}
			
		}
		
		if (programAction.equals("matriculation")) {
			//Only One Option so no need for switch statement 
			WebElement action = driver.findElement(By.id("RESULT5$0"));
			action.click();
			
		}
		
//		WebElement reason = driver.findElement(By.id("RESULT5$19"));
//		reason.click();
		
		UtilMeth.PauseForJS(driver, 60, true);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));		
		return this;
	}
	
	public StudentDataStaffMyNottingham statusBx(String status) {
		
		WebElement statusMagni = driver.findElement(By.id("SSR_RS_CONSMPTN_SSR_RS_STATUS$prompt$img$0"));
		statusMagni.click();
		
		UtilMeth.PauseForJS(driver, 60, true);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.cssSelector("[id^='ptModFrame']")));
		
		switch (status) {
		case "Completed":
			WebElement completed = driver.findElement(By.id("RESULT5$3"));
			completed.click();
			
			break;

		default:
			break;
		}
			
		UtilMeth.PauseForJS(driver, 60, true);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		
		
		return this;
	}
	

}
