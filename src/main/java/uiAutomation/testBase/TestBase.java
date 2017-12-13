package uiAutomation.testBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.TimeoutException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import uiAutomation.customListner.WebEventListener;
import uiAutomation.excelReader.Excel_Reader;

/**
 * 
 * @author Hemant Verma
 *
 */
public class TestBase {

	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	public WebDriver driver;
	protected int timeOutInSeconds=10;
	
	Excel_Reader excel;
	// public EventFiringWebDriver driver;
	public WebEventListener eventListener;
	public Properties prop = new Properties();
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;

	public WebDriver getDriver() {
		return driver;
	}

	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/main/java/uiAutomation/report/test" + formater.format(calendar.getTime()) + ".html", false);
	}

	public void loadData() throws IOException {
		File file = new File(System.getProperty("user.dir") + "/src/main/java/uiAutomation/config/config.properties");
		FileInputStream f = new FileInputStream(file);
		prop.load(f);

	}

	public void setDriver(EventFiringWebDriver driver) {
		this.driver = driver;
	}

	public void init() throws IOException {
		loadData();
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		System.out.println(prop.getProperty("browser"));
		
		if(prop.getProperty("gridmode").equals("true"))
			launchapp(prop.getProperty("browser"));  //it will launch the test in grid mode
		else
			selectBrowser(prop.getProperty("browser")); //it will run on local browser mode
		
		getUrl(prop.getProperty("url"));
	}

	//@SuppressWarnings("deprecation")
	public void selectBrowser(String browser) {
		System.out.println("selectBrowser: OperatingSystem - "+ System.getProperty("os.name"));
		System.out.println("selectBrowser: uer.dir="+ System.getProperty("user.dir"));
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equals("chrome")) {
				System.out.println("selectBrowser: Executing on CHROME");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				// driver = new EventFiringWebDriver(dr);
				// eventListener = new WebEventListener();
				// driver.register(eventListener);
			} else if (browser.equals("firefox")) {
				System.out.println("selectBrowser: Executing on FireFox");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				driver = new FirefoxDriver();
				// driver = new EventFiringWebDriver(dr);
				//eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			} else if (browser.equals("iexplorer")) {  //same code works fine with the RemoteWebDriver
				System.out.println("selectBrowser: Executing on iexplorer");
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
				DesiredCapabilities cap = DesiredCapabilities.internetExplorer();

				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				//cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);

				//driver = new InternetExplorerDriver(cap);
				driver = new InternetExplorerDriver(cap);
				// driver = new EventFiringWebDriver(dr);
				//eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			}
			else if(browser.equals("HtmlUnitDriver")) {
				System.out.println("selectBrowser: Executing on HtmlUnitDriver");
				driver = new HtmlUnitDriver(BrowserVersion.CHROME); 
			}
			else if(browser.equals("PhantomJSDriver")) {
				System.out.println("selectBrowser: Executing on PhantomJSDriver");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				//System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "/drivers/phantomjs.exe");  //this line is useless
				capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("user.dir") + "/drivers/phantomjs.exe");
				driver = new PhantomJSDriver(capabilities); 
			}
		} else if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
				driver = new ChromeDriver();
				// driver = new EventFiringWebDriver(dr);
				// eventListener = new WebEventListener();
				// driver.register(eventListener);
			} else if (browser.equals("firefox")) {
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/drivers/geckodriver");
				driver = new FirefoxDriver();
				// driver = new EventFiringWebDriver(dr);
				eventListener = new WebEventListener();
				// driver.register(eventListener);
				// setDriver(driver);
			}
		}
	}

	public void getUrl(String url) {
		log.info("navigating to :-" + url);
		driver.get(url);
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public String[][] getData(String excelName, String sheetName) {
		String path = System.getProperty("user.dir") + "/src/main/java/uiAutomation/data/" + excelName;
		excel = new Excel_Reader(path);
		String[][] data = excel.getDataFromSheet(sheetName, excelName);
		return data;
	}

	
	public String getStudentData(String excelName, String sheetName, String colName, int rowNum) {
		String path = System.getProperty("user.dir") + "/src/main/java/uiAutomation/data/" + excelName;
		excel = new Excel_Reader(path);
		String data = excel.getCellData(sheetName, colName, rowNum);
		return data;
	}

	public String writeTestData(String excelName, String sheetName, String colName, int rowNum, String msg) {
		String path = System.getProperty("user.dir") + "/src/main/java/uiAutomation/data/" + excelName;
		excel = new Excel_Reader(path);
		String data = excel.writeCellData(path,sheetName, colName, rowNum, msg);
		return data;
	}
	
	public WebElement waitForElement(WebDriver driver, int timeOutInSeconds, WebElement element) {  //for page factory initialized elements
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}
	
	public WebElement waitForElementClickable(WebDriver driver, int timeOutInSeconds, WebElement element) {  //for page factory initialized elements
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	
	public void waitForPageLoad(final WebDriver driver, int timeOutInSeconds) {

		 WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);

		    wait.until(new ExpectedCondition<Boolean>() {
		        public Boolean apply(WebDriver wdriver) {
		            return ((JavascriptExecutor) driver).executeScript(
		                "return document.readyState"
		            ).equals("complete");
		        }
		    });
	}
	
	public WebElement waitForElementVisible(WebDriver driver, int timeOutInSeconds, By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
		return driver.findElement(by);
		
	}

	
	public void ProcessingImgInvisible(WebDriver driver, int timeOutInSeconds, By by) { 
		if(isElementPresent(driver, by)) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
		}
	}


	public void waitAndSwitchtoframe(WebDriver driver, int timeOutInSeconds, By by) { 
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(by));
	}

	public void getFrames(WebDriver driver) { 
	    
		//int size = driver.findElements(By.tagName("iframe")).size();

//	    for(int i=0; i<=size; i++){
//		driver.switchTo().frame(i);
//		//here we can find element in each frame
//	    driver.switchTo().defaultContent();
//	    }
	    
	    List<WebElement> ele = driver.findElements(By.tagName("iframe"));
	    System.out.println("Number of frames in a page :" + ele.size());
	    for(WebElement el : ele){
	      //Returns the Id of a frame.
	        System.out.println("Frame Id :" + el.getAttribute("id"));
	      //Returns the Name of a frame.
	        System.out.println("Frame name :" + el.getAttribute("name"));
	    }

	}


	
	
	public void getScreenShot(String name) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/uiAutomation/screenshot/";
			File destFile = new File((String) reportDirectory + name + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pause(int sec){
		try{
			Thread.sleep(sec*1000);	
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void highlightMe(WebDriver driver, WebElement element) throws InterruptedException {
		// Creating JavaScriptExecuter Interface
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Execute javascript
		js.executeScript("arguments[0].style.border='4px solid yellow'", element);
		Thread.sleep(3000);
		js.executeScript("arguments[0].style.border=''", element);
	}

	public Iterator<String> getAllWindows(WebDriver driver) {

//		String parentWindowHandler = driver.getWindowHandle(); 
//		String subWindowHandler = null;

		Set<String> handles = driver.getWindowHandles();
		
		System.out.println("Number of Windows: "+handles.size());
		Iterator<String> iterator = handles.iterator();
//		while (iterator.hasNext()){
//		    subWindowHandler = iterator.next();
//		    driver.switchTo().window(subWindowHandler);
//		    System.out.println("subWindowHandler: " + subWindowHandler + " Window Title: "+ driver.getTitle() + " Window current URL: " + driver.getCurrentUrl());// + " Window page source: " + driver.getPageSource());
//		}
//
//		driver.switchTo().window(parentWindowHandler);
//	    System.out.println("parentWindowHandler: " + parentWindowHandler + " Window Title: "+ driver.getTitle() + " Window current URL: " + driver.getCurrentUrl());// + " Window page source: " + driver.getPageSource());
		return iterator;
	}

	public void getScreenShot(WebDriver driver, ITestResult result, String folderName) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/uiAutomation/";
			File destFile = new File((String) reportDirectory + "/" + folderName + "/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");

			FileUtils.copyFile(scrFile, destFile);

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getScreenShotOnSucess(WebDriver driver, ITestResult result) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		String methodName = result.getName();

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/uiAutomation/";
			File destFile = new File((String) reportDirectory + "/failure_screenshots/" + methodName + "_" + formater.format(calendar.getTime()) + ".png");

			FileUtils.copyFile(scrFile, destFile);

			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String captureScreen(String fileName) {
		if (fileName == "") {
			fileName = "blank";
		}
		File destFile = null;
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			String reportDirectory = new File(System.getProperty("user.dir")).getAbsolutePath() + "/src/main/java/uiAutomation/screenshot/";
			destFile = new File((String) reportDirectory + fileName + "_" + formater.format(calendar.getTime()) + ".png");
			FileUtils.copyFile(scrFile, destFile);
			// This will help us to link the screen shot in testNG report
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'> <img src='" + destFile.getAbsolutePath() + "' height='100' width='100'/> </a>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
	}

	public void log(String data) {
		log.info(data);
		Reporter.log(data);
		test.log(LogStatus.INFO, data);
	}

	public void getresult(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(LogStatus.PASS, result.getName() + " test is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, result.getName() + " test is skipped and skip reason is:-" + result.getThrowable());
		} else if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.ERROR, result.getName() + " test is failed" + result.getThrowable());
			String screen = captureScreen("");
			test.log(LogStatus.FAIL, test.addScreenCapture(screen));
		} else if (result.getStatus() == ITestResult.STARTED) {
			test.log(LogStatus.INFO, result.getName() + " test is started");
		}
	}

	@AfterMethod()
	public void afterMethod(ITestResult result) {
		getresult(result);
	}

	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		closeBrowser();
	}

	public void closeBrowser() {
		//driver.quit();
		log.info("browser closed");
		extent.endTest(test);
		extent.flush();
	}

	public WebElement waitForElement(WebDriver driver, long timeOutInSeconds,WebElement element) {  //for page factory initialized variables
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	public WebElement waitForElement(WebDriver driver, long timeOutInSeconds,By by) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		try {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		wait.until(ExpectedConditions.elementToBeClickable(by));
		return driver.findElement(by);
		}catch(TimeoutException te) {
			te.printStackTrace();
			return null;
		}
	}
	
	//@Parameters("browser")
	//@BeforeTest
	public void launchapp(String browser) throws IOException {   //This is Grid implementation of the project

		if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equals("chrome")) {
				//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver");
				System.out.println(" Executing on CHROME");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				String Node = "http://localhost:5001/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} else if (browser.equals("firefox")) {
				//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				System.out.println(" Executing on FireFox");
				String Node = "http://172.16.123.130:5000/wd/hub";
				DesiredCapabilities cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URL(Node), cap);
			} else if (browser.equalsIgnoreCase("iexplorer")) {
				System.out.println(" Executing on IE");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("ie");
				String Node = "http://192.168.0.101:5555/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}
		if (System.getProperty("os.name").contains("Window")) {
			System.out.println("LaunchApp: user dir = " + System.getProperty("user.dir"));
			if (browser.equals("chrome")) {
				System.out.println("LaunchApp: Executing on CHROME Grid");
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
				DesiredCapabilities cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				String Node = "http://localhost:4441/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				//loadData();
				getUrl(prop.getProperty("url"));
			} else if (browser.equals("firefox")) {
				System.out.println("LaunchApp: Executing on FireFox");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
				String Node = "http://localhost:4441/wd/hub";
				DesiredCapabilities cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				driver = new RemoteWebDriver(new URL(Node), cap);
				//loadData();
				getUrl(prop.getProperty("url"));
			} else if (browser.equalsIgnoreCase("iexplorer")) {
				System.out.println("LaunchApp: Executing on IE");
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
				DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
				cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
				cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
				//cap.setBrowserName("iexplorer");
				String Node = "http://localhost:4441/wd/hub";
				driver = new RemoteWebDriver(new URL(Node), cap);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				// Launch website
				getUrl(prop.getProperty("url"));
			} else {
				throw new IllegalArgumentException("The Browser Type is Undefined");
			}
		}
	}
	
	protected boolean isElementPresent(WebDriver driver, By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }

	public boolean isAlertPresent() 
	{ 
	    try 
	    { 
	        driver.switchTo().alert(); 
	        return true; 
	    }   // try 
	    catch (NoAlertPresentException Ex) 
	    { 
	        return false; 
	    }   // catch 
	}   // isAlertPresent()
	
	
	public void writeToFile(String fname,String studentID, String appNumber, String username, String password, String studentdata,String studentType) {
		System.out.println("Writing in the data file...");

		String path = System.getProperty("user.dir") + "/src/main/java/uiAutomation/data/" + fname;

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
		    fw = new FileWriter(path, true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
		    out.println("\nStudentType: "+studentType);
		    out.println("StudentID: "+ studentID);
		    out.println("Application Number: "+ appNumber);
		    out.println("Username: "+username);
		    out.println("Password: "+password);
		    out.println("Student Record: "+studentdata);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		finally {
		    try {
		        if(out != null)
		            out.close();
		        if(bw != null)
		            bw.close();
		        if(fw != null)
		            fw.close();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	}
	
	public void writeToDataFile(String fname,String msg) {
		System.out.println("Writing in the test data file...");

		String path = System.getProperty("user.dir") + "/src/main/java/uiAutomation/data/" + fname;

		FileWriter fw = null;
		BufferedWriter bw = null;
		PrintWriter out = null;
		try {
		    fw = new FileWriter(path, true);
		    bw = new BufferedWriter(fw);
		    out = new PrintWriter(bw);
		    out.println(msg);
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		finally {
		    try {
		        if(out != null)
		            out.close();
		        if(bw != null)
		            bw.close();
		        if(fw != null)
		            fw.close();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}

	}
	
	public void SelectElement(WebDriver driver,By by,String item) {
		WebElement element = waitForElement(driver, timeOutInSeconds, by);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(item);
	}

	public void SelectDate(WebDriver driver,By by) {
		//by default it will select the future date 1st day of Dec of current year
		waitForElement(driver,timeOutInSeconds,by).click();
		SelectElement(driver, By.id("PTMonth"), "December");
		waitForElement(driver, timeOutInSeconds, By.xpath("//*[@id='bodyCalendar']/tbody/tr[1]/td[8]")).click();
	}
}
