package system;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SessionListTest {
    public SessionList sessionList;

    @BeforeEach
	public void setup() {
        sessionList = SessionList.getInstance();
        ArrayList<Session> sessions = new ArrayList<Session>();
        sessions.add(new Session(LocalDate.parse("2018-07-09"), LocalDate.parse("2018-07-16")));
        sessions.add(new Session(LocalDate.parse("2018-07-17"), LocalDate.parse("2018-07-24")));
        sessions.add(new Session(LocalDate.parse("2018-07-25"), LocalDate.parse("2018-08-01")));
        sessionList.setSessions(sessions);	
    }
	
	@AfterEach
	public void tearDown() {
		sessionList = null;
	}

    @Test
    public void testHasSessionFalse(){
        assertFalse(sessionList.hasSession(LocalDate.parse("2018-07-01"), LocalDate.parse("2018-07-08")));
    }

    @Test
    public void testHasSessionTrue(){
        assertTrue(sessionList.hasSession(LocalDate.parse("2018-07-09"), LocalDate.parse("2018-07-16")));   
    }
    
}
