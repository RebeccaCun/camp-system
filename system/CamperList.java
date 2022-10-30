package system;

import java.util.ArrayList;

public class CamperList {
    private ArrayList<Camper> campers;
    private static CamperList camperList;

    private CamperList(){}

    public static CamperList getInstance(){
        if (camperList == null) {
			camperList = new CamperList();
		}
		return camperList;    
    }
    
}
