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
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        campers = new ArrayList<>();
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
        campers = new ArrayList<>();
    }

    /**
     * Returns the ID of the User class.
     * @return The ID of the User.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Returns the first name of the User class.
     * @return The String of the first name for the User.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns the last name of the User class.
     * @return The String of the last name for the User.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Returns the user name of the User class.
     * @return The String of the user name for the User.
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Returns the password of the User class.
     * @return The String of the password for the User.
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * Returns the email of the User class.
     * @return The String of the email for the User.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Returns the phone number of the User class.
     * @return The String of the phone number for the User.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Returns the preferred contact of the User class.
     * @return The String of the preferred contact for the User.
     */
    public String getPreferredContact() {
        return this.preferredContact;
    }
    
    /**
     * Returns the birthday of the User class.
     * @return The LocalDate of the birthday for the User.
     */
    public LocalDate getBirthday() {
        return this.birthday;
    }

    /**
     * Returns the address of the User class.
     * @return The String of the address for the User.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Returns the type of account for the User class.
     * @return The type of account for the User.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Returns the campers for the User class.
     * @return The ArrayList of campers for the User.
     */
    public ArrayList<Camper> getCampers() {
        return this.campers;
    }

    /**
     * Adds a list of campers to the User class.
     * @param campers The ArrayList of campers to be added.
     */
    public void addCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }

    /**
     * Adds a phone number to the User class.
     * @param phoneNumber The phone number to be added.
     */
    public void addPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Adds a password to the User class.
     * @param password The password to be added.
     */
    public void addPassword(String password){
        this.password = password;
    }
    
    /**
     * Adds a password to the User class.
     * @param password The password to be added.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Adds an email to the User class.
     * @param email The email to be added.
     */
    public void addEmail(String email) {
        this.email = email;
    }

    /**
     * Adds a preferred contact to the User class.
     * @param preferredContact The preferred contact to be added.
     */
    public void addPreferredContact(String preferredContact) {
        this.preferredContact = preferredContact;
    }

    /**
     * Adds a birthday to the User class.
     * @param birthday The birthday to be added.
     */
    public void addBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * Adds a camper to the User class.
     * @param camper The Camper to be added.
     */
    public void addCamper(Camper camper) {
        this.campers.add(camper);
    }

    /**
     * Adds an address to the User class.
     * @param address The address to be added.
     */
    public void addAddress(String address) {
        this.address = address;
    }

    /**
     * Sets the type of the User class
     * @param type The type that the User will be set to.
     */
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
