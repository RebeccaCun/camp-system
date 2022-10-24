package system;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    public Contact(String firstName, String lastName, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String toString() {
        return "Name: "+this.firstName+" "+this.lastName+"\nPhone number: "+this.phoneNumber+"\nAddress :"+this.address;
    }
}
