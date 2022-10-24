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
        this.id = id;
        this.cabinAge = cabinAge;
        this.sessionDuration = sessionDuration;
    }

    public UUID getUUID() {
        return id;
    }

    public void addMaxCampers(int maxCampers) {
        this.MaxNumberOfCampers = maxCampers;
    }

    public void addCampers(ArrayList<Camper> campers) {
        this.Campers = campers;
    }

    public void addCamper(Camper camper) {
        this.Campers.add(camper);
    }

    public void addSchedules(HashMap<Day, Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule, Day day) {
        schedules.put(day, schedule);
    }

    public String viewSchedules() {
        return System.out.println(schedules);
    }

    public void editSchedule(String schedule) {
        //need more info on method
    }

    public boolean hasAvailableBed() {
        return Campers.size() < this.MaxNumberOfCampers;
    }

    public String toString() {
        String print = "Cabin age: "+this.cabinAge+"\nMaximum number of campers: "+this.MaxNumberOfCampers+"\nSession Duration: "+this.sessionDuration+;
        for (int i = 0; i < Campers.size(); i++) {
			if (Campers.get(i) != null) {
                print += Campers.get(i)+"\n";
            }
		}
        print += "\nSchedules: "+System.out.println(schedules);
        return print;
    }
}
