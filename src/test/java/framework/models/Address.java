package framework.models;

import lombok.Data;
import lombok.Getter;

@Data
public class Address {
    private String  fistName;
    private String  lastName;
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
        Alabama     ( 1 ),
        Alaska      ( 2 ),
        Arizona     ( 3 ),
        Arkansas    ( 4 ),
        California  ( 5 ),
        Colorado    ( 6 ),
        Connecticut ( 7 );

        int value;
        State(int value){
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
