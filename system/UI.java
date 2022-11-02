package system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * User interface of the camp program
 * @author Cyber Council
 */
public class UI {
    private Scanner scanner;
    private CampSystemFACADE campSystem;

    /**
     * creates an instance of the UI class
     */
    public UI(){
        scanner = new Scanner(System.in);
        campSystem = new CampSystemFACADE();
    }

    /**
     * main run for the program
     */
    public void run(){
        System.out.println("\nWelcome to our camp website!");
        int choice;
        boolean stop = false;
        Type accountType;
        while(true){
            displayIntroMenu();
            choice = getUserCommand(3);
            if(choice == -1){
                System.out.println("Invalid input.");
            }else if(choice == 1){
                accountType = login();
                break;
            }else if(choice == 2){
                createAccount();
            } else if(choice == 3){
                campSystem.logout();
                System.out.println("Goodbye.");
                return;
            } 
        }

        while(!stop){
            if(accountType == Type.PARENT){
                displayUserMenu();
                choice = getUserCommand(5);
            }else if(accountType == Type.COUNSELOR){
                displayCounselorMenu();
                choice = getUserCommand(9);
            }else if(accountType == Type.DIRECTOR){
                displayDirectorMenu();
                choice = getUserCommand(7);
            }
                
            switch(choice){
                case 1:
                    addCamper();
                    break;
                case 2:
                    sessionSignup();
                    break;
                case 3:
                    printGeneralInformation();
                    break;
                case 4: 
                    printFAQ();
                    break;
                case 5:
                    logout();
                    stop = true;
                    break;
                case 6:
                    giveStrike();
                    break;
                case 7:
                    if(accountType == Type.DIRECTOR){
                        createCamp();
                    }else{
                        printRoster();
                    }
                    break;
                case 8:
                    printWeekInfo();
                    break;
                case 9:
                    printSchedule();
                    break;
                default:
                    System.out.println("Invalid input");
            }   
        } 
    }

    /**
     * displays the intro screen 
     */
    private void displayIntroMenu(){
        System.out.println("Press (1) to sign in to your account");
        System.out.println("Press (2) to create a new account");
        System.out.println("Press (3) to quit");
    }

    /**
     * displays the main menu with the parent's options
     */
    private void displayUserMenu(){
        System.out.println("\nWhat would you like to do today? \n(1) add a new Camper \n(2) sign up camper for a session \n(3) General Information \n(4) FAQ’s \n(5) Logout");
    }

    /** 
     * displays the main menu with the counselor's options
     */
    private void displayCounselorMenu(){
        displayUserMenu();
        System.out.println("(6) give Strike to a Camper");
        System.out.println("(7) print roster for cabin");
        System.out.println("(8) print next weeks information");
        System.out.println("(9) print Schedule");
    }

    /**
     * displays the main menu with the director's options
     */
    private void displayDirectorMenu(){
        displayUserMenu();
        System.out.println("(6) give Strike to a Camper");
        System.out.println("(7) create a new Camp");
    }

    /**
     * 
     * @param commands the number of possible choices
     * @return int showing users decison or -1 if the choice was invalid
     */
    private int getUserCommand(int commands){
        int choice = scanner.nextInt();
        if(choice > 0 && choice <= commands){
            return choice;
        }
        return -1;
    }

