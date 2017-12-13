package cs.test.peam;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_IAM;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;


public class TC001_PS_ManagingMandatoryCoursesInIAM extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_ManagingMandatoryCoursesInIAM.class.getName());	

	PS_HomePage homepage;
	PS_IAM iam;	
	

	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}

	@DataProvider(name="IAMData")
	public String[][] getIAMData(){
		String[][] testRecords = getData("TestData.xlsx", "IAM");
		return testRecords;
	}
	
	@BeforeClass
	public void setUp() throws IOException{
     init();
     homepage = new PS_HomePage(driver);
     iam = new PS_IAM(driver);
	}
	

	@Test (dataProvider="loginData")
	public void Login(String userid, String encryptedPassword, String runMode){
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the user "+ userid + " record as no run");

			homepage.login(userid,encryptedPassword,runMode);
		}catch (SkipException se) {System.out.println("Skipping the user "+ userid + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as verifyLoginWithValidCredentails for the user " + userid);
			getScreenShot("verifyLoginWithValidCredentails");
		}
	}

	@Test(dependsOnMethods={"Login"},dataProvider="IAMData")
	public void ManagingMandatoryCoursesInIAM(String student, String runMode) {
		try {
			if(runMode.equalsIgnoreCase("n"))
			throw new SkipException("user marked the student "+ student + " record as no run");

			log.info("=========== Starting TC001_PS_ManagingMandatoryCoursesInIAM Test=============");
			iam.OpenIAM();

			//Assert.assertEquals(iam.homePageHeaderValidation(),"Individual Activity Manager");
			//String student = getStudentData("TestData.xlsx", "IAM", "Student", 2);

			System.out.println("student for IAM is: " + student);
		    iam.searchStudentOnIAM(student);
			iam.verifyMandatoryCourse(student);
			log.info("=========== Finished TC001_PS_ManagingMandatoryCoursesInIAM Test=============");
		}catch (SkipException se) {System.out.println("Skipping the student "+ student + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as IAM for the user " + student);
			getScreenShot("IAM");
		}
	}

	@AfterClass
	public void endTest(){
	driver.close();
	}
}
