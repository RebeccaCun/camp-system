package system;

import java.util.ArrayList;

/**
 * An Schedule class that holds the information for a schedule for the camp.
 * @author Cyber Council
 */
public class Schedule {
    private ArrayList<Activity> activities;

    /**
     * Establishes an instance of the Schedule class.
     * @param activities An ArrayList representing the list of activities.
     */
    public Schedule(ArrayList<Activity> activities){ 
        this.activities = activities;
    }

    /**
     * Adds an activity to the Schedule class.
     * @param activity The activity to be added.
     */
    public void addActivity(Activity activity) {
        this.activities.add(activity);
    }

    /**
     * Removes a activity from the Schedule class.
     * @param activity The activity to be removed.
     */
    public void removeActivity(Activity activity) {
        for(int i = 0; i < this.activities.size(); i++) {
            if(this.activities.get(i) == activity) {
                this.activities.remove(i);
            }
        }
    }

    /**
     * Creates a string detailing the attributes of the Schedule class.
     * @return A string representation of the Schedule class.
     */
    public String toString(){
        String print = "Activities: ";
        for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i) != null) {
                print += activities.get(i)+"\n";
            }
		}
        return print;
    }
}
