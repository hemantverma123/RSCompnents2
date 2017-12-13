package admissions.pages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import admissions.UtilityMethods;

public class PaymentMyNottingham {
	
	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();

	By selectCampusToPayBtnEL = By.id("UN_PAY_WRK_UN_SELECT_BU$0");
	By payChargesBtnEL = By.id("SSF_SS_PMT_WRK_SSF_RESET_BAL$5$$0");
	By nextBtnEL = By.id("SSF_DERIVED_NAV_SSF_NEXT$8$");
	By contMakePaymentBtnEL = By.id("SSF_DERIVED_NAV_SSF_NEXT");

	public PaymentMyNottingham(WebDriver driver) {
		this.driver = driver;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayList tabs = new ArrayList (this.driver.getWindowHandles());
        this.driver.switchTo().window((String) tabs.get(1));
		
        UtilMeth.PauseForJS(this.driver, 2, false);
        
        this.driver.switchTo().defaultContent();
        this.driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		
	}
	
	public PaymentMyNottingham selectCampusToPay() {
		WebElement campusToPayBtn = driver.findElement(selectCampusToPayBtnEL);
		campusToPayBtn.click();
		UtilMeth.PauseForJS(driver, 10, true);
		
		return this;
	}
	
	public PaymentMyNottingham payCharges() {
		WebElement payChargesBtn = driver.findElement(payChargesBtnEL);
		payChargesBtn.click();
		UtilMeth.PauseForJS(driver, 10, true);
		
		return this;
	}
	
	public PaymentMyNottingham clickNextBtn() {
		
		WebElement nextBtn = driver.findElement(nextBtnEL);
		nextBtn.click();
		UtilMeth.PauseForJS(driver, 10, true);
		
		return this;
	}
	
	public PaymentMyNottingham clickContinueToMakePaymentBtn() {
		
		WebElement contMakePaymentBtn = driver.findElement(contMakePaymentBtnEL);
		contMakePaymentBtn.click();
		UtilMeth.PauseForJS(driver, 5, false);
		
		return this;
	}
	

}
