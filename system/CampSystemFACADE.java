package system;

import java.time.LocalDate;
import java.util.ArrayList;

public class CampSystemFACADE {
    private User currentUser;
    private SessionList sessions;
    private UserList users;

    public CampSystemFACADE(){
        sessions = SessionList.getInstance();
        users = UserList.getInstance();
    }

    public boolean login(String userName, String password){
        for(User user : users.getUsers()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                currentUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean createAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, String preferredContact, LocalDate birthday, String address){
        for(User user : users.getUsers()){
            if(user.getUserName().equals(userName)){
                return false;
            }
        }

        User newUser = new User(firstName, lastName, userName);
        newUser.addAddress(address);
        newUser.addEmail(email);
        newUser.addPassword(password);
        newUser.addBirthday(birthday);
        newUser.addPhoneNumber(phoneNumber);
        newUser.addPreferredContact(preferredContact);
        if(!users.addUser(newUser)){
            return false;
        }
        return true;
    }

    public boolean addCamper(String firstName, String lastName, LocalDate birthday, Contact emergencyContact, Contact doctorContact, ArrayList<String> allergies, ArrayList<Medication> medication, ArrayList<String> notes){
        Camper newCamper = new Camper(firstName, lastName, birthday);
        ArrayList<Contact> emergencyContacts = new ArrayList<Contact>();
        emergencyContacts.add(emergencyContact);
        newCamper.addEmergContacts(emergencyContacts);
        Medical medical = new Medical(doctorContact);
        medical.addAllergies(allergies);
        medical.addMedications(medication);
        newCamper.addMedical(medical);
        newCamper.addNotes(notes);

        CamperList.addCamper(newCamper);
        currentUser.addCamper(newCamper);

        return true;
    }

    public void sessionSignup(Camper camper, Session session){
        camper.addSession(session);
        session.decreaseAvailableSpots();
    }

    public Camper findCamperByName(String firstName, String lastName){
        Camper camper = null;
        for(Camper c : currentUser.campers){
            if(c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)){
                camper = c;
            }
        }
        return camper;
    }

    public ArrayList<Session> findAvailableSessions(int age){
        ArrayList<Session> availableSessions = new ArrayList<Session>();
        for(Session s : sessions.getSessions()){
            if(s.isAvailable() && s.viewAgeGroup() <= age && s.viewAgeGroup() + 2 >= age){
                availableSessions.add(s);
            }
        }
        return availableSessions;
    }

    public void logout(){
        currentUser = null;
    }
}
