package Models;

public class User {
	private String email;
	private String fistName;
	private String lastName;
	private String company;
	private String address;
	private String address2; // Additional address information
	private String city;
	private String zipCode;
	private String additionInformation;
	private String homePhone;
	private String mobilePhone;
	private String addressAlias;
	private String gender;
	private String password;
	private String dayBirth;
	private String yearBirth;

	private State state;
	private Country country;
	private MonthBirth monthBirth;

	public User(){
		final String email = "test2@test.com2";								//test1@test.com1 - for login
		final String gender = "Male";
		final String fistName = "TestFistName";
		final String lastName = "TestLastName";
		final String company = "TestCompany";
		final String address = "TestAddress";
		final String address2 = "TestAddress2"; 							// Additional address information
		final String city = "Fortuna";
		final User.State state = User.State.California; 											// Dropdown List have only values, so it is should be California
		final User.Country country =  User.Country.UnitedStates;										// Dropdown List have only values, so it is should be USA
		final String zipCode = "95540";
		final String additionInformation = "TestAdditionalInformation";
		final String homePhone = "7077259990";
		final String mobilePhone = "9610000000";
		final String addressAlias = "TestAddressAlias";
		final String password = "TestPassword1";
		final String dayBirth = "11";
		final User.MonthBirth monthBirth = User.MonthBirth.November;
		final String yearBirth = "1991";

		this.email = email;
		this.gender = gender;
		this.fistName = fistName;
		this.lastName = lastName;
		this.address = address;
		this.address2 = address2;
		this.additionInformation = additionInformation;
		this.country = country;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.company = company;
		this.homePhone = homePhone;
		this.mobilePhone = mobilePhone;
		this.addressAlias = addressAlias;
		this.password = password;
		this.dayBirth = dayBirth;
		this.monthBirth = monthBirth;
		this.yearBirth = yearBirth;
	}

	public User(String invalidValue){

		// Valid consts
		final String gender = "Male";
		final String yearBirth = "1991";
		final User.State state = User.State.California; 											// Dropdown List have only values, so it is should be California
		final User.Country country =  User.Country.UnitedStates;

		// Invalid consts
		final String dayBirth = "31";
		final User.MonthBirth monthBirth = MonthBirth.February;

		this.gender = gender;
		this.country = country;
		this.state = state;
		this.dayBirth = dayBirth;
		this.monthBirth = monthBirth;
		this.yearBirth = yearBirth;

		this.email = invalidValue;
		this.fistName = invalidValue;
		this.lastName = invalidValue;
		this.address = invalidValue;
		this.address2 = invalidValue;
		this.additionInformation = invalidValue;
		this.city = invalidValue;
		this.zipCode = invalidValue;
		this.company = invalidValue;
		this.homePhone = invalidValue;
		this.mobilePhone = invalidValue;
		this.addressAlias = invalidValue;
		this.password = invalidValue;
	}

	public static enum State{
		Alabama(1),
		Alaska(2),
		Arizona(3),
		Arkansas(4),
		California(5),
		Colorado(6),
		Connecticut(7);

		int value;
		State(int value){
				this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}
	
	public static enum MonthBirth{
		January (1),
		February (2),
		March (3),
		April (4),
		May (5),
		June (6),
		July (7),
		August (8),
		September (9),
		October (10),
		November (11),
		December (12);

		int value;
		MonthBirth(int value){
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public static enum Country{
		UnitedStates(1);
		int value;
		Country(int value) { this.value = value;}

		public int getValue() {
			return this.value;
		}
	}
	
	public String getDayBirth() {
		return dayBirth;
	}
	public void setDayBirth(String dayBirth) {
		this.dayBirth = dayBirth;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public MonthBirth getMonthBirth() {
		return monthBirth;
	}

	public void setMonthBirth(MonthBirth monthBirth) {
		this.monthBirth = monthBirth;
	}
}
