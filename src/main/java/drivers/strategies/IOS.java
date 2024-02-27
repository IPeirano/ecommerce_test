package drivers.strategies;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.FrameworkProperties;
import utils.Utils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class IOS implements DriverStrategy {
    @Override
    public AppiumDriver setStrategy() throws MalformedURLException {
        DesiredCapabilities defaultCaps = Utils.CapabilitiesReader.getDefaultCapabilities();
        DesiredCapabilities iosCaps = Utils.CapabilitiesReader.getIOSCapabilities("iPhone 13");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.merge(defaultCaps);
        caps.merge(iosCaps);
        String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + FrameworkProperties.getProperty("app_path_ios");
        caps.setCapability(MobileCapabilityType.APP, appUrl);

        URL url = new URL(FrameworkProperties.getProperty("appium_url"));

        // Returns the IOSDriver with the desired capabilities
        return new IOSDriver(url, caps);
    }
}
