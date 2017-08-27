package framework;

import java.io.*;
import java.util.Properties;

public class DataFromProp {

    public static String readDataFromFileByKey(final String fileName, final String keyValue) {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(
                    new File("src/test/java/cars/models/" + fileName + ".properties")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return props.getProperty(keyValue);
    }


}
