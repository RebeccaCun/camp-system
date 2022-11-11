package system;

import java.util.ArrayList;

/**
 * An CabinList class that contains all of the cabins for the camp system.
 * @author Cyber Council
 */
public class CabinList {
    private ArrayList<Cabin> cabins;
    private static CabinList cabinList;

    /**
     * Initializes an instance of the CabinList class.
     */
    private CabinList(){
        cabins = DataReader.getAllCabins();
        if(cabins == null){
            cabins = new ArrayList<Cabin>();
        }
        cabinList = this;
    }

    /**
     * Creates an instance of the CabinList class.
     * @return The created CabinList instance.
     */
    public static CabinList getInstance(){
        if (cabinList == null) {
			cabinList = new CabinList();
		}
		return cabinList;    
    }

    public void setCabins(ArrayList<Cabin> cabins){
        this.cabins = cabins;
    }

    /**
     * Searches for a specific Cabin and returns them if found.
     * @param minCabinAge The start date of the Cabin being searched for.
     * @param maxCabinAge The end date of the Cabin being searched for.
     * @return The Cabin being searched for.
     */
    public Cabin getCabin(int minCabinAge, int maxCabinAge) {
        for(int i = 0; i < cabins.size(); i++) {
            if(cabins.get(i) == new Cabin(minCabinAge, maxCabinAge)){
                return cabins.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the cabins of the CabinList.
     * @return The ArrayList of cabins in the CabinList.
     */
    public ArrayList<Cabin> getCabins() {
        return cabins;
    }

    /**
     * Searches for a specific Cabi  and returns a boolean depending of if it are found.
     * @param minCabinAge The min. cabin age of the Cabin being searched for.
     * @param maxCabinAge The max. cabin age of the Cabin being searched for.
     * @return The boolean representing the status of the Cabin.
     */
    public boolean hasCabin(int minCabinAge, int maxCabinAge) {
        for (int i = 0; i < cabins.size(); i++) {
            if(cabins.get(i) == new Cabin(minCabinAge, maxCabinAge)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a Cabin to the list and returns a boolean depending of if it was added.
     * @param cabin The cabin being added.
     * @return The boolean representing the status of the Cabin.
     */
    public boolean addCabin(Cabin cabin) {
        if(!hasCabin(cabin.getMinCabinAge(), cabin.getMaxCabinAge())) {
            cabins.add(cabin);
            return true;
        }
        return false;
    }

    /**
     * Saves the CabinList.
     */
    public void saveCabins() {
        DataWriter.saveCabins();
    } 
}
