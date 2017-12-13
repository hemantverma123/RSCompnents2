package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.UtilityMethods;

public class AgentViewMyNottingham {
	
	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	
	By newApplicationBtnEL = By.id("CIBAA_DERIVED_NEW_APP_LINK");

	public AgentViewMyNottingham(WebDriver driver) {
		this.driver = driver;
	}
	
	public AgentViewMyNottingham clickNewApplicationBtn() {
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		
		WebElement newApplicationBtn = driver.findElement(newApplicationBtnEL);
		newApplicationBtn.click();
		
		UtilMeth.PauseForJS(driver, 120, true);
		
		driver.switchTo().defaultContent();
		
		UtilMeth.PauseForJS(driver, 60, true);
		
		return this;
	}

}
