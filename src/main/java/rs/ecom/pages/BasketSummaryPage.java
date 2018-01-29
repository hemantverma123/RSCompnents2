package rs.ecom.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import framework.TestBase;

import org.junit.Assert;
import org.junit.Assert.*;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 
 * @author Hemant Verma
 *
 */

public class BasketSummaryPage extends TestBase {
	
	
	WebDriver driver;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(BasketSummaryPage.class.getName());


	@FindBy(xpath="//*[text()[contains(.,'My basket')]]")
	WebElement basketPageTitle;

	@FindBy(id="js-basketAmt")
	WebElement bCheckout;

	
	public BasketSummaryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void verifyPageTitle(String title) {
		//waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'"+product+"')]]")).click();
		Assert.assertEquals(basketPageTitle.getText(), title);

	}

	public void verifyProdOnBasketPage(String basketprod) {
		Assert.assertThat(waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'"+basketprod+"')]]")).getText(),containsString(basketprod));
		
	}
	
}
