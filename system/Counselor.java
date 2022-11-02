package system;

import java.util.ArrayList;
import java.util.UUID;

/**
 * A Counselor class that is a child of the User class.
 * @author Cyber Council
 */
public class Counselor extends User{
    private String biography;
    private ArrayList<Cabin> cabins;
    private Medical medical;

    /**
     * Establishes an instance of the Counselor class.
     * @param firstName A String representing the first name of the Counselor class.
     * @param lastName A String representing the last name of the Counselor class.
     * @param userName A String representing the username of the Counselor class.
     */
    public Counselor(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName);
        cabins = new ArrayList<>();
    }

    /**
     * Establishes an instance of the Counselor class with UUID.
     * @param id A UUID representing the ID of the Counselor.
     * @param firstName A String representing the first name of the Counselor class.
     * @param lastName A String representing the last name of the Counselor class.
     * @param userName A String representing the username of the Counselor class.
     */
    public Counselor(UUID id, String firstName, String lastName, String userName) {
        super(id, firstName, lastName, userName);
        cabins = new ArrayList<>();
    }

    /**
     * Adds a biography to the Counselor class.
     * @param biography The String to be added.
     */
    public void addBiography(String biography) {
        this.biography = biography;
    }

    /**
     * Adds a strike to a specific camper.
     * @param camper The camper who is receiving the strike.
     */
    public void giveStrike(Camper camper, String reason) {
        for (int i = 0; i < campers.size(); i++) {
            if(campers.get(i) == camper) {
                camper.giveStrike(reason); 
            }
        }
    }

    /**
     * Adds medical information to the Counselor class.
     * @param medical The medical info to be added.
     */
    public void addMedical(Medical medical) {
        this.medical = medical;
    }
    
    /**
     * Adds a list of cabins to the Counselor class.
     * @param cabins The ArrayList to be added.
     */
    public void addCabins(ArrayList<Cabin> cabins) {
        this.cabins = cabins;
    }

    /**
     * Returns the biography of the User class.
     * @return The string of the biography for the Counselor.
     */
    public String getBiography() {
        return this.biography;
    }

    /**
     * Returns the cabins of the Counselor class.
     * @return The ArrayList of the cabins for the Counselor.
     */
    public ArrayList<Cabin> getCabins() {
        return this.cabins;
    }

   /**
     * Returns the medical information of the Counselor class.
     * @return The Medical information for the Counselor.
     */
    public Medical getMedical() {
        return this.medical;
    }

    /**
     * Creates a string detailing the attributes of the Counselor class.
     * @return A string representation of the Counselor class.
     */
    public String toString() {
        String print = super.toString()+"Bio: "+this.biography+"\nCabins: ";
        for (int i = 0; i < cabins.size(); i++) {
			if (cabins.get(i) != null) {
                print += cabins.get(i)+"\n";
            }
		}
        return print+="\nMedical info: "+this.medical;
    }
}
