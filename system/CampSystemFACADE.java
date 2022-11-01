package system;

import java.time.LocalDate;
import java.util.ArrayList;

public class CampSystemFACADE {
    private User currentUser;
    private SessionList sessions;
    private CounselorList counselors;
    private UserList users;

    public CampSystemFACADE(){
        sessions = SessionList.getInstance();
        users = UserList.getInstance();
        counselors = CounselorList.getInstance();
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

    public void createUserAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, String preferredContact, LocalDate birthday, String address){
       
        User newUser = new User(firstName, lastName, userName);
        newUser.addAddress(address);
        newUser.addEmail(email);
        newUser.addPassword(password);
        newUser.addBirthday(birthday);
        newUser.addPhoneNumber(phoneNumber);
        newUser.addPreferredContact(preferredContact);
        users.addUser(newUser);
    }

    public void createCounselorAccount(String userName, String password, String email, String lastName, String firstName, String phoneNumber, String preferredContact, LocalDate birthday, String address, String biography, Medical medicalInfo){
        Counselor newCounselor = new Counselor(firstName, lastName, userName);
        newCounselor.addAddress(address);
        newCounselor.addEmail(email);
        newCounselor.addPassword(password);
        newCounselor.addBirthday(birthday);
        newCounselor.addPhoneNumber(phoneNumber);
        newCounselor.addPreferredContact(preferredContact);
        newCounselor.addBiography(biography);
        newCounselor.addMedical(medicalInfo);

        counselors.addCounselor(newCounselor);
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

    public int sessionSignup(Camper camper, Session session){
        int counter = 0;
        for(Cabin cabin : session.getCabins()){
            counter ++;
            if(camper.getAge() >= cabin.getMinCabinAge() || camper.getAge() <= cabin.getMaxCabinAge()){
                if(cabin.getMaxNumberOfCampers() <= cabin.getCampers().size()){
                    continue;
                }
                camper.addSession(session);
                session.decreaseAvailableSpots();
                cabin.addCamper(camper);
                return counter;
            }
        }
        return -1;
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

    public ArrayList<Session> findAvailableSessions(){
        ArrayList<Session> availableSessions = new ArrayList<Session>();
        for(Session s : sessions.getSessions()){
            if(s.isAvailable()){
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

    public void giveStrike(String firstName, String lastName, String reason){
        Camper camper = findCamperByName(firstName, lastName);
        camper.giveStrike(reason);
    }

    public void createSession(LocalDate start, LocalDate end, String theme){
        Session session = new Session(start, end);
        session.addTheme(theme);
        sessions.addSession(start, end);
    }

    /**
     * @param cabin
     */
    public void addCabinToSessions(Cabin cabin){
        for(Session session : sessions.getSessions()){
            session.addCabin(cabin);
        }
    }

    public String getUserInformation(){
        String info = new String();
        if(currentUser.getCampers().isEmpty()){
            info = "You have no campers added to your account.\n";
        }else{
            info = "You have the following campers added to your account:\n";
            for(Camper c : currentUser.getCampers()){
                info += c.getFirstName() + c.getLastName();
                if(!c.getSessions().isEmpty()){
                    info += "   Registered to the following sessions:\n";
                    for(Session s : c.getSessions()){
                        info += "   - " + s.getStartDate() + " - " + s.getEndDate() + ", Theme: " + s.getTheme();
                    }
                }
            }
        }
        return info;
    }
}
