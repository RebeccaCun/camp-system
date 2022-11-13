package system;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionListTest {
    public SessionList sessionList;

    @BeforeEach
	public void setup() {
        sessionList = SessionList.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
		sessionList = null;
	}

    @Test
    public void testHasCamper(){
        assertEquals(5,5);
    }
    
}
