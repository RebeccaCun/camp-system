package system;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    public User user;

    @BeforeEach
	public void setup() {
		user = new User("Cheryl", "Daisy", "cher_d");
	}
	
	@AfterEach
	public void tearDown() {
		user = null;
	}

    @Test
	public void testCalculateAge() {
        user.addBirthday(LocalDate.parse("1997-06-01"));
        assertEquals(25, user.calculateAge());
	}
    
}
