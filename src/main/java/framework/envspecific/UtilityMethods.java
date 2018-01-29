package framework.envspecific;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityMethods {
	
	By processing = By.id("processing");
	By saving = By.id("saveWait_win0");
	
	
	public void PauseForJS(WebDriver driver, float time, boolean waitForIcon) {
		if (waitForIcon) {
			try {
				WebElement pageWaitElement = driver.findElement(processing);
				WebDriverWait wait = new WebDriverWait(driver, (long) time);
				wait.until(ExpectedConditions.invisibilityOf(pageWaitElement));
				//System.out.println("Funky Circle has Gone!!!!");
				WebElement pageSavingElement = driver.findElement(saving);
				WebDriverWait saveWait = new WebDriverWait(driver, (long) time);
				saveWait.until(ExpectedConditions.invisibilityOf(pageSavingElement));
				//System.out.println("Saving Wait has Gone!!!!");

			} catch (Exception e) {
				System.out.println(
						"The Spinning Page Wait Icon was present for more that " + time + "Seconds.............");
				//Assert.fail();
			}
		} else {
			try {
				Thread.sleep((long) (time * 1000));
			} catch (InterruptedException e) {
				System.out.println("Even a Thread Sleep didn't work..................");
				e.printStackTrace();
			}
		}

	}
	
	public WebDriver checkForGreenTick(WebDriver driver, String domain) {
		
		WebElement imgGreenTick = driver.findElement(By.id("CIBAA_IMAGE_WRK_ABSENCE_IMAGE_02$0"));
		try {
			System.out.println(imgGreenTick.getAttribute("src"));
			assertEquals(domain+"/cache/CIBAA_COMPLETE_ICON_UKE_1.png", imgGreenTick.getAttribute("src"));
		} catch (Exception e) {
			System.out.println("Personal Details Green Tick Icon is Missing..");
		}
		
		return driver;
	}
	
	public boolean isElementPresent(WebDriver driver, By by){
        try{
            driver.findElement(by);
            return true;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }
	
	public static void waitForWindow(WebDriver driver)
            throws InterruptedException {
        //wait until number of window handles become 2 or until 6 seconds are completed.
        int timecount = 1;
        do {
            driver.getWindowHandles();
            Thread.sleep(200);
            timecount++;
            if (timecount > 30) {
                break;
            }
        }while (driver.getWindowHandles().size() != 2);

    }

    public static void switchToModalDialog(WebDriver driver, String parent) { 
            //Switch to Modal dialog
        if (driver.getWindowHandles().size() == 2) {
            for (String window : driver.getWindowHandles()) {
                if (!window.equals(parent)) {
                    driver.switchTo().window(window);
                    System.out.println("Modal dialog found");
                    break;
                }
            }
        }
    }


}
