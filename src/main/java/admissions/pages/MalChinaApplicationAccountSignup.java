package admissions.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class MalChinaApplicationAccountSignup extends TestBase {
	WebDriver driver;
	StudentJsonHandler jsHandler;
	
	UtilityMethods UtilMeth = new UtilityMethods();

	By firstName = By.id("CIBAA_RGSTR_WRK_FIRST_NAME");
	By middleName = By.id("CIBAA_RGSTR_WRK_MIDDLE_NAME");
	By lastName = By.name("CIBAA_RGSTR_WRK_LAST_NAME");
	By dateOfBirth = By.id("CIBAA_RGSTR_WRK_BIRTHDATE");
	By telePhone = By.id("CIBAA_RGSTR_WRK_PHONE");
	By emailID = By.id("CIBAA_RGSTR_WRK_EMAILID");
	By confirmEmailID = By.id("CIBAA_RGSTR_WRK_EMAIL_ADDR");
	By userID = By.id("CIBAA_REGISTER_HRS_OPRNAME");
	By password = By.id("CIBAA_RGSTR_WRK_HRS_OPRPSWD");
	By confirmPassword = By.id("CIBAA_RGSTR_WRK_HRS_CONFMPSWD");
	By secQ1 = By.id("CIBAA_RGSTR_WRK_CIBAA_QUESTION1");
	By secA1 = By.id("CIBAA_RGSTR_WRK_CIBAA_ANSWER1");
	By secQ2 = By.id("CIBAA_RGSTR_WRK_CIBAA_QUESTION2");
	By secA2 = By.id("CIBAA_RGSTR_WRK_CIBAA_ANSWER2");
	By secQ3 = By.id("CIBAA_RGSTR_WRK_CIBAA_QUESTION3");
	By secA3 = By.id("CIBAA_RGSTR_WRK_CIBAA_ANSWER3");
	By country = By.id("DERIVED_ADDR_COUNTRY");
	
	//By addressLink = By.id("DERIVED_ADDR_UPDATE_ADDRESS");
	By addressLink = By.xpath(".//*[text()[contains(.,'Edit Address')]]");
	
	//By submitBtn = By.id("CIBAA_RGSTR_WRK_SUBMIT_BTN");
	By submitBtn = By.name("CIBAA_RGSTR_WRK_SUBMIT_BTN");
	
	By pageTitle = By.id("win0div$ICField56");

	public MalChinaApplicationAccountSignup(WebDriver driver,StudentJsonHandler jsHandler){
		this.driver = driver;
		this.jsHandler = jsHandler;
		
//		UtilMeth.PauseForJS(driver, 1.5f, false);
//		
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
//		
//		WebDriverWait wait = new WebDriverWait(this.driver, 30);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
		
	}
		
	public MalChinaApplicationAccountSignup TypeFirstName(String firstname){
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(firstName).sendKeys(firstname,Keys.TAB);
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeMiddleName(String middlename) {
		driver.findElement(middleName).sendKeys(middlename,Keys.TAB);		
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeLastName(String lastname) {
		driver.findElement(lastName).sendKeys(lastname,Keys.TAB);
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeDob(String dob) {
		driver.findElement(dateOfBirth).sendKeys(dob,Keys.TAB);	
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeTelephone(String telephone) {
		driver.findElement(telePhone).sendKeys(telephone,Keys.TAB);	
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeEmailID(String emailid) {
		driver.findElement(emailID).sendKeys(emailid,Keys.TAB);	
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeConfirmEmailID(String confirmemail) {
		driver.findElement(confirmEmailID).sendKeys(confirmemail,Keys.TAB);	
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeuserID(String userid) {
		driver.findElement(userID).sendKeys(userid,Keys.TAB);	
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypePassword(String pass) {
		driver.findElement(password).sendKeys(pass,Keys.TAB);	
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeConfirmPassword(String pass) {
		driver.findElement(confirmPassword).sendKeys(pass,Keys.TAB);	
		UtilMeth.PauseForJS(driver, 20, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup SelectQuestion1(int questionIndex, String q1Answer) {
		Select select1 = new Select(driver.findElement(secQ1));
		select1.selectByIndex(questionIndex);
		UtilMeth.PauseForJS(driver, 20, true);
		WebElement a1 = driver.findElement(secA1);
		a1.sendKeys(Keys.TAB);
		a1.sendKeys(q1Answer, Keys.TAB);
		return this;
	}
	
	public MalChinaApplicationAccountSignup SelectQuestion2(int questionIndex, String q2Answer) {
		Select select2 = new Select(driver.findElement(secQ2));
		select2.selectByIndex(questionIndex);
		UtilMeth.PauseForJS(driver, 20, true);
		WebElement a2 = driver.findElement(secA2);
		a2.sendKeys(Keys.TAB);
		a2.sendKeys(q2Answer, Keys.TAB);
		return this;
	}
	
	public MalChinaApplicationAccountSignup SelectQuestion3(int questionIndex, String q3Answer) {
		Select select3 = new Select(driver.findElement(secQ3));
		select3.selectByIndex(questionIndex);
		UtilMeth.PauseForJS(driver, 20, true);
		WebElement a3 = driver.findElement(secA3);
		a3.sendKeys(Keys.TAB);
		a3.sendKeys(q3Answer, Keys.TAB);

		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).keyUp(Keys.CONTROL).build().perform();
		
//		 Actions action = new Actions(driver);
//	        action.keyDown(Keys.SHIFT).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN,
//	Keys.ARROW_DOWN, Keys.ARROW_DOWN)
//	                .keyUp(Keys.SHIFT).build().perform();

		//UtilMeth.PauseForJS(driver, 30, true);	
		return this;
	}
	
	public MalChinaApplicationAccountSignup ClickEditAddress() {
		waitForElement(driver, 10, addressLink).click();
		UtilMeth.PauseForJS(driver, 30, true);
		return this;
	}
	
	public MalChinaApplicationAccountSignup TypeCountry(String studentcountrycode) {
		System.out.println("entering studentcountrycode: " + studentcountrycode);
		waitForElement(driver, 10, country).clear();
//		waitForElement(driver, 10, country).click();
		waitForElement(driver, 10, country).sendKeys(studentcountrycode);
		UtilMeth.PauseForJS(driver, 30, true);
		return this;
		
	}
	
	public MalChinaApplicationAccountSignup ClickSubmitBtn() {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		Actions action = new Actions(driver);
		//action.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).keyUp(Keys.CONTROL).build().perform();
		action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
		//action.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).build().perform();
		waitForElement( 30,submitBtn).click();
		ProcessingImgInvisible(driver, 20, By.id("processing"));	
		driver.switchTo().defaultContent();
		return this;
	}

	public WebElement waitForElement(long timeOutInSeconds,By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by);
	}

	public void SetApplicantDetail() {

		driver.switchTo().defaultContent();
		//driver.switchTo().frame(driver.findElement(By.id("ptifrmtgtframe")));
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));
		
		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(firstName));
		
		TypeFirstName(jsHandler.getStudentFirstName()).TypeMiddleName(jsHandler.getStudentMiddlename())
		.TypeLastName(jsHandler.getStudentLastname()).TypeDob(jsHandler.getStudentDob(true)).TypeCountry(jsHandler.getStudentCountryCode())
		.TypeTelephone(jsHandler.getStudentPhone(true)).TypeEmailID(jsHandler.getStudentEmail())
		.TypeConfirmEmailID(jsHandler.getStudentEmail()).TypeuserID(jsHandler.getStudentUsername())
		.TypePassword(jsHandler.getStudentPassword()).TypeConfirmPassword(jsHandler.getStudentPassword())
		.SelectQuestion1(1, "q1Answer").SelectQuestion2(2, "q2Answer").SelectQuestion3(3, "q3Answer")
		.ClickEditAddress();
		System.out.println("SetApplicantDetail done.");
		driver.switchTo().defaultContent();
	}
	
}
