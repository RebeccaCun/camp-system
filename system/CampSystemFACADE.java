package system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A CampSystem class that holds the information for the camp system itself.
 * @author Cyber Council
 */
public class CampSystemFACADE {
    private User currentUser;
    private SessionList sessions;
    private CounselorList counselors;
    private CamperList campers;
    private UserList users;
    private CabinList cabins;
    private FileWriter writer;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public SessionList getSessions(){
        return sessions;
    }

    public void setSessions(SessionList sessions){
        this.sessions = sessions;
    }

    public CounselorList getCounselors(){
        return counselors;
    }

    public void setCounselors(CounselorList counselors){
        this.counselors = counselors;
    }

    public CamperList getCampers() {
        return campers;
    }

    public void setCampers(CamperList campers) {
        this.campers = campers;
    }

    public UserList getUsers() {
        return users;
    }

    public void setUsers(UserList users) {
        this.users = users;
    }

    public CabinList getCabins() {
        return cabins;
    }

    public void setCabins(CabinList cabins) {
        this.cabins = cabins;
    }

    public FileWriter getWriter() {
        return writer;
    }

    public void setWriter(FileWriter writer) {
        this.writer = writer;
    }

   
    
    /**
     * Initializes an instance of the CampSystem class.
     */
    public CampSystemFACADE(){
        sessions = SessionList.getInstance();
        users = UserList.getInstance();
        counselors = CounselorList.getInstance();
        campers = CamperList.getInstance();
        cabins = CabinList.getInstance();
    }

    /**
     * Logins a user.
     * @param userName The username of the user.
     * @param password The password of the user.
     * @return An integer depending on the type of the user's account, 
     */
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

    /**
     * Creates a user account.
     * @param userName The username of the new account.
     * @param password The password of the new account.
     * @param email The email of the new account.
     * @param lastName The last name of the new account.
     * @param firstName The first name of the new account.
     * @param phoneNumber The phone number of the new account.
     * @param preferredContact The preferred contact of the new account.
     * @param birthday The birthday of the new account.
     * @param address The address of the new account.
     */
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

    /**
     * Creates a counselor account.
     * @param userName The username of the new account.
     * @param password The password of the new account.
     * @param email The email of the new account.
     * @param lastName The last name of the new account.
     * @param firstName The first name of the new account.
     * @param phoneNumber The phone number of the new account.
     * @param preferredContact The preferred contact of the new account.
     * @param birthday The birthday of the new account.
     * @param address The address of the new account.
     * @param biography The biography of the new account.
     * @param medicalInfo The medical information of the new account.
     */
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

    /**
     * Creates and adds a camper to the camper list and user's account.
     * @param firstName The first name of the camper.
     * @param lastName The last name of the camper.
     * @param birthday The birthday of the camper.
     * @param emergencyContact The emergency contact of the camper.
     * @param medicalInfo The medical information of the camper.
     * @param notes The biography of the camper.
     * @return A boolean depending on if the camper was successfully added.
     */
    public void addCamper(String firstName, String lastName, LocalDate birthday, ArrayList<Contact> emergencyContacts, Medical medicalInfo, ArrayList<String> notes){
        Camper newCamper = new Camper(firstName, lastName, birthday);
        newCamper.addEmergContacts(emergencyContacts);
        newCamper.addMedical(medicalInfo);
        newCamper.addNotes(notes);

        campers.addCamper(newCamper);
        currentUser.addCamper(newCamper);
    }

