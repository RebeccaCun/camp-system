package system;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 
 * @author Cyber Council
 */
public class UserList {

    private ArrayList<User> users;
    private ArrayList<Counselor> counselors;
    private ArrayList<Camper> campers;

    private static UserList userList;

    /**
     * 
     */
    private UserList() {
        users = DataReader.getAllUsers();
        counselors = DataReader.getAllCounselors();
        campers = DataReader.getAllCampers();
    }

    /**
     * 
     * @return
     */
    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		return userList;   
    }
    
    /**
     * 
     * @param userName
     * @return
     */
    public User getUser(String userName) {
        return null;
    }

    /**
     * 
     * @param userName
     * @return
     */
    public boolean hasUser(String userName) {
        return true;
    }

    /**
     * 
     * @param userName
     * @return
     */
    public ArrayList<User> getUsers() {
        return null;
    }

    /**
     * 
     * @param userName
     * @return
     */
    public boolean addUser(User user) {
        return true;
    }

    /**
     * 
     * @param userName
     * @return
     */
    public void editUser(String userName, String password) {

    }

    /**
     * 
     * @param userName
     * @return
     */
    public void saveUsers() {

    }

    
}
