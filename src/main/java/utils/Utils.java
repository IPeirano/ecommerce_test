package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Utils {
    public static class CapabilitiesReader {
        public static DesiredCapabilities getDefaultCapabilities() {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, FrameworkProperties.getProperty("device"));
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, FrameworkProperties.getProperty("automation_name"));
            caps.setCapability("avdLaunchTimeout", Integer.parseInt(FrameworkProperties.getProperty("avd_launch_timeout")));
            return caps;
        }

        public static DesiredCapabilities getAndroidCapabilities(String deviceName) {
            String prefix = "android_" + deviceName.toLowerCase().replace(" ", "_");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, FrameworkProperties.getProperty(prefix + ".platform_name"));
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, FrameworkProperties.getProperty(prefix + ".device_name"));
            caps.setCapability(MobileCapabilityType.UDID, FrameworkProperties.getProperty(prefix + ".udid"));
            return caps;
        }

        public static DesiredCapabilities getIOSCapabilities(String deviceName) {
            String prefix = "ios_" + deviceName.toLowerCase().replace(" ", "_");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, FrameworkProperties.getProperty(prefix + ".platform_name"));
            caps.setCapability(MobileCapabilityType.UDID, FrameworkProperties.getProperty(prefix + ".udid"));
            caps.setCapability(MobileCapabilityType.APP, FrameworkProperties.getProperty("app_path_ios")); // Adjust as needed
            return caps;
        }
    }

    public static String getUserName() {
        return System.getenv("user_name");
    }

    public static String getUserPassword() {
        return System.getenv("user_password");
    }

    public static void setImplicitWait(AppiumDriver driver) {
        Duration implicitWaitDuration = Duration.ofSeconds(Constants.IMPLICIT_WAIT_TIME);
        driver.manage().timeouts().implicitlyWait(implicitWaitDuration.toSeconds(), TimeUnit.SECONDS);
    }

    public static String getItemTitle(WebElement item) {
        WebElement titleElement = item.findElement(By.id("title"));
        return titleElement.getText();
    }

    public static void compareLists(List<String> firstList, List<String> secondList) throws Exception {
        if (firstList.size() != secondList.size()) {
            throw new Exception("Lists do not have the same size");
        }
        if (!(firstList.containsAll(secondList) && secondList.containsAll(firstList))) {
            throw new Exception("Lists do not contain the same elements");
        }
    }

    public static String[] getRandomUserFromCSV() {

        // Retrieve the system-specific file separator and format the path accordingly
        String path = "data/user_mock_data.csv";
        String separator = File.separator;
        String[] pathComponents = path.split("/");
        String formattedPath = String.join(separator, pathComponents);

        try {
            List<String> lines = Files.readAllLines(Paths.get(formattedPath));

            if (lines.size() > 1) {
                Random random = new Random();
                int randomIndex = random.nextInt(lines.size() - 1) + 1; // Exclude the header
                String randomUserLine = lines.get(randomIndex);

                return randomUserLine.split(",");
            } else {
                return null;
            }
        } catch (IOException e) {
            Log.error("Error at " + e);
            return null;
        }
    }
}

