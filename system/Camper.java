package system;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.UUID;

/**
 * An Camper class that holds the information for a camper for the camp.
 * @author Cyber Council
 */
public class Camper {
    private UUID id;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private ArrayList<Contact> emergencyContacts;
    private ArrayList<Contact> guardians;
    private Medical medical;
    private int numberStrikes;
    private ArrayList<String> reasonStrikes;
    private ArrayList<String> notes;
    private ArrayList<Session> sessions;

    /**
     * Establishes an instance of the Camper class.
     * @param firstName A String representing the first name of the Camper class.
     * @param lastName A String representing the last name of the Camper class.
     * @param birthday A LocalDate representing the birthday of the Camper class.
     */
    public Camper(String firstName, String lastName, LocalDate birthday){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    /**
     * Establishes an instance of the Camper class with UUID.
     * @param id A UUID representing the ID of the Camper.
     * @param firstName A String representing the first name of the Camper class.
     * @param lastName A String representing the last name of the Camper class.
     * @param birthday A LocalDate representing the birthday of the Camper class.
     */
    public Camper(UUID id, String firstName, String lastName, LocalDate birthday){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public UUID getUUID() {
        return id;
    }

    public ArrayList<Session> getSessions() {
        return sessions;
    }

    public void setNumStrikes(int strikes) {
        this.numberStrikes = strikes;
    }

    public void addEmergContacts(ArrayList<Contact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public void addGuardians(ArrayList<Contact> guardians) {
        this.guardians = guardians;
    }

    public void addMedical(Medical medical) {
        this.medical = medical;
    }

    public void addStrikeReason(ArrayList<String> reasons) {
        this.reasonStrikes = reasons;
    }

    public void addNotes(ArrayList<String> notes) {
        this.notes = notes;
    }

    public void addSessions(ArrayList<Session> sessions) {
        this.sessions = sessions;
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    /**
     * Adds a strike to the camper.
     * @param reason The reason for the strike.
     */
    public void giveStrike(String reason){
        numberStrikes++;
        reasonStrikes.add(reason);
    }

    /**
     * Creates a string detailing the attributes of the Camper class.
     * @return A string representation of the Camper class.
     */
    public String toString(){
        String print = "Name :"+this.firstName+" "+this.lastName+"\nBirthday :"+this.birthday+"\nEmergency contacts :";
        for (int i = 0; i < emergencyContacts.size(); i++) {
			if (emergencyContacts.get(i) != null) {
                print += emergencyContacts.get(i)+"\n";
            }
		}
        print += "\nGuardians : ";
        for (int i = 0; i < guardians.size(); i++) {
			if (guardians.get(i) != null) {
                print += guardians.get(i)+"\n";
            }
		}
        print += "\nMedical information: "+this.medical+"\nPNumber of Strikes: "+this.numberStrikes+"Reason for strikes: ";
        for (int i = 0; i < reasonStrikes.size(); i++) {
			if (reasonStrikes.get(i) != null) {
                print += reasonStrikes.get(i)+"\n";
            }
		}
        print += "\nNotes: ";
        for (int i = 0; i < notes.size(); i++) {
			if (notes.get(i) != null) {
                print += notes.get(i)+"\n";
            }
		}
        print += "\nSessions: ";
        for (int i = 0; i < sessions.size(); i++) {
			if (sessions.get(i) != null) {
                print += sessions.get(i)+"\n";
            }
		}
        return print;
    }
}
