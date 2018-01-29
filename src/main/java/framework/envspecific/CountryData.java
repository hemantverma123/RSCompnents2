package framework.envspecific;

public class CountryData {

	public String getNationCode(String country) {

		switch(country.toLowerCase()) {
		case "afghanistan":
		return "AFG";

		case "canada":
		return "CAN";

		case "viet nam":
		return "VNM";

		case "bangladesh":
		return "BGD";
		
		case "brazil":
		return "BRA";
		
		case "china":
		return "CHN";
		
		case "france":
		return "FRA";
		
		case "india":
		return "IND";
		
		case "iraq":
		return "IRQ";
		
		case "japan":
		return "JPN";
		
		case "jersey":
		return "JEY";
		
		case "korea":
		return "KOR";
		
		case "poland":
		return "POL";
		
		case "singapore":
		return "SGP";
		
		case "switzerland":
		return "CHE";
		
		case "united kingdom":
		return "GBR";
		
		case "united states":
		return "USA";
		
		default: 
			System.out.println("Country is not in the switch case list...");
			return"";
		}
	}
	
	public String getCountryCode(String country) {
		switch(country.toLowerCase()) {
		case "afghanistan":
		return "AFG";

		case "canada":
		return "CAN";

		case "viet nam":
		return "VNM";
		
		case "bangladesh":
		return "BGD";
	
		case "brazil":
		return "BRA";
		
		case "china":
		return "CHN";
		
		case "france":
		return "FRA";
		
		case "india":
		return "IND";
		
		case "iraq":
		return "IRQ";
		
		case "japan":
		return "JPN";
		
		case "jersey":
		return "JEY";
		
		case "korea":
		return "KOR";
		
		case "poland":
		return "POL";
		
		case "singapore":
		return "SGP";
		
		case "switzerland":
		return "CHE";
		
		case "united kingdom":
		return "GBR";
		
		case "united states":
		return "USA";
		
		default: 
			System.out.println("Country is not in the switch case list...");
			return"";
		}
	}
	
	
	public String getPostCode(String country) {

		switch(country.toLowerCase()) {
		case "afghanistan":
			return "1234";
		case "united kingdom":
			return "NG7 2NR";
		case "united states":
			return "11111";
		case "switzerland":
			return "2222";	
		default:
			System.out.println("Returned default postcode for UK...");
			return "99999";
			
		}
	}
	
}
