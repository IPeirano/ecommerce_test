package drivers;

import drivers.strategies.DriverStrategy;
import drivers.strategies.DriverStrategyImplementer;
import io.appium.java_client.AppiumDriver;
import java.net.MalformedURLException;
import static utils.Constants.DRIVER_NOT_FOUND;

public class DriverSingleton {
    private static DriverSingleton instance = null;
    private static AppiumDriver driver;

    private DriverSingleton(String strategy) throws MalformedURLException {
        if (strategy == null) {
            throw new IllegalArgumentException(DRIVER_NOT_FOUND);
        }
        Instantiate(strategy);
    }

    public void Instantiate(String strategy) throws MalformedURLException {
        if (strategy == null) {
            throw new IllegalArgumentException(DRIVER_NOT_FOUND);
        }
        DriverStrategy driverStrategy = DriverStrategyImplementer.selectedStrategy(strategy);
        driver = driverStrategy.setStrategy();
    }

    public static void getInstance(String driver) throws MalformedURLException {
        if (instance == null) {
            instance = new DriverSingleton(driver);
        }
    }

    public static void closeObjectInstance() {
        instance = null;
        if (driver != null) {
            driver.quit();
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}
