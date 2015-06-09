import java.util.Scanner;
//The main class to interact with the user
public class InteractionPage {

	public static void main(String[] args) {		
		String startTimePoint = null;
		String bedTimePoint = null;
		String endTimePoint = null;
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your starting Time in the exact format as '2001.09.01 9:49 PM' : ");
		startTimePoint = scanner.nextLine();
		System.out.print("Enter the baby's bed Time: ");
		bedTimePoint = scanner.nextLine();
		System.out.print("Enter the Time when you ended your service at last: ");
		endTimePoint = scanner.nextLine();
		scanner.close();
	    try {
			System.out.println(String.valueOf(new countGenerator().getPaid(startTimePoint,bedTimePoint,endTimePoint)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

	
