package frameworkTest;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.mongodb.BasicDBObject;

import cs.curriculum.management.pages.StudentSupport;
import framework.MongoInterface;
import framework.TestBase;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterClass;

import uiAutomation.uiActions.MyNottingham.MyNottingham_Login;

public class TC001_MongoTest extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC001_MongoTest.class.getName());	

	MongoInterface mi;
	String userid;
	
	
	@DataProvider(name="MongoData")
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "MongoData");
		return testRecords;
	}

	
	@BeforeClass
	public void setUp() throws IOException{
	}
	

	@Test (dataProvider="MongoData")
	public void Login(String one, String two, String three){
		System.out.println(one+two+three);
	}

	

	@AfterClass
	public void endTest(){
		log.info("=========== Finished Create StudentSupport Test=============");
		//driver.close();
	}

}
