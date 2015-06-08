import static org.junit.Assert.*;

import org.junit.Test;


public class countGeneratorTest {
	@Test
	public void startsAt5PM() throws Exception {
		assertEquals(5, new countGenerator().getStartingTimeFromInput(5));
	} 
//I have tried Integer Hours Calculations and it works
	@Test
	public void TimeDifferecesByNonIntegerHours() throws Exception{
		assertEquals(0, new countGenerator().getTimeDiffereces("2001.09.01 11:10 PM","2001.09.02 12:00 AM"));
	}
	private int getTimeDiffereces(String time1,String time2) throws Exception {
		return new countGenerator().getTimeDiffereces(time1, time2);
	}
	@Test
	public void startsNoEarlierThan5PM() throws Exception {
		assertEquals("5:00 PM", new countGenerator().getStartingTimeCheck("2001.09.01 4:49 PM"));
	} 
	@Test
	public void startsNolaterThan4AM() throws Exception {
		assertEquals("4:00 AM", new countGenerator().getEndingTimeCheck("2001.09.02 4:15 AM"));
	} 
	
}

