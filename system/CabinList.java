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
     * @param minCabinAge The min. cabin age of the Cabin being added.
     * @param maxCabinAge The max. cabin age of the Cabin being added.
     * @return The boolean representing the status of the Cabin.
     */
    public void addCabin(Cabin cabin) {
        cabins.add(cabin);
    }

    /**
     * Edits a Cabin in the list.
     * @param cabin The Cabin to be edited.
     * @param minCabinAge The min. cabin age of the new Cabin.
     * @param maxCabinAge The max. cabin age of the new Cabin.
     */
    public void editCabin(Cabin cabin, int minCabinAge, int maxCabinAge) {
        for (int i = 0; i < cabins.size(); i++) {
            if(cabins.get(i) == cabin) {
                cabins.set(i,new Cabin(minCabinAge, maxCabinAge));
            }
        }
    }

    /**
     * Saves the CabinList.
     */
    public void saveCabins() {
        DataWriter.saveCabins();
    } 
}
