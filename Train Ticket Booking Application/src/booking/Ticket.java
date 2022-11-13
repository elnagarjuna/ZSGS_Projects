package booking;

import booking.Passenger.AllotedBerth;
import booking.Passenger.Berth;

public class Ticket {

	static int availableLowerBerth = Administrator.getAvailableLowerBerth();
	static int availableMiddleBerth = Administrator.getAvailableMiddleBerth();
	static int availableUpperBerth = Administrator.getAvailableUpperBerth();
	static int availableSideUpperBerth = Administrator.getAvailableSideUpperBerth();
	static int availableRACTicket = Administrator.getAvailableRACTicket();
	static int availableWaitingTicket = Administrator.getAvailableWaitingTicket();

	public void bookTicket(Passenger p) {
		if (availableWaitingTicket == 0) {
			System.out.println("No Tickets are Available.");
		} else if (p.getAge() > 60 && availableLowerBerth > 0) {
			System.out.println("Lower Berth is Alloted.");
			p.alloted = AllotedBerth.LOWER_BERTH;
			berthAllocation(p);
		} else if (p.getChildAge() != 0 && availableLowerBerth > 0) {
			System.out.println("Lower Berth is Alloted.");
			p.alloted = AllotedBerth.LOWER_BERTH;
			berthAllocation(p);
		} else if ((p.getBerthPreference().equals(Berth.LOWER_BERTH) && availableLowerBerth > 0)
				|| (p.getBerthPreference().equals(Berth.MIDDLE_BERTH) && availableMiddleBerth > 0)
				|| (p.getBerthPreference().equals(Berth.UPPER_BERTH) && availableUpperBerth > 0)
				|| (p.getBerthPreference().equals(Berth.SIDE_UPPER_BERTH) && availableSideUpperBerth > 0)) {
			if (p.getBerthPreference().equals(Berth.LOWER_BERTH)) {
				p.alloted = AllotedBerth.LOWER_BERTH;
				System.out.println("Lower Berth is Alloted.");
				berthAllocation(p);
			} else if (p.getBerthPreference().equals(Berth.MIDDLE_BERTH)) {
				p.alloted = AllotedBerth.MIDDLE_BERTH;
				System.out.println("Middle Berth is Alloted.");
				berthAllocation(p);
			} else if (p.getBerthPreference().equals(Berth.UPPER_BERTH)) {
				p.alloted = AllotedBerth.UPPER_BERTH;
				System.out.println("Upper Berth is Alloted.");
				berthAllocation(p);
			} else if (p.getBerthPreference().equals(Berth.SIDE_UPPER_BERTH)) {
				p.alloted = AllotedBerth.SIDE_UPPER_BERTH;
				System.out.println("Side Upper Berth is Alloted.");
				berthAllocation(p);
			}
		} else if (availableLowerBerth > 0) {
			p.alloted = AllotedBerth.LOWER_BERTH;
			System.out.println("Lower Berth is Alloted.");
			berthAllocation(p);
		} else if (availableMiddleBerth > 0) {
			p.alloted = AllotedBerth.MIDDLE_BERTH;
			System.out.println("Middle Berth is Alloted.");
			berthAllocation(p);
		} else if (availableUpperBerth > 0) {
			p.alloted = AllotedBerth.UPPER_BERTH;
			System.out.println("Upper Berth is Alloted.");
			berthAllocation(p);
		} else if (availableSideUpperBerth > 0) {
			p.alloted = AllotedBerth.SIDE_UPPER_BERTH;
			System.out.println("Side Upper Berth is Alloted.");
			berthAllocation(p);
		} else if (availableRACTicket > 0) {
			p.alloted = AllotedBerth.RAC;
			System.out.println("Your Ticket in RAC List.");
			berthAllocation(p);
		} else if (availableWaitingTicket > 0) {
			p.alloted = AllotedBerth.WAITHING_LIST;
			System.out.println("Your Ticket in Waiting List.");
			berthAllocation(p);
		}
	}

