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

        // correct the following ...
        User newUser = new User(firstName, lastName, userName);
        users.addUser(userName, password);
        return true;
    }

    public boolean addCamper(String firstName, String lastName, LocalDate birthday, Contact emergencyContact, Contact doctorContact, ArrayList<String> allergies, ArrayList<Medication> medication, ArrayList<String> notes){return true;}
    public boolean sessionSignup(Camper camper, Session Session){return true;}
    public Camper findCamperByName(String firstName, String lastName){
        Camper camper = null;
        for(Camper c : currentUser.campers){
            if(c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)){
                camper = c;
            }
        }
        return camper;
    }
    public ArrayList<Session> findAvailableSessions(int age){}
    public void logout(){}
}
