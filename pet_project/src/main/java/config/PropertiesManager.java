package config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class PropertiesManager {
    private static final String PROPERTY_FILE_NAME = "h2.properties";

    private Properties properties;

    public Properties getH2Properties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {
                properties.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

  /*  public Properties getH2Properties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {
                properties.load(stream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }*/


    public static Reader loadScript(String scriptName) {
        return new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(scriptName));
    }

}
