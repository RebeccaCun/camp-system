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
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * 
     * @return
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Creates a string detailing the attributes of the Medication class.
     * @return A string representation of the Medication class.
     */
    public String toString(){
<<<<<<< HEAD
        return "Take "+ description + " medication at the following time: " + time;
=======
        return "Name "+this.name+"\nTime: "+this.time;
>>>>>>> e839a270038a12ef0d592dde61636fab8512d862
    }
}