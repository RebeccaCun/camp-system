package system;

// import java.util.ArrayList;

// import org.junit.Test;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;

// import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CabinListTest {
    public static CabinList cabinList;

    @BeforeAll
    public static void setupOnce(){
        cabinList = CabinList.getInstance();
        // System.out.println("setupOnce");
    }

    @BeforeEach
    public void setup(){
        cabinList.setCabins(new ArrayList<Cabin>());
        // System.out.println("setup");
    }

    @Test
    public void hasCabinFalse(){
        assertFalse(cabinList.hasCabin(0,0));
    }

    @Test
    public void hasCabinTrue(){
        cabinList.getCabins().add(new Cabin(10,12));
        assertTrue(cabinList.hasCabin(10, 12));   
    }

    @Test
    public void addCabinTrue(){
        assertTrue(cabinList.addCabin(new Cabin(10,12)));
    }

    @Test 
    public void addCabinFalse(){
        cabinList.getCabins().add(new Cabin(10,12));
        assertFalse(cabinList.addCabin(new Cabin(10,12)));
    }

    @Test
    public void addNullCabin(){
        int lengthBefore = cabinList.getCabins().size();
        cabinList.addCabin(null);
        assertEquals(lengthBefore, cabinList.getCabins().size());
    }
}
