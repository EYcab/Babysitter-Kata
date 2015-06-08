
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

}