    /**
     * creates a new user account
     */
    private void createAccount(){
        while(true){
            System.out.println("Please choose an account type: (C)ounselor or (P)arent or press (X) to cancel");
            scanner.nextLine();
            String accountType = scanner.nextLine();
            if(accountType.equalsIgnoreCase("x")){
                break;
            }
            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();
            String username;
            while(true){
                System.out.print("Enter a username: ");
                username = scanner.nextLine();
                if(!campSystem.checkUsernameAvailability(username)){
                    System.out.println("Username already taken. Please choose a different one.");
                    continue;
                }
                break;
            }
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();
            System.out.print("Enter your email address: ");
            String email = scanner.nextLine();
            System.out.print("Enter your phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter your preferred method of contact (phone or email): ");
            String preferredContact = scanner.nextLine();
            System.out.print("Enter your birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            System.out.print("Enter your address: ");
            String address = scanner.nextLine();
            if(accountType.equalsIgnoreCase("c")){
                System.out.println("Enter a short biography: ");
                String biography = scanner.nextLine();
                Medical medicalInfo = getMedicalInfo();
                campSystem.createCounselorAccount(username, password, email, lastName, firstName, phoneNumber, preferredContact, birthday, address, biography, medicalInfo);
            }else{
                campSystem.createUserAccount(username, password, email, lastName, firstName, phoneNumber, preferredContact, birthday, address);
            }
            break;
        }
        
    }

    /**
     * lets user log into preexisting account
     * @return The type of account of the user.
     */
    private Type login(){
        while(true){
            System.out.print("Username: ");
            scanner.nextLine();
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();
            int loginResult = campSystem.login(username, password);
            Type loginType = Type.COUNSELOR;
            if(loginResult == -1){
                System.out.println("Username or password invalid. Try again.");
            }else{
                switch(loginResult){
                    case 1:
                        loginType = Type.PARENT;
                        break;
                    case 2:
                        loginType = Type.DIRECTOR;
                        break;
                }
                return loginType;
            }
            
        }
    }

    /**
     * adds camper to users account
     */
    private void addCamper(){
        while(true){
            System.out.print("Enter Campers first name: ");
            scanner.nextLine();
            String firstName = scanner.nextLine();
            System.out.print("Enter Campers last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter Campers birthday (format: yyyy-mm-dd): ");
            String birthdayString = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(birthdayString);
            Boolean more = true;
    
            while(more){
                System.out.println("\nEnter the following information about the EMERGENCY CONTACT:");
                Contact emergencyContact = createContact();
    
                System.out.println("Would you like to add another emergency contact? (True/False)");
                more = scanner.nextBoolean();
            }
            System.out.println("\nEnter the following information about the EMERGENCY CONTACT:");
            Contact emergencyContact = createContact();

            Medical medicalInfo = getMedicalInfo();
            
            System.out.print("\nWould you like to add general notes about the camper? (Y/N) ");
            String yn = scanner.nextLine();
            ArrayList<String> notes = new ArrayList<String>();
            while(yn.equalsIgnoreCase("y")){
                System.out.println("Enter note: ");
                String note = scanner.nextLine();
                notes.add(note);
                System.out.println("Would you like to add more notes about the camper? (Y/N) ");
                yn = scanner.nextLine();
            }
            
            askToAcceptWaiver();

            if(campSystem.addCamper(firstName, lastName, birthday, emergencyContact, medicalInfo, notes) == true){   // add guardian details?
                System.out.println(firstName + " " + lastName+ " has been successfully signed up as a Camper");
                break;
            }


            System.out.println("\nInvalid input. Start over.");
        }
    }

    /**
     * asks for the medical info that is needed to register a camper or a counselor
     * @return the medical info
     */
    private Medical getMedicalInfo(){
        System.out.println("\nEnter the following information about the DOCTOR");
        Contact doctorContact = createContact();
        System.out.println("Would you like to add Medications? (All necessary medications to be taken during camp must be added) Y/N");
        String yn = scanner.nextLine();
        ArrayList<Medication> medications = new ArrayList<Medication>();
        while(yn.equalsIgnoreCase("y")){
            System.out.println("\nWhat is the name of the medication?");
            String medName = scanner.nextLine();
            System.out.println("What time of day does the medicine have to be taken?");
            String medTime = scanner.nextLine();
            Medication medication = new Medication(medName, medTime);
            medications.add(medication);
            System.out.println("\nDo you want to add another medication? (Y/N)");
            yn = scanner.nextLine();
        }
        //for adding allergies
        System.out.println("\nWould you like to add any Allergies? (Y/N)");
        yn = scanner.nextLine();
        ArrayList<String> allergies = new ArrayList<String>();
        while(yn.equalsIgnoreCase("y")){
            System.out.println("\nWhat is the campers allergy to?");
            String allergy = scanner.nextLine();
            allergies.add(allergy);
            System.out.println("\nDo you want to add another allergy? (Y/N)");
            yn = scanner.nextLine();
        }
        Medical medicalInfo = new Medical(doctorContact);
        medicalInfo.addAllergies(allergies);
        medicalInfo.addMedications(medications);
        return medicalInfo;
    }

    /**
     * creates a new contact 
     * @return the new contact
     */
    private Contact createContact(){
        Contact contact;

        System.out.print("Enter the first name: "); 
        String firstName = scanner.nextLine();
        System.out.print("Enter the last name: "); 
        String lastName = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the phone number: ");
        String phoneNumber = scanner.nextLine();
        contact = new Contact(firstName, lastName, phoneNumber, address);
        
        return contact;
    }

    /**
     * prints general info for the camp
     */
    private void printGeneralInformation(){
        String information = campSystem.getUserInformation();
        System.out.println(information);
        ArrayList<Session> avSessions = campSystem.findAvailableSessions();
        System.out.println("Currently available sessions: ");
        for(Session s : avSessions){
            System.out.println(" - " + s.getStartDate() + " - " + s.getEndDate() + ", Theme: " + s.getTheme());
        }
        System.out.println("> The address of the camp is: 3738 Rofferd Drive, TN 42984 ");
        System.out.println("> The camps phone number is \"593-783-2849\"");
        System.out.println("> The camps email is \"campfuntimes@gmail.com \". Feel free to reach out with questions!");
        System.out.println("> Please arrive to you campers session within 1 hour of their call time. Parking will be availble, but limited.");
        System.out.println("> This camp has Lake Judomo bordering it and is an outdoors based camp. Sunscreen and Mosqito sprey is provided");
    }

    /**
     * prints FAQ's 
     */
    private void printFAQ(){
        System.out.println("Can my camper stay for multiple sessions? \n> Yes, guardians may sign up campers  for any number of sessions through their home menu!");
        System.out.println("Should my camper bring any specific items? \n> Campers will need a vairety of items, such as \n Watersport Essentails: swimsuit (2 or 3), goggles, towel, sunscreen \n > Daily: bedtime essentails, multiple shorts, shirts, socks, shower and bathroom products \n > Special event outfit: look at your sessions spirit night and bring relating accessories  ");
        System.out.println("What if my Camper does not want to or cannot participate in an activity? \n> Although participating is encouraged, campers are allowed to sit out in any activity. \nDepending on the number of kids sitting out, they will either have to sit out and watch the activity or will be escorted to another mini activity like arts & crafts or a game");
        System.out.println("What are some regular camp activities? \n> Swimming, archery, canoeing, overnight camp out by lake night, soccer, flag football, gymnastics, tag, camping survival strategies, reptile house, etc.");
        System.out.println("What kinds of payment do you accept?\n> We only accept Credit/Debit card and echecks");
        System.out.println("Can you sign your kid for more then one session? \n> Yes, you can sign campers up for multiple on your homepage ");
        System.out.println("If I have an emergency, can I pick up my camper from camp early? \n> Yes, as the campers guardian, you can pick up your camper from camp at any time.\n You may not get recompensated and will have to fill out paperwork and pack up your camper upon arrival");
        System.out.println("How many supervisors are there watching the campers? \n> We have one director for the camp and sustain a 1 counselor : 8 camper ratio");
        //activities
    }
   
    /**
     * signs up a camper for a session
     */
    private void sessionSignup(){
        System.out.println("\nEnter the first name of the camper you would like to register for a session: ");
        scanner.nextLine();
        String firstName = scanner.nextLine();
        System.out.println("\nEnter the last name of the camper you would like to register for a session: ");
        String lastName = scanner.nextLine();
        Camper camper = campSystem.findCamperByName(firstName, lastName);
        Session session = chooseSession(camper);
        if(session == null){
            return;
        }
        int signupResult = campSystem.sessionSignup(camper, session);
        if(signupResult == -1){
            System.out.println("No cabin available for your camper's age!");
            return;
        }
        System.out.println(firstName + lastName + "was successfully signed up for the Session in Cabin " + signupResult);
    }

    /**
     * lets a camper pick a session out of available ones
     * @param camper the camper to be signed up for a session
     * @return the session they chose to join
     */
    private Session chooseSession(Camper camper){
        ArrayList<Session> options = campSystem.findAvailableSessions();
        if(options.size() == 0){
            System.out.println("No sessions available for this camper!");
            return null;
        }
        int counter = 1;
        for(Session s : options){
            System.out.println(counter + ") " + "Start: " + s.getStartDate() + ", End: " + s.getEndDate() + ", Theme: " + s.getTheme());
            counter++;
        }
        System.out.print("Choose a session by selecting a number: ");
        int selection = scanner.nextInt();
        return options.get(selection - 1);
    }
    /* Themes decriptions for when we ask director
     * themes.concat("Session 1: Hawaiin - A week of Luau's with fresh atmophere and food, flowers galour, and dance \n");
        themes.concat("Session 2: Rockstars - Celebrate some classic rockstars in a week of dancing, playing music, and a talent show \n");
        themes.concat("Session 3: Ninja Warriors - Interested in Challenge courses, rockclimbing, and ninja karate lessons? You found the place! \n");
        themes.concat("Session 4: Can you Survive in the woods? - come test your knowledge of commmon survival skills and camp out with us in our premium tents  ");
        themes.concat("Session 5: Halloween");
        themes.concat("Session 6: The Animal Kingdom");
        themes.concat("Session 7: Heros vs Villains");
        themes.concat("Session 8: the Wild Wild West ");
        themes.concat("Session 9: Smores ");
     */



    /**
     * prints Waiver that must be signed in order to register a camper
     */
    private void askToAcceptWaiver(){

        while(true){
            System.out.println("By signing this Waiver and Release of Liability, with full appreciation of the risk involved, "
            + "on my own behalf and on behalf of my child(ren), I hereby voluntarily release and forever discharge the camp "
            + "from any and all legal or financial responsibility for any personal injury, disability, illness, damage, medical expense or death, arising from "
            + "or related to my child(ren)'s participation in Summer Camp. I agree, for myself and my child(ren), not to "
            + "make any type of legal or equitable claim on the camp "
            + "with respect to any injury I or my child(ren) may suffer, whether or not it "
            + "arises through the negligence, omission, default or other action of anyone affiliated with the camp, "
            + "including other campers. I further agree that if any such claim is made, I will indemnify and defend the "
            + "camp with respect to any such claim, injury or damage.");
            System.out.println("The camp cannot be attended without accepting this waiver. Type \"yes\" to accept the waiver. ");
            if(scanner.nextLine().equalsIgnoreCase("yes")){
                break;
            }
        }
    }

    /** 
     * gives a strike to a camper
     */
    private void giveStrike(){
        System.out.print("Enter campers first name: ");
        String firstName = scanner.next();
        System.out.print("Enter campers last name: ");
        String lastName = scanner.next();
        System.out.print("Enter the reason for the strike: ");
        String reason = scanner.next();
        campSystem.giveStrike(firstName, lastName, reason);
        System.out.println("Strike given to Camper " + firstName + lastName);
    }

    /**
     * creates a new camp and sets up sessions and cabins with the director's input
     */
    private void createCamp(){
        System.out.print("Enter number of Sessions: ");
        int numberSessions = scanner.nextInt();
        scanner.nextLine();
        for(int i = 1; i <= numberSessions; i++){
            System.out.println("Enter theme for Session " +i+ ": ");
            String theme = scanner.nextLine();
            System.out.println("Enter short description for Session " +i+ ": ");
            theme += ": " + scanner.nextLine();
            System.out.println("Enter start date (format: yyyy-mm-dd): ");
            String start = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(start);
            System.out.println("Enter end date (format yyyy-mm-dd): ");
            String end = scanner.nextLine();
            LocalDate endDate = LocalDate.parse(end);
            campSystem.createSession(startDate, endDate, theme);
        }
        System.out.println("Enter number of Cabins: ");
        int numberCabins = scanner.nextInt();
        for(int i = 1; i <= numberCabins; i++){
            System.out.println("Enter lowest age for the " + i + ". cabin: ");
            int minCabinAge = scanner.nextInt();
            System.out.println("Enter highest age for the " + i + ". cabin: ");
            int maxCabinAge = scanner.nextInt();
            Cabin newCabin = new Cabin(minCabinAge, maxCabinAge);
            campSystem.addCabinToSessions(newCabin);
        }
    }
    /**
     * print all the people in one session
     */
    private void printRoster(){
        System.out.println(campSystem.listSessions());
        System.out.println("For which session would you like the roster printed? Enter session number: ");
        int sessionNr = scanner.nextInt();
        campSystem.printRoster(sessionNr);
    }
    /**
     * prints the sessions info
     * ex. dates, theme
     */
    private void printWeekInfo(){
        System.out.println(campSystem.listSessions());
        System.out.println("For which session would you like the information printed? Enter session number: ");
        int sessionNr = scanner.nextInt();
        campSystem.printWeekInfo(sessionNr);
    }
    /**
     * prints out generic schedule for the camp
     */
    private void printSchedule(){
        System.out.println(campSystem.listSessions());
        System.out.println("For which session would you like the schedule printed? Enter session number: ");
        int sessionNr = scanner.nextInt();
        campSystem.printSchedule(sessionNr);
    }
    

     /**
     * logs user out of the system
     */
    private void logout(){
        System.out.println("Goodbye!");
        campSystem.logout();
    }

    /**
     * The main method!
     * @param args
     */
    public static void main(String[] args){
        UI ui = new UI();
        ui.run();
    }
    
}
