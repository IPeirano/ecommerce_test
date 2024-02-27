package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FrameworkProperties {
    private static String result = "";

    public static String getProperty(String key) {
        try {
            Properties properties = new Properties();
            String propFileName = Constants.PROP_FILE_NAME;

            // Obtain an inputStream to get the filename of framework properties
            InputStream inputStream = FrameworkProperties.class.getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException(Constants.FILE_NOT_FOUND);
            }

            result = properties.getProperty(key);

        } catch (IOException e) {
            Log.error("Error at " + e);
        }

        return result;
    }
}
