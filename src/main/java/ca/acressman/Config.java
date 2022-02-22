package ca.acressman;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static Properties getProperties() {
        try (InputStream input = Config.class.getResourceAsStream("/application.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        } catch (IOException ex) {
            throw new RuntimeException("Error loading properties", ex);
        }
    }
}
