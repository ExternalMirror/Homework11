import java.io.*;
import java.util.*;

class User extends UserSignupLogin {
    public static final String FOLDER_PATH = "/Users/user/Desktop/globalDictionary";
    public static String translation(String word, String firstLang, String secondLang, int dicType) {
        File folder = new File(FOLDER_PATH);
        String fileName = firstLang + "-" + secondLang + ".txt";
        File file = new File(folder, fileName);

        String result = null;
        word = word.toLowerCase();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Map<String, String> map = new HashMap<>();
            while (br.ready()) {
                String dicData = br.readLine();
                String[] dicDataParse = dicData.split("--");
                switch (dicType) {
                    case 1:
                        map.put(dicDataParse[0].toLowerCase(), dicDataParse[1].toLowerCase());
                        break;
                    case 2:
                        map.put(dicDataParse[1].toLowerCase(), dicDataParse[0].toLowerCase());
                        break;
                }
            }
            result = map.getOrDefault(word, "Translation not found");
        } catch (FileNotFoundException e) {
            System.err.println("Dictionary file not found: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
