package system;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {
    public UserList userList;

    @BeforeEach
	public void setup() {
        userList = UserList.getInstance();
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("Celeste", "Shinguji", "NA_KO"));
        users.add(new User("Lisette", "Applebee", "Bee_apple"));
        users.add(new User("Shuichi", "Akamatsu", "shoo_det"));
        userList.setUsers(users);
	}
	
	@AfterEach
	public void tearDown() {
		userList = null;
	}

    @Test
    public void hasUserFalse(){
        assertFalse(userList.hasUser("KO_OU"));
    }

    @Test
    public void hasUserTrue(){
        assertTrue(userList.hasUser("NA_KO"));   
    }
    
}