    /**
     * Signs up a camper to a session.
     * @param camper The camper being added.
     * @param session The session that the camper is being added to
     * @return The integer representing whether or not the camper was successfully added.
     */
    public int sessionSignup(Camper camper, Session session){
        int counter = 0;
        for(Cabin cabin : session.getCabins()){
            counter ++;
            if(camper.getAge() >= cabin.getMinCabinAge() && camper.getAge() <= cabin.getMaxCabinAge()){
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

    /**
     * Searches for a camper by their first and last name in the campers list.
     * @return The camper being searched for.
     */
    public Camper findCamperByName(String firstName, String lastName){
        Camper camper = null;
        for(Camper c : currentUser.campers){
            if(c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)){
                camper = c;
            }
        }
        return camper;
    }

    /**
     * Searches for available sessions in the sessions List.
     * @return A list of available sessions.
     */
    public ArrayList<Session> findAvailableSessions(){
        ArrayList<Session> availableSessions = new ArrayList<Session>();
        for(Session s : sessions.getSessions()){
            if(s.isAvailable()){
                availableSessions.add(s);
            }
        }
        return availableSessions;
    }

    /**
     * Logouts the current user.
     */
    public void logout(){
        users.saveUsers();
        campers.saveCampers();
        sessions.saveSessions();
        counselors.saveCounselors();
        cabins.saveCabins();
        
        currentUser = null;
    }

    /**
     * Searches for a username's availibility and returns a boolean depending if if it is available.
     * @param username The username being checked for.
     * @return The boolean dependent of if the username is usable or not.
     */
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

    /**
     * Gives a strike to a specific camper.
     * @param firstName The String of the first name of the camper.
     * @param lastName The String of the last name of the camper.
     * @param reason The String of the reason for the strike.
     */
    public void giveStrike(String firstName, String lastName, String reason){
        Camper camper = findCamperByName(firstName, lastName);
        camper.giveStrike(reason);
    }

    /**
     * Creates a session.
     * @param start The LocalDate of the start of the Session.
     * @param end The LocalDate of the end of the Session.
     * @param theme The String of the theme for the Session.
     */
    public void createSession(LocalDate start, LocalDate end, String theme){
        Session session = new Session(start, end);
        session.addTheme(theme);
        sessions.addSession(session);
    }

    /**
     * Adds a cabin to the Session list.
     * @param cabin The cabin being added.
     */
    public void addCabinToSessions(Cabin cabin){
        cabins.addCabin(cabin);
        for(Session session : sessions.getSessions()){
            session.addCabin(cabin);
        }
    }

    /**
     * Returns the information of the current user.
     * @return A String representing the information of the current user.
     */
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

    /**
     * Returns the Sessions.
     * @return A String representing the Sessions.
     */
    public String listSessions(){
        String sessionList = "Sessions:\n";
        int counter = 1;
        for(Session s : sessions.getSessions()){
            sessionList += counter + ") Start: " + s.getStartDate() + ", Theme: " + s.getTheme() + "\n";
            counter ++;
        }
        return sessionList;
    }

    /**
     * Prints the Roster into a file.
     * @param sessionNr An Integer representing the size of the Session list.
     */
    public void printRoster(int sessionNr){
        Session session = sessions.getSessions().get(sessionNr - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File rosterFile = new File("System/roster.txt");
        try{
            rosterFile.createNewFile();
            writer = new FileWriter(rosterFile);
            writer.write("Campers in your Cabin: \n");
            for(Camper c : toPrint.getCampers()){
                writer.write("- " + c.getFirstName() +" "+ c.getLastName() + ", " + c.getAge()+"\n");
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Prints the Session Week Info into a file.
     * @param sessionNr An Integer representing the size of the Session list.
     */
    public void printWeekInfo(int sessionNr){
        Session session = sessions.getSessions().get(sessionNr - 1);
        Cabin toPrint = findCounselorsCabin(session);
        File infoFile = new File("System/info.txt");
        try{
            infoFile.createNewFile();
            writer = new FileWriter(infoFile);
            writer.write("--- VITAL INFORMATION FOR THE WEEK FROM " + session.getStartDate() + " TO " + session.getEndDate() + " ---\n\n");
            for(Camper c : toPrint.getCampers()){
                writer.write("- " + c.getFirstName() + " "+ c.getLastName() + ":\n");
                writer.write("  -> ALLERGIES: \n");
                if(c.getMedical().getAllergies().isEmpty()){
                    writer.write("      no allergies");
                }else{
                    for(String allergy : c.getMedical().getAllergies()){
                        writer.write("      - " + allergy + "\n");
                    }
                }
                writer.write("  -> EMERGENCY CONTACTS: \n");
                for(Contact emergency : c.getEmergencyContacts()){
                    writer.write("      - " + emergency.getFirstName() + " " + emergency.getLastName() + ", " + emergency.getAddress() + ", " + emergency.getPhoneNumber()+ "\n");
                }
                writer.write("  -> MEDICAL INFORMATION: \n");
                Contact doc = c.getMedical().getDoctor();
                writer.write("      - Doctor: " + doc.getFirstName() + " " + doc.getLastName() + ", " + doc.getAddress() + ", " + doc.getPhoneNumber()+"\n");
                writer.write("      - Medications: \n");
                for(Medication m : c.getMedical().getMedications()){
                    writer.write("      " + m.getName() + ", " + m.getTime()+"\n");
                }
                
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Prints the Schedule Info into a file.
     * @param sessionNr An Integer representing the size of the Session list.
     */
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

    /**
     * Searches for a specific counselor's cabin.
     * @param userName The session where the cabin resides.
     * @return The cabin being searched for.
     */
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
