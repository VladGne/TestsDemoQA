package Models;

import lombok.Getter;
import lombok.Setter;

public class User {

	@Getter @Setter	private String email;
	@Getter @Setter private String fistName;
	@Getter @Setter private String lastName;
	@Getter @Setter private String company;
	@Getter @Setter private String address;
	@Getter @Setter private String address2; // Additional address information
	@Getter @Setter private String city;
	@Getter @Setter private String zipCode;
	@Getter @Setter private String additionInformation;
	@Getter @Setter private String homePhone;
	@Getter @Setter private String mobilePhone;
	@Getter @Setter private String addressAlias;
	@Getter @Setter private String gender;
	@Getter @Setter private String password;
	@Getter @Setter private String dayBirth;
	@Getter @Setter private String yearBirth;
	@Getter @Setter private State state;
	@Getter @Setter private Country country;
	@Getter @Setter private MonthBirth monthBirth;

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

		@Getter int value;
		State(int value){
				this.value = value;
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

		@Getter int value;
		MonthBirth(int value){
			this.value = value;
		}
	}

	public static enum Country{
		UnitedStates(21);

		@Getter int value;
		Country(int value) { this.value = value;}
	}
}
