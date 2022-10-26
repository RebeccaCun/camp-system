package system;

import java.time.LocalDate;
import java.util.ArrayList;

public class CampSystemFACADE {
    private User currentUser;
    private SessionList sessions;
    private CounselorsList counselors;
    private UserList users;

    public CampSystemFACADE(){
        sessions = SessionList.getInstance();
        users = UserList.getInstance();
    }

    // returns 1 for parent, 2 for director, 3 for counselor, -1 for incorrect input
    public int login(String userName, String password){
        for(User user : users.getUsers()){
            if(user.getUserName().equals(userName) && user.getPassword().equals(password)){
                currentUser = user;
                if(user.type == Type.PARENT){
                    return 1;
                }else if(user.type == Type.DIRECTOR){
                    return 2;
                }
            }
        }
        for(Counselor counselor : counselors.getCounselors()){
            if(counselor.getUserName().equals(userName) && counselor.getPassword().equals(password)){
                currentUser = counselor;
                return 3;
            }
        }
        return -1;
    }

    public boolean createUserAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, String preferredContact, LocalDate birthday, String address){
       
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

    public boolean createCounselorAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, String preferredContact, LocalDate birthday, String address, String biography, Medical medicalInfo){
        Counselor newCounselor = new Counselor(firstName, lastName, userName);
        newCounselor.addAddress(address);
        newCounselor.addEmail(email);
        newCounselor.addPassword(password);
        newCounselor.addBirthday(birthday);
        newCounselor.addPhoneNumber(phoneNumber);
        newCounselor.addPreferredContact(preferredContact);
        newCounselor.addBiography(biography);
        newCounselor.addMedical(medicalInfo);

        if(!counselors.addCounselor(newCounselor)){
            return false;
        }
        return true;
    }

    public boolean addCamper(String firstName, String lastName, LocalDate birthday, Contact emergencyContact, Medical medicalInfo, ArrayList<String> notes){
        Camper newCamper = new Camper(firstName, lastName, birthday);
        ArrayList<Contact> emergencyContacts = new ArrayList<Contact>();
        emergencyContacts.add(emergencyContact);
        newCamper.addEmergContacts(emergencyContacts);
        newCamper.addMedical(medicalInfo);
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

    public boolean checkUsernameAvailability(String username){
        for(User user : users.getUsers()){
            if(user.getUserName().equals(username)){
                return false;
            }
        }
        for(Counselor counselor : counselors.getCounselors()){
            if(counselor.getUserName().equals(username)){
                return false;
            }
        }
        return true;
    }

    public void giveStrike(String firstName, String lastName){
        Camper camper = findCamperByName(firstName, lastName);
        camper.giveStrike();
    }

    //todo: fix age group issue
    public void createSession(LocalDate start, LocalDate end, String theme){
        Session session = new Session(start, end, 12);
        session.addTheme(theme);
    }
}
