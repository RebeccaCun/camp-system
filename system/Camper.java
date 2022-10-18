package system;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Camper {
    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private ArrayList<Contact> emergencyContacts;
    private Medical medical;
    private boolean acceptedWaiver;
    private int numberStrikes;
    private ArrayList<String> reasonStrikes;
    private ArrayList<String> notes;
    private ArrayList<Session> sessions;

    public Camper(String firstName, String lastName, Date birthday, Contact emergencyContact, Contact guardian, ArrayList<String> notes){}

    public void giveStrike(){}

    public void addEmergencyContact(Contact emergencyContact){}

    public void addGuardian(Contact guardian){}

    public String toString(){
        return "this is a camper";
    }

}
