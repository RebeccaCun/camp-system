package system;

import java.util.ArrayList;
import java.util.Date;

public class CampSystemFACADE {
    private User currentUser;
    private SessionList sessions;
    private UserList users;

    public CampSystemFACADE(){}
    public boolean login(String userName, String password){return true;}
    public boolean createAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, String preferredContact, Date birthday, String address){return true;}
    public boolean addCamper(String firstName, String lastName, String relatioship, Date birthday, String firstNameGuardian, String lastNameGuardian,  String phoneNumberGuardian, String addressGuardian, String firstNameEmergency, String lastNameEmergency, String phoneEmergency, String addressEmergency, String firstNameDoctor, String lastNameDoctor, String phoneDoctor, String addressDoctor, ArrayList<String> allergies, ArrayList<Medication> medication, ArrayList<String> notes){return true;}
    public boolean sessionSignup(Camper camper, Session session){return true;}
    public void logout(){}
}
