package system;

import java.util.ArrayList;

/**
 * A Medical class that holds the attributes for the medical needs of a individual.
 * @author Cyber Council
 */
public class Medical {
    private Contact doctor;
    private ArrayList <String> allergies;
    private ArrayList <Medication> medications;

    /**
     * Establishes an instance of the Medical class.
     * @param doctor A Contact representing the doctor of the Medical class.
     */
    public Medical(Contact doctor) {
        this.doctor = doctor;
    }

    /**
     * Adds a list of allergies to the Medical class.
     * @param allergies An ArrayList of allergies representing the list of allergies.
     */
    public void addAllergies(ArrayList<String> allergies) {
        this.allergies = allergies;
    }

    /**
     * Adds a list of medication to the Medical class.
     * @param medications An ArrayList of Medication representing the list of Medications.
     */
    public void addMedications(ArrayList<Medication> medications) {
        this.medications = medications;
    }

    /**
     * Adds an allergy to the Allergies ArrayList.
     * @param allergy A String representing an allergy.
     */
    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }

    /**
     * Adds a medication to the Medications ArrayList.
     * @param medication A Medication representing a medication.
     */
    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    /**
     * gets the Contact for the doctor
     * @return contact 
     */    
    public Contact getDoctor() {
        return this.doctor;
    }

    /**
     * returns an arrayList of the campers allegies
     * @return ArrayList
     */
    public ArrayList<String> getAllergies() {
        return this.allergies;
    }

    /**
     * returns an ArrayList of all the campers medications
     * @return ArrayList
     */
    public ArrayList<Medication> getMedications() {
        return this.medications;
    }

    /**
     * Creates a string detailing the attributes of the Medical class.
     * @return A string representation of the Medical class.
     */
    public String toString() {
        String print = "Doctor: "+this.doctor+"\nAllergies: ";
        for (int i = 0; i < this.allergies.size(); i++) {
			if (this.allergies.get(i) != null) {
                print += this.allergies.get(i)+"\n";
            }
		}
        print += "\nMedications: ";
        for (int i = 0; i < this.medications.size(); i++) {
			if (this.medications.get(i) != null) {
                print += this.medications.get(i)+"\n";
            }
		}
        return print;
    }
}
