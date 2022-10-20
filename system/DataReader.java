package system;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.security.Guard;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends DataConstants {

    private static ArrayList<User> users;

    public static void main(String[] args) {
        ArrayList<Camper> u = getAllCampers();
    }
    /*
    public static ArrayList<User> getAllUsers() {
        users = new ArrayList<>();

        try {
            
            // read from the file that is defined in the DataConstants
            FileReader reader = new FileReader(CAMP_FILE_NAME);
            JSONParser parser = new JSONParser(); // make a JSON parser
			
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
            e.printStackTrace();
        }

        return users;
    }*/

    public static ArrayList<Camper> getAllCampers() {
        // initiailize the campers arraylist
        ArrayList<Camper> campers = new ArrayList<>();
        
        try {
            
            FileReader camperReader = new FileReader(CAMPERS_FILE_NAME);
            JSONParser camperParser = new JSONParser();
            JSONArray camperJSON = (JSONArray) new JSONParser().parse(camperReader);

            for (int i=0; i<camperJSON.size(); i++) {
                JSONObject camper = (JSONObject) camperJSON.get(i);

                // get the attributes...
                UUID camperID = UUID.fromString((String) camper.get(USER_ID));
                String firstName = (String) camper.get(FIRST_NAME);
                String lastName = (String) camper.get(LAST_NAME);
                String birthday = (String) camper.get(BIRTHDAY);

                System.out.println("\n" + camperID);
                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(birthday);

                // create the camper...
                Camper newCamper = new Camper(firstName, lastName, LocalDate.parse(birthday));

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

                // TODO sessions

                // // traverse to the camper in their json file
                // FileReader camperReader = new FileReader(CAMPERS_FILE_NAME);
                // JSONParser camperParser = new JSONParser();
                

                // for (int l=0;l<camperJSON.size(); l++) {
                //     JSONObject aCamper = (JSONObject) camperJSON.get(l);

                //     if (UUID.fromString((String) aCamper.get(USER_ID)).compareTo(camperID) == 0) {

                //     }

                // }

                // user.addCamper(new Camper(firstName, lastName, null, null, null, parCampers));
            }    
        } catch (Exception e) {
            e.printStackTrace();
        }

        return campers;
    }

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
    

    /*
    private static ArrayList<User> getParents(JSONObject camp) {
            ArrayList<User> = new ArrayList<User>();
            try {
                
                // read from users.json
                FileReader parentReader = new FileReader(USERS_FILE_NAME);
                JSONParser parentParser = new JSONParser(); // make a JSON parser

                // the user array
                JSONArray parentsJSON =  (JSONArray) new JSONParser().parse(parentReader);

                for (int j=0; j<parentsJSON.size(); j++) {
                    JSONObject parent = (JSONObject) parentsJSON.get(i);

                    UUID ID = UUID.fromString((String) parent.get(USER_ID));
                    String firstName = (String) parent.get(FIRST_NAME);
                    String lastName = (String) parent.get(LAST_NAME);
                    String userName = (String) parent.get(USERNAME);

                    User user = new User(firstName, lastName, userName);
                    user.addPhoneNumber( (String) parent.get(PHONE_NUMBER) );
                    user.addPreferredContact( (String) parent.get(PREFFERED_CONTACT) );
                    user.addBirthday( getDate((String) parent.get(BIRTHDAY) ));
                    user.addAddress( (String) parent.get(ADDRESS) );
                    JSONArray parCampers = (JSONArray) parent.get(CAMPERS);

                    for(int k=0; k < parCampers.size(); i++){
                        UUID camperID = UUID.fromString((String) parCampers.get(k));
                        Camper camper = UserList.getInstance().getCamperByUUID(camperID)
                        
                    }
                    
                    }

                    users.add(user);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void getCampers(JSONObject camp) {
        JSONArray campers = (JSONArray) camp.get(CAMPERS);
    
        for (int i=0; i<campers.size(); i++) {
            UUID camperID = UUID.fromString((String) campers.get(i));
        }
    }
    
    private static void getCounselors(JSONObject camp) {
        JSONArray couselors = (JSONArray) camp.get(COUNSELORS);
        
        for (int i=0; i<couselors.size(); i++) {
            UUID counselorID = UUID.fromString((String) couselors.get(i));
        }
    }

    private static void getDirector(JSONObject camp) {
        JSONObject director = (JSONObject) camp.get(DIRECTOR);

        UUID ID = UUID.fromString((String) director.get(USER_ID));
        String firstName = (String) director.get(FIRST_NAME);
        String lastName = (String) director.get(LAST_NAME);
        String userName = (String) director.get(USERNAME);
        String phoneNumber = (String) director.get(PHONE_NUMBER);
        String prefCont = (String) director.get(PREFFERED_CONTACT);
        String birthday = (String) director.get(BIRTHDAY);
        String address = (String) director.get(ADDRESS);
        
        // System.out.println(dirfirstName);
    }
    */
}
