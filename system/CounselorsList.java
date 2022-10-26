package system;
import java.util.ArrayList;
//json based
public class CounselorsList {
    private static User counselors;
    private ArrayList<User> counselor;

    private Counselors() {  //confused. be back later
        counselors = DataReader.getAllCounselors();
    }
    private static User getInstance(){
        if(counselors == null){
            counselors = new User();
        }
        return counselors;
    }

    public User getCounselor(String Username){
        return null;
    }
}
