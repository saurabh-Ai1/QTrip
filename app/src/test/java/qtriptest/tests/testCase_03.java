package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventureDetailsPage;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HistoryPage;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class testCase_03 {

    public static RemoteWebDriver driver;

 //   @BeforeTest(alwaysRun = true)
    public void createDriver() throws MalformedURLException {
        DriverSingleton driverSingleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = driverSingleton.getDriver();
    }

 //   @Test(description = "Verify that adventure booking and cancellation works fine",
 //           dataProvider = "test", dataProviderClass = DP.class, priority = 3,
 //           groups = "Booking and Cancellation Flow")
    public static void TestCase03(String userName, String password, String SearchCity,
            String AdventureName, String GuestName, String Date, String Count)
            throws InterruptedException {
        Boolean status;
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdventurePage adventurePage = new AdventurePage(driver);
        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        HistoryPage historyPage = new HistoryPage(driver);


        homePage.gotoHomePage();
        homePage.clickRegister();
      //  registerPage.registerUser(NewUserName, Password, true);
      registerPage.registerNewUser(userName, password, password, true);
        loginPage.performLogin(registerPage.lastGeneratedUsername, password);
        homePage.searchCity(SearchCity);
        homePage.selectCity(SearchCity);
        adventurePage.selectAdventure(AdventureName);
        adventureDetailsPage.setAdventureDetails(GuestName, Date, Count);

        status = adventureDetailsPage.isAdventureBookingSuceessful();
        if (status) {
            System.out.println("Adventure booking is successfull.!");
        } else {
            System.out.println("Failed to Adventure booking.!");
        }
        // Thread.sleep(2000);

        homePage.history();
        Thread.sleep(2000);

        String transactionId = historyPage.transactionDetails();

        historyPage.cancelReservation();
        Thread.sleep(2000);

        driver.navigate().refresh();
        Thread.sleep(2000);

        status = historyPage.isTransactionRemoved();
        if (status) {
            System.out.println("Transaction ID is removed successfully.!");
        } else {
            System.out.println("Failed to remove Transaction ID.!");
        }
        Thread.sleep(1000);

        homePage.logOutUser();

    }

 //   @AfterTest
    public void quitDriver() {
        driver.quit();
        System.out.println("driver quit()");
    }
}


