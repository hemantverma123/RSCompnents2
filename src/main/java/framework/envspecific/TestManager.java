package framework.envspecific;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestManager {

	static WebDriver driver;
	static String countryCode;
	//https://siq13cpkpj.execute-api.eu-west-1.amazonaws.com/dev/applicants/create/uk/1
	static String StudentDataURL = "https://siq13cpkpj.execute-api.eu-west-1.amazonaws.com/dev/applicants/create/";
	static int numberOfStudents = 1;
	
	static String myTestEnvUrl;
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
	
	public static String getMyTestEnvUrl() {
		return myTestEnvUrl;
	}

	public static String getCsTestEnvUrl() {
		return csTestEnvUrl;
	}

	public static String getStudentData(String countryCode) throws IOException {
		System.out.println("inside get student data function...");
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
		myTestEnvUrl = envUrlsArray[0];

		System.out.println("Test Environment URL -> "+myTestEnvUrl);
	}
}
