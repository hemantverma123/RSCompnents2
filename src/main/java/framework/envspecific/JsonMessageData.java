package framework.envspecific;

import java.util.Map;


public class JsonMessageData {

	private String phone;

	private String sex;

	private String middlename;

	private String payment;

	private String conviction;

	private String street;

	private String lastname;

	private String firstname;

	private String postcode;

	private String type;

	private String password;

	private String country;

	private String city;

	private String currency;

	private String updatedAt;

	private String id;

	private String username;

	private String county;

	private String createdAt;

	private String dob;

	private String entry;

	private String fulltime;

	private String visa;

	private String campus;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getConviction() {
		return conviction;
	}

	public void setConviction(String conviction) {
		this.conviction = conviction;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getFulltime() {
		return fulltime;
	}

	public void setFulltime(String fulltime) {
		this.fulltime = fulltime;
	}

	public String getVisa() {
		return visa;
	}

	public void setVisa(String visa) {
		this.visa = visa;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	@Override
	public String toString() {
		return "ClassPojo [phone = " + phone + ", sex = " + sex + ", middlename = " + middlename + ", payment = "
				+ payment + ", conviction = " + conviction + ", street = " + street + ", lastname = " + lastname
				+ ", firstname = " + firstname + ", postcode = " + postcode + ", type = " + type + ", password = "
				+ password + ", country = " + country + ", city = " + city + ", currency = " + currency
				+ ", updatedAt = " + updatedAt + ", id = " + id + ", username = " + username + ", county = " + county
				+ ", createdAt = " + createdAt + ", dob = " + dob + ", entry = " + entry + ", fulltime = " + fulltime
				+ ", visa = " + visa + ", campus = " + campus + "]";
	}
	
	// Add a container for the root element
	public static class Container {
		public JsonMessageData jsonMessageData;
	}
    

}


