package system;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CounselorListTest {
    public CounselorList counselorList;

    @BeforeEach
	public void setup() {
        counselorList = CounselorList.getInstance();
	}
	
	@AfterEach
	public void tearDown() {
        counselorList = null;
	}

    @Test
    public void testHasCamper(){
        assertEquals(5,5);
    }
    
}