package framework.envspecific;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import framework.TestBase;

public class StudentJsonHandler extends TestBase{
	
	JsonArray studentsArray = null;
	JsonObject studentObj = null;
	
	public StudentJsonHandler(String studentDataMessage) {
		
		JsonParser parser = new JsonParser();
		JsonObject rootObj = parser.parse(studentDataMessage).getAsJsonObject();
		studentsArray = rootObj.getAsJsonArray("message");
		
		for (JsonElement sa : studentsArray) {
			studentObj = sa.getAsJsonObject();
		}
		
	}
	
	public JsonObject getStudentObject() {
	return studentObj;	
	}
	
	public String getStudentSex() {
		return studentObj.get("sex").getAsString();			
	}
	
	public String getStudentEntry() {
		return studentObj.get("entry").getAsString();
	}
	
	public String getStudentVisa() {
		return studentObj.get("visa").getAsString();
	}
	
	public String getStudentCurrency() {	
		return studentObj.get("currency").getAsString();
	}
	
	public String getStudentPayment() {	
		return studentObj.get("payment").getAsString();
	}
	
	public String getStudentConviction() {
		return studentObj.get("conviction").getAsString();
	}
	
	public String getStudentFulltime() {	
		return studentObj.get("fulltime").getAsString();
	}
	
	public String getStudentCampus() {	
		return studentObj.get("campus").getAsString();
	}
	
	public String getStudentType() {	
		return studentObj.get("type").getAsString();
	}
	
	public String getStudentFirstName() {	
		return studentObj.get("firstname").getAsString();
	}
	
	public void setStudentFirstName(String firstname) {	
		studentObj.addProperty("firstname", firstname);
	}

	public String getStudentMiddlename() {	
		return studentObj.get("middlename").getAsString();
	}
	
	public String getStudentLastname() {	
		return studentObj.get("lastname").getAsString();
	}
	
	public String getStudentUsername() {	
		return studentObj.get("username").getAsString();
	}
	
	public String getStudentDob(Boolean convert) {	
		if(convert) {
			String Dob = studentObj.get("dob").getAsString();
			String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
			DateTimeFormatter dtf = DateTimeFormat.forPattern(pattern);
			DateTime dateTime = dtf.parseDateTime(Dob);
			String Dobconv = dateTime.toString("dd/MM/YYYY");
			return Dobconv;	
		}
		return studentObj.get("dob").getAsString();
	}
	
	public String getStudentEmail() {	
		return studentObj.get("email").getAsString();
	}
	
	public void setStudentEmail(int row) {
		studentObj.addProperty("email", getStudentFirstName()+"."+getStudentLastname()+row+"@fakemail.com");
	}
	
	public String getStudentPassword() {	
		//String temp = studentObj.get("password").getAsString()+"#1"; //Sometimes fails as no special character
		String temp = studentObj.get("password").getAsString(); //Sometimes fails as no special character
		return temp;
	}

	public void setStudentPassword(String pwd) {	
		studentObj.addProperty("password", pwd);
	}

	public String getStudentStreet() {	
		return studentObj.get("street").getAsString();
	}
	
	public String getStudentCity() {	
		return studentObj.get("city").getAsString();
	}
	
	public String getStudentCounty() {	
		return studentObj.get("county").getAsString();
	}
	
	public String getStudentCountry() {	
		return studentObj.get("country").getAsString();
	}

	public void setStudentCountry(String  studentcountry) {	
		studentObj.addProperty("country", studentcountry);
	}

	public String getStudentCountryCode() {	
		return studentObj.get("countrycode").getAsString();
	}

	public void setStudentResidencyCategory(String  ResidencyCategory) {	
		studentObj.addProperty("ResidencyCategory", ResidencyCategory);
	}

	public String getStudentResidencyCategory() {	
		return studentObj.get("ResidencyCategory").getAsString();
	}
	
	
	public void setStudentCountryCode(String  studentcountrycode) {	
		studentObj.addProperty("countrycode", studentcountrycode);
	}

	public String getStudentNationCode() {	
		return studentObj.get("nationcode").getAsString();
	}

	public void setStudentNationCode(String  studentnationcode) {	
		studentObj.addProperty("nationcode", studentnationcode);
	}

	
	public String getStudentPostcode() {	
		return studentObj.get("postcode").getAsString();
	}
	
	public void setStudentPostcode(String postcode) {	
		studentObj.addProperty("postcode", postcode);
	}

	public String getStudentPhone(Boolean StripWhiteSpace) {	
		if(StripWhiteSpace) {
		String Telephone = studentObj.get("phone").getAsString();
		Telephone = Telephone.replaceAll("\\s+", ""); // Remove Space in middle of string
		return Telephone;
		}
		return studentObj.get("phone").getAsString();
	}
	
	public void setStudentID(String studentid) {
		studentObj.addProperty("StudentID", studentid);
	}

	public String getStudentID() {	
		return studentObj.get("StudentID").getAsString();
	}

	public void setApplicationNumber(String appNumber) {
		studentObj.addProperty("ApplicationNumber", appNumber);
	}

	public String getApplicationNumber() {	
		return studentObj.get("ApplicationNumber").getAsString();
	}

}
