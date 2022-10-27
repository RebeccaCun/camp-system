package system;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.UUID;

public class Camper {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private ArrayList<Contact> emergencyContacts;
    private Medical medical;
    private int numberStrikes;
    private ArrayList<String> reasonStrikes;
    private ArrayList<String> notes;
    private ArrayList<Session> sessions;

    public Camper(String firstName, String lastName, LocalDate birthday){}

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public int getAge(){
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public ArrayList<Session> getSessions(){
        return sessions;
    }
    
    public Camper(UUID id, String firstName, String lastName, LocalDate birthday){
        this.id = id;
    }

    public void addAddress(String address) {
        
    }

    public void addEmergContacts(ArrayList<Contact> emergencyContacts) {
        
        
    }

    public void addGuardians(ArrayList<Contact>  guardians) {

    }

    public void addMedical(Medical medical) {
        
    }

    public void setNumStrikes(int strikes) {

    }

    public void addStrikeReason(ArrayList<String> reasons) {

    }

    public void addNotes(ArrayList<String> notes) {

    }

    public void addSessions(ArrayList<Session> sessions) {

    }

    public void addSession(Session session) {

    }

    public UUID getUUID() {
        return id;
    }

    public void giveStrike(){}

    public String toString(){
        return "this is a camper";
    }

}
