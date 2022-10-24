package system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class User {
    protected UUID id;
    protected String firstName; 
    protected String lastName;
    protected String userName;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected String preferredContact;
    protected LocalDate birthday;
    protected String address;
    protected Type type;
    protected ArrayList<Camper> campers;

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
    }

    public User(UUID id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }
    
    public void addCampers(ArrayList<Camper> campers) {

    }

    public void addPhoneNumber(String phoneNumber) {

    }

    public void setPassword(String password) {

    }

    public void addEmail(String email) {

    }

    public void addPreferredContact(String preferredContact) {

    }

    public void addBirthday(LocalDate birthday) {

    }

    public void addCamper(Camper camper) {

    }

    public void addAddress(String address) {

    }

    public void setType(Type type) {

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
