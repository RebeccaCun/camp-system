package system;
import java.util.ArrayList;

public class CounselorsList {
    private ArrayList<Counselor> counselors;
    private static CounselorsList counselorsList;

    private CounselorsList(){}
    
    public ArrayList<Counselor> getCounselor(){
        return this.counselors;
    }
    /**
     * creates instance of Counselor List if not already one existing
     * @return  CounselorList
     */
    public static CounselorsList getInstance(){
        if (counselorsList == null) {
			counselorsList = new CounselorsList();
		}
		return counselorsList;    
    }
    /**
     * checks if the list has this specific counselor
     * @param userName of counselor you are checking for
     * @return boolean if counselor is in List of not
     */
    public boolean hasCounselor(String userName){
        for(Counselor counselor: counselorsList)
        {
            if(counselor.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }
    /**
     * Adds a new counselor to the counselor list
     * @param userName
     * @param firstName
     * @param lastName
     * @param age
     * @param phoneNumber
     * @return boolean of if counselor was added correctly
     */
    public boolean addCounselor(String userName, String firstName, String lastName, int age, String phoneNumber){
        if(hasCounselor(userName) return false;

        CounselorsList.add(new Counselor(firstName, lastName, userName));
    }

    public void editCounselor(String username, String password){

    }
    /**
     * saves counsleor to system using DataWriter
     */
    public void saveCounselor(){
        DataWriter.saveCounselors();
    }
}
