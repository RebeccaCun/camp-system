package system;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        int result = system.login("testuser", "test");
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

    //----------------------------test null inputs somehow------------------------------------
    @Test
    public void createUserAccountShouldAddUserToList(){
        system.createUserAccount("testuser", "test", "test@gmail.com", "user", "test", "123456789", "email", LocalDate.of(1980, 01, 01), "Columbia, SC");
        assertEquals(1, system.getUsers().getUsers().size());
    }

    @Test
    public void createUserAccountEmptyValues(){
        system.createUserAccount("", "", "", "", "", "", "", null, "");
        assertEquals(0, system.getUsers().getUsers().size());
    }

    @Test
    public void createUserAccountNullValues(){
        system.createUserAccount(null, null, null, null, null, null, null, null, null);
        assertEquals(0, system.getUsers().getUsers().size());
    }

    //for the Counselor Account and the addCamper methods I did not test for null/empty inputs, since it would give the same result as the create User Account Tests
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
        Camper resultCamper = system.findCamperByName("Test", "Camper");
        assertEquals(camper, resultCamper);
    }

    @Test
    public void findCamperWithNullValuesShouldReturnNull(){
        assertEquals(null, system.findCamperByName(null, null));
    }

    @Test 
    public void findAvailableSessionsWithEmptyList(){
        assertEquals(0, system.findAvailableSessions().size());
    }

    @Test
    public void findAvailableSessionsWithOneSession(){
        Session session = new Session(LocalDate.of(2022,8,1), LocalDate.of(2022,8,7));
        session.addCabin(new Cabin(10,12));
        system.getSessions().addSession(session);
        assertEquals(1, system.findAvailableSessions().size());
    }

    @Test
    public void logoutWorks(){
        system.setCurrentUser(new User("Test", "User", "testuser"));
        system.logout();
        assertEquals(null, system.getCurrentUser());
    }

    @Test
    public void logoutWithoutCurrentUser(){
        system.logout();
        assertEquals(null, system.getCurrentUser());
    }

    @Test
    public void checkUsernameAvailabilityTrue(){
        assertEquals(true, system.checkUsernameAvailability("test"));
    }

    @Test 
    public void checkUsernameAvailabilityFalse(){
        system.getUsers().addUser(new User("test", "user", "testuser"));
        assertEquals(false, system.checkUsernameAvailability("testuser"));
    }

    @Test
    public void checkUsernameAvailabilityNull(){
        assertEquals(false, system.checkUsernameAvailability(null));
    }

    @Test
    public void giveStrikeShouldAddStrike(){
        Camper camper = new Camper("Test", "Camper", LocalDate.of(2011,1,1));
        system.getCampers().addCamper(camper);
        system.giveStrike("Test", "Camper", "bad behaviour");
        assertEquals(1,camper.getNumberStrikes());
    }

    @Test
    public void createSessionShouldAddSessionToList(){
        system.createSession(LocalDate.of(2022,8,1), LocalDate.of(2022,8,7), "testTheme");
        assertEquals(1, system.getSessions().getSessions().size());
    }

    @Test
    public void createSessionNullValues(){
        system.createSession(null,null,null);
        assertEquals(1, system.getSessions().getSessions().size());
    }

    @Test
    public void addCabinToSessionsShouldWork(){
        Session session = new Session(LocalDate.of(2022,8,1), LocalDate.of(2022,8,7));
        system.getSessions().addSession(session);
        system.addCabinToSessions(new Cabin(10,12));
        assertEquals(1, session.getCabins().size());
    }

    @Test
    public void addCabinToSessionNull(){
        Session session = new Session(LocalDate.of(2022,8,1), LocalDate.of(2022,8,7));
        system.getSessions().addSession(session);
        system.addCabinToSessions(null);
        assertEquals(0, session.getCabins().size());
    }

    @Test
    public void getUserInformationNoCamper(){
        system.setCurrentUser(new User("test", "user", "testuser"));
        String expected = "You have no campers added to your account.\n";
        assertEquals(expected, system.getUserInformation());
    }

    @Test
    public void getUserInformationWithCamper(){
        User user = new User("test", "user", "testuser");
        Camper camper = new Camper("test", "camper", LocalDate.of(2011,1,1));
        Session session = new Session(LocalDate.of(2022,1,1), LocalDate.of(2022,1,7));
        system.setCurrentUser(user);
        session.addTheme("testTheme");
        system.getUsers().addUser(user);
        system.getCampers().addCamper(camper);
        system.getSessions().addSession(session);
        user.addCamper(camper);
        camper.addSession(session);
        String expected = "You have the following campers added to your account:\ntest camper\n   Registered to the following sessions:\n   - "+ session.getStartDate() + " - " + session.getEndDate() + ", Theme: testTheme";
        assertEquals(expected, system.getUserInformation());
    }

    @Test
    public void listSessionsEmpty(){
        assertEquals("Sessions:\n", system.listSessions());
    }

    @Test
    public void listSessionsOneSession(){
        Session session = new Session(LocalDate.of(2022,1,1), LocalDate.of(2022,1,7));
        system.getSessions().addSession(session);
        session.addTheme("testTheme");
        String expected = "Sessions:\n1) Start: " + session.getStartDate() + ", Theme: testTheme\n";
        assertEquals(expected, system.listSessions());
    }

}
