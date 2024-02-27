package pages;

import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;
import utils.Utils;

import java.time.Duration;

import static utils.Constants.EXPLICIT_WAIT_TIME;

public class AddressScreen {
    private AppiumDriver driver;
    private final WebDriverWait wait;

    public AddressScreen() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    public void continueToCheckout() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(continueButton));
            continueButton.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    public void fillAddress() throws Exception {
        String[] user = Utils.getRandomUserFromCSV();
        try {
            firstName.sendKeys(user[0]);
            lastName.sendKeys(user[1]);
            postalCode.sendKeys(user[2]);
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }
}
