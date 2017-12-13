package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.BrowserManager;
import admissions.StudentJsonHandler;
import admissions.TestManager;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusContactAndAddress extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	
	UtilityMethods UtilMeth = new UtilityMethods();

	By pageTitle = By.id("CIBAA_LNDNG_WRK_PAGELABEL");

	By contactAndAddrLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$1");
	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");

	By phoneTypeEL = By.id("CIBAA_PHONE_STG_PHONE_TYPE$0");
	By countryCodeEL = By.id("CIBAA_PHONE_STG_COUNTRY_CODE$0");
	By phoneEL = By.id("CIBAA_PHONE_STG_PHONE$0");

	By emailTypeEL = By.id("CIBAA_EMAIL_STG_E_ADDR_TYPE$0");
	By emailIdEL = By.id("CIBAA_EMAIL_STG_EMAILID$0");

	By addressTypeEL = By.id("CIBAA_ADDRESSES_ADDRESS_TYPE$0");
	By countryEL = By.id("DERIVED_ADDR_COUNTRY$0");
	
	By editAddressEL = By.id("DERIVED_ADDR_UPDATE_ADDRESS$0");

	public FormStatusContactAndAddress(WebDriver driver,StudentJsonHandler jsHandler) {
		this.driver = driver;
		this.jsHandler = jsHandler;

//		WebElement contactAddressLnk = driver.findElement(contactAndAddrLnkEL);
//		contactAddressLnk.click();
//		
//		System.out.println("The Browser is -> "+BrowserManager.getBrowser());
//
//		WebDriverWait wait = new WebDriverWait(this.driver, 120);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
//
//		// Searching for Sub Title as there seems to be a difference between Student and
//		// Agent Applications on Personal Details page
//		if (driver.findElements(By.id("CIBAA_TRANS_WRK_DESCR254")).size() >= 1) {
//			System.out.println("Sub Heading !!!!! " + driver.findElement(By.id("CIBAA_TRANS_WRK_DESCR254")).getText());
//		}

	}

	public FormStatusContactAndAddress addressType() {

		UtilMeth.PauseForJS(driver, 20, true);

		Select select = new Select(driver.findElement(addressTypeEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 20, true);

		Actions actions = new Actions(driver);
		WebElement adTypelbx = driver.findElement(addressTypeEL);
		actions.moveToElement(adTypelbx);
		actions.click().build().perform();

		adTypelbx.sendKeys(Keys.TAB);

		return this;
	}

	public FormStatusContactAndAddress addressCountry(String country) {

		UtilMeth.PauseForJS(driver, 2f, false);

		System.out.println("Country -> " + country);

		WebElement mag = driver.findElement(By.id("DERIVED_ADDR_COUNTRY$prompt$img$0"));
		mag.click();

		UtilMeth.PauseForJS(driver, 20, true);
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptModFrame_0")));
		
		switch (country) {
		case "GBR":
			WebElement codeGBR = driver.findElement(By.id("RESULT0$80"));
			codeGBR.click();			
			break;
		case "CHN":
			WebElement codeCHN = driver.findElement(By.id("RESULT0$44"));
			codeCHN.click();			
			break;
		case "MYS":
			WebElement codeMYS = driver.findElement(By.id("RESULT0$159"));
			codeMYS.click();			
			break;
		default:
			break;
		}
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));

		return this;
	}

	public FormStatusContactAndAddress email(String email) {

		UtilMeth.PauseForJS(driver, 20, true);
		
		WebElement emailBx = driver.findElement(emailIdEL);
		emailBx.sendKeys(email);

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public FormStatusContactAndAddress emailType() {

		UtilMeth.PauseForJS(driver, 2f, false);
		
		if(BrowserManager.getBrowser().equalsIgnoreCase("Chrome")) {
			System.out.println("We Are Chrome");
			
			Select select = new Select(driver.findElement(emailTypeEL));
			select.selectByIndex(1);
			
		}else {
			
			Select select = new Select(driver.findElement(emailTypeEL));
			select.selectByIndex(1);
			
			UtilMeth.PauseForJS(driver, 20, true);
			
			Actions actions = new Actions(driver);
			WebElement emTypelbx = driver.findElement(emailTypeEL);
			actions.moveToElement(emTypelbx);
			actions.click().build().perform();
			
			emTypelbx.sendKeys(Keys.TAB);
		}


		return this;
	}

	public FormStatusContactAndAddress phoneType() {

		UtilMeth.PauseForJS(driver, 20, true);

		Select select = new Select(driver.findElement(phoneTypeEL));
		select.selectByIndex(1);

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public FormStatusContactAndAddress countryCodeTxtBx() {

		UtilMeth.PauseForJS(driver, 20, true);

		WebElement countryCode = driver.findElement(countryCodeEL);
		countryCode.sendKeys("044");

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public FormStatusContactAndAddress phoneNumber(String phoneNum) {

		UtilMeth.PauseForJS(driver, 20, true);

		WebElement phone = driver.findElement(phoneEL);
		phone.sendKeys(phoneNum);

		UtilMeth.PauseForJS(driver, 20, true);

		return this;
	}

	public FormStatusContactAndAddress saveBtn(Boolean modal) {

		UtilMeth.PauseForJS(driver, 20, true);

		WebElement saveBtn = driver.findElement(saveBtnEL);
		saveBtn.click();
		UtilMeth.PauseForJS(driver, 20, true);
		UtilMeth.PauseForJS(driver, 2.5f, false);

		if (modal == true) {
			Actions action = new Actions(driver);
			action.sendKeys(Keys.RETURN);
			action.perform();
			UtilMeth.PauseForJS(driver, 60, true);
		}

		return this;
	}
	
	public FormStatusContactAndAddress clickEditAddress() {
		
		UtilMeth.PauseForJS(driver, 20, true);
		
		WebElement edAddress = driver.findElement(editAddressEL);
		edAddress.click();
		
		return this;
	}

	public void SetContactAddress() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		waitForElement( driver,30,contactAndAddrLnkEL).click();
		UtilMeth.PauseForJS(driver, 60, true);
		this.waitForElementVisible(driver, 30, pageTitle);		
//		WebDriverWait wait = new WebDriverWait(this.driver, 120);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));

		// Searching for Sub Title as there seems to be a difference between Student and
		// Agent Applications on Personal Details page
		if (driver.findElements(By.id("CIBAA_TRANS_WRK_DESCR254")).size() >= 1) {
			System.out.println("Sub Heading !!!!! " + driver.findElement(By.id("CIBAA_TRANS_WRK_DESCR254")).getText());
		}

		countryCodeTxtBx().saveBtn(true);
		driver.switchTo().defaultContent();
		System.out.println("SetContactAddress done...");
		
	}
}
