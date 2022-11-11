package system;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class CabinListTest {
    public CabinList cabinList;

    @BeforeAll
    public void setupOnce(){
        cabinList = CabinList.getInstance();
    }

    @BeforeEach
    public void setup(){
        cabinList.setCabins(new ArrayList<Cabin>());
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
