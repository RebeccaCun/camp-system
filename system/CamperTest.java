package system;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CamperTest {
    private Camper campers = Camper.getInstance();
    private ArrayList<Camper> camperList = campers.getUsers();

    @BeforeEach
    public void setup(){
        camperList.clear();
        camperList.add(new Camper("Jannie", "Smith", "09/09/2012"));
        camperList.add(new User("Sandy", "Johnson", "02/14/2012"));
        DataWriter.saveUsers();
    }
    @AfterEach
    public void tearDown() {
        Camper.getInstance().getCampers().clear();
        DataWriter.saveUsers();

    }

    @Test
    void testHaveUserFirstNameValid() {
        String hasJannie = campers.getFirstName();
        boolean valid = false;
        if(hasJannie == "Jannie")
        {
            valid = true;
        }
        assertTrue(valid);
    }
    @Test
    void testHaveUserFirstNameValid2() {
        String hasSandy = campers.getFirstName();
        boolean valid = false;
        if(hasSandy == "Sandy")
            valid = true;
        assertTrue(valid);
    }

    @Test
    void testHaveUserLastNameValid() {
        String hasSmith = campers.getLastName();
        boolean valid = false;
        if(hasSmith == "Smith")
            valid = true;
        assertTrue(valid);
    }

    @Test
    void testHaveUserLastNameValid2() {
        String hasJohn = campers.getLastName();
        boolean valid = false;
        if(hasJohn == "Johnson")
            valid = true;
        assertTrue(valid);
    }
}
