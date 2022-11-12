package system;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
public class CampSystemFACADETest {
    private CampSystemFACADE system = new CampSystemFACADE();

    @BeforeEach
    public void setup(){
        system.setCurrentUser(null);
        system.getSessions().getSessions().clear();
        system.getSessions().saveSessions();
        system.getCounselors().getCounselors().clear();
        system.getCounselors().saveCounselors();
        system.getCampers().getCampers().clear();
        system.getCampers().saveCampers();
        system.getUsers().getUsers().clear();
        system.getUsers().saveUsers();
        system.getCabins().getCabins().clear();
        system.getCabins().saveCabins();
    }

    @Test
    public void loginAsParent(){
        User testUser = new User("Test", "User", "testuser");
        testUser.setPassword("test");
        testUser.setType(Type.PARENT);
        system.getUsers().addUser(testUser);
        int result = system.login("testuser", "null");
        assertEquals(1, result);
    }

    @Test
    public void loginAsCounselor(){
        Counselor testCounselor = new Counselor("Test", "Counselor", "testcounselor");
        testCounselor.setPassword("test");
        testCounselor.setType(Type.COUNSELOR);
        system.getCounselors().addCounselor(testCounselor);
        int result = system.login("testcounselor", "test");
        assertEquals(3, result);
    }

    @Test
    public void loginAsDirector(){
        User testDirector = new User("Test", "Director", "testdirector");
        testDirector.setPassword("test");
        testDirector.setType(Type.DIRECTOR);
        system.getUsers().addUser(testDirector);
        int result = system.login("testdirector", "test");
        assertEquals(2, result);
    }

    @Test 
    public void loginDataNull(){
        assertEquals(-1, system.login(null, null));
    }

    @Test
    public void loginDataInvalid(){
        User testUser = new User("Test", "User", "testuser");
        testUser.setPassword("test");
        testUser.setType(Type.PARENT);
        system.getUsers().addUser(testUser);
        assertEquals(-1, system.login("false", "input"));
    }

    //the next three methods are not testing for null inputs, since those are just passed on (should be fixed in our code)
    @Test
    public void createUserAccountShouldAddUserToList(){
        system.createUserAccount("testuser", "test", "test@gmail.com", "user", "test", "123456789", "email", LocalDate.of(1980, 01, 01), "Columbia, SC");
        assertEquals(1, system.getUsers().getUsers().size());
    }

    @Test
    public void createCounselorAccountShouldAddCounselorToList(){
        system.createCounselorAccount("testcounselor", "test", "test@gmail.com", "counselor", "test", "123456789", "email", LocalDate.of(2000, 01, 01), "Columbia, SC", "I like nature", new Medical(new Contact("Test", "Doctor", "123", "Office")));
        assertEquals(1, system.getCounselors().getCounselors().size());
    }

    @Test
    public void addCamperToCurrentUsersAccount(){
        User testUser = new User("Test", "User", "testuser");
        testUser.setPassword("test");
        testUser.setType(Type.PARENT);
        system.setCurrentUser(testUser);
        system.addCamper("test", "camper", LocalDate.of(2010, 01, 01), null, null, null);
        assertEquals(1, system.getCurrentUser().getCampers().size());
    }

    @Test
    public void signUpCamperToSessionShouldReturnOne(){
        Session session = new Session(LocalDate.of(2022, 8, 1), LocalDate.of(2022, 8, 7));
        Cabin cabin = new Cabin(10,12);
        session.addCabin(cabin);
        system.getSessions().addSession(session);
        Camper camper = new Camper("Test", "Camper", LocalDate.of(2011, 1, 1));
        assertEquals(1, system.sessionSignup(camper, session));
    }

    @Test
    public void sessionSignupWithNullValues(){
        assertEquals(-1,system.sessionSignup(null, null));
    }

    @Test
    public void findCamperByNameReturnsCamper(){
        Camper camper = new Camper("Test", "Camper", LocalDate.of(2011,1,1));
        system.getCampers().addCamper(camper);
        system.setCurrentUser(new User("Test", "User", "testuser"));
        system.getCurrentUser().addCamper(camper);
        Camper resultCamper = system.findCamperByName("Test", "Camper");
        assertEquals(camper, resultCamper);
    }

    @Test
    public void findCamperWithNullValuesShouldReturnNull(){
        assertEquals(null, system.findCamperByName(null, null));
    }






}
