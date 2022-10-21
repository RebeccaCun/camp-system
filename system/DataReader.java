package system;

import java.io.FileReader;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends DataConstants {

    private static ArrayList<User> users;
    private static ArrayList<Camper> campers;

    public static void main(String[] args) {
        ArrayList<Camper> u = getAllCampers();
    }
    
    /*
    public static ArrayList<User> getAllUsers() {
        users = new ArrayList<>();

        try {
            
            // read from the file that is defined in the DataConstants
            FileReader reader = new FileReader(CAMP_FILE_NAME);
            JSONParser parser = new JSONParser(); // maie a JSON parser
			
            // get the camp array from json
            JSONArray campJSON = (JSONArray)new JSONParser().parse(reader);

            // only have one object for the camp
            JSONObject camp = (JSONObject) campJSON.get(0);
            
            getParents(camp); // getParents(camp); //TODO
            getDirector(camp);
            getCampers(camp);
            getCounselors(camp);

            // users.add(new User());

        } catch (Exception e) {
            e.printStaciTrace();
        }

        return users;
    }*/

    public static ArrayList<Camper> getAllCampers() {
        // initiailize the campers arraylist
        campers = new ArrayList<>();
        
        try {
            
            FileReader camperReader = new FileReader(CAMPERS_FILE_NAME);
            JSONParser camperParser = new JSONParser();
            JSONArray camperJSON = (JSONArray) new JSONParser().parse(camperReader);

            for (int i=0; i<camperJSON.size(); i++) {
                JSONObject camper = (JSONObject) camperJSON.get(i);

                // get the attributes...
                UUID camperID = UUID.fromString((String) camper.get(USER_ID)); // NOTE
                String birthday = (String) camper.get(BIRTHDAY);

                // create the camper...
                Camper newCamper = new Camper(
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

                // // traverse to the camper in their json file
                // FileReader camperReader = new FileReader(CAMPERS_FILE_NAME);
                // JSONParser camperParser = new JSONParser();
                

                // for (int l=0;l<camperJSON.size(); l++) {
                //     JSONObject aCamper = (JSONObject) camperJSON.get(l);

                //     if (UUID.fromString((String) aCamper.get(USER_ID)).compareTo(camperID) == 0) {

                //     }

                // }

                // user.addCamper(new Camper(firstName, lastName, null, null, null, Campers));

                campers.add(newCamper);
            }
            
            getAllSessions();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return campers;
    }
    
    private static void getAllSessions() {
        try {
            
            FileReader sessionsReader = new FileReader(COUNSELORS_FILE_NAME);
            JSONParser sessionsParser = new JSONParser();
            JSONArray sessionsJSON = (JSONArray) new JSONParser().parse(sessionsReader);

            for (int i=0; i<sessionsJSON.size(); i++) {
                JSONObject session = (JSONObject) sessionsJSON.get(i);

                // get the attributes...
                UUID sessionID = UUID.fromString((String) session.get(USER_ID));

                // create the session
                Session newSession = new Session(
                    LocalDate.parse((String) session.get(START_DATE)),
                    LocalDate.parse((String) session.get(END_DATE)),
                    (String) session.get(AGE_GROUP));

                newSession.setAvailableSpots(
                    ((Long) session.get(AVAILABLE_SPOTS)).intValue());
                
                JSONArray themes = (JSONArray) session.get(THEMES);
                ArrayList<String> newThemes = new ArrayList<>();
                for (int j=0; j<themes.size(); j++)
                    newThemes.add((String) themes.get(i));
                
                // get cabins...
                JSONArray cabins = (JSONArray) session.get(CABINS);
                ArrayList<Cabin> sessionCabins = new ArrayList<>();
                for (int j=0; j<cabins.size(); j++) {
                    // sessionCabins.add((String) cabins.get(i));
                    // TODO add cabins
                }
                
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Cabin> getAllCabins() {
        try {
            
            FileReader cabinsReader = new FileReader(CABIN_FILE_NAME);
            JSONParser cabinsParser = new JSONParser();
            JSONArray cabinsJSON = (JSONArray) new JSONParser().parse(cabinsReader);

            for (int i=0; i<cabinsJSON.size(); i++) {
                JSONObject cabin = (JSONObject) cabinsJSON.get(i);

                // get the attributes...
                UUID cabinID = UUID.fromString((String) cabin.get(USER_ID));
                int cabinAge = ((Long) cabin.get(CABIN_AGE)).intValue();
                int maxCampers = ((Long) cabin.get(MAX_NO_OF_CAMPERS)).intValue();

                // TODO schedules
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static ArrayList<Counselor> getAllCounselors() {
        // initiailize the counselors arraylist
        ArrayList<Counselor> counselors = new ArrayList<>();
        
        try {
            FileReader counselorReader = new FileReader(COUNSELORS_FILE_NAME);
            JSONParser counselorParser = new JSONParser();
            JSONArray counselorJSON = (JSONArray) new JSONParser().parse(counselorReader);

            for (int i=0; i<counselorJSON.size(); i++) {
                JSONObject counselor = (JSONObject) counselorJSON.get(i);

                // create the counselor
                Counselor newCounselor = (Counselor) getUser(counselor);
                
                newCounselor.addBiography((String) counselor.get(BIOGRAPHY));
                newCounselor.addMedical(getMedical(
                    (JSONObject) counselor.get(MEDICAL)));

                // TODO cabins

            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return counselors;
    }

    /**
     * Return a user
     * @param user The JSONObject for the user
     * @return a new User class object
     */
    private static User getUser(JSONObject user) {

        UUID ID = UUID.fromString((String) user.get(USER_ID));
        String firstName = (String) user.get(FIRST_NAME);
        String lastName = (String) user.get(LAST_NAME);
        String userName = (String) user.get(USERNAME);
        
        User newUser = new User(firstName, lastName, userName);

        newUser.setPassword( (String) user.get(PASSWORD) );
        newUser.addEmail( (String) user.get(EMAIL) );
        newUser.addPhoneNumber( (String) user.get(PHONE_NUMBER) );
        newUser.addPreferredContact( (String) user.get(PREFFERED_CONTACT) );
        newUser.addBirthday( LocalDate.parse((String) user.get(BIRTHDAY)) );
        newUser.addAddress( (String) user.get(ADDRESS) );
        newUser.setType( (String) user.get(TYPE) );

        // JSONArray campers = (JSONArray) user.get(CAMPERS);
        // for(int i=0; i < campers.size(); i++){
        //     UUID camperID = UUID.fromString((String) campers.get(i));
        //     Camper camper = UserList.getInstance().getCamperByUUID(camperID);
        // }

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
}
