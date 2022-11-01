package system;

/**
 * A Medication class that holds the information for a medication.
 * @author Cyber Council
 */
public class Medication {
    private String name;
    private String time;

    /**
     * Establishes an instance of the Medication class.
     * @param name A String representing the name of the Medication.
     * @param time A String representing the time of the Medication.
     */
    public Medication(String name, String time){
        this.name = name;
        this.time = time; 
    }
    
    /**
     * used to get the name of the medication
     * @return String representing name of medication
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Gives the time of day the medication is taken (aka 5:00 or dinner time)
     * @return String of time
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Creates a string detailing the attributes of the Medication class.
     * @return A string representation of the Medication class.
     */
    public String toString(){
        return "Name "+this.name+"\nTime: "+this.time;
    }
}