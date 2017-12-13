package admissions.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.UtilityMethods;

public class MaintainApplicationsStaffMyNottingham {
	
	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	
	String studentID;
	String pageLabel = "Maintain Applications";

	By idFilterDropdownEL = By.id("ADM_MAINT_SCTY_EMPLID$op");
	By idTxtBxEL = By.id("ADM_MAINT_SCTY_EMPLID");
	By searchBtnEL = By.id("#ICSearch");

	public MaintainApplicationsStaffMyNottingham(WebDriver driver, String studentID) {
		this.driver = driver;
		this.studentID = studentID;
		
		this.driver.switchTo().defaultContent();
		this.driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		
		WebElement maintainAppLabel = (new WebDriverWait(driver, 10))
				  .until(ExpectedConditions.presenceOfElementLocated(By.id("app_label")));
		System.out.println(maintainAppLabel.getText());
		Assert.assertEquals("Not on Maintain Applications Page", pageLabel, maintainAppLabel.getText());
		
		
	}
	
	public MaintainApplicationsStaffMyNottingham enterID() {	
		Select select = new Select(driver.findElement(idFilterDropdownEL));
		select.selectByValue("8");
		UtilMeth.PauseForJS(driver, 5, true);
		
		WebElement textbox = driver.findElement(idTxtBxEL);
		textbox.sendKeys(studentID);
		UtilMeth.PauseForJS(driver, 5, true);
		
		return this;
	}
	
	public MaintainApplicationsStaffMyNottingham clickSearchBtn() {
		WebElement searchbtn = driver.findElement(searchBtnEL);
		searchbtn.click();
		UtilMeth.PauseForJS(driver, 60, true);
		
		return this;
	}
	
	

}
