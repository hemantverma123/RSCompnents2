package admissions.pages;

import org.openqa.selenium.WebDriver;
import admissions.UtilityMethods;

public class StaffHomeMyNottingham {

	WebDriver driver;
	UtilityMethods UtilMeth = new UtilityMethods();

	public StaffHomeMyNottingham(WebDriver driver) {
		this.driver = driver;
	}

	public StaffHomeMyNottingham gotoMaintainApplications() {

		driver.get("https://psuatb.transform.nottingham.ac.uk/psp/psuatb/EMPLOYEE/HRMS/c/PROCESS_APPLICATIONS.ADM_APPL_MAINTNCE.GBL?FolderPath=PORTAL_ROOT_OBJECT.HCAD_STUDENT_ADMISSIONS.HCAD_APPLICATION_MAINTENANCE.HC_ADM_APPL_MAINTNCE_GBL1&IsFolder=false&IgnoreParamTempl=FolderPath%2cIsFolder&cmd=uninav&cmd=uninav&uninavpath=Root%7BPORTAL_ROOT_OBJECT%7D.Campus%20Solutions%7BPTUN_22594252800052312%2CPORTAL_ROOT_OBJECT%7D.Student%20Admissions%7BHCAD_STUDENT_ADMISSIONS%7D.Application%20Maintenance%7BHCAD_APPLICATION_MAINTENANCE%7D.Maintain%20Applications%7BHC_ADM_APPL_MAINTNCE_GBL1%7D&Rnode=HRMS");

		return this;
	}

}
