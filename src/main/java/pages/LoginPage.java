package pages;

import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Log;

public class LoginPage {
    private AppiumDriver driver;

    public LoginPage() {
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public void logUser(String user, String pass) throws Exception {
        try {
            username.sendKeys(user);
            password.sendKeys(pass);
            loginButton.click();
        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }
}
