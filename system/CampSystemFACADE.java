package system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CampSystemFACADE {
    private User currentUser;
    private SessionList sessions;
    private CounselorList counselors;
    private CamperList campers;
    private UserList users;
    private CabinList cabins;
    private FileWriter writer;

    public CampSystemFACADE(){
        sessions = SessionList.getInstance();
        users = UserList.getInstance();
        counselors = CounselorList.getInstance();
        campers = CamperList.getInstance();
        cabins = CabinList.getInstance();
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
        newUser.setType(Type.PARENT);

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
        newCounselor.setType(Type.COUNSELOR);

        counselors.addCounselor(newCounselor);
    }

    public boolean addCamper(String firstName, String lastName, LocalDate birthday, Contact emergencyContact, Medical medicalInfo, ArrayList<String> notes){
        Camper newCamper = new Camper(firstName, lastName, birthday);
        ArrayList<Contact> emergencyContacts = new ArrayList<Contact>();
        emergencyContacts.add(emergencyContact);
        newCamper.addEmergContacts(emergencyContacts);
        newCamper.addMedical(medicalInfo);
        newCamper.addNotes(notes);

        campers.addCamper(newCamper);
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
        users.saveUsers();
        campers.saveCampers();
        sessions.saveSessions();
        counselors.saveCounselors();
        cabins.saveCabins();
        
        currentUser = null;
    }

    public boolean checkUsernameAvailability(String username){
        if(!users.getUsers().isEmpty()){
            for(User user : users.getUsers()){
                if(user.getUserName().equals(username)){
                    return false;
                }
            }
        }
        if(!counselors.getCounselors().isEmpty()){
            for(Counselor counselor : counselors.getCounselors()){
                if(counselor.getUserName().equals(username)){
                    return false;
                }
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
        sessions.addSession(session);
    }

    /**
     * @param cabin
     */
    public void addCabinToSessions(Cabin cabin){
        cabins.addCabin(cabin);
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
                info += c.getFirstName() + " " + c.getLastName();
                if(!c.getSessions().isEmpty()){
                    info += "\n   Registered to the following sessions:\n";
                    for(Session s : c.getSessions()){
                        info += "   - " + s.getStartDate() + " - " + s.getEndDate() + ", Theme: " + s.getTheme();
                    }
                }
            }
        }
        return info;
    }

    public String listSessions(){
        String sessionList = "Sessions:\n";
        int counter = 1;
        for(Session s : sessions.getSessions()){
            sessionList += counter + ") Start: " + s.getStartDate() + ", Theme: " + s.getTheme() + "\n";
            counter ++;
        }
        return sessionList;
    }

    public void printRoster(int sessionNr){
        Session session = sessions.getSessions().get(sessionNr - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File rosterFile = new File("System/roster.txt");
        try{
            rosterFile.createNewFile();
            writer = new FileWriter(rosterFile);
            writer.write("Campers in your Cabin: ");
            for(Camper c : toPrint.getCampers()){
                writer.write("- " + c.getFirstName() + c.getLastName() + ", " + c.getAge());
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printWeekInfo(int sessionNr){
        Session session = sessions.getSessions().get(sessionNr - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File infoFile = new File("System/info.txt");
        try{
            infoFile.createNewFile();
            writer = new FileWriter(infoFile);
            writer.write("--- VITAL INFORMATION FOR THE WEEK FROM " + session.getStartDate() + " TO " + session.getEndDate() + " ---");
            for(Camper c : toPrint.getCampers()){
                writer.write("- " + c.getFirstName() + c.getLastName() + ":");
                writer.write("  -> ALLERGIES: " + c.getMedical().getAllergies());
                if(c.getMedical().getAllergies().isEmpty()){
                    writer.write("      no allergies");
                }else{
                    for(String allergy : c.getMedical().getAllergies()){
                        writer.write("      - " + allergy);
                    }
                }
                writer.write("  -> EMERGENCY CONTACTS: ");
                for(Contact emergency : c.getEmergencyContacts()){
                    writer.write("      - " + emergency.getFirstName() + " " + emergency.getLastName() + ", " + emergency.getAddress() + ", " + emergency.getPhoneNumber());
                }
                writer.write("  -> MEDICAL INFORMATION: ");
                Contact doc = c.getMedical().getDoctor();
                writer.write("      - Doctor: " + doc.getFirstName() + " " + doc.getLastName() + ", " + doc.getAddress() + ", " + doc.getPhoneNumber());
                writer.write("      - Medications: ");
                for(Medication m : c.getMedical().getMedications()){
                    writer.write("      " + m.getName() + ", " + m.getTime());
                }
                writer.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void printSchedule(int sessionNr){
        Session session = sessions.getSessions().get(sessionNr - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File scheduleFile = new File("System/schedule.txt");
        try{
            scheduleFile.createNewFile();
            writer = new FileWriter(scheduleFile);
            writer.write(toPrint.viewSchedules());
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private Cabin findCounselorsCabin(Session session){
        Counselor couns = (Counselor) currentUser;
        for(Cabin c : session.getCabins()){
            for(Cabin counsCabin : couns.getCabins()){
                if(c == counsCabin){
                    return c;
                }
            }
        }
        return null;
    }
}
