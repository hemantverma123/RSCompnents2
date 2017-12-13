package admissions;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestManager {

	static WebDriver driver;
	static String countryCode;
	
	static String StudentDataURL = "https://siq13cpkpj.execute-api.eu-west-1.amazonaws.com/dev/applicants/create/";
	static int numberOfStudents = 1;
	
	static String myNottsTestEnvUrl;
	static String csTestEnvUrl;
	static String StudentData;
	
	static String ukPhoneNumber = "T:+44(0)1159515151F:+44(0)1158463666";
	static By ukPhoneEl = By.cssSelector("#alluserinfo > div:nth-child(2) > p:nth-child(2) > a");
	static String malaysiaPhoneNumber = "T:+6(03)89248686F:+6(03)89248005";
	static By malaysiaPhoneEL = By.cssSelector("#alluserinfo > div:nth-child(2) > p:nth-child(2) > a:nth-child(2)");
	static String chinaPhoneNumber = "+86 (0)574 8818 0330";
	static By chinaPhoneEl = By.cssSelector("#alluserinfo > div:nth-child(2) > p:nth-child(2) > a:nth-child(2)");
	
	
	public TestManager() {
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static String getMyNottsTestEnvUrl() {
		return myNottsTestEnvUrl;
	}

	public static String getCsTestEnvUrl() {
		return csTestEnvUrl;
	}

	public static String getStudentData(String countryCode) throws IOException {

		//Sort Student Data
		HttpCalls call = new HttpCalls();
		StudentData = call.run(StudentDataURL + countryCode + "/" + numberOfStudents);
		System.out.println(StudentData);
//		StudentJsonHandler jsHandler = new StudentJsonHandler(StudentData);
//		System.out.println("username: " + jsHandler.getStudentUsername());
//		System.out.println("Password: " + jsHandler.getStudentPassword());

		return StudentData;
	}

	public static void setupEnvironmentsAndBrowserForTests(String countryCode) throws IOException {
		//Sort URLS
		UonCountryEnvironments UonURL = new UonCountryEnvironments();
		String[] envUrlsArray = UonURL.CountryURL(countryCode);
		myNottsTestEnvUrl = envUrlsArray[0];
		csTestEnvUrl = envUrlsArray[1];
		System.out.println("Test Environment URL -> "+myNottsTestEnvUrl);
		System.out.println("Test Campus Solutions URL -> "+csTestEnvUrl);
		//Sort Browser
		//driver = BrowserManager.BrowserSelect();
		//System.out.println("The Browswer Selected Was -> "+BrowserManager.getBrowser());
	}
	
	public static void checkTelephoneForCountry(String countryCode) {
		if(countryCode.equals("UK")) {
			WebElement ukTeleNumberParentDiv = driver.findElement(By.id("UN_IH_FOOTER_CONTACT_US_PGLT1"));
			WebElement ukNumber = ukTeleNumberParentDiv.findElement(By.cssSelector("div>p:last-of-type"));
			String htmlString = ukNumber.getText();
			String noHTMLString = htmlString.replaceAll("\\<.*?>","").replaceAll("\\s","");
			System.out.println("Text ----------------->"+ukNumber.getText());
			assertEquals("Malaysia Telephone Number not on Chine Landing Page or We are on Wrong Page######", ukPhoneNumber,
					noHTMLString);
//			WebElement ukTeleNumber = driver.findElement(ukPhoneEl);
//			assertEquals("UK Telephone Number not on UK Landing Page or We are on Wrong Page", ukPhoneNumber,
//					ukTeleNumber.getText());
			System.out.println("UK Number -> " + ukNumber.getText());
		}else if(countryCode.equals("CN")) {
			WebElement chinaTeleNumber = driver.findElement(chinaPhoneEl);
			assertEquals("China Telephone Number not on Chine Landing Page or We are on Wrong Page", chinaPhoneNumber,
					chinaTeleNumber.getText());
			System.out.println("China Number -> " + chinaTeleNumber.getText());
		}else if(countryCode.equals("MY")) {
//			WebElement malaysiaTeleNumber = driver.findElement(malaysiaPhoneEL);
			WebElement malaysiaTeleNumberParentDiv = driver.findElement(By.id("UN_IH_FOOTER_CONTACT_US_PGLT1"));
			WebElement malaysiaNumber = malaysiaTeleNumberParentDiv.findElement(By.cssSelector("div>p:last-of-type"));
			String htmlString = malaysiaNumber.getText();
			String noHTMLString = htmlString.replaceAll("\\<.*?>","").replaceAll("\\s","");
			System.out.println("Text ----------------->"+malaysiaNumber.getText());
			assertEquals("Malaysia Telephone Number not on Chine Landing Page or We are on Wrong Page######", malaysiaPhoneNumber,
					noHTMLString);
		}else {
			System.out.println("Something Went Wrong with Telephone Number Check!!!!");
		}
		
	}

}
