package system;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.UriSelector;

class DataReaderTest {

    private UserList userL = UserList.getInstance();
    private CabinList cabinL = CabinList.getInstance();
    private CamperList camperL = CamperList.getInstance();
    private SessionList sessionL = SessionList.getInstance();
    private CounselorList counselorL = CounselorList.getInstance();
    
    private ArrayList<User> users = userL.getUsers();
    private ArrayList<Cabin> cabins = cabinL.getCabins();
    private ArrayList<Camper> campers = camperL.getCampers();
    private ArrayList<Session> sessions = sessionL.getSessions();
    private ArrayList<Counselor> counselors = counselorL.getCounselors();

    @BeforeEach
    public void setup() {
        users.clear();
        
        // Create User
        User u = new User("Mike", "Smith",
             "mSmith");
        u.addPassword("smithing");
        u.addEmail("smith@gmail.com");
        u.addPhoneNumber("803-454-2022");
        u.addPreferredContact("email");
        u.addBirthday(LocalDate.parse("2022-10-20"));
        u.addAddress("somewhere, USA");
        u.setType(Type.PARENT);

        // Add User to the UserList
        userL.addUser(u);
        // Save the User
        DataWriter.saveUsers();
    }

    @AfterEach
	public void tearDown() {
        UserList.getInstance().getUsers().clear();
        CabinList.getInstance().getCabins().clear();
        CamperList.getInstance().getCampers().clear();
        SessionList.getInstance().getSessions().clear();
        CounselorList.getInstance().getCounselors().clear();
        DataWriter.saveUsers();
        DataWriter.saveCabins();
        DataWriter.saveCampers();
        DataWriter.saveSessions();
        DataWriter.saveCounselors();
	}

    @Test
    void testGetUsersSize() {
        // get all users (runs after setup())
		users = DataReader.getAllUsers();
		assertEquals(1, users.size());
	}

    @Test
	void testGetUsersSizeZero() {
        // Clear the users
		UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
		assertEquals(0, users.size());
	}

    @Test
	void testGetUserBirthday() {
		users = DataReader.getAllUsers();
		assertEquals("2022-10-20", 
            users.get(0).getBirthday().toString());
	}

    @Test
    void testEmptyCampers() {
        users = DataReader.getAllUsers();
        assertEquals(0, users.get(0).getCampers().size());
    }

    @Test
    void testCamperAndUserID() {
        
        // Create User
        User u = new User("Mikey", "White",
                "mWhite");
        u.addBirthday(LocalDate.parse("2022-10-20"));
        u.setType(Type.PARENT);
   
        // Create new Camper for the User
        Camper c = new Camper("Long", "Kam", LocalDate.parse("2022-10-21"));
        // Add Medical Info to the Camper
        Medical med = new Medical(new Contact("doc", "tor", "80-479-3939", "everywhere"));
        c.addMedical(med);
        u.addCamper(c);

        // Save to Lists
        userL.addUser(u);
        camperL.addCamper(c);
        DataWriter.saveUsers();
        DataWriter.saveCampers();

        // Get the Users and Campers
        campers = DataReader.getAllCampers();
        users = DataReader.getAllUsers();
    
        // Check if the UUIDs are same
        assertEquals(0,
            campers.get(0).getUUID().compareTo( 
            users.get(1).getCampers().get(0).getUUID() ));
    }

    /**
     * Test whether DataReader can write a cabin 
     * with only calling the constructor
     */
    @Test
    void testWritingCabinConstructor() {
        // Create Cabin
        Cabin c = new Cabin(10, 12);
        cabinL.addCabin(c);
        DataWriter.saveCabins();

        // Get the Users and Campers
        cabins = DataReader.getAllCabins();
    
        assertEquals(1, cabins.size());
    }

    /**
     * Test whether DataReader can write a camper 
     * with only calling the constructor
     */
    @Test
    void testWritingCamperConstructor() {
        // Create new Camper for the User
        Camper c = new Camper("Long", "Kam", LocalDate.parse("2022-10-21"));

        // Save to Lists
        camperL.addCamper(c);
        DataWriter.saveCampers();

        // Get the Users and Campers
        campers = DataReader.getAllCampers();
    
        assertEquals(1, campers.size());
    }
    
    /**
     * Test whether DataReader can write a session 
     * with only calling the constructor
     */
    @Test
    void testWritingSessionConstructor() {

        // Create Session
        Session s = new Session(LocalDate.parse("2022-02-20"),
        LocalDate.parse("2022-03-20"));
        sessionL.addSession(s);
        
        DataWriter.saveSessions();
        sessions = DataReader.getAllSessions();
    
        assertEquals(1, sessions.size());
    }

    /**
     * Test whether DataReader can write a Counselor 
     * with only calling the constructor
     */
    @Test
    void testWritingCounselorConstructor() {

        // Create Counselor
        Counselor c = new Counselor("Amy", "Works", "aWorks");
        counselorL.addCounselor(c);
        
        DataWriter.saveCounselors();
        counselors = DataReader.getAllCounselors();
    
        assertEquals(1, counselors.size());
    }

    /**
     * Test whether DataReader can write a Counselor 
     * with only calling the constructor
     */
    @Test
    void testWritingUserConstructor() {

        // Create User
        userL.addUser(new User("Amy", "Works", "aWorks"));
        
        DataWriter.saveUsers();
        users = DataReader.getAllUsers();
    
        assertEquals(2, users.size());
    }

    @Test
    void testWritingEachOneByOne() {
        UserList.getInstance().getUsers().clear();
        DataWriter.saveUsers();
        int totalSize = 0;

        userL.addUser(new User("null", "void", "empty"));
        DataWriter.saveUsers();

        cabinL.addCabin(new Cabin(10, 11));
        DataWriter.saveCabins();

        camperL.addCamper(new Camper("null", "empty",
            LocalDate.parse("2022-10-20")));
        DataWriter.saveCampers();

        sessionL.addSession(new Session(LocalDate.parse("2022-10-10"),
            LocalDate.parse("2022-11-10")));
        DataWriter.saveSessions();

        counselorL.addCounselor(new Counselor("null", "null", "null"));
        DataWriter.saveCounselors();

        totalSize += DataReader.getAllSessions().size()
            + DataReader.getAllUsers().size() 
            + DataReader.getAllCabins().size() 
            + DataReader.getAllCampers().size() 
            + DataReader.getAllCounselors().size() ;
    
        assertEquals(5, totalSize);
    }
}