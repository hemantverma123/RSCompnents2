package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusWorkExperience extends TestBase{

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();

	String employer = "Acme Ltd";
	String employDateStart = "01/01/2017";
	String employDateEnd = "01/01/2017";
	String jobTitle = "Scaping the bottom Job";

	By workExperienceLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");

	By employerBxEL = By.id("CIBAA_WRK_EXP_EMPLOYER$0");
	By employDateStartBxEL = By.id("CIBAA_WRK_EXP_EMPLYR_PAID_START$0");
	By employDateEndBxEL = By.id("CIBAA_WRK_EXP_EMPLYR_PAID_END$0");
	By jobTitleBx = By.id("CIBAA_WRK_EXP_JOBTITLE$0");
	By typeWorkSelect = By.id("CIBAA_WRK_EXP_FULL_PART_TIME$0");

	By saveBtn = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");

	public FormStatusWorkExperience(WebDriver driver) {
		this.driver = driver;
	}

	public FormStatusWorkExperience typeEmployer() {

		WebElement employ = driver.findElement(employerBxEL);
		employ.sendKeys(employer);

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}

	public FormStatusWorkExperience typeJobStartDate() {

		WebElement start = driver.findElement(employDateStartBxEL);
		start.sendKeys(employDateStart);

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public FormStatusWorkExperience typeJobEndDate() {

		WebElement end = driver.findElement(employDateEndBxEL);
		end.sendKeys(employDateEnd);

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public FormStatusWorkExperience selectTypeWork() {

		Select select = new Select(driver.findElement(typeWorkSelect));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public FormStatusWorkExperience typeJobTitle() {

		WebElement title = driver.findElement(jobTitleBx);
		title.sendKeys(jobTitle);

		UtilMeth.PauseForJS(driver, 20, true);
		return this;	
	}
	
	public FormStatusWorkExperience clickSaveBtn() {
		
		WebElement save = driver.findElement(saveBtn);
		save.click();
		
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	
	public void setWorkExperience() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		WebElement workExperienceLnk = this.driver.findElement(workExperienceLnkEL);
		workExperienceLnk.click();
		UtilMeth.PauseForJS(driver, 60, true);
	
		typeEmployer().typeJobStartDate().typeJobEndDate().selectTypeWork().typeJobTitle().clickSaveBtn();
		driver.switchTo().defaultContent();
	}
}
