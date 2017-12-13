package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusResearchDetails extends TestBase{

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	String countryCode;
	String lipsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent lectus nulla, convallis et porttitor id, pellentesque nec purus. Nam orci. ";

	By researchDetailsUKLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$6");
	By researchDetailsCHNLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$4");
	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");

	public FormStatusResearchDetails(WebDriver driver, String countryCode) {
		this.driver = driver;
		this.countryCode = countryCode;
	}

	public FormStatusResearchDetails typeResearchTopic() {

		By researchTopicTitleEL = By.id("CIBAA_RS_TOPIC_SSR_RS_RSRCH_TOPIC_LBL$0");

		UtilMeth.PauseForJS(driver, 1.1f, false);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(researchTopicTitleEL));

		WebElement iframeDiv = driver.findElement(By.id("cke_1_contents"));
		WebElement iframe = iframeDiv.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe);

		WebElement resTopic = driver.findElement(By.tagName("p"));
		resTopic.click();
		resTopic.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

		UtilMeth.PauseForJS(driver, 2.1f, false);

		return this;
	}

	public FormStatusResearchDetails typeTopicDescription() {

		By topicDescTitleEL = By.id("CIBAA_RS_TOPIC_SSR_RS_DESCRLONG_LBL$0");

		UtilMeth.PauseForJS(driver, 1.1f, false);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(topicDescTitleEL));

		WebElement iframeDiv = driver.findElement(By.id("cke_2_contents"));
		WebElement iframe = iframeDiv.findElement(By.tagName("iframe"));

		driver.switchTo().frame(iframe);

		WebElement topicDesc = driver.findElement(By.tagName("p"));
		topicDesc.click();
		topicDesc.sendKeys(lipsum);

		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

		UtilMeth.PauseForJS(driver, 1.1f, false);

		return this;
	}

	public FormStatusResearchDetails saveBtn() {

		WebElement saveBtn = waitForElement(driver, timeOutInSeconds, saveBtnEL);
		saveBtn.click();

		UtilMeth.PauseForJS(driver, 60, true);

		return this;
	}
	
	public void setResearchDetails() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		switch (countryCode) {
		case "UK":
			WebElement resDestailsUKlnk = driver.findElement(researchDetailsUKLnkEL);
			resDestailsUKlnk.click();
			break;
		case "CN":
			WebElement resDestailsCHNlnk = driver.findElement(researchDetailsCHNLnkEL);
			resDestailsCHNlnk.click();
			break;
		default:
			System.out.println("Switch Statement Failed");
			break;
		}

		//UtilMeth.PauseForJS(driver, 20, true);
		
		typeResearchTopic().typeTopicDescription().saveBtn();
		driver.switchTo().defaultContent();
		System.out.println("setResearchDetails done...");

	}

}
