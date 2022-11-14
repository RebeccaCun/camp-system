package system;

import java.time.LocalDate;
import java.util.ArrayList;

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
    public void testAvailableFalse(){
		session.setAvailableSpots(4);
		ArrayList<Cabin> cabins = new ArrayList<Cabin>();
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		session.addCabins(cabins);
        assertFalse(session.isAvailable());
    }

	@Test
    public void testAvailableTrue(){
		session.setAvailableSpots(6);
		ArrayList<Cabin> cabins = new ArrayList<Cabin>();
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		cabins.add(new Cabin(12, 14));
		session.addCabins(cabins);
        assertTrue(session.isAvailable());
    }
    
}
