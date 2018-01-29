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
import org.testng.Assert;

import framework.TestBase;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 
 * @author Hemant Verma
 *
 */

public class ProductPage extends TestBase {
	
	
	WebDriver driver;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(ProductPage.class.getName());


	@FindBy(xpath="//*[text()[contains(.,'Solder Paste')]]")
	WebElement Solder;
	@FindBy(xpath="//*[text()[contains(.,'Solder Pastes')]]")
	WebElement SolderPastes;
	@FindBy(xpath="//*[text()[contains(.,'My basket')]]")
	WebElement basketPageTitle;
	@FindBy(xpath="//*[text()[contains(.,'Solder Paste')]]")
	WebElement Solder3;

	@FindBy(id="atbBtn-1")
	WebElement add;
	
	@FindBy(id="js-basketAmt")
	WebElement bBasket;

	
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public void selectproduct(String product) {
		String elementxpath = "//*[text()[contains(.,'"+product+"')]]";
		System.out.println(elementxpath);
		waitForElement(driver, 10,By.xpath(elementxpath)).click();

	}

	public void selectproductcat(String prodcat) {
		waitForElement(driver, 10,By.xpath("//*[text()[contains(.,'"+prodcat+"')]]")).click();
	}
	
	public void addToBasket() {
		add.click();		
	}
	
	public void clickbasket() {
		bBasket.click();		
	}
}
