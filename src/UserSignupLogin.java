import java.io.*;

public class UserSignupLogin {

    static final String USER_ACCESS_FILE = "C:/Users/user/Desktop/globalDictionary/UserAccess/UserAccess.txt";

    public static void signUpUser(String username, String password) {
        if (doesAccountExist(username, password)) {
            System.out.println("This account already exists");
            return;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(USER_ACCESS_FILE, true))) {
            bw.write(username.trim() + "--" + password.trim() + "\n");
            System.out.println("Account has been created successfully.");
            bw.newLine();
        } catch (Exception ex) {
            System.err.println("Error occurred while creating admin account");
            ex.printStackTrace();
        }
    }


    public static void logInUser(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_ACCESS_FILE))) {
            while (br.ready()) {
                String accountData = br.readLine(); //putting info to accountData
                String[] dataParse = accountData.split("--");
                if (username.trim().equals(dataParse[0]) && password.trim().equals(dataParse[1])) {
                    System.out.println("Access granted");
                    return;
                }
            }
        } catch (Exception ex) {
            System.err.println("Error occurred while accessing admin account");
            ex.printStackTrace();
        }
        System.out.println("Login failed");
    }

    private static boolean doesAccountExist(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(USER_ACCESS_FILE))) {
            while (br.ready()) {
                String accountData = br.readLine(); //putting info to accountData
                String[] dataParse = accountData.split("--");
                if (username.trim().equals(dataParse[0]) && password.trim().equals(dataParse[1])) {
                    return true;
                }
            }
        } catch (Exception eX) {
            System.out.println("Error occurred while checking admin accounts");
            eX.printStackTrace();
        }
        return false;
    }
}