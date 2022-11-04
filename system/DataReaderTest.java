package system;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataReaderTest {

    private UserList ul = UserList.getInstance();
    private CamperList cl = CamperList.getInstance();
    private ArrayList<User> users = ul.getUsers();
    private ArrayList<Camper> campers = CamperList.getInstance().getCampers();

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
        ul.addUser(u);
        // Save the User
        DataWriter.saveUsers();
    }

    @AfterEach
	public void tearDown() {
        UserList.getInstance().getUsers().clear();
		DataWriter.saveUsers();
        CamperList.getInstance().getCampers().clear();
		DataWriter.saveCampers();
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
        ul.addUser(u);
        cl.addCamper(c);
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
}