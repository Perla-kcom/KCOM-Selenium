package kcom.library;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    protected Properties properties = null;
    protected InputStream inputStream = Configuration.class.getClassLoader().getResourceAsStream("config/rarstest.properties");

    public Configuration() throws IOException {

        properties = new Properties();
        properties.load(inputStream);
    }

    public String getTestEnvironment() {
        return properties.getProperty("url");
    }

    public String getUserName() {
        return properties.getProperty("user");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
