package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * A Cabin class that holds the information for a cabin at the camp.
 * @author Cyber Council
 */
public class Cabin {
    private UUID id;
    private int cabinAge;
    private int MaxNumberOfCampers;
    private int sessionDuration;
    private ArrayList<Camper> campers;
    private HashMap<Day, Schedule> schedules;

    /**
     * Establishes an instance of the Cabin class.
     * @param cabinAge A Integer representing the cabin age of the Cabin.
     * @param sessionDuration A Interger representing the session duration of the Cabin.
     */
    public Cabin(int cabinAge, int sessionDuration) {
        this.cabinAge = cabinAge;
        this.sessionDuration = sessionDuration;
    }

    /**
     * Establishes an instance of the Cabin class with UUID.
     * @param id A UUID representing the ID of the Cabin.
     * @param cabinAge A Integer representing the cabin age of the Cabin.
     * @param sessionDuration A Interger representing the session duration of the Cabin.
     */
    public Cabin(UUID id, int cabinAge, int sessionDuration) {
        this.id = id;
        this.cabinAge = cabinAge;
        this.sessionDuration = sessionDuration;
    }

    /**
     * Returns the UUID of the Cabin class.
     * @return the UUID of the Cabin.
     */
    public UUID getUUID() {
        return id;
    }

    /**
     * Adds the max number of campers to the Cabin class.
     * @param maxCampers The integer to be added.
     */
    public void addMaxCampers(int maxCampers) {
        this.MaxNumberOfCampers = maxCampers;
    }

    /**
     * Adds a list of campers to the Cabin class.
     * @param campers The ArrayList to be added.
     */
    public void addCampers(ArrayList<Camper> campers) {
        this.campers = campers;
    }

    /**
     * Adds a camper to the list of campers in the Cabin class.
     * @param camper The camper to be added.
     */
    public void addCamper(Camper camper) {
        this.campers.add(camper);
    }

    /**
     * Adds a HashMap of schedules to the Cabin class.
     * @param schedules The HashMap to be added.
     */
    public void addSchedules(HashMap<Day, Schedule> schedules) {
        this.schedules = schedules;
    }

    /**
     * Adds a schedule to the HashMap of schedules to the Cabin class.
     * @param schedule The schedule to be added.
     * @param day The day to be added.
     */
    public void addSchedule(Schedule schedule, Day day) {
        schedules.put(day, schedule);
    }

    /**
     * Creates a string detailing the schedules of the Cabin class.
     * @return A string representation of the schedules.
     */
    public String viewSchedules() {
        String print = "";
        for (Day day : schedules.keySet()) {
            print += day;
            print += schedules.get(day)+"\n";
        }
        return print;
    }

    /**
     * Edits a former schedule.
     * @param oldSchedule The old schedule to be edited.
     * @param newSchedule The new schedule to be added.
     */
    public void editSchedule(Schedule oldSchedule, Schedule newSchedule) {
        for (Day day : this.schedules.keySet()) {
            if(schedules.get(day) == oldSchedule) {
                schedules.replace(day, newSchedule);
            }
        }
    }

    /**
     * Creates a boolean depending on if the Cabin have available beds.
     * @return A boolean representing the status of the cabin.
     */
    public boolean hasAvailableBed() {
        return campers.size() < this.MaxNumberOfCampers;
    }

    /**
     * Creates a string detailing the attributes of the Cabin class.
     * @return A string representation of the Cabin class.
     */
    public String toString() {
        String print = "Cabin age: "+this.cabinAge+"\nMaximum number of campers: "+this.MaxNumberOfCampers+"\nSession Duration: "+this.sessionDuration;
        for (int i = 0; i < campers.size(); i++) {
			if (campers.get(i) != null) {
                print += campers.get(i)+"\n";
            }
		}
        print += "\nSchedules: ";
        for (Day day : schedules.keySet()) {
            print += day+", ";
            print += schedules.get(day)+"\n";
        }
        return print;
    }
}
