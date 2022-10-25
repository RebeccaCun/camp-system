package system;
import java.util.ArrayList;
//json based
public class CounselorsList {
    private static User counselors;
    private ArrayList<User> counselor;

    private Counselors() {
        CounselorList = DataWriter.getUserJSON();
    }
    private static Users getInstance(){
        if(users == null){
            counselors = new User();
        }
        return counselors;
    }
}
