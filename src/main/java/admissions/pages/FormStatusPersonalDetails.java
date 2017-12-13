package admissions.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import admissions.StudentJsonHandler;
import admissions.UtilityMethods;
import uiAutomation.testBase.TestBase;

public class FormStatusPersonalDetails extends TestBase{

	WebDriver driver;
	StudentJsonHandler jsHandler;
	UtilityMethods UtilMeth = new UtilityMethods();

	By personalDetailsLnkEL = By.id("CIBAA_LNDNG_WRK_CIBAA_SECTION_LINK$0");
	By titleSelectEL = By.id("CIBAA_PERDT_STG_NAME_PREFIX$0");
	By fullNameEL = By.id("CIBAA_PERDT_STG_LONG_FULL_NAME$0");
	By firstNameEL = By.id("CIBAA_PERDT_STG_FIRST_NAME$0");
	By lastNameEL = By.id("CIBAA_PERDT_STG_LAST_NAME$0");
	By genderEL = By.id("CIBAA_PERDT_STG_SEX$0");
	By maritalEL = By.id("CIBAA_PERDT_STG_MAR_STATUS$0");
	By countryEL = By.id("CIBAA_CTZENSHIP_COUNTRY$0");
	By birthCountryEL = By.id("CIBAA_PERDT_STG_COUNTRY$0");
	By disabledEL = By.id("CIBAA_PERDT_STG_DISABLED$0");
	By convictionYesEL = By.id("CIBAA_PERDT_STG_SAD_UC_CRIMALCONV$13$$0");
	By convictionNoEL = By.id("CIBAA_PERDT_STG_SAD_UC_CRIMALCONV$14$$0");
	By religionEL = By.id("CIBAA_PERDT_STG_RELIGIOUS_PREF$0");
	By raceEl = By.id("CIBAA_ETHNF_STG_ETHNIC_GRP_CD$0");
	By saveBtnEL = By.id("CIBAA_LNDNG_WRK_SAVE_BTN");
	By birthLocationEL = By.id("CIBAA_PERDT_STG_BIRTHPLACE$0");
	By countryOfCitizenShip = By.id("CIBAA_CTZENSHIP_COUNTRY$0");
	By dateOfbirthEL = By.id("CIBAA_PERDT_STG_BIRTHDATE$0");
	
	By pageTitle = By.id("CIBAA_LNDNG_WRK_PAGELABEL");

	public FormStatusPersonalDetails(WebDriver driver,StudentJsonHandler jsHandler) {
		this.driver = driver;
		this.jsHandler = jsHandler;

//		UtilMeth.PauseForJS(this.driver, 60, true);
//		WebElement personalDetailsLink = this.driver.findElement(personalDetailsLnkEL);
//		personalDetailsLink.click();
//		
//		WebDriverWait wait = new WebDriverWait(this.driver, 120);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
//		
//		// Searching for Sub Title as there seems to be a difference between Student and Agent Applications on Personal Details page
//		if(driver.findElements(By.id("CIBAA_TRANS_WRK_DESCR254")).size() >= 1) {
//			System.out.println("Sub Heading !!!!! "+driver.findElement(By.id("CIBAA_TRANS_WRK_DESCR254")).getText());
//		}
//		
	}

	public FormStatusPersonalDetails titleSelect(String title) {
		UtilMeth.PauseForJS(driver, 60, true);
		Select select = new Select(driver.findElement(titleSelectEL));

		switch (title) {
		case "Doctor":
			select.selectByValue("Dr");
			break;
		case "Miss":
			select.selectByValue("Miss");
			break;
		case "Mr":
			select.selectByValue("Mr");
			break;
		case "Mrs":
			select.selectByValue("Mrs");
			break;
		case "Ms":
			select.selectByValue("Ms");
			break;
		case "Professor":
			select.selectByValue("Prof");
			break;

		default:
			System.out.println("Selecting Title has Failed...");
			break;
		}

		return this;
	}
	
	public FormStatusPersonalDetails firstNameTxt(String firstname) {

		UtilMeth.PauseForJS(driver, 20, true);
		WebElement fullNameTxtBx = driver.findElement(firstNameEL);
		fullNameTxtBx.sendKeys(firstname);

		return this;
	}
	
	public FormStatusPersonalDetails lastNameTxt(String lastname) {

		UtilMeth.PauseForJS(driver, 20, true);
		WebElement fullNameTxtBx = driver.findElement(lastNameEL);
		fullNameTxtBx.sendKeys(lastname);

		return this;
	}

	public FormStatusPersonalDetails longNameTxt(String firstname, String lastname) {

		UtilMeth.PauseForJS(driver, 20, true);
		WebElement fullNameTxtBx = driver.findElement(fullNameEL);
		fullNameTxtBx.sendKeys(firstname + " " + lastname);

		return this;
	}

