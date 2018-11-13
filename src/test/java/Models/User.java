package Models;

public class User {
	private String email;
	private String fistName;
	private String lastName;
	private String company;
	private String address;
	private String address2; // Additional address information
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String additionInformation;
	private String homePhone;
	private String mobilePhone;
	private String addressAlias;
	private String gender;
	private String password;
	private String dayBirth;
	private String monthBirth;
	private String yearBirth;
	
	public static enum state{
		Alabama,
		Alaska,
		Arizona,
		Arkansas,
		California,
		Colorado,
		Connecticut;
	}
	
	public static enum monthBirth{
		January,
		February,
		March,
		April,
		May,
		June,
		July,
		August,
		September,
		October,
		November,
		December;
	}
	
	public String getDayBirth() {
		return dayBirth;
	}
	public void setDayBirth(String dayBirth) {
		this.dayBirth = dayBirth;
	}
	public String getMonthBirth() {
		return monthBirth;
	}
	public void setMonthBirth(String monthBirth) {
		this.monthBirth = monthBirth;
	}
	public String getYearBirth() {
		return yearBirth;
	}
	public void setYearBirth(String yearBirth) {
		this.yearBirth = yearBirth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFistName() {
		return fistName;
	}
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAdditionInformation() {
		return additionInformation;
	}
	public void setAdditionInformation(String additionInformation) {
		this.additionInformation = additionInformation;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getAddressAlias() {
		return addressAlias;
	}
	public void setAddressAlias(String addressAlias) {
		this.addressAlias = addressAlias;
	}	
}
