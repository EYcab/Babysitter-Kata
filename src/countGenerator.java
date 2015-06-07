
import java.text.SimpleDateFormat;
import java.util.Date;
	 

public class countGenerator {
	public int getStartingTimeFromInput(int i) {
		// TODO Auto-generated method stub
		return 5;
	}

public int getTimeDiffereces(int Hourdiff) throws Exception{
			String time1 = "2001.09.01 11:10 PM";
			String time2 = "2001.09.02 12:00 AM";
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
			Date date1 = format.parse(time1);
			Date date2 = format.parse(time2);
			long difference = date2.getTime() - date1.getTime();
			return Hourdiff = (int) (difference/1000/60/60);
		}

}
