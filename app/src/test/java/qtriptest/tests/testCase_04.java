package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

public class testCase_04 {
    public static RemoteWebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void createDriver() throws MalformedURLException {
        DriverSingleton driverSingleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = driverSingleton.getDriver();
    }

    @Test(description = "Verify that booking history can be viewed", dataProvider = "test",
            dataProviderClass = DP.class, priority = 4, groups = "Reliability Flow")
    public static void TestCase04(String userName, String password, String dataset1,
            String dataset2, String dataset3) throws InterruptedException {
        Boolean status;
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AdventurePage adventurePage = new AdventurePage(driver);
        AdventureDetailsPage adventureDetailsPage = new AdventureDetailsPage(driver);
        HistoryPage historyPage = new HistoryPage(driver);


        homePage.gotoHomePage();
        homePage.clickRegister();
        registerPage.registerNewUser(userName, password,password, true);
        loginPage.performLogin(registerPage.lastGeneratedUsername, password);

        String str1[] = dataset1.split(";");
        List<String> arr1 = new ArrayList<String>();
        arr1 = Arrays.asList(str1);
        String city1 = arr1.get(0);
        String adventure1 = arr1.get(1);
        String name1 = arr1.get(2);
        String date1 = arr1.get(3);
        String count1 = arr1.get(4);

        homePage.searchCity(city1);
        homePage.selectCity(city1);
        adventurePage.selectAdventure(adventure1);
        adventureDetailsPage.setAdventureDetails(name1, date1, count1);
        Thread.sleep(2000);
        homePage.gotoHomePage();
        Thread.sleep(2000);

        String str2[] = dataset2.split(";");
        List<String> arr2 = new ArrayList<String>();
        arr2 = Arrays.asList(str2);
        String city2 = arr2.get(0);
        String adventure2 = arr2.get(1);
        String name2 = arr2.get(2);
        String date2 = arr2.get(3);
        String count2 = arr2.get(4);

        homePage.searchCity(city2);
        homePage.selectCity(city2);
        adventurePage.selectAdventure(adventure2);
        adventureDetailsPage.setAdventureDetails(name2, date2, count2);
        Thread.sleep(2000);
        homePage.gotoHomePage();
        Thread.sleep(2000);

        String str3[] = dataset3.split(";");
        List<String> arr3 = new ArrayList<String>();
        arr3 = Arrays.asList(str3);
        String city3 = arr3.get(0);
        String adventure3 = arr3.get(1);
        String name3 = arr3.get(2);
        String date3 = arr3.get(3);
        String count3 = arr3.get(4);

        homePage.searchCity(city3);
        homePage.selectCity(city3);
        adventurePage.selectAdventure(adventure3);
        adventureDetailsPage.setAdventureDetails(name3, date3, count3);
        Thread.sleep(2000);
        homePage.gotoHomePage();
        Thread.sleep(2000);

        homePage.history();
        Thread.sleep(2000);


        List<WebElement> allBookings =
                driver.findElements(By.xpath("//tbody[@id='reservation-table']/tr/th"));
        System.out.println("The number of rows in the table is " + allBookings.size());
        if (allBookings.size() == 3) {
            System.out.println("All bookings are displayed Successfully.!");
        } else {
            System.out.println("Failed to display all bookings");
        }
        Thread.sleep(2000);

        homePage.logOutUser();
    }

    @AfterTest
    public void quitDriver() {
        driver.quit();
        System.out.println("driver quit()");
    }
}

