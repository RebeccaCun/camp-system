package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Cabin {
    private UUID id;
    private int cabinAge;
    private int MaxNumberOfCampers;
    private int sessionDuration;
    private ArrayList<Camper> Campers;
    private HashMap<Day, Schedule> schedules;

    public Cabin(int cabinAge, int sessionDuration) {
        this.cabinAge = cabinAge;
        this.sessionDuration = sessionDuration;
    }

    public Cabin(UUID id, int cabinAge, int sessionDuration) {
    }

    public UUID getUUID() {
        return id;
    }

    public void addMaxCampers(int maxCampers) {

    }

    public void addCampers(ArrayList<Camper> campers) {

    }

    public void addCamper(Camper camper) {

    }

    public void addSchedules(HashMap<Day, Schedule> schedules) {

    }

    public void addSchedule(Schedule schedule, Day day) {

    }

    public String viewSchedules() {
        return "";
    }

    public void editSchedule(String schedule) {

    }

    public boolean hasAvailableBed() {
        return false;
    }

    public String toString() {
        return "";
    }
}
