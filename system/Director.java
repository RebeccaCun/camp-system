package system;

import java.util.ArrayList;

/**
 * A Director class that is a child of the User class.
 * @author Cyber Council
 */
public class Director extends User{
    private ArrayList<String> counselorNames;

    /**
     * Establishes an instance of the Director class.
     * @param firstName A String representing the first name of the Director class.
     * @param lastName A String representing the last name of the Director class.
     * @param userName A String representing the username of the Director class.
     */
    public Director(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName);
        counselorNames = new ArrayList<>();
    }

    /**
     * Creates a boolean depending on if a specific user is a counselor or not.
     * @param counselor The user to be verified.
     * @return A boolean representing the status of the user.
     */
    public boolean verifyCounselor(User counselor) {
        if(counselor.calculateAge() >= 16) {
            return true;
        }
        return false;
    }

    /**
     * Adds a strike to a specific camper.
     * @param camper The camper who is receiving the strike.
     */
    public void giveStrike(Camper camper, String reason) {
        for (int i = 0; i < campers.size(); i++) {
            if(campers.get(i) == camper) {
                camper.giveStrike(reason); //needs to be implemented in Camper
            }
        } 
    }   

    /**
     * Creates a string detailing the attributes of the Director class.
     * @return A string representation of the Director class.
     */
    public String toString() {
        String print = super.toString()+"\nCounselor names: ";
        for (int i = 0; i < counselorNames.size(); i++) {
			if (counselorNames.get(i) != null) {
                print += counselorNames.get(i)+"\n";
            }
		}
        return print;
    }
}
