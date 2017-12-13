package admissions.helper;

import org.openqa.selenium.WebDriver;

import admissions.StudentJsonHandler;
import admissions.pages.EditAddressLinkPage;
import admissions.pages.LandingPageMyNottingham;
import admissions.pages.MalChinaApplicationAccountSignup;
import admissions.pages.RegistrationCompletePageMyNottingham;

public class StudentRegistration {

	public StudentRegistration() {

	}

	public WebDriver RegisterAsAStudent(WebDriver driver, StudentJsonHandler jsHandler, String nationCode) {

		// Welcome To Nottingham Landing Page
		LandingPageMyNottingham landingPage = new LandingPageMyNottingham(driver);
		landingPage.clickApplyCourseBtn();

		// Malaysia Application Account Signup Page
		MalChinaApplicationAccountSignup AppSignUp = new MalChinaApplicationAccountSignup(driver,jsHandler);
		AppSignUp.TypeFirstName(jsHandler.getStudentFirstName()).TypeMiddleName(jsHandler.getStudentMiddlename())
				.TypeLastName(jsHandler.getStudentLastname()).TypeDob(jsHandler.getStudentDob(true))
				.TypeTelephone(jsHandler.getStudentPhone(true)).TypeEmailID(jsHandler.getStudentEmail())
				.TypeConfirmEmailID(jsHandler.getStudentEmail()).TypeuserID(jsHandler.getStudentUsername())
				.TypePassword(jsHandler.getStudentPassword()).TypeConfirmPassword(jsHandler.getStudentPassword())
				.SelectQuestion1(1, "q1Answer").SelectQuestion2(2, "q2Answer").SelectQuestion3(3, "q3Answer")
				.ClickEditAddress();

		// Edit Address Link
		EditAddressLinkPage EdAddress = new EditAddressLinkPage(driver,jsHandler);
		EdAddress.TypeAddressLine1(jsHandler.getStudentStreet()).TypeAddressLine2(jsHandler.getStudentStreet())
				.TypeAddressLine3(jsHandler.getStudentStreet()).TypeCity(jsHandler.getStudentCity())
				.TypeCounty().TypePostcode(jsHandler.getStudentPostcode()).ClickOkBtn();

		// Malaysia Application Account Submit Button
		AppSignUp.ClickSubmitBtn();

		// Registration Complete Page Click GoTo Login
		RegistrationCompletePageMyNottingham RegPage = new RegistrationCompletePageMyNottingham(driver, jsHandler);
		RegPage.ClickGoToLoginPageBtn();

		// Student is Registered at this point!!
		System.out.println("Student has Registered...");

		return driver;

	}

}
