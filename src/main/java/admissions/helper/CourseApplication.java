package admissions.helper;

import org.openqa.selenium.WebDriver;

import admissions.StudentJsonHandler;
import admissions.UonCountries;
import admissions.pages.ApplicationSummaryMyNottingham;
import admissions.pages.FormStatusAdditionalQuestions;
import admissions.pages.FormStatusContactAndAddress;
import admissions.pages.FormStatusPersonalDetails;
import admissions.pages.FormStatusPersonalStatement;
import admissions.pages.FormStatusReferences;
import admissions.pages.FormStatusResearchDetails;
import admissions.pages.FormStatusResidency;
import admissions.pages.FormStatusSupportingDocuments;
import admissions.pages.FormStatusTestInformation;
import admissions.pages.FormStatusWorkExperience;
import admissions.pages.LandingPageMyNottingham;
import admissions.pages.NewApplicationMyNottingham;
import admissions.pages.StudentHomeMyNottingham;
import admissions.pages.SubmitApplicationMyNottingham;

public class CourseApplication {

	String courseLvl;
	String country;
	
	public CourseApplication(String courseLvl, String country) {
		this.courseLvl = courseLvl;
		this.country = country;
	}

	public WebDriver courseApplication(WebDriver driver, String nationCode, StudentJsonHandler jsHandler) {
	
		switch (courseLvl) {
		case "PGT":
			PGTApplication(driver, nationCode, jsHandler);
			break;
		case "PGR":
			PGRApplication(driver, nationCode, jsHandler);
			break;
		case "LLL":
			LLLApplication(driver, nationCode, jsHandler);
			break;
		case "UG":
			UGApplication(driver, nationCode, jsHandler);
			break;
		default:
			System.out.println("Switch Statement for Course Application Failed...");
			break;
		}

		return driver;
	}

	public WebDriver PGTApplication(WebDriver driver, String nationCode, StudentJsonHandler jsHandler) {

		// Goes Back to Landing Page .... Login with new Username & Password
		landingPage(driver, jsHandler);

		// Student My Nottingham HomePage
		studentHomepage(driver, jsHandler);

		// Student Starts New Application => PGT
		NewApplicationMyNottingham newApplication = new NewApplicationMyNottingham(driver,jsHandler, country);
		newApplication.SelectCampus().ClickNextBtn().SelectInternational("No").ClickNextBtn().SelectCourseLvl(courseLvl)
				.ClickNextBtn().SelectTerm().ClickNextBtn().ClickSearch().SelectCourse(1).ClickNextBtn()
				.SelectFullTime(true).ClickNextBtn().ClickConfirmSelectionBtn();

		
		/*
		 * 
		 * Form Status Pages
		 * 
		 */
		
		//...................................................................................................................
		// Personal Details
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			personalDetailsMalPGT(driver, nationCode, jsHandler);

		} else {
			personalDetails(driver, nationCode, jsHandler);
		}
		
		//...................................................................................................................
		// Contact & Address
		contactAddress(driver,jsHandler);
		
		//...................................................................................................................
		// Residency
		if(country.equals(UonCountries.MALAYSIA.toString())) {
			residencyMalPGT(driver,jsHandler, country, courseLvl);
			
		}else {
			
			residency(driver,jsHandler, country, courseLvl, nationCode);
		}
		
		//...................................................................................................................
		//Passport Info
		
		//...................................................................................................................
		//Educational Details
		
