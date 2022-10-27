package system;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.UUID;

/**
 * A User class that holds the information for a User of the camp system.
 * @author Cyber Council
 */
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

    /**
     * Establishes an instance of the User class.
     * @param firstName A String representing the first name of the User class.
     * @param lastName A String representing the last name of the User class.
     * @param userName A String representing the username of the User class.
     */
    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
    }

    /**
     * Establishes an instance of the User class with UUID.
     * @param id A UUID representing the ID of the User.
     * @param firstName A String representing the first name of the User class.
     * @param lastName A String representing the last name of the User class.
     * @param userName A String representing the username of the User class.
     */
    public User(UUID id, String firstName, String lastName, String userName) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword(){
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPreferredContact() {
        return this.preferredContact;
    }
    
    public LocalDate getBirthday() {
        return this.birthday;
    }

    public String getAddress() {
        return this.address;
    }

    public Type getType() {
        return this.type;
    }

    public ArrayList<Camper> getCampers() {
        return this.campers;
    }

    public void addCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }

    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addPassword(String password){
        this.password = password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public void addEmail(String email) {
        this.email = email;
    }

    public void addPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }

    public void addBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void addCamper(Camper camper) {
        this.campers.add(camper);
    }

    public void addAddress(String address) {
        this.address = address;
    }

    public void setType(Type type) {
        this.type = type;
    }

     /**
     * Calculates and returns the user's age.
     * @return An integer representing the age of the user in years.
     */
    protected int calculateAge() {    
        LocalDate currentDate = LocalDate.now();  
        return Period.between(this.birthday, currentDate).getYears();
    }

     /**
     * Enrolls a camper for the User class.
     * @param camper The camper to be added.
     */
    public void enrollCamper(Camper camper) {
        this.campers.add(camper);
    }

    /**
     * Creates a string detailing the attributes of the User class.
     * @return A string representation of the User class.
     */
    public String toString() {
        String print = "Name :"+this.firstName+" "+this.lastName+"\nUsername :"+this.userName+"\nPassword :"+this.password+"\nEmail: "+this.email+
        "\nPhone Number: "+this.phoneNumber+"\nPreferred Contact: "+this.preferredContact+"\nAddress: "+this.address+"\nType of account: "+this.type+"\nCampers: ";
        for (int i = 0; i < campers.size(); i++) {
			if (campers.get(i) != null) {
                print += campers.get(i)+"\n";
            }
		}
        return print;
    }  
}
