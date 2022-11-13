package booking;

import java.util.Scanner;

import booking.Passenger.Berth;
import booking.Passenger.Gender;

public class PassengerDetail {

	static int i = 1;

	public Passenger passengerDetailCollecting() {

		String name, childName;
		int age, childAge;
		Gender gender = null, childGender = null;
		Berth berthPreference = null;

		Passenger p = new Passenger();

		@SuppressWarnings("resource")
		Scanner detail = new Scanner(System.in);
		System.out.print("Enter passenger's Name                                    : ");
		name = detail.next();
		p.setName(name);
		System.out.print("Enter passenger's Age                                     : ");
		age = detail.nextInt();
		if (age > 5) {
			p.setAge(age);
		} else {
			System.out.println("Age must be greater than 5 for Passenger.");
			return null;
		}
		System.out.print("Enter passenger's Gender as [F or M or O]                 : ");
		String g = detail.next();
		if (g.equalsIgnoreCase("F") || g.equalsIgnoreCase("M")) {
			if (g.equalsIgnoreCase("F")) {
				gender = Gender.FEMALE;
			} else if (g.equalsIgnoreCase("M")) {
				gender = Gender.MALE;
			}
			p.setGender(gender);
			System.out.println("\tYou travel with your child under 5 or equal old ?");
			System.out.println("\t1. Yes.");
			System.out.println("\t2. No.");
			System.out.println("\t-------------------------------------------------\n");
			System.out.print("\tEnter your choice : ");
			int gChoice = detail.nextInt();
			if (gChoice == 1) {
				System.out.print("\tEnter your child's Age                : ");
				childAge = detail.nextInt();
				if (childAge > 0) {
					if (childAge <= 5) {
						System.out.print("\tEnter your child's Name               : ");
						childName = detail.next();
						System.out.print("\tEnter your child's Gender as [F or M] : ");
						g = detail.next();
						if (g.equalsIgnoreCase("F")) {
							childGender = Gender.FEMALE;
						} else if (g.equalsIgnoreCase("M")) {
							childGender = Gender.MALE;
						} else {
							System.out.println("Child gender must be in above mentioned characters.");
							return null;
						}
						p.setChildName(childName);
						p.setChildAge(childAge);
						p.setChildGender(childGender);
						System.out.print("Enter passenger's Berth Preference as [U or M or L or SU] : ");
						String bp = detail.next();
						if (bp.equalsIgnoreCase("U")) {
							berthPreference = Berth.UPPER_BERTH;
						} else if (bp.equalsIgnoreCase("M")) {
							berthPreference = Berth.MIDDLE_BERTH;
						} else if (bp.equalsIgnoreCase("L")) {
							berthPreference = Berth.LOWER_BERTH;
						} else if (bp.equalsIgnoreCase("SU")) {
							berthPreference = Berth.SIDE_UPPER_BERTH;
						} else {
							System.out.println("Berth Preference must be in above mentioned character.");
							return null;
						}
						p.pnrNumber = i++;
						p.setBerthPreference(berthPreference);
						return p;
					} else {
						System.out.println("You Book a Separate Ticket for your Child.");
					}
				} else {
					System.out.println("Age must be greater then 0 and less then or equalt 5 for Child.");
					return null;
				}
			} else if (gChoice == 2) {
				childName = null;
				childAge = 0;
				childGender = null;
				System.out.print("Enter passenger's Berth Preference as [U or M or L or SU] : ");
				String bp = detail.next();
				if (bp.equalsIgnoreCase("U")) {
					berthPreference = Berth.UPPER_BERTH;
				} else if (bp.equalsIgnoreCase("M")) {
					berthPreference = Berth.MIDDLE_BERTH;
				} else if (bp.equalsIgnoreCase("L")) {
					berthPreference = Berth.LOWER_BERTH;
				} else if (bp.equalsIgnoreCase("SU")) {
					berthPreference = Berth.SIDE_UPPER_BERTH;
				} else {
					System.out.println("Berth Preference must be in above mentioned character.");
					return null;
				}
				p.pnrNumber = i++;
				p.setChildName(childName);
				p.setChildAge(childAge);
				p.setChildGender(childGender);
				p.setBerthPreference(berthPreference);
				return p;
			} else {
				System.out.println("Please select the above options only.");
				return null;
			}
		} else if (g.equalsIgnoreCase("O")) {
			gender = Gender.OTHERS;
			childName = null;
			childAge = 0;
			childGender = null;
			System.out.print("Enter passenger's Berth Preference as [U or M or L or SU] : ");
			String bp = detail.next();
			if (bp.equalsIgnoreCase("U")) {
				berthPreference = Berth.UPPER_BERTH;
			} else if (bp.equalsIgnoreCase("M")) {
				berthPreference = Berth.MIDDLE_BERTH;
			} else if (bp.equalsIgnoreCase("L")) {
				berthPreference = Berth.LOWER_BERTH;
			} else if (bp.equalsIgnoreCase("SU")) {
				berthPreference = Berth.SIDE_UPPER_BERTH;
			} else {
				System.out.println("Berth Preference must be in above mentioned character.");
				return null;
			}
			p.pnrNumber = i++;
			p.setGender(childGender);
			p.setChildName(childName);
			p.setChildAge(childAge);
			p.setChildGender(childGender);
			p.setBerthPreference(berthPreference);
			return p;
		} else {
			System.out.println("Passenger's gender must be in above mentioned charcters.");
			return null;
		}
		return null;
	}
}