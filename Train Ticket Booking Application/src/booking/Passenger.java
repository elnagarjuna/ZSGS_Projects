package booking;

public class Passenger {

	enum Gender {
		FEMALE, MALE, OTHERS;
	}

	enum Berth {
		LOWER_BERTH, MIDDLE_BERTH, UPPER_BERTH, SIDE_UPPER_BERTH;
	}

	enum AllotedBerth {
		LOWER_BERTH, MIDDLE_BERTH, UPPER_BERTH, SIDE_UPPER_BERTH, RAC, WAITHING_LIST;
	}

	private String name;
	private int age;
	private Gender gender;
	private String childName;
	private int childAge;
	private Gender childGender;
	private Berth berthPreference;
	int pnrNumber;
	AllotedBerth alloted;

	Passenger() {
		alloted = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public int getChildAge() {
		return childAge;
	}

	public void setChildAge(int childAge) {
		this.childAge = childAge;
	}

	public Gender getChildGender() {
		return childGender;
	}

	public void setChildGender(Gender childGender) {
		this.childGender = childGender;
	}

	public Berth getBerthPreference() {
		return berthPreference;
	}

	public void setBerthPreference(Berth berthPreference) {
		this.berthPreference = berthPreference;
	}
}