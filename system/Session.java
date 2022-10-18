package system;
import java.util.UUID;
import java.util.Date;
import java.util.ArrayList;

/**
 * 
 * @author Cyber Council
 */
public class Session {

    private UUID id;
    private Date startDate;
    private Date endDate;
    private int durationPerWeek;
    private int ageGroup;
    private int availableSpots;
    private ArrayList<Cabin> cabins;

    /**
     * 
     * @param startDate
     * @param endDate
     * @param ageGroup
     */
    public Session(String startDate, String endDate, String ageGroup) {

    }
    
    /**
     * 
     * @return
     */
    public boolean isAvailable() {
        return true;
    }
    
    /**
     * 
     * @return
     */
    public int viewAgeGroup() {
        return 0;
    }
    
    /**
     * 
     */
    public void addCabin(Cabin cabin) {
        ;
    }
    
    /**
     * 
     * @return
     */
    public String toString() {
        return "";
    }
}