package system;

import java.time.LocalDate;
import java.util.ArrayList;

public class CampSystemFACADE {
    private User currentUser;
    private SessionList sessions;
    private UserList users;

    public CampSystemFACADE(){}
    public boolean login(String userName, String password){return true;}
    public boolean createAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, String preferredContact, LocalDate birthday, String address){return true;}
    public boolean addCamper(String firstName, String lastName, LocalDate birthday, Contact emergencyContact, Contact doctorContact, ArrayList<String> allergies, ArrayList<Medication> medication, ArrayList<String> notes){return true;}
    public boolean sessionSignup(Camper camper, Session session){return true;}
    public void logout(){}
}
