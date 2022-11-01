package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.Random;

/**
 * A Cabin class that holds the information for a cabin at the camp.
 * @author Cyber Council
 */
public class Cabin {
    private UUID id;
    private int minCabinAge;
    private int maxCabinAge;
    private int MaxNumberOfCampers = 8;
    private ArrayList<Camper> campers;
    private HashMap<Day, Schedule> schedules;

    /**
     * Establishes an instance of the Cabin class.
     * @param minCabinAge A Integer representing the minimum cabin age of the Cabin.
     * @param maxCabinAge A Integer representing the maximum cabin age of the Cabin.
     */
    public Cabin(int minCabinAge, int maxCabinAge) {
        this.minCabinAge = minCabinAge;
        this.maxCabinAge = maxCabinAge;
        Day days[] = {Day.SUNDAY, Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY};
        ArrayList<Activity> template = new ArrayList<Activity>();
        template.add(new Activity("Archery", "Field"));
        template.add(new Activity("Swimming Time", "Pool"));
        template.add(new Activity("Fishing", "Lake"));
        template.add(new Activity("Arts and Crafts", "Recreation Center"));
        template.add(new Activity("Card Games", "Recreation Center"));
        template.add(new Activity("Scavenger Hunt", "Forest"));
        template.add(new Activity("Relay Race", "Field"));
        template.add(new Activity("Capture the Flag", "Field"));
        template.add(new Activity("Game Time", "Game Center"));
        template.add(new Activity("Talent Show", "Recreation Center"));
        template.add(new Activity("Forest Hike", "Forest"));
        ArrayList<Activity> schedule = new ArrayList<Activity>();
        int number = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int number5 = 0;
        int numberLoop = -1;
        Random rand = new Random();
        number = rand.nextInt(template.size());
        schedule.add(template.get(number));
        for (int i = 0; i < 7; i++) {
            schedule.add(new Activity("Breakfast", "Cafeteria"));
            while (numberLoop == -1) {
                number2 = rand.nextInt(template.size());
                if (number2 != number) {
                    schedule.add(template.get(number2));
                    numberLoop = 1;
                }
            }
            schedule.add(new Activity("Lunch", "Cafeteria"));
            numberLoop = -1;
            while (numberLoop == -1) {
                number3 = rand.nextInt(template.size());
                if (number3 != number2 && number3 != number) {
                    schedule.add(template.get(number3));
                    numberLoop = 1;
                }
            }
            numberLoop = -1;
            while (numberLoop == -1) {
                schedule.add(template.get(number));
                number4 = rand.nextInt(template.size());
                if (number4 != number3 && number4 != number2 && number4 != number) {
                    schedule.add(template.get(number4));
                    numberLoop = 1;
                }
            }
            schedule.add(new Activity("Dinner", "Cafeteria"));
            numberLoop = -1;
            while (numberLoop == -1) {
                number5 = rand.nextInt(template.size());
                if (number5 != number4 && number5 != number3 && number5 != number2 && number5 != number) {
                    schedule.add(template.get(number5));
                    numberLoop = 1;
                }
            }
            schedules.put(days[i], new Schedule(schedule));
        }
    }

    /**
     * Establishes an instance of the Cabin class with UUID.
     * @param id A UUID representing the ID of the Cabin.
     * @param minCabinAge A Integer representing the minimum cabin age of the Cabin.
     * @param maxCabinAge A Integer representing the maximum cabin age of the Cabin.
     */
    public Cabin(UUID id, int minCabinAge, int maxCabinAge) {
        this.id = id;
        this.minCabinAge = minCabinAge;
        this.maxCabinAge = maxCabinAge;
    }

    public UUID getUUID() {
        return id;
    }

    public int getMinCabinAge(){
        return this.minCabinAge;
    }


    public int getMaxCabinAge(){
        return maxCabinAge;
    }

    public int getMaxNumberOfCampers(){
        return MaxNumberOfCampers;
    }

    public ArrayList<Camper> getCampers(){
        return campers;
    }

    public HashMap<Day, Schedule> getSchedules() {
        return this.schedules;
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
        String print = "Cabin Age Range: "+this.minCabinAge+" to "+this.maxCabinAge+"\nMaximum number of campers: "+this.MaxNumberOfCampers;
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
