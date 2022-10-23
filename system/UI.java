package system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private CampSystemFACADE campSystem;

    public UI(){
        scanner = new Scanner(System.in);
        campSystem = new CampSystemFACADE();
    }

    public void run(){
        System.out.println("Welcome to our camp website!");
        int choice;
        boolean stop = false;
        while(true){
            displayIntroMenu();
            choice = getUserCommand(2);
            if(choice == -1){
                System.out.println("Invalid input.");
            }else if(choice == 1){
                login();
                break;
            }else if(choice == 2){
                createAccount();
            }  
        }

        while(!stop){
            displayMainMenu();
            choice = getUserCommand(6);
            switch(choice){
                case '1':
                    addCamper();
                    break;
                case '2':
                    sessionSignup();
                    break;
                case '3':
                    finalizePayment();
                    break;
                case '4':
                    printGeneralInformation();
                    break;
                case '5': 
                    printFAQ();
                    break;
                case '6':
                    logout();
                    stop = true;
                    break;
                default:
                    System.out.println("Invalid input");
            }
        } 

    }

    private void displayIntroMenu(){
        System.out.println("Press (1) to sign in to your account");
        System.out.println("Press (2) to create a new account");
    }

    private void displayMainMenu(){ //for choices: create child acct, add child to session
        System.out.println("What would you like to do today? \n(1) add a new Camper \n(2) sign up camper for a session \n(3) finalize payment \n(4) General Information \n(5) FAQ’s (6) Logout");
    }

    private int getUserCommand(int commands){
        int choice = scanner.nextInt();
        if(choice > 0 && choice <= commands){
            return choice;
        }
        return -1;
    }

    private void createAccount(){
        while(true){
            System.out.print("\nEnter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("\nEnter your last name: ");
            String lastName = scanner.nextLine();
            System.out.print("\nEnter a username: ");
            String username = scanner.nextLine();
            System.out.print("\nEnter a password: ");
            String password = scanner.nextLine();
            System.out.print("\nEnter your email address: ");
            String email = scanner.nextLine();
            System.out.print("\nEnter your phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("\nEnter your preferred method of contact (phone or email): ");
            String preferredContact = scanner.nextLine();
            System.out.print("\nEnter your birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            System.out.print("\nEnter your address: ");
            String address = scanner.nextLine();
            if(campSystem.createAccount(username, password, email, lastName, firstName, phoneNumber, preferredContact, birthday, address) == true){
                System.out.println("\nAccount created successfully.");
                break;
            }
            System.out.println("\nInvalid input. Start over.");
        }
        
    }

    private void login(){
        while(true){
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            if(campSystem.login(username, password) == true){
                System.out.println("Login successful.");
                break;
            }
            System.out.println("Username or password invalid. Try again.");
        }
    }

    private void addCamper(){
        while(true){
            System.out.print("\nEnter Campers first name: ");
            String firstName = scanner.nextLine();
            System.out.print("\nEnter Campers last name: ");
            String lastName = scanner.nextLine();
            System.out.print("\nEnter Campers birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            System.out.println("\n Enter the following information about the EMERGENCY CONTACT:");
            Contact emergencyContact = createContact();
            //maybe add guardians here (?)
            //medical info starts here
            System.out.println("\nEnter the following information about the Campers DOCTOR");
            Contact doctorContact = createContact();
            System.out.println("\nWould you like to add Medications to your Campers Account (All necessary medications to be taken during camp must be added) Y/N");
            String yn = scanner.nextLine();
            ArrayList<Medication> medications;
            while(yn.equalsIgnoreCase("y")){
                System.out.print("\nWhat is the name of the Campers medication");
                String medName = scanner.nextLine();
                System.out.print("\nWhat time of day does the camper need to take medicine" );
                String medTime = scanner.nextLine();
                Medication medication = new Medication(medName, medTime);
                medications.add(medication);
                System.out.print("\nDo you want to add another medication? (Y/N) ");
                yn = scanner.nextLine();
            }
            //for adding allergies
            System.out.println("\nWould you like to add any Allergies to your Campers Account (All necessary allergies to be taken during camp must be added) Y/N");
            yn = scanner.nextLine();
            ArrayList<String> allergies;
            while(yn.equalsIgnoreCase("y")){
                System.out.print("\nWhat is the campers allergy to?");
                String allergy = scanner.nextLine();
                allergies.add(allergy);
                System.out.print("\nDo you want to add another allergy? Y/N");
                yn = scanner.nextLine();
            }
            
            System.out.print("\n Would you like to add general notes about the camper? (Y/N) ");
            yn = scanner.nextLine();
            ArrayList<String> notes;
            while(yn.equalsIgnoreCase("y"){
                System.out.print("\n Enter note: ");
                String note = scanner.nextLine();
                notes.add(note);
                System.out.print("\n Would you like to add more notes about the camper? (Y/N) ");
                yn = scanner.nextLine();
            }
            
            if(campSystem.addCamper(firstName, lastName, birthday, emergencyContact, doctorContact, allergies, medications, notes) == true){   // add guardian details?
                System.out.println(firstName + lastName+ "has been successfully signed up as a Camper");
                break;
            }
            System.out.println("\nInvalid input. Start over.");
        }
    }

    private Contact createContact(){
        Contact contact;

        System.out.print("\n Enter the first name: "); 
        String firstName = scanner.nextLine();
        System.out.print("\n Enter the last name: "); 
        String lastName = scanner.nextLine();
        System.out.print("\nEnter the address: ");
        String address = scanner.nextLine();
        System.out.print("\nEnter the phone number: ");
        String phoneNumber = scanner.nextLine();
        contact = new Contact(firstName, lastName, phoneNumber, address);
        
        return contact;
    }

    private void finalizePayment(){}

    private void printGeneralInformation(){}

    private void printFAQ(){
        System.out.println("Can my camper stay for multipple sessions? \nYes, guardians may sign up campers  for any number of sessions through their home menu!");
        System.out.println("Should my camper bring any specific items? \nCampers will need a vairety of items, such as \n watersport essentails: swimsuit (2 or 3), goggles, towel, sunscreen \n Daily: bedtime essentails, multiple shorts, shirts, socks, shower and bathroom products \n Special event outfit: look at your sessions spirit night and bring relating accessories  ");
        System.out.println("What if my Camper does not want to or can not participate in an activity? \nAlthough participating is encouraged, campers are allowed to sit out in any activity. \nDepending on the number of kids sitting out, they will either have to sit out and watch the activity or will be escorted to another mini activity like arts & crafts or a game");
        System.out.println("What are some regular camp activities? \nswimming, archery, canoeing, overnight camp out by lake night, soccer, flag football, gymnastics, tag, camping survival strategies, reptile house, etc.");
        System.out.println("What kinds of payment do you accept?\n We only accept Credit/Debit card and echecks");
        System.out.println("Can you sign your kid for more then one session? \nYes, you can sign campers up for multiple on your homepage ");
        System.out.println("If I have an emergency, can I pick up my camper from camp early? \n Yes, as the campers guardian, you can pick up your camper from camp at any time.\n You may not get recompensated and will have to fill out paperwork and pack up your camper upon arrival");
        //activities
    }

    private void logout(){
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private void sessionSignup(){
        System.out.print("\nEnter the first name of the camper you would like to register for a session: ");
        String firstName = scanner.nextLine();
        System.out.print("\nEnter the last name of the camper you would like to register for a session: ");
        String lastName = scanner.nextLine();
        Camper camper = campSystem.findCamperByName(firstName, lastName);
        Session session = chooseSession();
        campSystem.sessionSignup(camper, session);
    }

    private Session chooseSession(){
        //prints available sessions and lets the user choose one
    }
    
    private void askToAcceptWaiver(){}
    public static void main(String[] args){
        UI ui = new UI();
        ui.run();
    }
    
}
