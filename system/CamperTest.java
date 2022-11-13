package system;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CamperTest {
    public Camper camper;

    @BeforeEach
    public void setup(){
        camper = new Camper("Jannie", "Smith", LocalDate.parse("2012-03-08"));
        //DataWriter.saveUsers();
    }
    @AfterEach
    public void tearDown() {
        camper = null;
        //DataWriter.saveUsers();

    }

    @Test
    void testFirstNameValid() {
        String hasJannie = camper.getFirstName();
        boolean valid = false;
        if(hasJannie == "Jannie")
        {
            valid = true;
        }
        assertTrue(valid);
    }

    @Test
    void testLastNameValid() {
        String hasSmith = camper.getLastName();
        boolean valid = false;
        if(hasSmith == "Smith")
            valid = true;
        assertTrue(valid);
    }

    @Test
    void testValidBirthday(){
        LocalDate bday = camper.getBirthday();
        boolean valid = false;
        if(bday.equals(LocalDate.parse("2012-03-08")))
            valid = true;
        assertTrue(valid);
    }
}
