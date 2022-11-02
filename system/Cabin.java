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
        id = UUID.randomUUID();
        campers = new ArrayList<>();
        schedules = new HashMap<Day, Schedule>();
    
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
        int number = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int number5 = 0;
        Random rand = new Random();
        int numberLoop = -1;
        for (int i = 0; i < 7; i++) {
            ArrayList<Activity> schedule = new ArrayList<Activity>();
            schedule.add(new Activity("Breakfast", "Cafeteria"));
            schedule.get(schedule.size()-1).addStartTime("08:00");
            schedule.get(schedule.size()-1).addEndTime("08:30");
            number = rand.nextInt(template.size());
            schedule.add(template.get(number));
            schedule.get(schedule.size()-1).addStartTime("09:00");
            schedule.get(schedule.size()-1).addEndTime("10:30");
            while (numberLoop == -1) {
                number2 = rand.nextInt(template.size());
                if (number2 != number) {
                    schedule.add(template.get(number2));
                    schedule.get(schedule.size()-1).addStartTime("11:00");
                    schedule.get(schedule.size()-1).addEndTime("11:45");
                    numberLoop = 1;
                }
            }
            schedule.add(new Activity("Lunch", "Cafeteria"));
            schedule.get(schedule.size()-1).addStartTime("12:00");
            schedule.get(schedule.size()-1).addEndTime("12:30");
            numberLoop = -1;
            while (numberLoop == -1) {
                number3 = rand.nextInt(template.size());
                if (number3 != number2 && number3 != number) {
                    schedule.add(template.get(number3));
                    schedule.get(schedule.size()-1).addStartTime("1:00");
                    schedule.get(schedule.size()-1).addEndTime("3:00");
                    numberLoop = 1;
                }
            }
            numberLoop = -1;
            while (numberLoop == -1) {
                number4 = rand.nextInt(template.size());
                if (number4 != number3 && number4 != number2 && number4 != number) {
                    schedule.add(template.get(number4));
                    schedule.get(schedule.size()-1).addStartTime("3:30");
                    schedule.get(schedule.size()-1).addEndTime("5:30");
                    numberLoop = 1;
                }
            }
            schedule.add(new Activity("Dinner", "Cafeteria"));
            schedule.get(schedule.size()-1).addStartTime("6:00");
            schedule.get(schedule.size()-1).addEndTime("6:30");
            numberLoop = -1;
            while (numberLoop == -1) {
                number5 = rand.nextInt(template.size());
                if (number5 != number4 && number5 != number3 && number5 != number2 && number5 != number) {
                    schedule.add(template.get(number5));
                    schedule.get(schedule.size()-1).addStartTime("7:00");
                    schedule.get(schedule.size()-1).addEndTime("8:00");
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
        campers = new ArrayList<>();
        schedules = new HashMap<>();
    }

    /**
     * Returns the ID of the Cabin class.
     * @return The ID of the Cabin.
     */
    public UUID getUUID() {
        return id;
    }

    /**
     * Returns the minimum cabin age of the Cabin class.
     * @return The integer for the minimum cabin age of the Cabin.
     */
    public int getMinCabinAge(){
        return this.minCabinAge;
    }

    /**
     * Returns the maximum cabin age of the Cabin class.
     * @return The integer for the maximum cabin age of the Cabin.
     */
    public int getMaxCabinAge(){
        return maxCabinAge;
    }

    /**
     * Returns the maximum number of campers of the Cabin class.
     * @return The integer for the max number of campers of the Cabin.
     */
    public int getMaxNumberOfCampers(){
        return MaxNumberOfCampers;
    }

    /**
     * Returns the campers of the Cabin class.
     * @return The ArrayList of Campers of the Cabin.
     */
    public ArrayList<Camper> getCampers(){
        return campers;
    }

    /**
     * Returns the schedules of the Cabin class.
     * @return The HashMap for the schedule of the Cabin.
     */
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
        String print = new String();
        for(Day day : Day.values()){
            print += day.toString() + ": \n";
            print += schedules.get(day).toString();
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
