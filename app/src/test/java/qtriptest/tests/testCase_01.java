package qtriptest.tests;

import qtriptest.DP;
import qtriptest.pages.HomePage;
import qtriptest.pages.LoginPage;
import qtriptest.pages.RegisterPage;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_01 {
    static RemoteWebDriver driver;

    @BeforeTest
    public static void createDriver() throws MalformedURLException {
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        driver.manage().window().maximize();
    }

    @Test(description = "Verify user registration -login -logout",dataProvider = "test",dataProviderClass = DP.class)
    public static void TestCase01(String userName, String password) throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        boolean status;
        // Navigate to HomePgae
        homePage.gotoHomePage();
        // Click on Register page
        homePage.clickRegister();
        // Verify that register page is displayed
        status = registerPage.validateRegisterPage();
        if (status) {
            System.out.print("Successfully navigated to registerPgae");
        } else {
            System.out.print("Failed to register to navigatee page");
        }
        // Register a User
        registerPage.registerNewUser(userName, password, password, true);


        // add the Thread.sleep here
        Thread.sleep(5000);
        // Verify that user is navigated to loginPage

        loginPage.navigateToLoginPage();
        status = loginPage.isLoginPageDisplayed();
        
        if (status) {
            System.out.println("Successfullt navigated to Login page");
        } else {
            System.out.println("failed to register to Login page:");
        }

        // Login created user
        loginPage.performLogin(registerPage.lastGeneratedUsername, password);
        Thread.sleep(5000);
        // Verify that user is logged in
        if (homePage.isUserLoggedIn()) {
            System.out.println("User logged in successfully");
        } else {
            System.out.println("Failed to Login");
        }

        // Click on the logout Button
        homePage.logOutUser();
        Thread.sleep(5000);

        // Verify that user is logged out
        if (!homePage.isUserLoggedIn()) {
            System.out.println("User is logged out successfully");
        } else {
            System.out.println("Failed-User is not logged out");
        }
    }

    @AfterSuite
    public static void quitDriver() {
        driver.quit();
    }


}

