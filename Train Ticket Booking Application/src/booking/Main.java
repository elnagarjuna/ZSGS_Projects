package booking;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		PassengerDetail pd = new PassengerDetail();
		TicketsList tl = new TicketsList();
		Ticket t = null;
		boolean flag = true;
		while (flag) {
			try {
				System.out.println("---------------------------------");
				System.out.println("1. Book Ticket.");
				System.out.println("2. Cancel Ticket.");
				System.out.println("3. Booked Tickets List.");
				System.out.println("4. Available Tickets List.");
				System.out.println("5. Are you admin please select 5.");
				System.out.println("6. Exit.");
				System.out.println("---------------------------------\n");
				System.out.print("Enter your choice : ");
				int choice = scan.nextInt();
				switch (choice) {
				case 1:
					Passenger p = pd.passengerDetailCollecting();
					t = new Ticket();
					if (p != null) {
						t.bookTicket(p);
					} else {
						break;
					}
					break;
				case 2:
					System.out.print("Enter your PNR Number : ");
					int pnrNumber = scan.nextInt();
					if (Administrator.passengersList.containsKey(pnrNumber)) {
//						t = new Ticket();
						t.cancelTicket(pnrNumber);
					} else {
						System.out.println("PNR Number is not found..!, Please Enter valid PNR Number.");
					}
					break;
				case 3:
					System.out.print("Enter your Admin ID : ");
					String adminID1 = scan.next();
					System.out.print("Enter your Password : ");
					String password1 = scan.next();
					if (Administrator.adminID.equals(adminID1) && Administrator.adminID.equals(password1)) {
						tl.bookedTicketList();
					} else {
						System.out.println("Please enter a valid AdminID or Password.");
					}
					break;
				case 4:
					tl.availableTicketList();
					break;
				case 5:
					System.out.print("Enter your Admin ID : ");
					String adminID = scan.next();
					System.out.print("Enter your Password : ");
					String password = scan.next();
					if (Administrator.adminID.equals(adminID) && Administrator.adminID.equals(password)) {
						Administrator admin = new Administrator();
						admin.numberOfBerthAllocation();
					} else {
						System.out.println("Please enter a valid AdminID or Password.");
					}
					break;
				case 6:
					System.out.println("Thankyou Please visit again..!");
					flag = false;
					break;
				default:
					System.out.println("Please choose a valid choice.");
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
				scan.next();
			}
		}
		scan.close();
	}
}