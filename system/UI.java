package system;

import java.time.LocalDate;
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
        boolean invalid = false;
        int choice;
        while(!invalid){
            displayIntroMenu();
            choice = getUserCommand(2);
            if(choice == 1){
                login();
            }
            else if(choice == 2){
                createAccount();
            }
            else{
                System.out.println("Invalid Input.");
                invalid = true;
            }
        }  
        while(!invalid){
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
                    break;
                default:
                    System.out.println("Invalid input");
                    invalid = true;
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
    };
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
            System.out.print("\nEnter your relationship to Camper: ");
            String relationship = scanner.nextLine();
            System.out.print("\nEnter Campers birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            System.out.print("\n Enter the following information about the EMERGENCY CONTACT:");
            Contact emergContact = createTheContact(); //for emergency contact. add to arrayList?
            //add guardians
            //medical info starts here
            System.out.println("Enter the folling information about the Campers DOCTOR");
            Contact docContact = createTheContact();
            System.out.println("Would you like to add Medications to your Campers Account (All necessary medications to be taken during camp must be added) Y/N")
            String yn = scanner.nextLine();
            while(yn.equalsIgnoreCase("y")){
                //add medical info
                System.out.print("What is the name of the Campers medication");
                String medName = scanner.nextLine();
                System.out.print("What time of day does the camper need to take medicine" );
                String medTime = scanner.nextLine();
                System.out.print("Do you want to add another medicatipn?");
                yn = scanner.nextLine();
            }
            //for adding allergies
            System.out.println("Would you like to add any Allergies to your Campers Account (All necessary allergies to be taken during camp must be added) Y/N")
            yn = scanner.nextLine();
            while(yn.equalsIgnoreCase("y")){
                System.out.print("What is the campers allergy to?");
                String medName = scanner.nextLine();
                System.out.print("Do you want to add another medicatipn?");
                yn = scanner.nextLine();
            }
            System.out.print("Would you like to sign a camper up for a session now? (Y/N)");
            yn = scanner.nextLine();
            if(yn.equalsIgnoreCase("y")){
                sessionSignup();
            }
            if(campSystem.addCamper(firstName, lastName, birthday, emergContact, docContact)){   //Fix this
                System.out.println(firstName + lastName+ "has been successfully signed up as a Camper");
                break;
            }
            System.out.println("\nInvalid input. Start over.");
        }
    }
    private Contact createTheContact(){
        Contact aContact;
        while(true){ //emergency contact
            System.out.print("\n Enter the first name of Campers emergency contact"); 
            String emFirstName = scanner.nextLine();
            System.out.print("\n Enter the last name of Campers emergency contact"); 
            String emLastName = scanner.nextLine();
            System.out.print("\nEnter contact email address: ");
            String ememail = scanner.nextLine();
            System.out.print("\nEnter contact phone number: ");
            String emphoneNumber = scanner.nextLine();
            aContact = new Contact(emFirstName, emLastName, emphoneNumber);
            if(aContact != null){
                System.out.println("\nAccount created successfully.");
                break;
                
            }
            System.out.print("\nInvalid input. Start over.");
        }
        return aContact;
    }
    private void finalizePayment(){}
    private void printGeneralInformation(){}
    private void printFAQ(){
        System.out.println("Can my camper stay for multipple sessions? Yes, guardians may sign up campers  for any number of sessions through their home menu!")
        System.out.println("Should my camper bring any specific items? Campers will need a vairety of items, such as... ");
    }
    private void logout(){
        System.out.println("Goodbye!");
        System.exit(0);
    }
    private void sessionSignup(){}
    private void askToAcceptWaiver(){}
    public static void main(String[] args){
        UI ui = new UI();
        ui.run();
    }
    
}
