package system;

import java.util.ArrayList;

public class Activity {
    private String title;
    private String location;
    private String startTime;
    private String endTime;
    private ArrayList<String> notes;

    public Activity(String title, String location){
        this.title = title;
        this.location = location;
    }

    public void addStartTime(String startTime) {

    }
    
    public void addEndTime(String endTime) {
        
    }
    
    public void addNotes(ArrayList<String> note){

    }
    
    public void removeNotes(String notes){

    }
    
    public String viewTime(){
        return " ";
    }
    
    public void changeTime(String time){

    }
    
    public int calculateDuration(){
        return 0;
    }
    
    public String toString(){
        return " ";
    }
    
}
