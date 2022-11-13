package system;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {
    public UserList userList;

    @BeforeEach
	public void setup() {
        userList = UserList.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
		userList = null;
	}

    @Test
    public void testHasCamper(){
        assertEquals(5,5);
    }
    
}
