package system;

import java.util.ArrayList;

public class User {
    //protected UUID id;
    protected String firstName; 
    protected String lastName;
    protected String userName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected String preferredContact;
    protected Date birthday;
    protected String address;
    //protected ArrayList<Camper> campers;

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
    }

    public void addPhoneNumber(String phoneNumber) {

    }
    
    public void addPreferredContact(String preferredContact) {

    }

    public void addCamper(Camper camper) {

    }

    private int calculateAge() {
        return 0;
    }

    public void enrollCamper(Camper camper, Session session) {

    }

    public String toString() {
        return "";
    }  
}
