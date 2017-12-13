package cs.test.peam;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class HeadlessChrome
{
  @Test
  public void createChromeDriverHeadless() throws IOException
  {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setBinary("C:\\Users\\brahv\\AppData\\Local\\Google\\Chrome SxS\\Application");
      chromeOptions.addArguments("--headless");

      WebDriver Driver = new ChromeDriver(chromeOptions);
      Driver.navigate().to("https://the-internet.herokuapp.com/login");

      WebDriverWait waitForUsername = new WebDriverWait(Driver, 5000);
      waitForUsername.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

      Driver.findElement(By.id("username")).sendKeys("tomsmith");
      Driver.findElement(By.cssSelector("button.radius")).click();

      WebDriverWait waitForError = new WebDriverWait(Driver, 5000);
      waitForError.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
      
      System.out.println("headless chrome completed...");

      Assert.assertTrue(Driver.findElement(By.id("flash")).getText().contains("Your password is invalid!"));
      Driver.quit();
  }
}