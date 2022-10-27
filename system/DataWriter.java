package system;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 
 * @author Cyber Council
 */
public class DataWriter extends DataConstants {

    /**
     * 
     */
    public static void saveUsers() {
        UserList userListClass = UserList.getInstance();
        ArrayList<User> users = userListClass.getUsers();

        JSONArray jsonUsers = new JSONArray();

        // create the json objects
        for (User user : users) {
            jsonUsers.add(getUserJSON(user));
        }

        //  Write JSON file
        try (FileWriter file = new FileWriter(USERS_FILE_NAME)) {
            file.write(jsonUsers.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a JSONObject based on the given User
     * @param user the User which needs to be converted to JSONObject
     * @return a JSONObject for the given User
     */
    private static JSONObject getUserJSON(User user) {
        JSONObject userDetails = new JSONObject();

		userDetails.put(USER_ID, user.getId().toString());
		userDetails.put(FIRST_NAME, user.getFirstName());
		userDetails.put(LAST_NAME, user.getLastName());
		userDetails.put(USERNAME, user.getUserName());
		userDetails.put(PASSWORD, user.getPassword());
		userDetails.put(EMAIL, user.getEmail());
		userDetails.put(PHONE_NUMBER, user.getPhoneNumber());
		userDetails.put(PREFFERED_CONTACT, user.getPrefferedContact());
		userDetails.put(BIRTHDAY, user.getBirthday());
		userDetails.put(ADDRESS, user.getAddress());
		userDetails.put(TYPE, user.getType().toString());
		
        // ArrayList from getCampers() to JSONArray
        JSONArray jsonCampers = new JSONArray();
        ArrayList<Camper> campers = user.getCampers();
        for (Camper camper : campers)
            jsonCampers.add(camper.getUUID().toString());
        userDetails.put(CAMPERS, jsonCampers);
        
        return userDetails;
    }

    /**
     * 
     */
    public static void saveCounselors() {
        UserList counselorListClass = CounselorList.getInstance();
        ArrayList<Counselor> counselors = counselorListClass.getCounselors();

        JSONArray jsonCounselors = new JSONArray();

        // create the json objects
        for (Counselor counselor : counselors) {
            jsonCounselors.add(getCounselorJSON(counselor));
        }

        //  Write JSON file
        try (FileWriter file = new FileWriter(COUNSELORS_FILE_NAME)) {
            file.write(jsonCounselors.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a JSONObject based on the given Counselor
     * @param counselor the Counselor which needs to be converted to JSONObject
     * @return a JSONObject for the given Counselor
     */
    private static JSONObject getCounselorJSON(Counselor counselor) {

        // the counselor inherits user, so many attributes are common
        JSONObject counselorDetails = getUserJSON(counselor);
		
        // writing biography
        counselorDetails.put(BIOGRAPHY, counselor.getBiography());

        // writing the Medical attribute
        JSONObject jsonMedical = new JSONObject();
        Medical med = counselor.getMedical();

        // writing doctor
        JSONObject jsonDoctor = new JSONObject();
        Contact doctor = med.getDoctor();
        jsonDoctor.put(FIRST_NAME, doctor.getFirstName());
        jsonDoctor.put(LAST_NAME, doctor.getLastName());
        jsonDoctor.put(PHONE_NUMBER, doctor.getPhoneNumber());
        jsonDoctor.put(ADDRESS, doctor.getAddress());

        jsonMedical.put(DOCTOR, jsonDoctor);

        // writing allergies
        JSONArray jsonAllergies = new JSONArray();
        ArrayList<String> allergies = med.getAllergies();
        
        for (String allergy : allergies)
            jsonAllergies.add(allergy);

        jsonMedical.put(ALLERGIES, jsonAllergies);

        // writing medications
        JSONArray jsonMedications = new JSONArray();
        ArrayList<Medication> medications = med.getMedications();
        
        for (Medication medication : medications) {
            JSONObject jsonMedication = new JSONObject();
            
            jsonMedication.put(DESCRIPTION, medication.getDescription());
            jsonMedication.put(TIME, medication.getTime());

            jsonMedications.add(jsonMedication);
        }    
        
        jsonMedical.put(MEDICATIONS, jsonMedications);

        // Add the whole medical object to the counselor...
        counselorDetails.put(MEDICAL, jsonMedical);

        // writing the Cabin attribute...
        JSONArray jsonCabins = new JSONArray();
        ArrayList<Cabin> cabins = counselor.getCabins();
        
        for (Cabin cabin : cabins)
            jsonCabins.add(cabins.getID().toString());    

        counselorDetails.put(CABINS, jsonCabins);
        
        return counselorDetails;
    }


    /**
     * 
     */
    public static void saveCampers() {
        UserList camperListClass = CamperList.getInstance();
        ArrayList<Camper> campers = camperListClass.getUsers();

        JSONArray jsonCampers = new JSONArray();

        // create the json objects
        for (Camper camper : campers) {
            jsonCampers.add(getCamperJSON(camper));
        }

        //  Write JSON file
        try (FileWriter file = new FileWriter(CAMPERS_FILE_NAME)) {
            file.write(jsonCampers.toJSONString());
            file.flush();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a JSONObject based on the given Camper
     * @param camper the Camper which needs to be converted to JSONObject
     * @return a JSONObject for the given Camper
     */
    private static JSONObject getCamperJSON(Camper camper) {
        JSONObject camperDetails = new JSONObject();

		camperDetails.put(USER_ID, camper.getUUID().toString());
		camperDetails.put(FIRST_NAME, camper.getFirstName());
		camperDetails.put(LAST_NAME, camper.getLastName());
        // the birthday is in a local Date format
        camperDetails.put(BIRTHDAY, camper.getBirthday().toString());

        // write the emergency contacts
        camperDetails.put(EMERGENCY_CONTACTS, 
            getContactJSON( camper.getEmergencyContacts() ));

        // write the guradian contacts
        camperDetails.put(GUARDIANS, 
            getContactJSON( camper.getGuardians() ));

        // writing the Medical attribute
        JSONObject jsonMedical = new JSONObject();
        Medical med = camper.getMedical();

        // writing doctor
        JSONObject jsonDoctor = new JSONObject();
        Contact doctor = med.getDoctor();
        jsonDoctor.put(FIRST_NAME, doctor.getFirstName());
        jsonDoctor.put(LAST_NAME, doctor.getLastName());
        jsonDoctor.put(PHONE_NUMBER, doctor.getPhoneNumber());
        jsonDoctor.put(ADDRESS, doctor.getAddress());

        jsonMedical.put(DOCTOR, jsonDoctor);

        // writing allergies
        JSONArray jsonAllergies = new JSONArray();
        ArrayList<String> allergies = med.getAllergies();
        
        for (String allergy : allergies)
            jsonAllergies.add(allergy);

        jsonMedical.put(ALLERGIES, jsonAllergies);

        // writing medications
        JSONArray jsonMedications = new JSONArray();
        ArrayList<Medication> medications = med.getMedications();
        
        for (Medication medication : medications) {
            JSONObject jsonMedication = new JSONObject();
            
            jsonMedication.put(DESCRIPTION, medication.getDescription());
            jsonMedication.put(TIME, medication.getTime());

            jsonMedications.add(jsonMedication);
        }    
        
        jsonMedical.put(MEDICATIONS, jsonMedications);

        // Add the whole medical object to the camper...
        camperDetails.put(MEDICAL, jsonMedical);

        // Write num of strikes attribute
        camperDetails.put(NUMBER_STRIKES, camper.getNumberStrikes());

        // Write reason of stikes
        JSONArray jsonReasonStrikes = new JSONArray();
        ArrayList<String> reasonStrikes = camper.getReasonStrikes();
        for (String reasonStrike : reasonStrikes)
            jsonReasonStrikes.add(reasonStrike);
        camperDetails.put(REASON_STRIKES, jsonReasonStrikes);

        // Write notes
        JSONArray jsonNotes = new JSONArray();
        ArrayList<String> notes = camper.getNotes();
        for (String note : notes)
            jsonNotes.add(note);
        camperDetails.put(NOTES, jsonNotes);

        // Write sessions
        JSONArray jsonSessions = new JSONArray();
        ArrayList<Session> sessions = camper.getSessions();
        for (Session session : sessions)
            jsonReasonStrikes.add(session.getUUID().toString());
        camperDetails.put(SESSIONS, jsonSessions);

        return camperDetails;
    }

    /**
     * Return a JSONArray of the given Contacts ArrayList
     * @param contacts ArrayList<Contact>
     * @return JSONArray of Contacts
     */
    private static JSONArray getContactJSON(ArrayList<Contact> contacts) {
        JSONArray jsonArray = new JSONArray();
        
        for (Contact contact : contacts) {
            JSONObject jsonContact = new JSONObject();
            
            jsonContact.put(FIRST_NAME, contact.getFirstName());
            jsonContact.put(LAST_NAME, contact.getLastName());
            jsonContact.put(PHONE_NUMBER, contact.getPhoneNumber());
            jsonContact.put(ADDRESS, contact.getAddress());

            jsonArray.add(jsonContact);
        }

        return jsonArray;
    }
    
}
