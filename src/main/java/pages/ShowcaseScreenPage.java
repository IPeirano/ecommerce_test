package pages;

import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ShowcaseScreenPage {
    private AppiumDriver driver;

    public ShowcaseScreenPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    public List<WebElement> getAllInventoryItems() {
        return driver.findElements(By.id("inventory_item"));
    }
}
