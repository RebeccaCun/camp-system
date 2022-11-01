package system;

/**
 * A Contact class that holds the contact information of a individual.
 * @author Cyber Council
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    /**
     * Establishes an instance of the Contact class.
     * @param firstName A String representing the first name of the Contact class.
     * @param lastName A String representing the last name of the Contact class.
     * @param phoneNumber A String representing the phone number of the Contact class.
     * @param address A String representing the address of the Contact class.
     */
    public Contact(String firstName, String lastName, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    /**
     * 
     * @return
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * 
     * @return
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * 
     * @return
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * 
     * @return
     */
    public String getAddress() {
        return this.address;
    }
    
    /**
     * Creates a string detailing the attributes of the Contact class.
     * @return A string representation of the Contact class.
     */
    public String toString() {
        return "Name: "+this.firstName+" "+this.lastName+"\nPhone number: "+this.phoneNumber+"\nAddress :"+this.address;
    }
}
