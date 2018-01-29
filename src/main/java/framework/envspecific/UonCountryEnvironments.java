package framework.envspecific;

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
				// Country specific URL e.g. Malasiya
		break;
		case "CN":
			// Country specific URL e.g. China			
		break;
		default:
			System.out.println("Using url for UK for the country " + country);
			
			if (env.equals("PUAT")) {  //Assuming the testurl sits in PAT env
				envUrlsArray[0] = "http://uk.rs-online.com/web/";
				envUrlsArray[1] = "Any other env or url";
			}
			else if (env.equals("UAT")) {
				//UAT env			
			} 
			else if (env.equals("SIT")) {
				//SIT env			}
			} 
			else {
				System.out.println("Missing env for the country...");
			}
		}
	}

}
