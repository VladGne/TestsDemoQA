package Models;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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
	private boolean news;
	private boolean options;

	@Getter
	public enum State{
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
	}

	@Getter
	public enum MonthBirth{
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
	}

	@Getter
	public enum Country{
		UnitedStates(21);

		int value;
		Country(int value) { this.value = value;}
	}
}
