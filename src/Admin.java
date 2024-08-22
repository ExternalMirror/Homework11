import java.io.*;
import java.util.*;

public class Admin extends AdminSignupLogin {
    public static final String FOLDER_PATH = "/Users/user/Desktop/globalDictionary";

    public static void writeWordsToDictionary(String firstLangName, String secondLangName, String firstWord, String secondWord) {
        File folder = new File(FOLDER_PATH);
        String fileName = firstLangName + "-" + secondLangName + ".txt";
        File file = new File(folder, fileName);


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(firstWord + "--" + secondWord);
            bw.newLine();
            System.out.println("Translation successfully added to dictionary");
        } catch (IOException ex) {
            System.err.println("An error occurred while writing to the dictionary file");
            ex.printStackTrace();
        }
    }

    public static void removeWordsFromDictionary(String firstLangName, String secondLangName, String firstWord, String secondWord) {
        String fileName = firstLangName + "-" + secondLangName + ".txt";
        File file = new File(FOLDER_PATH, fileName);

        //1.Read file add inside temporary String and get data separated
        //2.separate words pair for comparison
        //3. then insert value of String inside file
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            boolean dataRemoved = false;
            while (br.ready()) {
                String beRemovedData = br.readLine();
                String[] beRemovedDataParse = beRemovedData.split("--");
                //being together didn't work so i separated for comparison
                String firstRemovedWordPart = beRemovedDataParse[0].toLowerCase().trim();
                String secondRemovedWordPart = beRemovedDataParse[1].toLowerCase().trim();
                // used equalsignorecase for comparison, used ignore for possibility
                if (!firstRemovedWordPart.equalsIgnoreCase(firstWord) || !secondRemovedWordPart.equalsIgnoreCase(secondWord)) {
                    // append data
                    content.append(beRemovedData).append("\n"); //new paragraph
                } else {
                    dataRemoved = true;
                }
            }
            //inserting new modified value
            if (dataRemoved) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write(content.toString()); //putting new data into file
                }
                System.out.println("Translation has been removed from dictionary.");
            } else {
                System.out.println("Translation has not been not found in dictionary.");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
//        while (br.ready()) {
//            String beRemovedData = br.readLine();
//            String[] beRemovedDataParse = beRemovedData.split("--");
//            content.append(beRemovedDataParse[0].toLowerCase()).append("--").append(beRemovedDataParse[1].toLowerCase());
//            if (!content.equals(firstWord + "--" + secondWord)) {
//                content.append(beRemovedData).append("\n");
//            } else {
//                removed = true;
//            }
//        }
    }


    public static void changeWordsInDictionary(String firstLangName, String secondLangName, String oldFirstWord, String oldSecondWord, String newFirstWord, String newSecondWord) {
        String fileName = firstLangName + "-" + secondLangName + ".txt";
        File file = new File(FOLDER_PATH, fileName);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder content = new StringBuilder();
            boolean dataChanged = false; //still not changed
            while (br.ready()) {
                String line = br.readLine();
                String[] words = line.split("--");
                String firstChangedWordPart = words[0].toLowerCase().trim();
                String secondChangedWordPart = words[1].toLowerCase().trim();

                if (firstChangedWordPart.equalsIgnoreCase(oldFirstWord) && secondChangedWordPart.equalsIgnoreCase(oldSecondWord)) {
                    content.append(newFirstWord).append("--").append(newSecondWord).append("\n");
                    dataChanged = true;
                } else {
                    content.append(line).append("\n");
                }
            }
            if (dataChanged) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    bw.write(content.toString());
                }
                System.out.println("Translation has been updated in the dictionary.");
            } else {
                System.out.println("Translation to be changed was not found in the dictionary.");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
