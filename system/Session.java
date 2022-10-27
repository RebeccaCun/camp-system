package system;
import java.util.UUID;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * @author Cyber Council
 */
public class Session {

    private UUID id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int ageGroup;
    private int availableSpots;
    private String theme;
    private ArrayList<Cabin> cabins;

    /**
     * 
     * @param startDate
     * @param endDate
     * @param ageGroup
     */
    public Session(LocalDate startDate, LocalDate endDate, int ageGroup) {

    }

    public LocalDate getStartDate(){
        return startDate;
    }

    public LocalDate getEndDate(){
        return endDate;
    }

    /**
     * 
     * @param id 
     * @param startDate
     * @param endDate
     * @param ageGroup
     */
    public Session(UUID id, LocalDate startDate, LocalDate endDate, int ageGroup) {
        this.id = id;
    }

    public void decreaseAvailableSpots() {
        availableSpots --;
    }

    public UUID getUUID() {
        return id;
    }

    public String getTheme() {   //add themes AND decription going with the theme;
        return "";
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
    public void addCabins(ArrayList<Cabin> cabins) {
        ;
    }

    /**
     * 
     */
    public void addTheme(String theme) {
        ;
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