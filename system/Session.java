package system;

import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * An Session class that holds the information for a session for the camp.
 * @author Cyber Council
 */
public class Session {
    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int availableSpots;
    private String theme;
    private ArrayList<Cabin> cabins;

    /**
     * Establishes an instance of the Session class.
     * @param startDate A String representing the start date of the Session.
     * @param endDate A String representing the end date of the Session.
     */
    public Session(LocalDate startDate, LocalDate endDate) {
        id = UUID.randomUUID();
        this.startDate = startDate;
        this.endDate = endDate;
        cabins = new ArrayList<>();
    }

    /**
     * Establishes an instance of the Session class with UUID.
     * @param id A UUID representing the ID of the Session.
     * @param startDate A String representing the start date of the Session.
     * @param endDate A String representing the end date of the Session.
     */
    public Session(UUID id, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        cabins = new ArrayList<>();
    }

    public LocalDate getStartDate(){
        return this.startDate;
    }

    public LocalDate getEndDate(){
        return this.endDate;
    }

    public ArrayList<Cabin> getCabins(){
        return this.cabins;
    }

    public UUID getUUID() {
        return this.id;
    }

    public String getTheme() {   //add themes AND decription going with the theme;
        return this.theme;
    }

    /**
     * 
     * @return
     */
    public int getAvailableSpots() {
        return this.availableSpots;
    }

    public void decreaseAvailableSpots() { //not in the uml - Rebecca
        this.availableSpots--;
    }
    
    public void setAvailableSpots(int availableSpots) {
        this.availableSpots = availableSpots;
    }
    
    /**
     * Creates a boolean depending on if the Session have available spots.
     * @return A boolean representing the status of the Session.
     */
    public boolean isAvailable() {
        return cabins.size() < availableSpots;
    }
    
    /**
     * Adds a list of cabins to the Session class.
     * @param allergies An ArrayList to be added.
     */
    public void addCabins(ArrayList<Cabin> cabins) {
        this.cabins = cabins;
    }

    /**
     * Adds a theme to the Session class.
     * @param theme The theme to be added.
     */
    public void addTheme(String theme) {
        this.theme = theme;
    }

    /**
     * Adds a cabin to the Session class.
     * @param cabin The cabin to be added.
     */
    public void addCabin(Cabin cabin) {
        this.cabins.add(cabin);
    }
    
    /**
     * Creates a string detailing the attributes of the Session class.
     * @return A string representation of the Session class.
     */
    public String toString() {
        String print = "Start Date: "+this.startDate+"\nEnd Date: "+this.endDate+"\nAvailable spots: "+this.availableSpots+"\nTheme: "+this.theme+"\nCabins: ";
        for (int i = 0; i < cabins.size(); i++) {
			if (cabins.get(i) != null) {
                print += cabins.get(i)+"\n";
            }
		}
        return print;
    }
}