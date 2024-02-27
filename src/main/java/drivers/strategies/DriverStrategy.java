package drivers.strategies;

import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;

public interface DriverStrategy {
    AppiumDriver setStrategy() throws MalformedURLException;
}
