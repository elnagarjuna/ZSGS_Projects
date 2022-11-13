package booking;

public class TicketsList {

	public void bookedTicketList() {

		if (Administrator.passengersList.size() > 0) {
			int i = 1;
			System.out
					.println("Sl.No.\tPNR No.\tName\tAge\tGender\tChild Name\tChild Age\tChild Gender\tAlloted Berth");
			for (Passenger p : Administrator.passengersList.values()) {
				System.out.print(i++);
				System.out.print("\t" + p.pnrNumber);
				System.out.print("\t" + p.getName());
				System.out.print("\t" + p.getAge());
				System.out.print("\t" + p.getGender());
				if (p.getChildName() == null && p.getChildAge() == 0 && p.getChildGender() == null) {
					System.out.print("\t-NIL-\t-NIL-\t-NIL-");
				} else {
					System.out.print("\t" + p.getChildName());
					System.out.print("\t" + p.getChildAge());
					System.out.print("\t" + p.getChildGender());
				}
				System.out.println("\t" + p.alloted);
			}
		} else {
			System.out.println("No Passengers Details Found.");
		}
	}

	public void availableTicketList() {

		if ((Ticket.availableLowerBerth == 0) && (Ticket.availableMiddleBerth == 0) && (Ticket.availableUpperBerth == 0)
				&& (Ticket.availableSideUpperBerth == 0) && (Ticket.availableRACTicket == 0)
				&& (Ticket.availableWaitingTicket == 0)) {
			System.out.println("No Tickets are Available..!");
		} else {
			System.out.println("Available Lower Berths are         : " + Ticket.availableLowerBerth);
			System.out.println("Available Middle Berths are        : " + Ticket.availableMiddleBerth);
			System.out.println("Available Upper Berths are         : " + Ticket.availableUpperBerth);
			System.out.println("Available Side Upper Berths are    : " + Ticket.availableSideUpperBerth);
			System.out.println("Available RAC Tickets are          : " + Ticket.availableRACTicket);
			System.out.println("Available Waiting List Tickets are : " + Ticket.availableWaitingTicket + "\n");
		}
	}
}