		//...................................................................................................................
		//Work Experience
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			FormStatusWorkExperience workExperience = new FormStatusWorkExperience(driver);
			workExperience.typeEmployer().typeJobStartDate().typeJobEndDate().selectTypeWork().typeJobTitle()
					.clickSaveBtn();

		}
		
		//...................................................................................................................
		//Personal Statement
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			FormStatusPersonalStatement perStatement = new FormStatusPersonalStatement(driver, country, courseLvl);
			perStatement.careerObjectives().majorStrengths().whyNow().managingTeams().budgets().pastPresent()
					.workAfterGrad().aspectAppeal().careerProg().salaryAdv().greaterBiz().buildCon().careerChange()
					.generalBiz().selfFulfill().whyDecide().visitedNotts().metRep().saveBtn();

		}
		
		//...................................................................................................................
		//Additional Questions
		if (country.equals(UonCountries.UNITED_KINGDOM.toString())) {
			FormStatusAdditionalQuestions addQuestions = new FormStatusAdditionalQuestions(driver, jsHandler, country, courseLvl);
			addQuestions.agentHelpingSelect().intApplicant().saveBtn();

		}
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			FormStatusAdditionalQuestions addQuestions = new FormStatusAdditionalQuestions(driver,jsHandler, country, courseLvl);
			addQuestions.payForStudiesSelect().hearAboutUniSelect().agentHelpingSelect().saveBtn();
		}

		//...................................................................................................................
		// Supporting Documents
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			FormStatusSupportingDocuments suppDocs = new FormStatusSupportingDocuments(driver, country, courseLvl);
			suppDocs.saveBtn().submitAppBtn();
		}
		
		//...................................................................................................................
		// References
		if (country.equals(UonCountries.UNITED_KINGDOM.toString())) {
			FormStatusReferences refs = new FormStatusReferences(driver);
			refs.clickDeclaration().refDummyData().clickSaveBtn().submitAppBtn();			
		}

		// Submit Application Page
		SubmitApplicationMyNottingham submitApp = new SubmitApplicationMyNottingham(driver,jsHandler);
		submitApp.acceptTermsChkBx().submitAppRedBtn();

		return driver;
	}

	public WebDriver PGRApplication(WebDriver driver, String nationCode, StudentJsonHandler jsHandler) {

		//String courseLvl = "PGR";

		// Goes Back to Landing Page .... Login with new Username & Password
		landingPage(driver, jsHandler);

		// Student My Nottingham HomePage
		studentHomepage(driver, jsHandler);

		// Student Starts New Application => PGR
		NewApplicationMyNottingham newApplication = new NewApplicationMyNottingham(driver,jsHandler, country);
		newApplication.SelectCampus().ClickNextBtn().SelectInternational("No").ClickNextBtn().SelectCourseLvl(courseLvl)
				.ClickNextBtn().SelectTerm().ClickNextBtn().ClickSearch().SelectCourse(1).ClickNextBtn()
				.SelectFullTime(true).ClickNextBtn().selectStartDate(1).ClickNextBtn().ClickConfirmSelectionBtn();

		// Personal Details
		personalDetails(driver, nationCode, jsHandler);

		// Contact & Address
		contactAddress(driver,jsHandler);

		// Residency
		residency(driver,jsHandler, country, courseLvl, nationCode);

		// Research Details
		FormStatusResearchDetails researchDetails = new FormStatusResearchDetails(driver, courseLvl);
		researchDetails.typeResearchTopic().typeTopicDescription().saveBtn();

		// Supporting Documents
		// supportingDocs(driver, country, courseLvl);

		// Additional Questions
		FormStatusAdditionalQuestions addQuestions = new FormStatusAdditionalQuestions(driver, jsHandler, country, courseLvl);
		addQuestions.agentHelpingSelect().saveBtn().clickSubmitBtn();

		// Personal Statement
		// FormStatusPersonalStatement perStatement = new
		// FormStatusPersonalStatement(driver, country, courseLvl);
		// perStatement.uKPgrApp().saveBtn().clickSubmitBtn();

		// Submit Application Page
		SubmitApplicationMyNottingham submitApp = new SubmitApplicationMyNottingham(driver, jsHandler);
		submitApp.acceptTermsChkBx().submitAppRedBtn();

		return driver;
	}

	public WebDriver UGApplication(WebDriver driver, String nationCode, StudentJsonHandler jsHandler) {

		//String courseLvl = "UG";

		// Goes Back to Landing Page .... Login with new Username & Password
		landingPage(driver, jsHandler);

		// Student My Nottingham HomePage
		studentHomepage(driver, jsHandler);

		// Student Starts New Application =>
		NewApplicationMyNottingham newApplication = new NewApplicationMyNottingham(driver, jsHandler, country);
		newApplication.SelectCampus().ClickNextBtn().SelectInternational("No").ClickNextBtn().SelectCourseLvl(courseLvl)
				.ClickNextBtn().SelectTerm().ClickNextBtn().ClickSearch().SelectCourse(1).ClickNextBtn()
				.SelectFullTime(true).ClickNextBtn();
		
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			newApplication.ClickNextBtn().ClickConfirmSelectionBtn();

		}else {
			newApplication.ClickConfirmSelectionBtn();
		}
			

		// Personal Details
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			personalDetailsMalPGT(driver, nationCode, jsHandler);
		}else {
			personalDetails(driver, nationCode, jsHandler);
		}

		// Contact & Address
		contactAddress(driver,jsHandler);

		// Residency
		if (country.equals(UonCountries.MALAYSIA.toString())) {
			System.out.println("MAL - Residency");
			residencyMalPGT(driver,jsHandler, nationCode, courseLvl);
		}else {		
			FormStatusResidency residency = new FormStatusResidency(driver, jsHandler, country, courseLvl);
			residency.dateOfEntry().nationalitySelect(nationCode).permResidencySelect(nationCode)
			.resCategorySelect(nationCode).saveBtn();
		}
		
		//Passport Info
		
		//Eductional Details
		
		//Personal Statement
		
		//Additional Questions

		// Supporting Documents
		// FormStatusSupportingDocuments suppDocs = new
		// FormStatusSupportingDocuments(driver, country, courseLvl);
		// suppDocs.selectReasonForNotProviding(1).saveBtn();
		// supportingDocs(driver, country, courseLvl);

		// Additional Questions
		FormStatusAdditionalQuestions addQuestions = new FormStatusAdditionalQuestions(driver,jsHandler, country, courseLvl);
		addQuestions.agentHelpingSelect().intApplicant().saveBtn().clickSubmitBtn();

		// Personal Statement
		// FormStatusPersonalStatement perStatement = new
		// FormStatusPersonalStatement(driver, country, courseLvl);
		// perStatement.uKPgtApp().saveBtn().clickSubmitBtn();

		// Submit Application Page
		SubmitApplicationMyNottingham submitApp = new SubmitApplicationMyNottingham(driver,jsHandler);
		submitApp.acceptTermsChkBx().submitAppRedBtn();

		return driver;
	}

	public WebDriver LLLApplication(WebDriver driver, String nationCode, StudentJsonHandler jsHandler) {

		//String courseLvl = "LLL";

		// Goes Back to Landing Page .... Login with new Username & Password
		landingPage(driver, jsHandler);

		// Student My Nottingham HomePage
		studentHomepage(driver, jsHandler);

		// Student Starts New Application => PGT
		NewApplicationMyNottingham newApplication = new NewApplicationMyNottingham(driver,jsHandler, country);
		newApplication.SelectCampus().ClickNextBtn().SelectInternational("No").ClickNextBtn().SelectCourseLvl(courseLvl)
				.ClickNextBtn().SelectTerm().ClickNextBtn().ClickSearch().SelectCourse(1).ClickNextBtn()
				.SelectFullTime(true).ClickNextBtn().selectStartDate(1).ClickNextBtn().ClickConfirmSelectionBtn();

		personalDetails(driver, nationCode, jsHandler);

		contactAddress(driver,jsHandler);

		// Residency
		residency(driver,jsHandler, country, courseLvl, nationCode);

		// Test Information
		FormStatusTestInformation testInfo = new FormStatusTestInformation(driver,jsHandler, country, courseLvl);
		testInfo.typeOfTest().typeScore().typeGrade().dateOfTest().clickSaveBtn();

		// Supporting Documents
		// FormStatusSupportingDocuments suppDocs = new
		// FormStatusSupportingDocuments(driver, country, courseLvl);
		// suppDocs.saveBtn();
		// supportingDocs(driver, country, courseLvl);

		// Additional Questions
		FormStatusAdditionalQuestions addQuestions = new FormStatusAdditionalQuestions(driver,jsHandler, country, courseLvl);
		addQuestions.overallScore().saveBtn().clickSubmitBtn();

		// Submit Application Page
		SubmitApplicationMyNottingham submitApp = new SubmitApplicationMyNottingham(driver,jsHandler);
		submitApp.acceptTermsChkBx().submitAppRedBtn();

		ApplicationSummaryMyNottingham appSummary = new ApplicationSummaryMyNottingham(driver,jsHandler);
		appSummary.confirmSuccess();

		return driver;
	}

	private WebDriver landingPage(WebDriver driver, StudentJsonHandler jsHandler) {

		// Goes Back to Landing Page .... Login with new Username & Password
		LandingPageMyNottingham landingPage = new LandingPageMyNottingham(driver);
		landingPage.TypeUserName(jsHandler.getStudentUsername()).TypePassword(jsHandler.getStudentPassword())
				.ClickSignInBtn();

		return driver;
	}

	private WebDriver studentHomepage(WebDriver driver,StudentJsonHandler jsHandler) {

		// Student My Nottingham HomePage
		StudentHomeMyNottingham studentHomepage = new StudentHomeMyNottingham(driver,jsHandler);
		studentHomepage.clickApplyNowBtn();
		return driver;
	}

	private WebDriver personalDetails(WebDriver driver, String nationCode, StudentJsonHandler jsHandler) {

		// Personal Details
		FormStatusPersonalDetails perDetails = new FormStatusPersonalDetails(driver,jsHandler);
		perDetails.titleSelect("Mr").birthLocation().birthCountrySelect(nationCode)
				.genderSelect(jsHandler.getStudentSex()).countryOfCitizenShip(nationCode).disabledSelect("NO")
				.convictionRadio("NO").saveBtn();
		return driver;
	}

	private WebDriver personalDetailsMalPGT(WebDriver driver, String nationCode, StudentJsonHandler jsHandler) {
		System.out.println("----->     MAL-PGT - Personel Details");
		// Personal Details
		FormStatusPersonalDetails perDetails = new FormStatusPersonalDetails(driver,jsHandler);
		perDetails.titleSelect("Mr").longNameTxt(jsHandler.getStudentFirstName(),  jsHandler.getStudentLastname()).genderSelect(jsHandler.getStudentSex()).maritalSelect("Single")
				.countryOfCitizenShip(nationCode).disabledSelect("NO").convictionRadio("NO").religionSelect()
				.raceSelect().saveBtn();
		return driver;
	}

	private WebDriver contactAddress(WebDriver driver,StudentJsonHandler jsHandler) {

		// Contact & Address
		FormStatusContactAndAddress contactAddr = new FormStatusContactAndAddress(driver,jsHandler);
		contactAddr.countryCodeTxtBx().saveBtn(true);
		return driver;
	}

	private WebDriver residency(WebDriver driver,StudentJsonHandler jsHandler, String country, String courseLvl, String nationCode) {

		// Residency
		FormStatusResidency residency = new FormStatusResidency(driver,jsHandler, country, courseLvl);
		residency.dateOfEntry().nationalitySelect(nationCode).permResidencySelect(nationCode).resCategorySelect("P")
				.saveBtn();

		return driver;
	}
	
	private WebDriver residencyMalPGT(WebDriver driver,StudentJsonHandler jsHandler, String country, String courseLvl) {
		System.out.println("----->     Mal-PGT - Residency");
		
		// Residency
		FormStatusResidency residency = new FormStatusResidency(driver,jsHandler, country, courseLvl);
		residency.NricRadio(true).saveBtn();

  	    return driver;
	}

//	private WebDriver supportingDocs(WebDriver driver, String country, String courseLvl) {
//		FormStatusSupportingDocuments suppDocs = new FormStatusSupportingDocuments(driver, country, courseLvl);
//		suppDocs.selectDocumentType(1).selectReasonForNotProviding(1).saveBtn();
//		return driver;
//	}

}
