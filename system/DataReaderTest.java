package system;

import static org.junit.jupiter.api.Assertions.*;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataReaderTest {

    private UserList ul = UserList.getInstance();
    private ArrayList<User> users = ul.getUsers();

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
}