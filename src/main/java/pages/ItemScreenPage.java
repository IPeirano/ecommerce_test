package pages;

import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;

import static utils.Constants.EXPLICIT_WAIT_TIME;

public class ItemScreenPage {
    private AppiumDriver driver;
    private final WebDriverWait wait;

    public ItemScreenPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCart;

    @FindBy(id = "item-title")
    private WebElement itemTitle;

    @FindBy(id = "back-to-products")
    private WebElement backToProducts;

    @FindBy(id = "shopping-cart")
    private WebElement cartButton;

    public void addItemToCart() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(addToCart));
            addToCart.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    public void backToProducts() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(backToProducts));
            backToProducts.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    public void goToCart() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(cartButton));
            cartButton.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }
}
