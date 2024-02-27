package drivers.strategies;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.FrameworkProperties;
import utils.Utils;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Android implements DriverStrategy {
    @Override
    public AppiumDriver setStrategy() throws MalformedURLException {
        DesiredCapabilities defaultCaps = Utils.CapabilitiesReader.getDefaultCapabilities();
        DesiredCapabilities androidCaps = Utils.CapabilitiesReader.getAndroidCapabilities("Galaxy S21");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.merge(defaultCaps);
        caps.merge(androidCaps);
        String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + FrameworkProperties.getProperty("apk_path");
        caps.setCapability(MobileCapabilityType.APP, appUrl);

        URL url = new URL(FrameworkProperties.getProperty("appium_url"));

        // Returns the AndroidDriver with the desired capabilities
        return new AndroidDriver(url, caps);
    }
}
