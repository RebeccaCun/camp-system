package system;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataReader extends DataConstants {

    private static ArrayList<User> users;

    public static void main(String[] args) {
        ArrayList<User> u = getAllUsers();
    }
    
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
    }

    private static void getParents(JSONObject camp) {
        JSONArray parents = (JSONArray) camp.get(PARENTS);
        
        for (int i=0; i<parents.size(); i++) {
            UUID parentID = UUID.fromString((String) parents.get(i));
            // System.out.println(userID);

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
                    user.addBirthday( (String) parent.get(BIRTHDAY) );
                    user.addAddress( (String) parent.get(ADDRESS) );
                    
                    // campers specific to this parent
                    JSONArray parCampers = (JSONArray) parent.get(CAMPERS);

                    for (int k=0; k<parCampers.size(); k++) {
                        UUID camperID = UUID.fromString((String) parCampers.get(i));
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
}
