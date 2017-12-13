package cs.test.peam;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import cs.peam.pages.PS_HomePage;
import cs.peam.pages.PS_Login;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.testBase.TestBase;

public class TC001_PS_Login extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_PS_Login.class.getName());	

	PS_Login loginpage;
	PS_HomePage homepage;
	

	
	@DataProvider(name="loginData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "LoginTestData");
		return testRecords;
	}

	
	@BeforeClass
	public void setUp() throws IOException{
    init();
	homepage = new PS_HomePage(driver);  //it will login first

	}
	
	
	@Test (dataProvider="loginData")
	public void verifyLoginWithValidCredentails(String userid, String encryptedPassword, String runMode){
		
		try {
			if(runMode.equalsIgnoreCase("n"))
				throw new SkipException("user marked the user "+ userid + " record as no run");
			
			log.info("=========== Starting verifyLoginWithValidCredentails Test=============");
			homepage.login(userid,encryptedPassword,runMode);
			Assert.assertEquals(homepage.verifyLogin()," Manage Documents");
			homepage.LogOut();
			log.info("=========== Finished verifyLoginWithValidCredentails Test=============");
		}catch (SkipException se) {System.out.println("Skipping the user "+ userid + " as marked no run");} 
 		 catch (Exception e) {
			System.out.println("Taking screenshot as verifyLoginWithValidCredentails for the user " + userid);
			getScreenShot("verifyLoginWithValidCredentails");
		}
	}
	
	@Test
	public void testfun2() {
		System.out.println("test fun2");
	}


	@AfterClass
	public void endTest(){
	driver.close();
	}

}
