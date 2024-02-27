import drivers.DriverSingleton;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.*;
import utils.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import static utils.Log.endTest;
import static utils.Log.startTest;


public class Tests {
    AppiumDriver driver;
    ShowcaseScreenPage showcaseScreenPage;
    LoginPage loginPage;
    ItemScreenPage itemScreenPage;
    CartScreenPage cartScreenPage;
    AddressScreen addressScreen;
    PurchaseScreen purchaseScreen;

    @BeforeSuite
    public void initializeObjects() throws MalformedURLException {

        // Initialize all POM / driver + implicit wait
        DriverSingleton.getInstance(FrameworkProperties.getProperty(Constants.DEVICE));
        driver = DriverSingleton.getDriver();
        Utils.setImplicitWait(driver);
        showcaseScreenPage = new ShowcaseScreenPage();
        itemScreenPage = new ItemScreenPage();
        cartScreenPage = new CartScreenPage();
        addressScreen = new AddressScreen();
        purchaseScreen = new PurchaseScreen();

        // Initialize Logger
        Log.getLogData(Log.class.getName());
    }

    @Test(testName = "Verify Purchase Flow")
    public void verifyPurchaseFlow() throws MalformedURLException {
        initializeObjects();
        List<WebElement> inventoryItems;
        List<String> cartItemsTitle;
        List<String> itemListTitles = new ArrayList<>();

        try {

            startTest("Verify Purchase Flow");

            Log.info("Log user");
            loginPage.logUser(Utils.getUserName(), Utils.getUserPassword());
            Log.debug("Login Screen Page");

            Log.info("Get all items on showcase page");
            inventoryItems = showcaseScreenPage.getAllInventoryItems();
            Log.debug("Showcase Screen Page");

            Log.info("Go to item view");
            inventoryItems.get(0).click();

            Log.info("Add item to cart");
            itemScreenPage.addItemToCart();
            Log.debug("Item Screen Page");

            // Add item to a list for asserting it later with the items in checkout cart
            itemListTitles.add(Utils.getItemTitle(inventoryItems.get(0)));

            Log.info("Go back to showcase page");
            itemScreenPage.backToProducts();

            Log.debug("Showcase Screen Page");
            Log.info("Go to item view");
            inventoryItems.get(1).click();

            Log.info("Add item to cart");
            itemScreenPage.addItemToCart();
            Log.debug("Showcase Screen Page");

            // Add item to a list for asserting it later with the items in checkout cart
            itemListTitles.add(Utils.getItemTitle(inventoryItems.get(1)));

            Log.info("Go to cart");
            itemScreenPage.goToCart();
            Log.debug("Cart Screen Page");

            Log.info("Get cart items titles");
            cartItemsTitle = cartScreenPage.getCartItemsTitle();

            Log.info("Compare lists between added items and the ones present in the cart");
            Utils.compareLists(itemListTitles, cartItemsTitle);

            Log.info("Go to checkout");
            cartScreenPage.goToAddress();
            Log.debug("Address Screen");

            Log.info("Fill all the fields and go to Confirm Purchase Screen");
            addressScreen.fillAddress();
            addressScreen.continueToCheckout();

            Log.info("Assert purchase");
            purchaseScreen.confirmPurchase();
            Log.debug("Purchase Screen");
            purchaseScreen.assertPurchase();

            endTest("Verify Purchase Flow");

        } catch (Exception e) {
            Log.error("Error at " + e);
        }
    }

    @AfterSuite
    public void closeObjects() {
        DriverSingleton.closeObjectInstance();
    }
}