import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 if you are admin");
        System.out.println("Enter 2 if you are user");
        System.out.print("Access Type:");
        int asAccessType = sc.nextInt();
        sc.nextLine(); // consume newline
        switch (asAccessType) {
            case 1:
                System.out.println("Welcome to Admin panel");
                System.out.println("Do you want to Signup or Login");
                String authenticationOptionForAdmin = sc.nextLine();
                if (authenticationOptionForAdmin.equalsIgnoreCase("signup")) {
                    System.out.println("Please first enter your username and password for access");
                    System.out.print("Username:");
                    String adminUsername = sc.nextLine();
                    System.out.print("Password:");
                    String adminPassword = sc.nextLine();
                    AdminSignupLogin.signUpAdmin(adminUsername, adminPassword);
                } else if (authenticationOptionForAdmin.equalsIgnoreCase("login")) {
                    System.out.println("Please first enter your username and password for access");
                    System.out.print("Username:");
                    String adminUsername = sc.nextLine();
                    System.out.print("Password:");
                    String adminPassword = sc.nextLine();
                    AdminSignupLogin.logInAdmin(adminUsername, adminPassword);
                } else {
                    System.out.println("Invalid option");
                    return;
                }
                System.out.println("These are already created dictionaries:");
                FileToolMethods.fileNames();
                System.out.println("1. do you want to do modifications for existing dictionaries");
                System.out.println("2. do you want to create new dictionary");
                int modificateOrCreate = sc.nextInt();
                switch (modificateOrCreate) {
                    case 1:
                        System.out.println("Which modifications, do you want to do");
                        System.out.println("1.Add word in already existing dictionaries");
                        System.out.println("2.Remove word from certain dictionary");
                        System.out.println("3.Change word from certain dictionary");
                        int modification = sc.nextInt();
                        sc.nextLine();
                        switch (modification) {
                            case 1:
                                System.out.println("Please enter from which language to which you will add additional translations");
                                System.out.print("From:");
                                String firstLangName = sc.nextLine();
                                System.out.print("To:");
                                String secondLangName = sc.nextLine();
                                if (!FileToolMethods.fileExistingChecker(firstLangName, secondLangName)) {
                                    System.out.println("Now, Please enter words that you want to add");
                                    System.out.print("Enter first word:");
                                    String firstWord = sc.nextLine();
                                    System.out.print("Enter second word:");
                                    String secondWord = sc.nextLine();
                                    Admin.writeWordsToDictionary(firstLangName, secondLangName, firstWord, secondWord);

                                }
                                break;
                            case 2:
                                System.out.println("Enter the language names:");
                                System.out.print("From:");
                                String firstLangNameRemove = sc.nextLine();
                                System.out.print("To:");
                                String secondLangNameRemove = sc.nextLine();
                                if (!FileToolMethods.fileExistingChecker(firstLangNameRemove, secondLangNameRemove)) {
                                    System.out.println("now, translated word pair");
                                    System.out.print("Enter first word:");
                                    String removeFirstWord = sc.nextLine();
                                    System.out.print("Enter second word:");
                                    String removeSecondWord = sc.nextLine();
                                    Admin.removeWordsFromDictionary(firstLangNameRemove, secondLangNameRemove, removeFirstWord, removeSecondWord);
                                }
                                break;
                            case 3:
                                System.out.println("Enter the language names:");
                                System.out.print("From:");
                                String firstLangNameChange = sc.nextLine();
                                System.out.print("To:");
                                String secondLangNameChange = sc.nextLine();
                                if (!FileToolMethods.fileExistingChecker(firstLangNameChange, secondLangNameChange)) {
                                    System.out.println("now, translated word pair");
                                    System.out.print("Enter old first word:");
                                    String changeOldFirstWord = sc.nextLine();
                                    System.out.print("Enter old second word:");
                                    String changeOldSecondWord = sc.nextLine();
                                    System.out.print("Enter new first word:");
                                    String putNewFirstWord = sc.nextLine();
                                    System.out.print("Enter new second word:");
                                    String putNewSecondWord = sc.nextLine();
                                    Admin.changeWordsInDictionary(firstLangNameChange, secondLangNameChange, changeOldFirstWord, changeOldSecondWord, putNewFirstWord, putNewSecondWord);
                                }
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Please enter from which language to which dictionary you want to create");
                        System.out.print("From: ");
                        sc.nextLine();
                        String firstLangNameForDictionary = sc.nextLine();
                        System.out.print("To: ");
                        String secondLangNameForDictionary = sc.nextLine();
                        FileToolMethods.createNewDictionary(firstLangNameForDictionary, secondLangNameForDictionary);
                        break;
                }
                break;
            case 2:
                System.out.println("Welcome to User panel");
                System.out.println("Do you want to Signup or Login");
                String authenticationOptionForUser = sc.nextLine();
                if (authenticationOptionForUser.equalsIgnoreCase("signup")) {
                    System.out.println("Please first enter your username and password for access");
                    System.out.print("Username:");
                    String userUsername = sc.nextLine();
                    System.out.print("Password:");
                    String userPassword = sc.nextLine();
                    UserSignupLogin.signUpUser(userUsername, userPassword);
                } else if (authenticationOptionForUser.equalsIgnoreCase("login")) {
                    System.out.println("Please first enter your username and password for access");
                    System.out.print("Username:");
                    String userUsername = sc.nextLine();
                    System.out.print("Password:");
                    String userPassword = sc.nextLine();
                    UserSignupLogin.logInUser(userUsername, userPassword);
                } else {
                    System.out.println("Invalid option");
                    return;
                }
                System.out.println("These are translations, you can look up to");
                FileToolMethods.fileNames();
                System.out.println("Which translation do you want to search");
                System.out.print("First language:");
                String firstLang = sc.nextLine().toLowerCase();
                System.out.print("Second language:");
                String secondLang = sc.nextLine().toLowerCase();
                if (!FileToolMethods.fileExistingChecker(firstLang, secondLang)) {
                    System.out.println("Dictionary has been found. Proceeding...");
                    System.out.println("which Interpretation you want to use");
                    System.out.println("1.Normal");
                    System.out.println("2.Reversed");
                    System.out.print("Decision:");
                    int dicType = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter the word you want to translate:");
                    String word = sc.nextLine().toLowerCase();
                    String translationResult = User.translation(word, firstLang, secondLang, dicType);
                    System.out.println(translationResult);
                }
                break;
            default:
                System.out.println("Invalid option");
        }
    }
}