package pages;

import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;
import org.testng.Assert;

import java.time.Duration;

import static utils.Constants.EXPLICIT_WAIT_TIME;

public class PurchaseScreen {
    private AppiumDriver driver;
    private final WebDriverWait wait;

    public PurchaseScreen() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(id = "checkout_complete_container")
    private WebElement confirmationBox;

    public void confirmPurchase() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(finishButton));
            finishButton.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    public void assertPurchase() {
            wait.until(ExpectedConditions.visibilityOf(confirmationBox));
            Assert.assertTrue(confirmationBox.isDisplayed(), "Successful purchase!");
        }
}
