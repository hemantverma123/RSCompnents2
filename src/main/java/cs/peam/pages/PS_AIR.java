package cs.peam.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import cs.peam.pages.PS_Login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.time.StopWatch;

import uiAutomation.testBase.TestBase;

/**
 * 
 * @author Hemant Verma
 *
 */

public class PS_AIR extends TestBase {

	@FindBy(id="SSR_AIRSRCH_WRK_SSR_ITEM_ID")
	WebElement Academic_Item_ID;

	@FindBy(id="SSR_AIRSRCH_WRK_SSR_PB_SRCH")
	WebElement btn_Search;

	WebDriver driver;	
	
	PS_HomePage homepage;
	StopWatch timer = new StopWatch();
	
	private final static Logger log = Logger.getLogger(PS_AIR.class.getName());

	public PS_AIR(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		homepage = new PS_HomePage(driver);
		
		log.info("PS_AIR called");		
	}
	

	public void OpenAIR()
	{
		homepage.OpenAIR();
	}

	public String AIRPageValidation()
	{
		String text = homepage.homePageHeaderValidation();
		return text;
	}
	

	public HashMap<String, String> verifyCompensatableCourses(List<String> LAcademicIDs)
	{
		HashMap<String, String> hm_compCourses = new HashMap<>(); 
		
		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));

		for(String AcademicID:LAcademicIDs) {
			waitForElement(driver,10,Academic_Item_ID).sendKeys(AcademicID);
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			waitForElement(driver,10,btn_Search).click();
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			
			waitForElement(driver,10,By.id("RSLT_ITEM_ID$0")).click();
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			waitForElement(driver,10,By.id("ICTAB_1")).click();
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			waitForElement(driver,10,By.id("ICTAB_2_194")).click();
			ProcessingImgInvisible(driver, 10, By.id("processing"));
			
			String str = driver.findElement(By.xpath("//*[@id='win0divSSR_AIR_ELEMGP$0']/table/tbody/tr/td[2]/span[3]")).getText();
			int numOfCourses = Integer.parseInt(str.substring(str.indexOf("of")+2).trim());
			
			int i=0;  
		    while(i<numOfCourses){   
	
		    	String courseName = driver.findElement(By.xpath("//*[@id='ITEM_DESCR3$"+i+"']")).getText();
	
		    	waitForElement(driver,10,By.id("DERIVED_SSR_AIR_DISPLAY_ATTR$"+i)).click();
				ProcessingImgInvisible(driver, 10, By.id("processing"));
		        
				String CourseChildItemID = waitForElement(driver,10,By.id("SSR_AIR_ELEM_SSR_CHILD_ITEM_ID")).getText();
				boolean compensatable = waitForElement(driver,10,By.id("SSR_AIR_ELEM_AD_SCC_CAF_ATTR_YNO$192$$0")).isSelected();
		        
		        if(compensatable)
		        {
		        	System.out.println(AcademicID + " - " + CourseChildItemID + " - " + courseName + "	compensatable!!!!");
		        	hm_compCourses.put(CourseChildItemID, "Comp");
		        }
		        else
		        {
		        	//student must pass non compensatable course
		        	System.out.println(AcademicID + " - " + CourseChildItemID + " - " + courseName + "	not compensatable!!!!");
		        	hm_compCourses.put(CourseChildItemID, "notComp");
		        	
		        }
		    
				waitForElement(driver,10,By.id("DERIVED_SSR_AIR_CANCEL_PB")).click();
				ProcessingImgInvisible(driver, 10, By.id("processing"));
		        
		    i++;  
		    }  
		    waitForElement(driver,10,By.id("#ICCancel")).click();	
		    	
		}			
		
		driver.switchTo().defaultContent();
		return hm_compCourses;

	}

	public int SpecialWeightedAverageScore(String strAcademic_Item_ID)    
	{
		String SpecialWeightedAverageScoreValue = "0";
		int special_weighted_average = 0;

		waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));

		System.out.println("SpecialWeightedAverageScore: Academic_Item_ID " + strAcademic_Item_ID);
		waitForElement(driver,10,Academic_Item_ID).clear();			
		waitForElement(driver,10,Academic_Item_ID).sendKeys(strAcademic_Item_ID);  //00000098037
		
		new Select(driver.findElement(By.id("SSR_AIRSRCH_WRK_SCC_CAF_ATTRIB_NM"))).selectByVisibleText("UoN Special Weighted Average"); //Academic Item Attribute
		waitForElement(driver,10,By.id("SSR_AIRSRCH_WRK_SSR_PB_SRCH")).click();
		ProcessingImgInvisible(driver, 10, By.id("processing"));
		
		//*[text()[contains(.,'No matching records found')]]/parent::*/following-sibling::*/descendant::*[@id="#ICOK"]
		
		driver.switchTo().defaultContent();
		if(isElementPresent(driver, By.id("#ICOK"))){  //todo need to fix here as this is not either alert or an element. need to see how to handle modal. gives error on academic id 00000098037
			System.out.println("Stage " + strAcademic_Item_ID + " does not have associated UoN Special Weighted Average value");
			waitForElement(driver, 10,By.id("#ICOK")).click();						
		}
		else{
			waitAndSwitchtoframe(driver,10,By.id("ptifrmtgtframe"));	
			waitForElement(driver, 10,driver.findElement(By.id("RSLT_ITEM_ID$0")));
				driver.findElement(By.id("RSLT_ITEM_ID$0")).click();
				SpecialWeightedAverageScoreValue =  driver.findElement(By.id("SSR_AIR_ADD_SCC_CAF_ATTR_NVAL$0")).getAttribute("value");
				if(SpecialWeightedAverageScoreValue.equals(""))
					special_weighted_average =0;
				else special_weighted_average = Integer.parseInt(SpecialWeightedAverageScoreValue);
		}
		driver.switchTo().defaultContent();
		return special_weighted_average;
	}


}
