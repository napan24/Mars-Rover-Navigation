// Use Case: We can use a Singleton pattern for a class that manages configuration
//  settings for an application.


class ConfigurationManager {
    private static ConfigurationManager instance;
    private Properties properties = new Properties();

    private ConfigurationManager() {
        // Load configuration properties from a file
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}

public class SingletonPatternDemo {
    public static void main(String[] args) {
        ConfigurationManager config = ConfigurationManager.getInstance();
        System.out.println(config.getProperty("appName"));
    }
}
