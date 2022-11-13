package booking;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class Administrator {

	static String adminID = "admin";

	private static int availableLowerBerth;
	private static int availableMiddleBerth;
	private static int availableUpperBerth;
	private static int availableSideUpperBerth;
	private static int availableRACTicket;
	private static int availableWaitingTicket;

	static Queue<Integer> racTicketList;
	static Queue<Integer> waitingTicketList;
	static TreeMap<Integer, Passenger> passengersList;

	Administrator() {
		racTicketList = new LinkedList<>();
		waitingTicketList = new LinkedList<>();
		passengersList = new TreeMap<>();
	}

	public void numberOfBerthAllocation() {
		@SuppressWarnings("resource")
		Scanner count = new Scanner(System.in);
		System.out.print("Enter Total Number of Tickets : ");
		int totalCount = count.nextInt();
		int waiting = (totalCount * 10) / 100;
		Administrator.availableRACTicket = (totalCount * 20) / 100;
		Administrator.availableLowerBerth = (totalCount * 20) / 100;
		Administrator.availableMiddleBerth = (totalCount * 20) / 100;
		Administrator.availableUpperBerth = (totalCount * 20) / 100;
		if (totalCount > 10) {
			Administrator.availableSideUpperBerth = (totalCount * 10) / 100;
		} else {
			Administrator.availableSideUpperBerth = (totalCount * 20) / 100;
		}
		int total = Administrator.availableLowerBerth + Administrator.availableMiddleBerth
				+ Administrator.availableUpperBerth + Administrator.availableSideUpperBerth
				+ Administrator.availableRACTicket + waiting;
		Administrator.availableWaitingTicket = (waiting + (totalCount - total));
	}

	public static int getAvailableLowerBerth() {
		return availableLowerBerth;
	}

	public static void setAvailableLowerBerth(int availableLowerBerth) {
		Administrator.availableLowerBerth = availableLowerBerth;
	}

	public static int getAvailableMiddleBerth() {
		return availableMiddleBerth;
	}

	public static void setAvailableMiddleBerth(int availableMiddleBerth) {
		Administrator.availableMiddleBerth = availableMiddleBerth;
	}

	public static int getAvailableUpperBerth() {
		return availableUpperBerth;
	}

	public static void setAvailableUpperBerth(int availableUpperBerth) {
		Administrator.availableUpperBerth = availableUpperBerth;
	}

	public static int getAvailableSideUpperBerth() {
		return availableSideUpperBerth;
	}

	public static void setAvailableSideUpperBerth(int availableSideUpperBerth) {
		Administrator.availableSideUpperBerth = availableSideUpperBerth;
	}

	public static int getAvailableRACTicket() {
		return availableRACTicket;
	}

	public static void setAvailableRACTicket(int availableRACTicket) {
		Administrator.availableRACTicket = availableRACTicket;
	}

	public static int getAvailableWaitingTicket() {
		return availableWaitingTicket;
	}

	public static void setAvailableWaitingTicket(int availableWaitingTicket) {
		Administrator.availableWaitingTicket = availableWaitingTicket;
	}
}