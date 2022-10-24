package system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Counselor extends User{
    private String biography;
    private ArrayList<Cabin> cabins;
    private Medical medical;

    public Counselor(String firstName, String lastName, String userName) {
        super(firstName, lastName, userName);
    }

    public Counselor(UUID id, String firstName, String lastName, String userName) {
        super(id, firstName, lastName, userName);
    }

    public void addBiography(String biography) {
        this.biography = biography;
    }

    public void giveStrike(Camper camper, String reason) {
        //need more info on method
    }

    public void addMedical(Medical medical) {
        this.medical = medical;
    }
    
    public void addCabins(ArrayList<Cabin> cabins) {
        this.cabins = cabins;
    }
    
    public String toString() {
        String print = super().toString()+"Bio: "+this.biography"\n";
        for (int i = 0; i < cabins.size(); i++) {
			if (cabins.get(i) != null) {
                print += cabins.get(i)+"\n";
            }
		}
        return print+="\n";
    }
}