	public FormStatusPersonalDetails genderSelect(String gender) {

		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(genderEL));
		if (gender.equals("Male")) {
			select.selectByValue("M");
		} else {
			select.selectByValue("F");
		}
		return this;
	}

	public FormStatusPersonalDetails maritalSelect(String martial) {
		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(maritalEL));
		switch (martial) {
		case "Single":
			select.selectByValue("S");
			break;
		case "Divorced":
			select.selectByValue("D");
			break;
		case "Married":
			select.selectByValue("M");
			break;
		case "Widowed":
			select.selectByValue("W");
			break;
		default:
			System.out.println("Martial Select Failed");
			break;
		}
		return this;
	}

	public FormStatusPersonalDetails nationalitySelect(String country) {
		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(countryEL));
		switch (country) {
		case "MY":
			select.selectByValue("MYS");
			break;
		case "CN":
			select.selectByValue("CHN");
			break;
		case "UK":
			select.selectByValue("GBR");
			break;
		default:
			System.out.println("Needs Country Code");
			break;
		}
		return this;
	}
	
//	public FormStatusPersonalDetails birthCountrySelect(String nationcode) {
//		UtilMeth.PauseForJS(driver, 20, true);
//		Select select = new Select(driver.findElement(birthCountryEL));
//		switch (nationcode) {
//		case "MY":
//			select.selectByValue("MYS");
//			break;
//		case "CN":
//			select.selectByValue("CHN");
//			break;
//		case "GBR":
//			select.selectByValue("GBR");
//			break;
//		default:
//			System.out.println("Needs Country Code");
//			break;
//		}
//		return this;
//	}
	
	public FormStatusPersonalDetails birthCountrySelect(String country) {
		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(birthCountryEL));
		//select.selectByValue(country);  //value is usa
		select.selectByVisibleText(country);
		return this;
	}


	public FormStatusPersonalDetails disabledSelect(String disabled) {
		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(disabledEL));
		if (disabled.equals("YES")) {
			select.selectByValue("Y");
		} else {
			select.selectByValue("N");
		}
		return this;
	}

	public FormStatusPersonalDetails convictionRadio(String conviction) {
		UtilMeth.PauseForJS(driver, 20, true);
		WebElement criminalRadio;
		if(conviction.equals("YES")) {
			criminalRadio = driver.findElement(convictionNoEL);
		}else {
			
			criminalRadio = driver.findElement(convictionNoEL);
		}
		criminalRadio.click();
		return this;
	}
	
	public FormStatusPersonalDetails religionSelect() {
		
		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(religionEL));
		select.selectByValue("ONM");		
		return this;
	}
	
	public FormStatusPersonalDetails raceSelect() {
		UtilMeth.PauseForJS(driver, 20, true);
		Select select = new Select(driver.findElement(raceEl));
		select.selectByValue("M");
		return this;
	}
	
	public FormStatusPersonalDetails saveBtn() {
		UtilMeth.PauseForJS(driver, 30, true);
		
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).sendKeys(Keys.END).keyUp(Keys.CONTROL).build().perform();
		waitForElement(driver,60,saveBtnEL).click();;
		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	
	public FormStatusPersonalDetails birthLocation() {
		UtilMeth.PauseForJS(driver, 60, true);
		WebElement birthLocation = driver.findElement(birthLocationEL);
		birthLocation.sendKeys("Birth Location");
		
		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}
	
	public FormStatusPersonalDetails countryOfCitizenShip(String country) {
		Select select = new Select(driver.findElement(countryOfCitizenShip));
		//select.selectByValue(country);
		select.selectByVisibleText(country);
		return this;
	}
	
	public FormStatusPersonalDetails dateOfBirth(String date) {
		
		WebElement db = driver.findElement(dateOfbirthEL);
		db.sendKeys(date);
		
		UtilMeth.PauseForJS(driver, 60, true);
		return this;
	}

	public void SetPersonalDetails(String nationCode) {
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));

//		WebElement personalDetailsLink = waitForElement(driver, 30, personalDetailsLnkEL);
		waitForElement(driver, 30, personalDetailsLnkEL).click();
		UtilMeth.PauseForJS(driver, 60, true);
		this.waitForElementVisible(driver, 60, pageTitle);
//		WebDriverWait wait = new WebDriverWait(this.driver, 120);
//		wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
		
		// Searching for Sub Title as there seems to be a difference between Student and Agent Applications on Personal Details page
		if(driver.findElements(By.id("CIBAA_TRANS_WRK_DESCR254")).size() >= 1) {
			System.out.println("Sub Heading !!!!! "+driver.findElement(By.id("CIBAA_TRANS_WRK_DESCR254")).getText());
		}

		switch (nationCode) {
		case "MYS":
			titleSelect("Mr").longNameTxt(jsHandler.getStudentFirstName(), jsHandler.getStudentLastname()).genderSelect(jsHandler.getStudentSex()).maritalSelect("Single").nationalitySelect("MY").disabledSelect("NO").convictionRadio("NO").religionSelect().raceSelect().saveBtn();
			break;
		case "CN":
			break;
		case "GBR":
			//titleSelect("Mr").birthLocation().birthCountrySelect(nationCode).genderSelect(jsHandler.getStudentSex()).countryOfCitizenShip(nationCode).disabledSelect("NO").convictionRadio("NO").saveBtn();
			titleSelect("Mr").birthLocation().birthCountrySelect(jsHandler.getStudentCountry()).genderSelect(jsHandler.getStudentSex()).countryOfCitizenShip(jsHandler.getStudentCountry()).disabledSelect("NO").convictionRadio("NO").saveBtn();
			break;
		default:
			System.out.println("Invalid Country Code...");
			break;
		}

		System.out.println("SetPersonalDetails done for " + nationCode);
		driver.switchTo().defaultContent();
	}
}
