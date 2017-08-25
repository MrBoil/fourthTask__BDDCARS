package framework;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class DataFromJson {
    private static String configForTestingPath = "src/test/resources/configForTesting.json";

    private static Object readJson(String whatDoYouWant, String filePath) {
        JSONParser parser = new JSONParser();
        try {
            FileReader fileReader = new FileReader(filePath);
            JSONObject json = (JSONObject) parser.parse(fileReader);
            return json.get(whatDoYouWant);

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getSiteURL() {
        return readJson("siteName", configForTestingPath).toString();
    }

    public static String getBrowserName() {
        return readJson("browser", configForTestingPath).toString();
    }

    public static Long getImplicityWaitTime() {
        return Long.valueOf(readJson("implicitlyWaitTime", configForTestingPath).toString()).longValue();
    }

    public static Long getSctiptTimeoutTime() {
        return Long.valueOf(readJson("scriptTimeoutTime", configForTestingPath).toString());
    }

    public static Long getWebDriverWaitTime() {
        return Long.valueOf(readJson("webDriverWaitTime", configForTestingPath).toString());
    }
}