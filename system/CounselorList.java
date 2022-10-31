package system;
import java.util.ArrayList;

public class CounselorList {

    private ArrayList<Counselor> counselors;
    private static CounselorList counselorList;

    /**
     * creates Counselor list of all counselors in the system
     */
    private CounselorList() {  
        counselors = DataReader.getAllCounselors();
    }
    /**
     * gets an instance of the counselor and adds it to the list of counselors
     * @return CounselorList with all the counselors
     */
    public static CounselorList getInstance(){
        if(counselorList == null){
            counselorList = new CounselorList();
        }
        return counselorList;
    }
    /**
     * gets specific counselor based on username
     * @param username of the counselor you are looking for
     * @return the counselor based on username
     */
    public Counselor getCounselor(String username){
        for(Counselor c : counselors){
            if(username.equals(c.getUserName())){
                return c;
            }
        }
        return null;
    }

    /**
     * gets a arrayList of all the counselors in the system
     * @return Counselor ArrayList
     */
    public ArrayList<Counselor> getCounselors(){
        return counselors;
    }

    /**
     * adds a new counselor to counselors arrayList
     * @param counselor to add
     */
    public void addCounselor(Counselor counselor) {
        counselors.add(counselor);
    }
}
