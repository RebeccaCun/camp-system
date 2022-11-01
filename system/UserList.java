package system;

import java.util.ArrayList;

/**
 * An UserList class that contains all of the users for the camp system.
 * @author Cyber Council
 */
public class UserList {
    private ArrayList<User> users = DataReader.getAllUsers();
    private static UserList userList;

    /**
     * Initializes an instance of the UserList class.
     */
    private UserList() {}

    /**
     * Creates an instance of the UserList class.
     * @return The created UserList instance.
     */
    public static UserList getInstance() {
        if (userList == null) {
			userList = new UserList();
		}
		return userList;   
    }
    
    /**
     * Searches for a specific User and returns them if found.
     * @param userName The username of the User being searched for.
     * @return The user being searched for.
     */
    public User getUser(String userName) {
        for(User user : users)
        {
            if(user.getUserName().equals(userName)){
                return user;
            }
        }
        return null;
    }

    /**
     * Searches for a specific User and returns a boolean depending of it they are found.
     * @param userName The username of the User being searched for.
     * @return The boolean representing the status of the User..
     */
    public boolean hasUser(String userName) {
        for(User user : users)
        {
            if(user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Adds a user to the User class.
     * @param user The User to be added.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Edits a User in the list.
     * @param user The user to be edited.
     * @param newFirstName The first name of the new User.
     * @param newLastName The last name of the new User.
     * @param newUserName The user name of the new User.
     */
    public void editUser(User user, String newFirstName, String newLastName, String newUserName) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i) == user) {
                users.set(i,new User(newFirstName, newLastName, newUserName));
            }
        }
    }

    /**
     * Saves the UserList.
     */
    public void saveUsers() {
        DataWriter.saveUsers();
    }    
}
