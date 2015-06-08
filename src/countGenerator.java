
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
	 

public class countGenerator {
	//String time1;
	//String time2;
	//String EarliestStartingTime = " 5:00 PM";
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
	public int getStartingTimeFromInput(int i) {
		return 5;
	}

public int getTimeDiffereces(Date date1, Date date2) throws Exception{
			//Date date1 = format.parse(time1);
			//Date date2 = format.parse(time2);
			long difference = date2.getTime() - date1.getTime();
			return (int) (difference/1000/60/60);			
		}


public Date getTimePoint(String t, String dateBound, String bound){
	Date inputDate = null;
	try {
		inputDate = format.parse(t);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	String[] split = t.split(" ");
	Date dateBounds = null;
	try {
		dateBounds = format.parse(split[0] + dateBound);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	
	long difference = inputDate.getTime() - dateBounds.getTime();
		if (bound == "Start"){
			if(difference>0){
		    	return inputDate;
		    }
			else
		    	return dateBounds;	
		}
		else if (bound == "End") {
			if(difference < 0){
		    	return inputDate;
		    }
		    else {
		    	return dateBounds;		
		    }
		}
		return null;
	}

public int getPaid(String startTimePoint, String bedTimePoint,String endTimePoint) throws Exception {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
	Date startTime = getTimePoint(startTimePoint," 5:00 PM","Start");
	Date bedTime = getTimePoint(bedTimePoint," 5:00 PM","Start");
	Date finishedTime = getTimePoint(endTimePoint," 4:00 AM","End");
	String[] split = endTimePoint.split(" ");
	Date midnight = null;
	try {
		midnight = format.parse(split[0] + " 12:00 AM");
	} catch (ParseException e) {
		e.printStackTrace();
	}
	int paidBeforeBedTime = 0;
	int paidDuringBedTime = 0;
	int paidDuringlateNightTime = 0; 
	if (getTimeDiffereces(startTime,bedTime)>0)
	{ 
		int beforeBedTime = getTimeDiffereces(startTime,bedTime);
		paidBeforeBedTime = 12*beforeBedTime;
	}
	if (getTimeDiffereces(bedTime,midnight)>0)
	{
		int BedTime = getTimeDiffereces(bedTime,midnight);
		paidDuringBedTime = 8*BedTime;
	}
	if (getTimeDiffereces(midnight,finishedTime)>0)
	{
		int lateNightTime = getTimeDiffereces(midnight,finishedTime);
		paidDuringlateNightTime = 16*lateNightTime;
	}
		return paidBeforeBedTime + paidDuringBedTime + paidDuringlateNightTime;
}


}
