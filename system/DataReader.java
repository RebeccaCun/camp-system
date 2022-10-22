package system;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.time.LocalDate;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Reads data
 * @author CyberCouncil
 */
public class DataReader extends DataConstants {

    private static ArrayList<User> users;
    private static ArrayList<Counselor> counselors;
    private static ArrayList<Session> sessions;
    private static ArrayList<Camper> campers;
    private static ArrayList<Cabin> cabins;

    /**
     * Return sessions
     * @return ArrayList<Session>
     */
    public static ArrayList<Session> getAllSessions() {
        // getAllUsers() and getAllCounselors() initiate all the five
        // ArrayLists of this class. So, if these methods are not called,
        // then call them first before returning the array.

        if (users == null || counselors == null) {
            getAllUsers();
            getAllCounselors();
        }

        return sessions;
    }

    /**
     * Return campers
     * @return ArrayList<Camper>
     */
    public static ArrayList<Camper> getAllCampers() {
        // getAllUsers() and getAllCounselors() initiate all the five
        // ArrayLists of this class. So, if these methods are not called,
        // then call them first before returning the array.

        if (users == null || counselors == null) {
            getAllUsers();
            getAllCounselors();
        }

        return campers;
    }

    /**
     * Return cabins
     * @return ArrayList<Cabin>
     */
    public static ArrayList<Cabin> getAllCabins() {
        // getAllUsers() and getAllCounselors() initiate all the five
        // ArrayLists of this class. So, if these methods are not called,
        // then call them first before returning the array.

        if (users == null || counselors == null) {
            getAllUsers();
            getAllCounselors();
        }

        return cabins;
    }
    
