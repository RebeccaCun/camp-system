package system;

import java.util.Scanner;

public class UI {
    private Scanner scanner;
    private CampSystemFACADE campSystem;

    public void run(){
        System.out.println("Welcome to our camp website!");
        displayMainMenu();
        int choice = getUserCommand();
        if(choice == 1){
            login();
        }
        else if(choice == 2){
            createAccount();
        }
        else{
            System.out.println("Invalid Input.");
            displayMainMenu();
        }
    }
    private void displayMainMenu(){
        System.out.println("Press (1) to sign in to your account");
        System.out.println("Press (2) to create a new account");
    }
    private int getUserCommand(){
        int choice = scanner.nextInt();
        return choice;
    };
    private void createAccount(){}
    private void login(){}
    private void addCamper(){}
    private void finalizePayment(){}
    private void printGeneralInformation(){}
    private void printFAQ(){}
    private void logout(){}
    private void sessionSignup(){}
    private void askToAcceptWaiver(){}
    public static void main(String[] args){
        
    }
    
}
