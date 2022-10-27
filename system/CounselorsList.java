package system;
import java.util.ArrayList;

public class CounselorsList {
    private ArrayList<Counselor> counselors;
    private static CounselorsList counselorsList;

    private CounselorsList(){}

    public ArrayList<Counselor> getCounselor(){
        return this.counselors;
    }

    public static CounselorsList getInstance(){
        if (counselorsList == null) {
			counselorsList = new CounselorsList();
		}
		return counselorsList;    
    }

    public boolean hasCounselor(String userName){
        for(Counselor counselor: counselorsList)
        {
            if(counselor.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public boolean addCounselor(String userName, String firstName, String lastName, int age, String phoneNumber){
        if(hasCounselor(userName) return false;

        CounselorsList.add(new Counselor(firstName, lastName, userName));
    }

    public void editCounselor(String username, String password){

    }

    public void saveCounselor(){
        DataWriter.saveCounselors();
    }
}
