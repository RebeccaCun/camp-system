package system;
import java.util.ArrayList;

/**
 * 
 * @author Cyber Council
 */
public class UserList {

    private ArrayList<User> users;
    private static UserList userList;

    /**
     * 
     */
    private UserList() {

    }

    /**
     * 
     * @return
     */
    public static UserList getInstance() {

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
    public boolean addUser(String userName, String password) {
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