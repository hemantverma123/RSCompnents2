package cs.test.admissions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import uiAutomation.testBase.TestBase;
import uiAutomation.uiActions.MyNottingham.MyNottingham_Login;

public class TC000_MyNottingham_Login extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC000_MyNottingham_Login.class.getName());	

	MyNottingham_Login loginpage;
	
	@BeforeClass
	public void setUp() throws IOException{
     init();

	}
	
//	@Test
//	public void verifyLoginWithInvalidCredentails(){
//		log.info("=========== Starting verifyLoginWithInvalidCredentails Test=============");
//		loginpage = new OP_Login(driver);
//		loginpage.loginToApplication("brahv", "Agra0717");
//		getScreenShot("verifyLoginWithInvalidCredentails");
//		Assert.assertEquals(loginpage.getInvalidLoginText(), "Your User ID and/or Password are invalid");
//		log.info("=========== Finished verifyLoginWithInvalidCredentails Test=============");
//	}
	

	@Test
	public void verifyLoginWithValidCredentails(){
		log.info("=========== Starting verifyLoginWithValidCredentails Test=============");
		loginpage = new MyNottingham_Login(driver);
		loginpage.loginToApplication("user", "pwd");
		getScreenShot("verifyLoginWithValidCredentails");
		Assert.assertEquals(loginpage.getHomePageText()," Manage Documents");
		log.info("=========== Finished verifyLoginWithValidCredentails Test=============");
	}

	
	@AfterClass
	public void endTest(){
	driver.close();
	}

}
