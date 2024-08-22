import java.io.*;

public class FileToolMethods {
    public static final String FOLDER_PATH = "/Users/user/Desktop/globalDictionary";
    public static void fileNames(){
        File folder = new File(FOLDER_PATH);
        File[] files = folder.listFiles(); //in here put all file names

        if (files == null) {
            System.out.println("there are no files in folder");
            return;
        }

        for (File file : files) {
            String fileName = file.getName(); // get files names each

            if (fileName.contains("-")) { // conditions for ones with "in middle"
                System.out.println(fileName);

            }    }
    }

    public static boolean fileExistingChecker(String firstLangName, String secondLangName){
        File folder = new File(FOLDER_PATH);
        String fileName = firstLangName + "-" + secondLangName + ".txt";
        String reversedFilename = secondLangName + "-" + firstLangName + ".txt";
        File file = new File(folder, fileName);
        File reversedFile = new File(folder, reversedFilename);

        if (!file.exists() && !reversedFile.exists()) {
            return true; // there is no dictionary
        } else if (reversedFile.exists()) {
            System.out.println("Dictionary exists, but it's reversed between " + firstLangName + " and " + secondLangName);
            System.out.println("Please add reversed version, if you want to access");
            return true; // exist but reversed
        }
        return false; // non-existent
    }

    public static void createNewDictionary(String firstLangName, String secondLangName) {
        File folder = new File(FOLDER_PATH);
        String fileName = firstLangName + "-" + secondLangName + ".txt";
        String swappedFileName = secondLangName + "-" + firstLangName + ".txt";
        File file = new File(folder, fileName);
        File swappedFile = new File(folder, swappedFileName);

        if (file.exists() && swappedFile.exists()) {
            System.out.println("Dictionary already exists.");
        } else if (swappedFile.exists()) {
            System.out.println("Dictionary exists, but it's reversed between " + firstLangName + " and " + secondLangName);
            System.out.println("Please add the reversed version if you want to access it.");
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("New dictionary created: " + fileName);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
//    public static String FileContent(String firstLangName, String secondLangName) {
//        String fileName = firstLangName + "-" + secondLangName + ".txt";
//        File file = new File(FOLDER_PATH, fileName);
//        StringBuilder content = new StringBuilder();
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            while (br.ready()) {
//                content.append(br).append("\n");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return content.toString();
//    }
}
