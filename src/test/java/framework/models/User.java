package framework.models;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@ToString
public class User {

	List<Order> orders;
	private String     email;
	private String     firstName;
	private String     lastName;
	private Gender     gender;
	private String     password;
	private String     dayBirth;
	private String     yearBirth;
	private MonthBirth monthBirth;
	private boolean    news;
	private boolean    options;


	@Getter
	public enum MonthBirth{
		January		( 1 ),
		February	( 2 ),
		March		( 3 ),
		April		( 4 ),
		May			( 5 ),
		June		( 6 ),
		July		( 7 ),
		August		( 8 ),
		September	( 9 ),
		October		( 10 ),
		November	( 11 ),
		December	( 12 );

		int value;
		MonthBirth(int value){
			this.value = value;
		}
	}

	@Getter @ToString
	public enum Gender{
		Male,
		Female
	}

	private String  address;
	private String  address2; // Additional address information
	private String  company;
	private String  city;
	private String  zipCode;
	private State   state;
	private Country country;
	private String  homePhone;
	private String  mobilePhone;
	private String  addressAlias;
	private String  additionInformation;

	@Getter
	public enum State{
		Alabama		( 1 ),
		Alaska		( 2 ),
		Arizona		( 3 ),
		Arkansas	( 4 ),
		California	( 5 ),
		Colorado	( 6 ),
		Connecticut	( 7 );

		int value;
		State(int value){
				this.value = value;
		}
	}

	@Getter
	public enum Country{
		UnitedStates	( 21 );

		int value;
		Country(int value) { this.value = value;}
	}
}