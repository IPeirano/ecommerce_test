package pages;

import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static utils.Constants.EXPLICIT_WAIT_TIME;

public class CartScreenPage {
    private AppiumDriver driver;
    private final WebDriverWait wait;

    public CartScreenPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME));
    }

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "cart_item")
    private List<WebElement> cartItems;

    public void goToAddress() throws Exception {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            checkoutButton.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    public List<String> getCartItemsTitle() {
        List<String> titles = new ArrayList<>();

        for (WebElement cartItem : cartItems) {
            WebElement title = cartItem.findElement(By.id("title"));
            titles.add(title.getText());
        }
        return titles;
    }
}
