package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.UtilityMethods;

public class Ipay88Payment {

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();
	WebDriverWait wait;
	String I88PaymentTitle ="iPay88 Payment Page";

	By proceedBtnEL = By.name("Submit");
	By completeBtnEL = By.id("CompleteBtn");
	
	public Ipay88Payment(WebDriver driver) {
		this.driver = driver;
		
		this.driver.switchTo().defaultContent();
		this.wait = new WebDriverWait(driver, 10);
	}
	
	public Ipay88Payment clickProceedIpay88Payment() {
		
		WebElement debug = driver.findElement(By.id("ipay_form"));
		WebElement Btn = debug.findElement(proceedBtnEL);
		Btn.click();
		
		return this;
	}
	
	public Ipay88Payment clickCompleteBtn() {
		
		wait.until(ExpectedConditions.titleIs(I88PaymentTitle));
		System.out.println(driver.getTitle());
		
		WebElement i88CompleteBtn = driver.findElement(completeBtnEL);
		i88CompleteBtn.click();
		return this;
	}

}
