package admissions;

public class UonCountryEnvironments {
	String URL;
	//static String env = "UATB";
	static String env = "PUAT";

	String envUrlsArray[] = new String[2];

	public static String getEnv() {
		return env;
	}

	public static void setEnv(String setEnv) {
		env = setEnv;
	}

	public String[] CountryURL(String country) {

//		if (System.getProperty("testEnvironment") != null)
//			env = System.getProperty("testEnvironment");

		System.out.println("The Test Environment Being used is -> " + env);

		pickUrl(country);

		return envUrlsArray;
	}

	public String[] CountryURL(String country, String overide) {

		env = overide;

		System.out.println("The Test Environment Being used is -> " + env);

		pickUrl(country);

		return envUrlsArray;
	}

	private void pickUrl(String country) {

		switch (country) {
		case "MY":
			if (env.equals("PUAT")) {
				envUrlsArray[0] = "https://pspuat.transform.nottingham.ac.uk/psp/pspuat/EMPLOYEE/EMPL/h/?tab=UN_IH_GUEST_MALAYSIA";
				envUrlsArray[1] = "https://cspuat.transform.nottingham.ac.uk/psp/cspuat";
			}else if (env.equals("UATA")) {
				envUrlsArray[0] = "https://psuata.transform.nottingham.ac.uk/psp/psuata/EMPLOYEE/EMPL/h/?tab=UN_IH_GUEST_MALAYSIA";
				envUrlsArray[1] = "https://csuata.transform.nottingham.ac.uk/cs/csuata";
			}else if (env.equals("UATB")) {
				envUrlsArray[0] = "https://psuatb.transform.nottingham.ac.uk/psp/psuatb/EMPLOYEE/EMPL/h/?tab=UN_IH_GUEST_MALAYSIA";
				envUrlsArray[1] = "https://csuatb.transform.nottingham.ac.uk/cs/csuatb";
			}
			else {
				System.out.println("Missing env for the Malasiya...");
			}
			break;
		case "CN":
			if (env.equals("PUAT")) {
				envUrlsArray[0] = "https://pspuat.transform.nottingham.ac.uk/psp/pspuat/EMPLOYEE/EMPL/h/?tab=UN_IH_GUEST_CHINA";
				envUrlsArray[1] = "https://cspuat.transform.nottingham.ac.uk/psp/cspuat";
			}
			else if (env.equals("UATA")) {
				envUrlsArray[0] = "https://psuata.transform.nottingham.ac.uk/psp/psuata/EMPLOYEE/EMPL/h/?tab=UN_IH_GUEST_CHINA";
				envUrlsArray[1] = "https://csuata.transform.nottingham.ac.uk/cs/csuata";
			}
			else if (env.equals("UATB")) {
				envUrlsArray[0] = "https://psuatb.transform.nottingham.ac.uk/psp/psuatb/EMPLOYEE/EMPL/h/?tab=UN_IH_GUEST_CHINA";
				envUrlsArray[1] = "https://csuatb.transform.nottingham.ac.uk/cs/csuatb";
			}else {
				System.out.println("Missing env for the country CN...");
			}
			break;
//		case "UK":
//			if (env.equals("PUAT")) {
//				envUrlsArray[0] = "https://pspuat.transform.nottingham.ac.uk/psp/pspuat/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST";
//				envUrlsArray[1] = "https://cspuat.transform.nottingham.ac.uk/psp/cspuat";
//			}
//			else if (env.equals("UATA")) {
//				envUrlsArray[0] = "https://psuata.transform.nottingham.ac.uk/psp/psuata/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST";
//				envUrlsArray[1] = "https://csuata.transform.nottingham.ac.uk/cs/csuata";
//			} 
//			else if (env.equals("UATB")) {
//				envUrlsArray[0] = "https://psuatb.transform.nottingham.ac.uk/psp/psuatb/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST";
//				envUrlsArray[1] = "https://csuatb.transform.nottingham.ac.uk/cs/csuatb";
//			} 
//			else {
//				System.out.println("Missing env for the country UK...");
//			}
//			break;
		default:
			System.out.println("Using url for UK for the country " + country);
			
			if (env.equals("PUAT")) {
				envUrlsArray[0] = "https://pspuat.transform.nottingham.ac.uk/psp/pspuat/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST";
				envUrlsArray[1] = "https://cspuat.transform.nottingham.ac.uk/psp/cspuat";
			}
			else if (env.equals("UATA")) {
				envUrlsArray[0] = "https://psuata.transform.nottingham.ac.uk/psp/psuata/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST";
				envUrlsArray[1] = "https://csuata.transform.nottingham.ac.uk/cs/csuata";
			} 
			else if (env.equals("UATB")) {
				envUrlsArray[0] = "https://psuatb.transform.nottingham.ac.uk/psp/psuatb/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST";
				envUrlsArray[1] = "https://csuatb.transform.nottingham.ac.uk/cs/csuatb";
			} 
			else {
				System.out.println("Missing env for the country UK...");
			}
		}
	}

}
