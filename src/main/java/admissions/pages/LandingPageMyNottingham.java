package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import admissions.UtilityMethods;

public class LandingPageMyNottingham {

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();

	By userNameInput = By.id("userid");
	By passwordInput = By.id("pwd");
	By signInBtn = By.name("Submit");
	By applyChnMalCourseBtn = By.id("sgnup");

	public LandingPageMyNottingham(WebDriver driver) {
		this.driver = driver;
		
		driver.switchTo().defaultContent();
		UtilMeth.PauseForJS(driver, 1.1f, false);
	}

	public LandingPageMyNottingham clickApplyCourseBtn() {
		driver.findElement(applyChnMalCourseBtn).click();
		return this;

	}

	public LandingPageMyNottingham TypeUserName(String username) {
		driver.findElement(userNameInput).sendKeys(username);
		UtilMeth.PauseForJS(driver, 1.1f, false);
		return this;
	}

	public LandingPageMyNottingham TypePassword(String password) {
		driver.findElement(passwordInput).sendKeys(password);
		UtilMeth.PauseForJS(driver, 1.1f, false);
		return this;
	}

	public LandingPageMyNottingham ClickSignInBtn() {
		driver.findElement(signInBtn).click();
		return this;
	}
}
