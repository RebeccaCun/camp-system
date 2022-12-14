package system;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Writes Data to JSON
 * @author Cyber Council
 */
public class DataWriter extends DataConstants {

    /**
     * Save the User ArrayList from the UserList class
     * into the USERS_FILE_NAME file
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
		userDetails.put(PREFFERED_CONTACT, user.getPreferredContact());
		userDetails.put(ADDRESS, user.getAddress());

        try {
            userDetails.put(TYPE, user.getType().toString());
        } catch (NullPointerException n) {
            userDetails.put(TYPE, null);
        }

        try {
            userDetails.put(BIRTHDAY,
                user.getBirthday().toString());
        } catch (NullPointerException n) {
            userDetails.put(BIRTHDAY, null);
        }

        // ArrayList from getCampers() to JSONArray
        JSONArray jsonCampers = new JSONArray();
        ArrayList<Camper> campers = user.getCampers();
        for (Camper camper : campers)
            jsonCampers.add(camper.getUUID().toString());
        userDetails.put(CAMPERS, jsonCampers);

        return userDetails;
    }

    /**
     * Save the Counselor ArrayList from the CounselorsList class
     * into the COUNSELORS_FILE_NAME file
     */
    public static void saveCounselors() {
        CounselorList counselorListClass = CounselorList.getInstance();
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

        JSONObject jsonDoctor = new JSONObject();
        JSONArray jsonAllergies = new JSONArray();
        JSONArray jsonMedications = new JSONArray();
        try {
            // writing doctor
            Contact doctor = med.getDoctor();
            jsonDoctor.put(FIRST_NAME, doctor.getFirstName());
            jsonDoctor.put(LAST_NAME, doctor.getLastName());
            jsonDoctor.put(PHONE_NUMBER, doctor.getPhoneNumber());
            jsonDoctor.put(ADDRESS, doctor.getAddress());

            // writing allergies
            ArrayList<String> allergies = med.getAllergies();
            for (String allergy : allergies)
                jsonAllergies.add(allergy);

            // writing medications
            ArrayList<Medication> medications = med.getMedications();
            for (Medication medication : medications) {
                JSONObject jsonMedication = new JSONObject();

                jsonMedication.put(NAME, medication.getName());
                jsonMedication.put(TIME, medication.getTime());

                jsonMedications.add(jsonMedication);
            }
        } catch (Exception e) {
            // do nothing if they are null
        }
        jsonMedical.put(DOCTOR, jsonDoctor);
        jsonMedical.put(ALLERGIES, jsonAllergies);
        jsonMedical.put(MEDICATIONS, jsonMedications);

        // Add the whole medical object to the counselor...
        counselorDetails.put(MEDICAL, jsonMedical);

        // writing the Cabin attribute...
        JSONArray jsonCabins = new JSONArray();
        ArrayList<Cabin> cabins = counselor.getCabins();

        for (Cabin cabin : cabins)
            jsonCabins.add(cabin.getUUID().toString());

        counselorDetails.put(CABINS, jsonCabins);

        return counselorDetails;
    }


