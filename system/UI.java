package system;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Cyber Council
 */
public class UI {
    private Scanner scanner;
    private CampSystemFACADE campSystem;

    public UI(){
        scanner = new Scanner(System.in);
        campSystem = new CampSystemFACADE();
    }

    /**
     * main run for the program
     * calls for and saves all info from user
     */
    public void run(){
        System.out.println("Welcome to our camp website!");
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
                choice = getUserCommand(6);
            }else if(accountType == Type.DIRECTOR){
                displayDirectorMenu();
                choice = getUserCommand(7);
            }
                
            switch(choice){
                case '1':
                    addCamper();
                    break;
                case '2':
                    sessionSignup();
                    break;
                case '3':
                    printGeneralInformation();
                    break;
                case '4': 
                    printFAQ();
                    break;
                case '5':
                    logout();
                    stop = true;
                    break;
                case '6':
                    giveStrike();
                    break;
                case '7':
                    createCamp();
                    break;
                default:
                    System.out.println("Invalid input");
            }   
        } 
    }

    /**
     * display intro screen
     */
    private void displayIntroMenu(){
        System.out.println("Press (1) to sign in to your account");
        System.out.println("Press (2) to create a new account");
        System.out.println("Press (3) to quit");
    }

    /**
     * displays user menu
     */
    private void displayUserMenu(){ //for choices: create child acct, add child to session
        System.out.println("What would you like to do today? \n(1) add a new Camper \n(2) sign up camper for a session \n(3) General Information \n(4) FAQ’s (5) Logout");
    }

    private void displayCounselorMenu(){
        displayUserMenu();
        System.out.println("(6) give Strike to a Camper");
    }

    private void displayDirectorMenu(){
        displayCounselorMenu();
        System.out.println("(7) create a new Camp");
    }

    /**
     * 
     * @param commands users input
     * @return int showing users decison 
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
            System.out.print("Please choose an account type: (C)ounselor or (P)arent ");
            String accountType = scanner.nextLine();
            System.out.print("\nEnter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("\nEnter your last name: ");
            String lastName = scanner.nextLine();
            // how to verify account type?
            String username;
            while(true){
                System.out.print("\nEnter a username: ");
                username = scanner.nextLine();
                if(!campSystem.checkUsernameAvailability(username)){
                    System.out.println("Username already taken. Please choose a different one.");
                    continue;
                }
                break;
            }
            
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
            boolean result;
            if(accountType.equalsIgnoreCase("c")){
                System.out.println("Enter a short biography: ");
                String biography = scanner.nextLine();
                Medical medicalInfo = getMedicalInfo();
                result = campSystem.createCounselorAccount(username, password, email, lastName, firstName, phoneNumber, preferredContact, birthday, address, biography, medicalInfo);
            }else{
                result = campSystem.createUserAccount(username, password, email, lastName, firstName, phoneNumber, preferredContact, birthday, address);
            }
            if(result == true){
                System.out.println("\nAccount created successfully.");
                break;
            }
            System.out.println("\nInvalid input. Start over.");
        }
        
    }

    /**
     * lets user log into preexisting account
     */
    private Type login(){
        while(true){
            System.out.print("Username: ");
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

            Medical medicalInfo = getMedicalInfo();
            
            System.out.print("\n Would you like to add general notes about the camper? (Y/N) ");
            String yn = scanner.nextLine();
            ArrayList<String> notes = new ArrayList<String>();
            while(yn.equalsIgnoreCase("y")){
                System.out.print("\n Enter note: ");
                String note = scanner.nextLine();
                notes.add(note);
                System.out.print("\n Would you like to add more notes about the camper? (Y/N) ");
                yn = scanner.nextLine();
            }
            
            askToAcceptWaiver();

            if(campSystem.addCamper(firstName, lastName, birthday, emergencyContact, medicalInfo, notes) == true){   // add guardian details?
                System.out.println(firstName + lastName+ "has been successfully signed up as a Camper");
                break;
            }


            System.out.println("\nInvalid input. Start over.");
        }
    }

    private Medical getMedicalInfo(){
        System.out.println("\nEnter the following information about the DOCTOR");
        Contact doctorContact = createContact();
        System.out.println("\nWould you like to add Medications? (All necessary medications to be taken during camp must be added) Y/N");
        String yn = scanner.nextLine();
        ArrayList<Medication> medications = new ArrayList<Medication>();
        while(yn.equalsIgnoreCase("y")){
            System.out.print("\nWhat is the name of the medication");
            String medName = scanner.nextLine();
            System.out.print("\nWhat time of day does the medicine have to be taken? " );
            String medTime = scanner.nextLine();
            Medication medication = new Medication(medName, medTime);
            medications.add(medication);
            System.out.print("\nDo you want to add another medication? (Y/N) ");
            yn = scanner.nextLine();
        }
        //for adding allergies
        System.out.println("\nWould you like to add any Allergies? Y/N");
        yn = scanner.nextLine();
        ArrayList<String> allergies = new ArrayList<String>();
        while(yn.equalsIgnoreCase("y")){
            System.out.print("\nWhat is the campers allergy to?");
            String allergy = scanner.nextLine();
            allergies.add(allergy);
            System.out.print("\nDo you want to add another allergy? Y/N");
            yn = scanner.nextLine();
        }
        Medical medicalInfo = new Medical(doctorContact);
        medicalInfo.addAllergies(allergies);
        medicalInfo.addMedications(medications);
        return medicalInfo;
    }

    /**
     * create a new contact within the account- doctor, emerg contact
     * @return a new contact
     */
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

    /**
     * prints overall info
     */
    private void printGeneralInformation(){
        System.out.println("> The address of the camp is: 3738 Rofferd Drive, TN 42984 ");
        System.out.println("> The camps phone number is \"593-783-2849\"");
        System.out.println("> The camps email is \"campfuntimes@gmail.com \". Feel free to reach out with questions!");
        System.out.println("> Please arrive to you campers session within 1 hour of their call time. Parking will be availble, but limited.");
    }

    /**
     * prints FAQ's
     */
    private void printFAQ(){
        System.out.println("Can my camper stay for multiple sessions? \nYes, guardians may sign up campers  for any number of sessions through their home menu!");
        System.out.println("Should my camper bring any specific items? \nCampers will need a vairety of items, such as \n watersport essentails: swimsuit (2 or 3), goggles, towel, sunscreen \n Daily: bedtime essentails, multiple shorts, shirts, socks, shower and bathroom products \n Special event outfit: look at your sessions spirit night and bring relating accessories  ");
        System.out.println("What if my Camper does not want to or can not participate in an activity? \nAlthough participating is encouraged, campers are allowed to sit out in any activity. \nDepending on the number of kids sitting out, they will either have to sit out and watch the activity or will be escorted to another mini activity like arts & crafts or a game");
        System.out.println("What are some regular camp activities? \nswimming, archery, canoeing, overnight camp out by lake night, soccer, flag football, gymnastics, tag, camping survival strategies, reptile house, etc.");
        System.out.println("What kinds of payment do you accept?\n We only accept Credit/Debit card and echecks");
        System.out.println("Can you sign your kid for more then one session? \nYes, you can sign campers up for multiple on your homepage ");
        System.out.println("If I have an emergency, can I pick up my camper from camp early? \n Yes, as the campers guardian, you can pick up your camper from camp at any time.\n You may not get recompensated and will have to fill out paperwork and pack up your camper upon arrival");
        //activities
    }
   
    /**
     * sign up for session
     */
    private void sessionSignup(){
        System.out.print("\nEnter the first name of the camper you would like to register for a session: ");
        String firstName = scanner.nextLine();
        System.out.print("\nEnter the last name of the camper you would like to register for a session: ");
        String lastName = scanner.nextLine();
        Camper camper = campSystem.findCamperByName(firstName, lastName);
        Session session = chooseSession(camper);
        if(session == null){
            return;
        }
        campSystem.sessionSignup(camper, session);
        System.out.println(firstName + lastName + "was successfully signed up for Session "+ session + "! ");
    }

    /**
     * lets a camper join a session
     * @param camper 
     * @return the session they chose to join
     */
    private Session chooseSession(Camper camper){
        int age = camper.getAge();
        ArrayList<Session> options = campSystem.findAvailableSessions(age);
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
     * prints Waiver that must be signed for camper
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
            System.out.print("The camp cannot be attended without accepting this waiver. Type \"yes\" to accept the waiver. ");
            if(scanner.nextLine().equalsIgnoreCase("yes")){
                break;
            }
        }
    }

    private void giveStrike(){
        System.out.print("Enter campers first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter campers last name: ");
        String lastName = scanner.nextLine();
        campSystem.giveStrike(firstName, lastName);
        System.out.println("Strike given to Camper " + firstName + lastName);
    }

    private void createCamp(){
        System.out.println("Enter number of Sessions: ");
        int numberSessions = scanner.nextInt();
        for(int i = 1; i <= numberSessions; i++){
            System.out.println("Enter theme for Session " +i+ ": ");
            String theme = scanner.nextLine();
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
            //todo: fix age group issue
        }
    }

     /**
     * choose to Logout
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
