package rs.ecom.pages;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import framework.TestBase;

import org.apache.commons.lang3.time.StopWatch;

/**
 * 
 * @author Hemant Verma
 *
 */

public class SearchResultPage extends TestBase {
	
	
	WebDriver driver;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(SearchResultPage.class.getName());


	@FindBy(xpath="//*[@id='zeroResultBody']/h2[1]")
	WebElement SearchError;

	@FindBy(id="searchForm:findBtnweb")
	WebElement bSearch;
	
	@FindBy(id="js-basketAmt")
	WebElement bBasket;

	
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifysearch(String result) {
		//waitForElement(driver, 10,By.xpath(elementxpath)).click();
		switch(result) {
		case "You have encountered an Error...": Assert.assertThat(SearchError.getText(),containsString(result));
		case "We couldn't find any results for 'no product exist'": Assert.assertThat(SearchError.getText(),containsString(result));
		case "Your search returned 38 products in 2 categories": Assert.assertThat(SearchError.getText(),containsString(result));
		default:
			System.out.println("invalid test");
		}

	}

}
