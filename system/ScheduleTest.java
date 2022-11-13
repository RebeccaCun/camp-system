package system;
//import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScheduleTest {
    public Schedule schedule;
    public ArrayList<Activity> activities;

    @BeforeEach
    public void setup(){
        //activities.add(0, "Swimming");
        schedule = new Schedule(activities);
    }
    @AfterEach
    public void tearDown(){
        schedule = null; 
    }

    @Test
    public void testHasActivity()
    {
        schedule.addActivity(new Activity("Swimming","Pool"));
        activities = schedule.getActivities();
        if(activities !=null){
            assert(true);
        }
        assert(false);
    }
}
