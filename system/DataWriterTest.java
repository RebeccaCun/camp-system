package system;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {

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
	void testWritingZeroUsers() {
		users = DataReader.getAllUsers();
		assertEquals(0, users.size());
	}

    @Test
	void testWritingOneUser() {
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

        DataWriter.saveUsers();
		assertEquals("mSmith", 
            DataReader.getAllUsers().get(0).getUserName());
	}

	@Test
	void testWritingFiveUsers() {
		userL.addUser(new User("Amy", "Smith", "asmith"));
		userL.addUser(new User("Amy", "Smith", "bsmith"));
		userL.addUser(new User("Amy", "Smith", "csmith"));
		userL.addUser(new User("Amy", "Smith", "dsmith"));
		userL.addUser(new User("Amy", "Smith", "esmith"));
		
        DataWriter.saveUsers();

        assertEquals("esmith", DataReader.getAllUsers().get(4).getUserName());
	}
	
	@Test
	void testWritingEmptyUser() {
		userL.addUser(new User("", "", ""));
		DataWriter.saveUsers();
		
        assertEquals("", DataReader.getAllUsers().get(0).getUserName());
	}

    @Test
	void testWritingNullUser() {
		User us = new User(null, null, null);
        
		userL.addUser(us);
        DataWriter.saveUsers();

        assertEquals(null, 
            DataReader.getAllUsers().get(0).getPhoneNumber());
	}

    @Test
	void testWritingEmptyCabin() {        
		cabinL.addCabin(new Cabin(0,0));
        DataWriter.saveCabins();

        assertEquals(0, 
            DataReader.getAllCabins().get(0).getCampers().size());
	}

    @Test
	void testWritingNullCamper() {
		Camper ca = new Camper(null, null, null);
        
		camperL.addCamper(ca);
        DataWriter.saveCampers();

        assertEquals(null, 
            DataReader.getAllCampers().get(0).getBirthday());
	}
    
    @Test
	void testWritingNullSession() {
		Session se = new Session(null, null);

		sessionL.addSession(se);
        DataWriter.saveSessions();

        assertEquals(null, 
            DataReader.getAllSessions().get(0).getTheme());
	}

    @Test
	void testWritingNullCounselor() {
		Counselor cs = new Counselor(null, null, null);
        
		counselorL.addCounselor(cs);
        DataWriter.saveCounselors();

        assertEquals(null, 
            DataReader.getAllCounselors().get(0).getPhoneNumber());
	}

    /**
     * Test whether DataWriter can write a camper 
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
}