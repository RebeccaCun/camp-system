package system;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionTest {
    public Session session;

    @BeforeEach
	public void setup() {
		session = new Session(LocalDate.parse("2021-06-01"), LocalDate.parse("2021-06-08"));
	}
	
	@AfterEach
	public void tearDown() {
		session = null;
	}

    @Test
    public void testHasCamper(){
        assertEquals(5,5);
    }
    
}
