package system;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 
 * @author Cyber Council
 */
public class DataWriter extends DataConstants {

    public static void saveUsers() {
        UserList userListClass = UserList.getInstance();
        ArrayList<User> users = userListClass.getUsers();

        JSONArray jsonUsers = new JSONArray();

        // create the json objects
        for (User user : users) {
            jsonUsers.add(getUserJSON(user));
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
        
        return userDetails;
    }
    
}