    /**
     * Save the Camper ArrayList from the CamperList class
     * into the CAMPERS_FILE_NAME file
     */
    public static void saveCampers() {
        CamperList camperListClass = CamperList.getInstance();
        ArrayList<Camper> campers = camperListClass.getCampers();

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
        try {
            camperDetails.put(BIRTHDAY, camper.getBirthday().toString());
        } catch (NullPointerException n) {
            camperDetails.put(BIRTHDAY, null);
        }

        // write the emergency contacts
        camperDetails.put(EMERGENCY_CONTACTS,
            getContactJSON( camper.getEmergencyContacts() ));

        // write the guradian contacts
        camperDetails.put(GUARDIANS,
            getContactJSON( camper.getGuardians() ));

        // writing the Medical attribute
        JSONObject jsonMedical = new JSONObject();
        // Try getting the Medical attribute from Camper
        // But, it could be null
        try {
            Medical med = camper.getMedical();
            // continue if not null

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

                jsonMedication.put(NAME, medication.getName());
                jsonMedication.put(TIME, medication.getTime());

                jsonMedications.add(jsonMedication);
            }

            jsonMedical.put(MEDICATIONS, jsonMedications);

        } catch (NullPointerException n) {
            // if the Medical attribute is not initialized,
            //  then do nothing.
        }

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
     * Save the Session ArrayList from the SessionList class
     * into the SESSION_FILE_NAME file
     */
    public static void saveSessions() {
        SessionList sessionListClass = SessionList.getInstance();
        ArrayList<Session> sessions = sessionListClass.getSessions();

        JSONArray jsonSessions = new JSONArray();

        // create the json objects
        for (Session session: sessions) {
            jsonSessions.add(getSessionJSON(session));
        }

        //  Write JSON file
        try (FileWriter file = new FileWriter(SESSION_FILE_NAME)) {
            file.write(jsonSessions.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a JSONObject based on the given Session
     * @param session the Session which needs to be converted to JSONObject
     * @return a JSONObject for the given Session
     */
    private static JSONObject getSessionJSON(Session session) {
        JSONObject sessionDetails = new JSONObject();

        try {
            sessionDetails.put(START_DATE,
                session.getStartDate().toString());
        } catch (NullPointerException n) {
            sessionDetails.put(START_DATE, null);
        }

        try {
            sessionDetails.put(END_DATE,
                session.getEndDate().toString());
        } catch (NullPointerException n) {
            sessionDetails.put(END_DATE, null);
        }

        try {
		    sessionDetails.put(USER_ID, session.getUUID().toString());
        } catch (NullPointerException n) {
            sessionDetails.put(USER_ID, null);
        }

        sessionDetails.put(AVAILABLE_SPOTS, session.getAvailableSpots());
        sessionDetails.put(THEME, session.getTheme());

        // get the cabin UUID array
        ArrayList<Cabin> cabins = session.getCabins();
        // make the JSONArray to populate
        JSONArray jsonCabins = new JSONArray();

        for (Cabin cabin : cabins)
            jsonCabins.add(cabin.getUUID().toString());
        sessionDetails.put(CABINS, jsonCabins);

        return sessionDetails;
    }

    /**
     * Save the Cabin ArrayList from the CabinList class
     * into the CABIN_FILE_NAME file
     */
    public static void saveCabins() {
        CabinList cabinClassList = CabinList.getInstance();
        ArrayList<Cabin> cabins = cabinClassList.getCabins();

        JSONArray jsonCabins = new JSONArray();

        // create the json objects
        for (Cabin cabin: cabins) {
            jsonCabins.add(getCabinJSON(cabin));
        }

        //  Write JSON file
        try (FileWriter file = new FileWriter(CABIN_FILE_NAME)) {
            file.write(jsonCabins.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return a JSONObject based on the given Cabin
     * @param cabin the Cabin which needs to be converted to JSONObject
     * @return a JSONObject for the given cabin
     */
    private static JSONObject getCabinJSON(Cabin cabin) {
        JSONObject cabinDetails = new JSONObject();

	    cabinDetails.put(USER_ID, cabin.getUUID().toString());
        cabinDetails.put(MIN_CABIN_AGE, cabin.getMinCabinAge());
        cabinDetails.put(MAX_CABIN_AGE, cabin.getMaxCabinAge());
        cabinDetails.put(MAX_NO_OF_CAMPERS, cabin.getMaxNumberOfCampers());

        // add schedules...
        JSONObject jsonSchedule = new JSONObject();
        HashMap<Day, Schedule> schedules = cabin.getSchedules();

        // get the Day values
        Set<Day> setDays = schedules.keySet();

        // convert Day values to String and add to an ArrayList
        ArrayList<String> days = new ArrayList<>();
        for (Day day : setDays)
            days.add(day.toString());
        // days contains list of all the Day values from the JSON but as Strings

        // loop through each day..
        for (String day : days) {
            JSONArray daySchedules = new JSONArray();
            Schedule sched = schedules.get(Day.valueOf(day));

            ArrayList<Activity> activities = sched.getActivities();

            for (Activity activity : activities) {
                JSONObject jsonActivity = new JSONObject();

                jsonActivity.put(TITLE, activity.getTitle());
                jsonActivity.put(LOCATION, activity.getLocation());
                jsonActivity.put(START_TIME, activity.getStartTime());
                jsonActivity.put(END_TIME, activity.getEndTime());

                // get the notes ArrayList and add to a JSONArray
                ArrayList<String> notes = activity.getNotes();
                JSONArray jsonNotes = new JSONArray();
                for (String note : notes)
                    jsonNotes.add(note);
                jsonActivity.put(NOTES, jsonNotes);

                daySchedules.add(jsonActivity);
            }

            jsonSchedule.put(day, daySchedules);
        }
        cabinDetails.put(SCHEDULES, jsonSchedule);

        // get campers...
        JSONArray jsonCampers = new JSONArray();
        ArrayList<Camper> campers = cabin.getCampers();
        for (Camper camper : campers)
            jsonCampers.add(camper.getUUID().toString());

        cabinDetails.put(CAMPERS, jsonCampers);

        return cabinDetails;
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
