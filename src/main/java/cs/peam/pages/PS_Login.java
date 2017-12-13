package cs.peam.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import uiAutomation.testBase.TestBase;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;


/**
 * 
 * @author Hemant Verma
 *
 */

public class PS_Login extends TestBase {
	
	WebDriver driver;

	private final static Logger log = Logger.getLogger(PS_Login.class.getName());

	@FindBy(id="userid")
	WebElement username;

//	@FindBy(id = "email")
//	WebElement emailAddress;

	@FindBy(id = "pwd")
	WebElement passowrd;

	@FindBy(xpath = "//*[@name='Submit']")
	WebElement SignIn;

	@FindBy(id = "psloginerror")
	WebElement psloginerror;
	
	public PS_Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnsignIn() {
		waitForElement(driver, 10, SignIn);
		SignIn.click();
		//log.info("click on sign in and object is:-" + SignIn.toString());
	}

	public void enterUsername(String userid) {
		waitForElement(driver, 10, username);
		this.username.sendKeys(userid);
	}

	public void enterPassword(String pwd) {
		waitForElement(driver, 10, passowrd);
		this.passowrd.sendKeys(pwd);
	}


	public String getInvalidLoginText() {
		
		waitForElement(driver, 10, psloginerror);
		String text = psloginerror.getText();
		return text;
	}
	

	public void loginToApplication(String userid, String encryptedPassword, String runMode) {

		String key = "NottinghamUni123"; // 128 bit key
        String initVector = "RandomInitVector"; // 16 bytes IV

        //String password = decrypt(key, initVector,encryptedPassword);
        String password = encryptedPassword;
	    enterUsername(userid);
		enterPassword(password);
		clickOnsignIn();
	}
	
	public String verifylogin()
	{
		return waitForElement(driver,10,driver.findElement(By.id("btn-manageDocs"))).getText(); 
	}
    public static String encrypt(String key, String initVector, String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: "
                    + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
	
	public void ApplicantLogin(String userid, String Password) {
	    enterUsername(userid);
		enterPassword(Password);
		clickOnsignIn();
	}

}
