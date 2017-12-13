package cs.test.peam;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class HeadlessBrowsers {

	@Test
	public void allBrowsers() {
		System.out.println("Hello Browser");
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setJavascriptEnabled(true);
        //capabilities.setCapability("takesScreenshot", true);

		//PhantomJS works with only v3.5.3 instead of v3.6.0
		capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, System.getProperty("user.dir") + "/drivers/phantomjs.exe");
        //System.setProperty("phantomjs.binary.path", System.getProperty("user.dir") + "/drivers/phantomjs.exe");
        
        //WebDriver driver = new PhantomJSDriver();
		WebDriver driver = new PhantomJSDriver(capabilities);
		//WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new InternetExplorerDriver();
		//WebDriver driver = new HtmlUnitDriver();
		
		driver.navigate().to("https://www.google.co.in/");
		driver.get("http://google.com");
	}
	
}
