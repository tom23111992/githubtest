package until;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurations extends Properties {

    public static String defaultUrl;
    private static Configurations properties;
    private static String configPath = "src/main/resources/config.properties";

    public static void setProperties() {

        InputStream is = null;
        properties = new Configurations();

        try {
            is = new FileInputStream(configPath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        init();
    }

    private static void init() {
        defaultUrl = properties.getProperty("url");
    }

}