    /**
     * Returns users
     * @return ArrayList<User>
     */
    public static ArrayList<User> getAllUsers() {
        users = new ArrayList<>();

        try {
            // read from the file that is defined in the DataConstants
            FileReader reader = new FileReader(USERS_FILE_NAME);
            JSONParser parser = new JSONParser(); // make a JSON parser
			
            // get the camp array from json
            JSONArray userJSON = (JSONArray) new JSONParser().parse(reader);

            for (int i=0; i<userJSON.size(); i++) {
                JSONObject user = (JSONObject) userJSON.get(i);

                // create the user
                User newUser = getUser(user, "");
                           
                // add this new User to the User ArrayList
                users.add(newUser);
            }    

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Returns counselors
     * @return ArrayList<Counselor>
     */
    public static ArrayList<Counselor> getAllCounselors() {
        // initiailize the counselors arraylist
        counselors = new ArrayList<>();
        
        try {
            FileReader counselorReader = new FileReader(COUNSELORS_FILE_NAME);
            JSONParser counselorParser = new JSONParser();
            JSONArray counselorJSON = (JSONArray) new JSONParser().parse(counselorReader);

            for (int i=0; i<counselorJSON.size(); i++) {
                JSONObject counselor = (JSONObject) counselorJSON.get(i);

                // create the counselor
                // Counselor extends User, so many attributes are common
                Counselor newCounselor = (Counselor) getUser(counselor, "counselor");
                
                newCounselor.addBiography((String) counselor.get(BIOGRAPHY));
                newCounselor.addMedical(getMedical(
                    (JSONObject) counselor.get(MEDICAL)));

                // add the cabins to the counselor
                newCounselor.addCabins(getSomeCabins(counselor));
                
                // add this new Counselor to the Counselor ArrayList
                counselors.add(newCounselor);
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return counselors;
    }

    /**
     * Fill in the campers ArrayList
     * @return ArrayList<Camper>
     */
    private static ArrayList<Camper> getCampers() {
        // initiailize the campers arraylist
        campers = new ArrayList<>();
        
        try {
            
            FileReader camperReader = new FileReader(CAMPERS_FILE_NAME);
            JSONParser camperParser = new JSONParser();
            JSONArray camperJSON = (JSONArray) new JSONParser().parse(camperReader);

            for (int i=0; i<camperJSON.size(); i++) {
                JSONObject camper = (JSONObject) camperJSON.get(i);

                // get the attributes...
                String birthday = (String) camper.get(BIRTHDAY);

                // create the camper...
                Camper newCamper = new Camper(
                    UUID.fromString((String) camper.get(USER_ID)),
                    (String) camper.get(FIRST_NAME), 
                    (String) camper.get(LAST_NAME), 
                    LocalDate.parse(birthday));

                // create a new emergency contacts arraylist from the emergency contacts JSON array
                ArrayList<Contact> newEmegConts = getContacts( (JSONArray) camper.get(EMERGENCY_CONTACTS) );
                newCamper.addEmergContacts(newEmegConts);

                // create a new guardians arraylist from the guardians JSON array
                ArrayList<Contact> newGuardians = getContacts( (JSONArray) camper.get(GUARDIANS) );
                newCamper.addGuardians(newGuardians);

                // get the medical information
                JSONObject medical = (JSONObject) camper.get(MEDICAL);
                newCamper.addMedical(getMedical(medical));

                // get the accepted waiver info...
                Boolean accWaiver = (Boolean) camper.get(ACCEPTED_WAIVER);
                newCamper.setAccectedWaiver(accWaiver);

                // get the Number Strikes info...
                int numberStrikes =((Long) camper.get(NUMBER_STRIKES)).intValue();
                newCamper.setNumStrikes(numberStrikes);

                // get the reason strikes info...
                JSONArray reasonStrikes = (JSONArray) camper.get(REASON_STRIKES);
                ArrayList<String> newStrikes = new ArrayList<>();
                
                for (int j=0; j<reasonStrikes.size(); j++)
                    newStrikes.add( (String) reasonStrikes.get(j) );
                newCamper.addStrikeReason(newStrikes);

                // get the notes info...
                JSONArray notes = (JSONArray) camper.get(NOTES);
                ArrayList<String> newNotes = new ArrayList<>();
                
                for (int j=0; j<notes.size(); j++)
                    newNotes.add( (String) notes.get(j) );
                newCamper.addNotes(newNotes);     

                // add the sessions later...

                // add the Camper to the Camper Array
                campers.add(newCamper);
            }
            
            // first initialize the sessions arrayList
            getSessions();

            // Now, add sessions to the campers...
            for (int i=0; i<camperJSON.size(); i++) {

                // get the camper again...
                JSONObject camper = (JSONObject) camperJSON.get(i);
                // get the sessions array...
                JSONArray JSONSessions = (JSONArray) camper.get(SESSIONS);
                
                ArrayList<Session> newSessions = new ArrayList<>();
                for (int j=0; j<JSONSessions.size(); j++) {
                    
                    // search the array of sessions for the Session by the UUID
                    Session newSession = getSessionByUUID(
                        UUID.fromString((String) JSONSessions.get(j)));

                    // if the session exists, add to the sessions ArrayList
                    if (newSession != null)
                        newSessions.add(newSession);

                    // TODO else session is in campers but not in sessions?
                }

                // get the camper from the campers arrayList and 
                // add its sessions to it...
                campers.get(i).addSessions(newSessions);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return campers;
    }
    
    /**
     * Fill in the sessions ArrayList
     * @return ArrayList<Session>
     */
    private static ArrayList<Session> getSessions() {
        sessions = new ArrayList<>();
        
        try {
            
            FileReader sessionsReader = new FileReader(SESSION_FILE_NAME);
            JSONParser sessionsParser = new JSONParser();
            JSONArray sessionsJSON = (JSONArray) new JSONParser().parse(sessionsReader);

            for (int i=0; i<sessionsJSON.size(); i++) {
                JSONObject session = (JSONObject) sessionsJSON.get(i);

                // create the session
                Session newSession = new Session(
                    UUID.fromString((String) session.get(USER_ID)),
                    LocalDate.parse((String) session.get(START_DATE)),
                    LocalDate.parse((String) session.get(END_DATE)),
                    ((Long) session.get(AGE_GROUP)).intValue());

                newSession.setAvailableSpots(
                    ((Long) session.get(AVAILABLE_SPOTS)).intValue());
                
                // get the themes array...
                JSONArray themes = (JSONArray) session.get(THEMES);
                ArrayList<String> newThemes = new ArrayList<>();
                for (int j=0; j<themes.size(); j++)
                    newThemes.add((String) themes.get(i));
                newSession.addThemes(newThemes);

                newSession.addCabins(getSomeCabins(session));

                // add this new Session to the Session Array
                sessions.add(newSession);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sessions;
    }

    /**
     * Fill in the cabins ArrayList
     * @return ArrayList<Cabin>
     */
    private static ArrayList<Cabin> getCabins() {
        cabins = new ArrayList<>();
        
        try {
            
            FileReader cabinsReader = new FileReader(CABIN_FILE_NAME);
            JSONParser cabinsParser = new JSONParser();
            JSONArray cabinsJSON = (JSONArray) new JSONParser().parse(cabinsReader);

            for (int i=0; i<cabinsJSON.size(); i++) {
                JSONObject cabin = (JSONObject) cabinsJSON.get(i);

                // create the new cabin
                Cabin newCabin = new Cabin(
                    UUID.fromString((String) cabin.get(USER_ID)), 
                    ((Long) cabin.get(CABIN_AGE)).intValue(),
                    ((Long) cabin.get(SESSION_DURATION)).intValue());

                newCabin.addMaxCampers( ((Long) cabin.get(MAX_NO_OF_CAMPERS)).intValue() );

                // get the schedules...
                HashMap<Day, Schedule> newSchedules = new HashMap<>();
                JSONObject schedules = (JSONObject) cabin.get(SCHEDULES);
                
                // get the all Day values in the schedules
                Set<String> s =  schedules.keySet();
                ArrayList<String> dayList = new ArrayList<>(s);

                for (int j=0; j<schedules.size(); j++) {
                    
                    // get the Day for this iteration
                    String day = dayList.get(j);

                    // Create a new Schedule from the ArrayList of Activity
                    Schedule newSchedule = new Schedule(
                        getActivities((JSONArray) schedules.get(day)));

                    // Add the Day and Schedule to the HashMap of schedules
                    newSchedules.put(Day.valueOf(day), newSchedule);
                    
                }
                newCabin.addSchedules(newSchedules);

                // add Campers to Cabin later...

                // add the Cabin to the Arraylist of Cabin
                cabins.add(newCabin);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cabins;
    }

    /**
     * 
     * @param someObject
     * @return
     */
    private static void addCampersToCabins() {
        // add Campers to the Cabin

        try {
            
            FileReader cabinsReader = new FileReader(CABIN_FILE_NAME);
            JSONParser cabinsParser = new JSONParser();
            JSONArray cabinsJSON = (JSONArray) new JSONParser().parse(cabinsReader);

            for (int i=0; i<cabinsJSON.size(); i++) {
                JSONObject cabin = (JSONObject) cabinsJSON.get(i);
                cabins.get(i).addCampers(getSomeCampers(cabin));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }

    /**
     * Return all the campers which are in the given JSONObject
     * @param someObject JSONObject which must contain the CAMPERS DataConstant
     * @return an ArrayList of Campers
     */
    private static ArrayList<Camper> getSomeCampers(JSONObject someObject) {
        if (campers == null)
            getCampers(); // initialize all the campers
                    
        JSONArray JSONcampers = (JSONArray) someObject.get(CAMPERS);
        ArrayList<Camper> newCampers = new ArrayList<>();

        for (int i=0; i<JSONcampers.size(); i++) {

            // get the camper id
            UUID camperID = UUID.fromString((String) JSONcampers.get(i));
            
            // search the array of campers for the Camper
            Camper newCamper = getCamperByUUID(camperID);

            // if the Camper exists, add to the Camper array
            if (newCamper != null)
                newCampers.add(newCamper);
            
            // TODO else Camper is in the object but not in campers?
        }

        return newCampers;
    }

    /**
     * Return all the cabins which are in the given JSONObject
     * @param someObject JSONObject which must contain the CABINS DataConstant
     * @return an ArrayList of Cabin
     */
    private static ArrayList<Cabin> getSomeCabins(JSONObject someObject) {
        if (cabins == null)
            getCabins(); // initialize all the cabins first
                            
        // get cabins...
        JSONArray JSONcabins = (JSONArray) someObject.get(CABINS);
        ArrayList<Cabin> newCabins = new ArrayList<>();

        for (int i=0; i<JSONcabins.size(); i++) {

            // get the camper id
            UUID cabinID = UUID.fromString((String) JSONcabins.get(i));
            
            // search the array of cabins for the Cabin
            Cabin newCabin = getCabinByUUID(cabinID);

            // if the cabin exists, add to the Cabin array
            if (newCabin != null)
                newCabins.add(newCabin);
            
            // TODO else cabin is in the object but not in cabins?
        }

        return newCabins;
    }


    /**
     * Search and return the Session in the sessions array by the UUID
     * @param id UUID of the Session
     * @return Session if they exist in sessions and null otherwise
     */
    private static Session getSessionByUUID(UUID id) {
        for (Session session : sessions) {
            if (session.getUUID().compareTo(id) == 0)
                return session;
        }

        return null;
    }

    /**
     * Search and return the Cabin in the cabins array by the UUID
     * @param id UUID of the Cabin
     * @return Cabin if they exist in cabins and null otherwise
     */
    private static Cabin getCabinByUUID(UUID id) {
        for (Cabin cabin : cabins) {
            if (cabin.getUUID().compareTo(id) == 0)
                return cabin;
        }

        return null;
    }

    /**
     * Search and return the Camper in the campers array by the UUID
     * @param id UUID of the Camper
     * @return Camper if they exist in campers and null otherwise
     */
    private static Camper getCamperByUUID(UUID id) {
        for (Camper camper : campers) {
            if (camper.getUUID().compareTo(id) == 0)
                return camper;
        }

        return null;
    }

    /**
     * Return a user
     * @param user The JSONObject for the user
     * @return a new User class object
     */
    private static User getUser(JSONObject user, String userType) {
        User newUser;
        
        if (userType.equals("counselor")) {
            newUser = new Counselor(
                UUID.fromString((String) user.get(USER_ID)),
                (String) user.get(FIRST_NAME),
                (String) user.get(LAST_NAME),
                (String) user.get(USERNAME));
        }
        
        else {
            newUser = new User(
                UUID.fromString((String) user.get(USER_ID)),
                (String) user.get(FIRST_NAME),
                (String) user.get(LAST_NAME),
                (String) user.get(USERNAME));
        }

        newUser.setPassword( (String) user.get(PASSWORD) );
        newUser.addEmail( (String) user.get(EMAIL) );
        newUser.addPhoneNumber( (String) user.get(PHONE_NUMBER) );
        newUser.addPreferredContact( (String) user.get(PREFFERED_CONTACT) );
        newUser.addBirthday( LocalDate.parse((String) user.get(BIRTHDAY)) );
        newUser.addAddress( (String) user.get(ADDRESS) );
       
        String type = (String) user.get(TYPE);
        Type newType = Type.valueOf(type);
        newUser.setType(newType);

        // initialize the campers ArrayList first...
        getCampers();

        // Now add campers to cabins, because campers have been initialized...
        addCampersToCabins();
        
        // add Campers to the User
        newUser.addCampers(getSomeCampers(user));

        return newUser;
    }

    /**
     * Return medical
     * @param medical The JSONObject for the medical
     * @return a new Medical class object
     */
    private static Medical getMedical(JSONObject medical) {
        
        // get the doctor object
        JSONObject doctor = (JSONObject) medical.get(DOCTOR);
    
        // create a new doctor and add the info
        Contact doc = new Contact(
            (String) doctor.get(FIRST_NAME), 
            (String) doctor.get(LAST_NAME), 
            (String) doctor.get(PHONE_NUMBER) );
        doc.addAddress( (String) doctor.get(ADDRESS) );

        // get the allergies array
        ArrayList<String> newAllergies = new ArrayList<>();
        JSONArray allergies = (JSONArray) medical.get(ALLERGIES);

        // loop through and add info to the created allergies array
        for (int i=0; i<allergies.size(); i++)
            newAllergies.add( (String) allergies.get(i) );
        
        // get the medications array 
        ArrayList<Medication> newMedications = new ArrayList<>();
        JSONArray medications = (JSONArray) medical.get(MEDICATIONS);
        
        // loop through all medications
        for (int i=0; i<medications.size(); i++) {
            // the array contains Medication objects
            JSONObject medication = (JSONObject) medications.get(i);

            // get the medication info and add to the arraylist
            newMedications.add( new Medication(
                (String) medication.get(DESCRIPTION),
                (String) medication.get(TIME) ));
        }
        
        Medical toRetMed = new Medical(doc);
        toRetMed.addAllergies(newAllergies);
        toRetMed.addMedications(newMedications);
        
        return toRetMed;
    }

    /**
     * Return ArrayList of contacts
     * @param contacts The JSONArray of contacts
     * @return an ArrayList of Contact
     */
    private static ArrayList<Contact> getContacts(JSONArray contacts) {
        ArrayList<Contact> contactsList = new ArrayList<>();

        // loop through the JSONArray 
        for (int j=0; j<contacts.size(); j++) {
            // get a JSONObject (which contains ONE contact)
            JSONObject contact = (JSONObject) contacts.get(j);
            String address = (String) contact.get(ADDRESS);

            Contact newContact = new Contact(
                (String) contact.get(FIRST_NAME), 
                (String) contact.get(LAST_NAME), 
                (String) contact.get(PHONE_NUMBER));
            newContact.addAddress(address);

            // add this contact to the contacts arraylist
            contactsList.add(newContact);
        }

        return contactsList;
    }

    /**
     * Return the Activity array
     * @param schedules a JSONArray of schedules
     * @return The ArrayList of Activities
     */
    private static ArrayList<Activity> getActivities(JSONArray schedules) {
       ArrayList<Activity> newActivites = new ArrayList<>();

        for (int i=0; i<schedules.size(); i++) {
            JSONObject activity = (JSONObject) schedules.get(i);
            
            Activity newActivity = new Activity(
                (String) activity.get(TITLE),
                (String) activity.get(LOCATION));

            newActivity.addStartTime( (String) activity.get(START_TIME) );
            newActivity.addEndTime( (String) activity.get(END_TIME) );
            
            // get the ArrayList of notes
            ArrayList<String> newNotes = new ArrayList<>();
            JSONArray notes = (JSONArray) activity.get(NOTES);
            for (int j=0; j<notes.size(); j++)
                newNotes.add( (String) notes.get(j) );
            
            newActivity.addNotes(newNotes);

            newActivites.add(newActivity);
        }

        return newActivites;
    }
}
