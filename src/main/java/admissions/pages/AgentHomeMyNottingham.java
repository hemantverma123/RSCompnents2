package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.UtilityMethods;

public class AgentHomeMyNottingham {
	
	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	
	By agentCentreBtnEL = By.id("btn-manageDocs");

	public AgentHomeMyNottingham(WebDriver driver) {
	
		this.driver = driver;
	}
	
	public AgentHomeMyNottingham clickAgentCentreBtn() {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(agentCentreBtnEL));
		
		WebElement agentCentreBtn = driver.findElement(agentCentreBtnEL);
		agentCentreBtn.click();
		
		UtilMeth.PauseForJS(driver, 60, true);
		
		return this;
	}
}
