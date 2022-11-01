package system;

import java.util.ArrayList;
import java.time.LocalTime;
import java.time.Duration;

/**
 * An Activity class that holds the information for an activity for the camp.
 * @author Cyber Council
 */
public class Activity {
    private String title;
    private String location;
    private String startTime; //format: HH:MM
    private String endTime; //format: HH:MM
    private ArrayList<String> notes;

    /**
     * Establishes an instance of the Activity class.
     * @param title A String representing the title of the Activity.
     * @param location A String representing the location of the Activity.
     */
    public Activity(String title, String location){
        this.title = title;
        this.location = location;
        notes = new ArrayList<>();
    }

    /**
     * Adds the start time of the activity to the Activity class.
     * @param startTime The String to be added.
     */
    public void addStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Adds the end time of the activity to the Activity class.
     * @param endTime The String to be added.
     */
    public void addEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Adds a list of notes to the Activity class.
     * @param notes The ArrayList to be added.
     */
    public void addNotes(ArrayList<String> notes){
        this.notes = notes;
    }

    /**
     * Removes a note from the Activity class.
     * @param note The String to be removed.
     */
    public void removeNote(String note){
        for(int i = 0; i < this.notes.size(); i++) {
            if(this.notes.get(i) == note) {
                this.notes.remove(i);
            }
        }
    }

    /**
     * Creates a string detailing the duration of the activity.
     * @return A string representation of the time.
     */
    public String viewTime(){
        return "This activity starts "+this.startTime+" and ends at "+this.endTime+".";
    }

    /**
     * Calculates and returns the duration of the activity.
     * @return An long representing the total time of the activity in minutes.
     */
    public long calculateDuration(){
        LocalTime start = LocalTime.of(Integer.parseInt(startTime.substring(0, 1)), Integer.parseInt(startTime.substring(3, 4)), 0);
        LocalTime end = LocalTime.of(Integer.parseInt(endTime.substring(0, 1)), Integer.parseInt(endTime.substring(3, 4)), 0);
        return Duration.between(start, end).toMinutes();
    }

    /**
     * 
     * @return
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 
     * @return
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * 
     * @return
     */
    public String getStartTime() {
        return this.startTime;
    }

    /**
     * 
     * @return
     */
    public String getEndTime() {
        return this.endTime;
    }

    /**
     * 
     * @return
     */
    public ArrayList<String> getNotes() {
        return this.notes;
    }

    /**
     * Creates a string detailing the attributes of the Activity class.
     * @return A string representation of the Activity class.
     */
    public String toString(){
        return this.title+"is located at"+this.location+" from "+this.startTime+" to "+this.endTime;
    }
    
}