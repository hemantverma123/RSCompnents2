package admissions.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.UtilityMethods;

public class MenuComponent {
	
	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	
	By uonHomeIconEL = By.id("uonhomeText");
	By uonUserNameLnkEL = By.id("uonUserName");
	By uonUserLandingLnkEL = By.id("uon_logo_anchor");
	
	public MenuComponent(WebDriver driver) {
		this.driver = driver;
		
		this.driver.switchTo().defaultContent();

		
	}
		
	public MenuComponent uonHomeIcon() {
		//UtilMeth.PauseForJS(driver, 20, true);
		WebElement homeIcon = driver.findElement(uonHomeIconEL);
		homeIcon.click();
		
		return this;
	}
	
	public MenuComponent uonUserName(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			System.out.println("Deliberate Wait for Username to appear .....");
			e.printStackTrace();
		}
		WebElement userName = driver.findElement(uonUserNameLnkEL);
		userName.click();
				
		return this;
	}
	
	public MenuComponent uonUserLanding() {
		WebElement userLand = driver.findElement(uonUserLandingLnkEL);
		userLand.click();
			
		return this;
	}
	
	public MenuComponent uonSignOut() {
		
		driver.get("https://psuatb.transform.nottingham.ac.uk/psp/psuatb/EMPLOYEE/EMPL/h/?cmd=logout");
		
		return this;
	}
	
	public String getStudentIDFromProfile(){
		uonHomeIcon();
		uonUserName();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uonUserName")));
		WebElement tmp = driver.findElement(By.id("personalInformationUser"));
		WebElement id = tmp.findElement(By.cssSelector(".col-md-4>p"));
		String studentID = id.getText();
		System.out.println("Student ID ->"+studentID);
		uonSignOut();
		return studentID;
	}
	
	
	
	

}
