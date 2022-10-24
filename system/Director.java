    package system;

public class Director extends User{
    private ArrayList<String> counselorNames;

    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private Counselor verifyCounselor(User counselor) {
        //need more info on method
        return null;
    }

    public void giveStrike(Camper camper, String reason) {
        //need more info on method
    }

    public String getOverview() {
        //need more info on method
        return "";
    }

    public String toString() {
        String print = super().toString()+"Counselor names: ";
        for (int i = 0; i < counselorNames.size(); i++) {
			if (counselorNames.get(i) != null) {
                print += counselorNames.get(i)+"\n";
            }
		}
        return print;
    }
}
