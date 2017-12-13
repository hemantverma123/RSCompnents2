package admissions;

public enum UonCountries {
	CHINA("China", "CN", "CHN"), 
	MALAYSIA("Malaysia", "MY", "MYS"), 
	UNITED_KINGDOM("United Kingdom", "UK", "GBR"); 	

	private final String toString;
	private final String countryCode;
	private final String nationCode;

	private UonCountries(String toString, String countryCode, String nationCode) {
		this.toString = toString;
		this.countryCode = countryCode;
		this.nationCode = nationCode;
	}

	public String countryCode() {
		return countryCode;
	}

	public String nationCode() {
		return nationCode;
	}

	public String toString() {
		return toString;
	}
}
