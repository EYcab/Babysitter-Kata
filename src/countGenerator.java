
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
	 

public class countGenerator {
	String time1;
	String time2;
	SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
	public int getStartingTimeFromInput(int i) {
		return 5;
	}

public int getTimeDiffereces(String time1, String time2) throws Exception{
			Date date1 = format.parse(time1);
			Date date2 = format.parse(time2);
			long difference = date2.getTime() - date1.getTime();
			return (int) (difference/1000/60/60);
			
		}
public String getStartingTimeCheck(String t) {
	Date inputDate = null;
	try {
		inputDate = format.parse(t);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	String[] split = t.split(" ");
	Date dateBound = null;
	try {
		dateBound = format.parse(split[0]+" 5:00 PM");
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	long difference = inputDate.getTime() - dateBound.getTime();
    if(difference<0){
    	return "5:00 PM";
    }
    else
    	return split[1]+" PM";
}

public String getEndingTimeCheck(String endTime) {
	Date inputDate2 = null;
	try {
		inputDate2 = format.parse(endTime);
	} catch (ParseException e) {
		e.printStackTrace();
	}
	String[] split = endTime.split(" ");
	Date dateBound = null;
	try {
		dateBound = format.parse(split[0]+" 4:00 AM");
	} catch (ParseException e) {
		e.printStackTrace();
	}
	long difference = inputDate2.getTime() - dateBound.getTime();
    if(difference>0){
    	return "4:00 AM";
    }
    else
    	return split[1]+" AM";
}



}
