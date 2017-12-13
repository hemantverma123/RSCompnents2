package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import admissions.UtilityMethods;

public class FormStatusFundingInformation {
	
	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();

	By fundingInfoLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$5");
	By paymentMethodEL = By.id("CIBAA_FAFND_STG_CIBAA_SRC_FUND$0");
	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");

	public FormStatusFundingInformation(WebDriver driver) {
		this.driver = driver;
		
		UtilMeth.PauseForJS(this.driver, 60, true);
		WebElement fundingInfoLink = this.driver.findElement(fundingInfoLnkEL);
		fundingInfoLink.click();
	}
	
	public FormStatusFundingInformation paymentMethod() {
		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(paymentMethodEL));
		select.selectByValue("OWNF");		
		return this;
	}
	
	public FormStatusFundingInformation saveBtn() {
		
		WebElement saveBtn = driver.findElement(saveBtnEL);
		saveBtn.click();
		
		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}

}