	public void berthAllocation(Passenger p) {

		if (p.alloted.equals(AllotedBerth.LOWER_BERTH)) {
			Administrator.passengersList.put(p.pnrNumber, p);
			availableLowerBerth--;
		} else if (p.alloted.equals(AllotedBerth.MIDDLE_BERTH)) {
			Administrator.passengersList.put(p.pnrNumber, p);
			availableMiddleBerth--;
		} else if (p.alloted.equals(AllotedBerth.UPPER_BERTH)) {
			Administrator.passengersList.put(p.pnrNumber, p);
			availableUpperBerth--;
		} else if (p.alloted.equals(AllotedBerth.SIDE_UPPER_BERTH)) {
			Administrator.passengersList.put(p.pnrNumber, p);
			availableSideUpperBerth--;
		} else if (p.alloted.equals(AllotedBerth.RAC)) {
			Administrator.racTicketList.add(p.pnrNumber);
			Administrator.passengersList.put(p.pnrNumber, p);
			availableRACTicket--;
		} else if (p.alloted.equals(AllotedBerth.WAITHING_LIST)) {
			Administrator.waitingTicketList.add(p.pnrNumber);
			Administrator.passengersList.put(p.pnrNumber, p);
			availableWaitingTicket--;
		}
		System.out.println("PNR Number is       : " + p.pnrNumber);
		System.out.println("Passenger Name is   : " + p.getName());
		System.out.println("Passenger Age is    : " + p.getAge());
		System.out.println("Passenger Gender is : " + p.getGender());
		if ((p.getChildName() != null) && (p.getChildAge() != 0) && (p.getChildGender() != null)) {
			System.out.println("Passenger Child Name is   : " + p.getChildName());
			System.out.println("Passenger Child Age is    : " + p.getChildAge());
			System.out.println("Passenger Child Gender is : " + p.getChildGender());
		}
		System.out.println("Alloted Berth is    : " + p.alloted);
		System.out.println("---------------Ticket Booked Successfully..!---------------\n");
	}

	public void cancelTicket(int pnrNumber) {
		if (Administrator.waitingTicketList.contains(Integer.valueOf(pnrNumber))) {
			Administrator.waitingTicketList.remove(pnrNumber);
			Administrator.passengersList.remove(pnrNumber);
			availableWaitingTicket++;
			System.out.println("Your Ticket is Cancelled Successfully.");
		} else {
			if (!Administrator.racTicketList.contains(Integer.valueOf(pnrNumber))) {
				Passenger p = Administrator.passengersList.get(pnrNumber);
				AllotedBerth cancelledAllotedBerth = p.alloted;
				Administrator.passengersList.remove(pnrNumber);
				System.out.println("Your Ticket is Cancelled Successfully.");

				if (cancelledAllotedBerth.equals(AllotedBerth.LOWER_BERTH)) {
					availableLowerBerth++;
				} else if (cancelledAllotedBerth.equals(AllotedBerth.MIDDLE_BERTH)) {
					availableMiddleBerth++;
				} else if (cancelledAllotedBerth.equals(AllotedBerth.UPPER_BERTH)) {
					availableUpperBerth++;
				} else if (cancelledAllotedBerth.equals(AllotedBerth.SIDE_UPPER_BERTH)) {
					availableSideUpperBerth++;
				}

				if (Administrator.racTicketList.size() > 0) {
					Passenger passengerFromRAC = Administrator.passengersList.get(Administrator.racTicketList.poll());
					Administrator.racTicketList.remove(passengerFromRAC.pnrNumber);
					availableRACTicket++;
					if (Administrator.waitingTicketList.size() > 0) {
						Passenger passengerFromWaitingList = Administrator.passengersList
								.get(Administrator.waitingTicketList.poll());
						Administrator.waitingTicketList.remove(passengerFromWaitingList.pnrNumber);

						passengerFromWaitingList.alloted = AllotedBerth.RAC;
						Administrator.racTicketList.add(passengerFromWaitingList.pnrNumber);
						availableWaitingTicket++;
						availableRACTicket--;
					}
					bookTicket(passengerFromRAC);
				}

			} else {
				System.out.println("Your Ticket is in RAC. So, its Against Cancellation.");
			}
		}
	}
}