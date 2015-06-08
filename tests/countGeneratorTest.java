import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class countGeneratorTest {
	@Test
	public void startsAt5PM() throws Exception {
		assertEquals(5, new countGenerator().getStartingTimeFromInput(5));
	} 
//I have tried Integer Hours Calculations and it works
	@Test
	public void TimeDifferecesByNonIntegerHours() throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		assertEquals(0, new countGenerator().getTimeDiffereces(formatter.parse("2001.09.01 11:10 PM"),formatter.parse("2001.09.02 12:00 AM")));
	}
	private int getTimeDiffereces(Date time1,Date time2) throws Exception {
		return new countGenerator().getTimeDiffereces(time1, time2);
	}
	@Test
	public void startsNoEarlierThan5PM() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		assertEquals(formatter.parse("2001.09.01 5:00 PM"), new countGenerator().getTimePoint("2001.09.01 4:49 PM"," 5:00 PM","Start"));
	} 
	@Test
	public void startsNolaterThan4AM() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		assertEquals(formatter.parse("2001.09.02 4:00 AM"), new countGenerator().getTimePoint("2001.09.02 3:19 PM"," 4:00 AM","End"));	
	} 
//The starting bedTime is bounded between 5:00 PM and 4:00 AM,yet what is taken into account for our paid hour
//for bedTime starting point is bounded by 5:00 PM and 12:00 AM
//Thus,the bedTime is started no Earlier than 5:00 PM for our calculation.
	@Test
	public void paidHoursCalculatedBefore12AM() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		String bedTimePoint = "2001.09.01 9:49 PM";
		String startTimePoint = "2001.09.01 4:49 PM";
		String EndTime = "2001.09.02 3:49 AM";
		Date Midnight = formatter.parse("2001.09.02 12:00 AM");
		Date startTime = new countGenerator().getTimePoint(startTimePoint," 5:00 PM","Start");
		Date bedTime = new countGenerator().getTimePoint(bedTimePoint," 5:00 PM","Start");
		Date finishedTime = new countGenerator().getTimePoint(EndTime," 4:00 AM","End");
		assertEquals(4, new countGenerator().getTimeDiffereces(startTime,bedTime));
		assertEquals(2, new countGenerator().getTimeDiffereces(bedTime,Midnight));		
		assertEquals(3, new countGenerator().getTimeDiffereces(Midnight,finishedTime));
	}
}

