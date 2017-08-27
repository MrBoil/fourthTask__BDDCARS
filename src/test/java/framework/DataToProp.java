package framework;

import java.io.*;
import java.util.Properties;

public class DataToProp {

    public static void writeDataToProp(final String fileName, final String key, final String value) {
        File file = new File("src/test/java/cars/models/" + fileName + ".properties");
        InputStream in = null;
        OutputStream out = null;
        Properties props = new Properties();
        try {
            in = new FileInputStream(file);
            props.load(in);
            props.setProperty(key, value);
            out = new FileOutputStream(file);
            props.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readDataFromProp(final String fileName, final String keyValue) {
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
