import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;


public class countGeneratorTest {
//I have tried Integer Hours Calculations and it works
	@Test
	public void TimeDifferecesByNonIntegerHours() throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		assertEquals(0, new countGenerator().getTimeDifferences(formatter.parse("2001.09.01 11:10 PM"),formatter.parse("2001.09.02 12:00 AM")));
	}
//Summarize my function.
	private int getTimeDifferences(Date time1,Date time2) throws Exception {
		return new countGenerator().getTimeDifferences(time1, time2);
	}
	@Test
	public void startsNoEarlierThan5PM() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		assertEquals(formatter.parse("2001.09.01 5:49 PM"), new countGenerator().getTimePoint("2001.09.01 5:49 PM"," 5:00 PM","Start"));
	} 
	@Test
	public void startsNolaterThan4AM() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		assertEquals(formatter.parse("2001.09.02 4:00 AM"), new countGenerator().getTimePoint("2001.09.02 3:19 PM"," 4:00 AM","End"));	
	} 
//The starting bedTime is bounded between 5:00 PM and 4:00 AM,yet what is taken into account for our paid hour
//for bedTime starting point is bounded by 5:00 PM and 12:00 AM
//Thus,the bedTime is started no Earlier than 5:00 PM for our calculation.
//This design rules out the change of the babysitter coming to work after midnight
	@Test
	public void paidHoursCalculated() throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
		String startTimePoint = "2001.09.01 9:49 PM";
		String bedTimePoint = "2001.09.01 10:59 PM";
		String endTimePoint = "2001.09.02 3:49 AM";
		Date Midnight = formatter.parse("2001.09.02 12:00 AM");
		Date startTime = new countGenerator().getTimePoint(startTimePoint," 5:00 PM","Start");
		Date bedTime = new countGenerator().getTimePoint(bedTimePoint," 5:00 PM","Start");
		Date finishedTime = new countGenerator().getTimePoint(endTimePoint," 4:00 AM","End");
		assertEquals(1, new countGenerator().getTimeDifferences(startTime,bedTime));
		if (new countGenerator().getTimeDifferences(startTime,bedTime)==0){
		assertEquals(1, new countGenerator().getTimeDifferences(startTime,Midnight));
		}
		else
		assertEquals(1, new countGenerator().getTimeDifferences(bedTime,Midnight));
		assertEquals(3, new countGenerator().getTimeDifferences(Midnight,finishedTime));
		assertEquals(68, new countGenerator().getPaid(startTimePoint,bedTimePoint,endTimePoint));
	}
//Refactor all the repetitive if statement in getPaid() function,since it makes no sense to return a negative calulated paid time 
}

