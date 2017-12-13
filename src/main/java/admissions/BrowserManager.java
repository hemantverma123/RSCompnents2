package admissions;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserManager {

	static WebDriver driver;
	static String browser = "Chrome";

	public static String getBrowser() {
		return browser;
	}

	public static void setBrowser(String browser) {
		BrowserManager.browser = browser;
	}

	public BrowserManager() {
		// TODO Auto-generated constructor stub
	}

	public static WebDriver BrowserSelect() {
		//Set Default Value if not set by System.getProperty
		if(System.getProperty("browserType") != null) browser = System.getProperty("browserType");
		
		System.out.println("Browser Being Used is -> "+browser);
		
		
		//Set Browser to use ...
		if (browser.equalsIgnoreCase("firefox")) {
			FirefoxProfile firefoxProfile = new FirefoxProfile();
			firefoxProfile.setPreference("browser.privatebrowsing.autostart", true);
			FirefoxOptions options = new FirefoxOptions();
			options.setLogLevel(Level.OFF);
			options.setProfile(firefoxProfile);
			DesiredCapabilities capabilities = options.addTo(DesiredCapabilities.firefox());  //tried to switch off the detailed logs but not working
			System.setProperty("webdriver.gecko.driver", "C:\\SeleniumDrivers\\geckodriver.exe");
			driver = new FirefoxDriver(capabilities);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\SeleniumDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\SeleniumDrivers\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:\\SeleniumDrivers\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("phantom")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setJavascriptEnabled(true);
			caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					"C:\\SeleniumDrivers\\phantomjs.exe");
			driver = new PhantomJSDriver();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;

	}

}
