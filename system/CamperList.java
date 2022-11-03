package system;

import java.util.ArrayList;

/**
 * An CamperList class that contains all of the campers for the camp system.
 * @author Cyber Council
 */
public class CamperList {
    private ArrayList<Camper> campers;
    private static CamperList camperList;

    /**
     * Initializes an instance of the CamperList class.
     */
    private CamperList(){
        campers = DataReader.getAllCampers();
        if(campers == null){
            campers = new ArrayList<Camper>();
        }
        camperList = this;
    }

    /**
     * Creates an instance of the CamperList class.
     * @return The created CamperList instance.
     */
    public static CamperList getInstance(){
        if (camperList == null) {
			camperList = new CamperList();
		}
		return camperList;    
    }

    /**
     * Adds a camper to the CabinList.
     * @param camper The camper being added.
     */
    public void addCamper(Camper camper){
        campers.add(camper);
    }

    /**
     * Returns the campers of the CamperList.
     * @return The ArrayList of campers in the CamperList.
     */
    public ArrayList<Camper> getCampers(){
        return campers;
    }

    /**
     * Saves the CamperList.
     */
    public void saveCampers(){
        DataWriter.saveCampers();
    }
    
}
