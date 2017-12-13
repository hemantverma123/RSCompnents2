package helperCodeTest;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.testng.annotations.*;

import com.mongodb.MongoCommandException;
import com.mongodb.MongoSecurityException;
import com.relevantcodes.extentreports.ExtentReports;

import helpercode.MongoInterface;
import uiAutomation.testBase.TestBase;
import com.mongodb.BasicDBObject;

public class MongoInterfaceTest extends TestBase{

	BasicDBObject query;
	MongoInterface mi;

	//public static final Logger log = Logger.getLogger(TC003_CaseCreation.class.getName());	

	@DataProvider(name="MongoData")  //can be used to import data from the excel sheet
	public String[][] getTestData(){
		String[][] testRecords = getData("TestData.xlsx", "MongoData");
		return testRecords;
	}

	
	@BeforeClass
	public void setUp() throws IOException{
		log.info("=========== Starting MongoDB Interface Test=============");
		mi = new MongoInterface("generalStorage1");
		//mi = new MongoInterface("students");
		// mi.createuser();
	}
	
	
	@Test (dataProvider="MongoData", priority=1)
	public void writeToMongodb(String one, String two, String three) {
		System.out.println("Writing excel data to Mongodb...");
		System.out.println(one+two+three+ new Date());
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy:hh-mm-ss");
		//formater.format(calendar.getTime());

		BasicDBObject msg = new BasicDBObject();
		msg.append("one", one).append("two", two).append("three", three).append("InsertDate", new Date()).append("CalenderDate", formater.format(calendar.getTime()));

		
		mi.writeToMongofromExcel(msg);
	}
	
	@Test (priority=2)
	public void runMangoTest() throws IOException {
		System.out.println("running mongo test...");

//		String userid = "hexgb1";
//		String casenum = "Case 223859";
//		
//		BasicDBObject msg = new BasicDBObject();  
//		msg.append("userid", userid).append("casenumber", casenum);
//		System.out.println("Writing to MongoDB: " + msg);
//		mi.addDataToMongo(msg);
		
		query = new BasicDBObject();
		mi.showMongoData(query);
		//mi.writeMongoToFile("mongodata.csv",query);
		
//		query.put("First",new BasicDBObject("$regex", "Rec").append("$options", "i"));
//		mi.showMongoData(query);
//		query.put("First",new BasicDBObject("$regex", "^Rec").append("$options", "i"));
//		mi.showMongoData(query);
//		query.put("First",new BasicDBObject("$regex", "^record$").append("$options", "i"));
//		mi.showMongoData(query);
//		query.put("First",new BasicDBObject("$regex", "Record$").append("$options", "i"));
//		mi.showMongoData(query);

		mi.findDocumentById("5a0d6e69fddfb02004f83a8c");
	}

	@AfterClass 
	public void endTest(){
		log.info("=========== Finished MongoDB Interface Test=============");
		mi.mongoClose();
	}
	
	
}
