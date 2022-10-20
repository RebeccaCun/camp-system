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

    public void run(){}
    private void displayMainMenu(){}
    private int getUserCommand(){
        return 1;
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
    private void addCamper(){}
    private void finalizePayment(){}
    private void printGeneralInformation(){}
    private void printFAQ(){}
    private void logout(){}
    private void sessionSignup(){}
    private void askToAcceptWaiver(){}
    public static void main(String[] args){
        UI ui = new UI();
        ui.run();
    }
    
}
