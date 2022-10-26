package system;

import java.util.ArrayList;

public class Activity {
    private String title;
    private String location;
    private String startTime;
    private String endTime;
    private ArrayList<String> notes;

    /**
     * Creates an activity
     * @param title of activity
     * @param location
     */
    public Activity(String title, String location){
        this.title = title;
        this.location = location;
    }

    public void addStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public void addEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public void addNotes(ArrayList<String> notes){
        this.notes = notes;
    }
    
    public void removeNotes(String notes){

    }
    /**
     * returns time to event
     * @return String
     */
    public String viewTime(){
        return "This activity is at "+ startTime + " and ends at " + endTime;
    }
    
    public void changeTime(String time){
        this.startTime = time;
    }
    //to do
    public int calculateDuration(){
        return 0;
    }

    /**
     * @return String of all infomation
     */
    public String toString(){
        return title + "is located at" + location+ " from "+ startTime + " to " + endTime;
    }
    
}
