package system;
import java.util.ArrayList;

public class CounselorList {

    private ArrayList<Counselor> counselors;
    private static CounselorList counselorList;

    private CounselorList() {  
        counselors = DataReader.getAllCounselors();
    }

    public CounselorList getInstance(){
        if(counselorList == null){
            counselorList = new CounselorList();
        }
        return counselorList;
    }

    public Counselor getCounselor(String username){
        for(Counselor c : counselors){
            if(username.equals(c.getUserName())){
                return c;
            }
        }
        return null;
    }
}
