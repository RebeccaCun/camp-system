package system;

import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private CampSystemFACADE campSystem;

    public UI(){
        scanner = new Scanner(System.in);
    }

    public void run(){
        System.out.println("Welcome to our camp website!");
        boolean invalid = false;
        int choice;
        while(!invalid){
            displayIntroMenu();
            choice = getUserCommand();
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
            choice = getUserCommand();
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
    private int getUserCommand(){
        int choice = scanner.nextInt();
        return choice;
    };
    private void createAccount(){}
    private void login(){

    }
    private void addCamper(){
        System.out.println("Enter Campers name: ");
        String CamperName = scanner.nextLine();

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
