package system;

import java.util.ArrayList;

public class CabinList {
    private ArrayList<Cabin> cabins;
    private static CabinList cabinList;

    private CabinList(){}

    public static CabinList getInstance(){
        if (cabinList == null) {
			cabinList = new CabinList();
		}
		return cabinList;    
    }
    
}
