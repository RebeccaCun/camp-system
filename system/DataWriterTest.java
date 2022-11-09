package system;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataWriterTest {

    private UserList ul = UserList.getInstance();
    private CamperList cl = CamperList.getInstance();
    private ArrayList<User> users = ul.getUsers();
    private ArrayList<Camper> campers = CamperList.getInstance().getCampers();

    @BeforeEach
    public void setup() {
        UserList.getInstance().getUsers().clear();
        
        DataWriter.saveUsers();
    }

    @AfterEach
	public void tearDown() {
        // UserList.getInstance().getUsers().clear();
		// DataWriter.saveUsers();
        // CamperList.getInstance().getCampers().clear();
		// DataWriter.saveCampers();
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
        ul.addUser(u);

        DataWriter.saveUsers();
		assertEquals("mSmith", 
            DataReader.getAllUsers().get(0).getUserName());
	}

	// @Test
	// void testWritingFiveUsers() {
	// 	userList.add(new User("asmith", "Amy", "Smith", 19, "803-454-3344"));
	// 	userList.add(new User("bsmith", "Amy", "Smith", 19, "803-454-3344"));
	// 	userList.add(new User("csmith", "Amy", "Smith", 19, "803-454-3344"));
	// 	userList.add(new User("dsmith", "Amy", "Smith", 19, "803-454-3344"));
	// 	userList.add(new User("esmith", "Amy", "Smith", 19, "803-454-3344"));
	// 	DataWriter.saveUsers();
	// 	assertEquals("esmith", DataLoader.getUsers().get(4).getUserName());
	// }
	
	// @Test
	// void testWritingEmptyUser() {
	// 	userList.add(new User("", "", "", 0, ""));
	// 	DataWriter.saveUsers();
	// 	assertEquals("", DataLoader.getUsers().get(0).getUserName());
	// }
	
	@Test
	void testWritingNullUser() {
		User us = new User(null, null, null);
        
        // users.add(us);
		ul.addUser(us);
        DataWriter.saveUsers();

        assertEquals(null, 
            DataReader.getAllUsers().get(0).getPhoneNumber());
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
        cl.addCamper(c);
        DataWriter.saveCampers();

        // Get the Users and Campers
        campers = DataReader.getAllCampers();
    
        assertEquals(1, campers.size());
    }
}