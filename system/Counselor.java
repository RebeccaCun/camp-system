package system;

import java.time.LocalDate;

public class Counselor extends User{
    private String biography;
    private ArrayList<Cabin> cabins;
    //private Medical medical;

    public Counselor(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName);
    }

    public void addPassword(String password) {

    }



    public void addBiography(String biography) {

    }

    public void giveStrike(Camper camper, String reason) {

    }

    public String toString() {
        return "";
    }
}
