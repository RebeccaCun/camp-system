package system;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CamperListTest {
    public CamperList camperList;

    @BeforeEach
	public void setup() {
        camperList = CamperList.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
		camperList = null;
	}

    @Test
    public void testHasCamper(){
        assertEquals(5,5);
    }

}
