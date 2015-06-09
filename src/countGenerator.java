
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
	 

public class countGenerator {
	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
public int getTimeDifferences(Date date1, Date date2) throws Exception{
			long difference = date2.getTime() - date1.getTime();
			if (difference>0)
			{
			//return the hours recorded for hourly pay.
			return (int) (difference/1000/60/60);
			}
			else {
			return 0;
			}
		}

//t is for the actual operating time point, dataBound is set for the period,i.e. the ealiest starting time.
//Bound is defined for the kind of operation,either "Start" or "End" to check if the time read in is mismatched.
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
//All time point record the real service time  
public int getPaid(String startTimePoint, String bedTimePoint,String endTimePoint) throws Exception {
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
//Amount paid for before bed time
	paidBeforeBedTime = 12*getTimeDifferences(startTime,bedTime);
//BedTime Paid amount
//StartTime could be later than bedTime
	if (getTimeDifferences(startTime,bedTime) == 0){
	paidDuringBedTime = 8*getTimeDifferences(startTime,midnight);
	}
	else{
	paidDuringBedTime = 8*getTimeDifferences(bedTime,midnight);
	}
//late Night Paid amount
	paidDuringlateNightTime = 16*getTimeDifferences(midnight,finishedTime);
//Total amount calculated	
	return paidBeforeBedTime + paidDuringBedTime + paidDuringlateNightTime;
}


}
