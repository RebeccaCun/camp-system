package system;
import java.io.FilterInputStream;
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
        for(User user : userList)
        {
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    /**
     * 
     * @param userName
     * @return
     */
    public boolean hasUser(String userName) {
        for(User user : userList)
        {
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param userName
     * @return
     */
    public ArrayList<User> getUsers() {
        return userList;;
    }

    /**
     * 
     * @param userName
     * @return
     */
    public boolean addUser(String userName, String firstName, String lastName) {
        if(hasUser(userName)) return false;
        userList.add(new User(firstName, lastName, userName));
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
        DataWriter.saveUsers();
    }

    
}
