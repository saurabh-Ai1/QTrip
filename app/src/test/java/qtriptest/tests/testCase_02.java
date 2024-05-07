package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
// import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testCase_02 {
    static RemoteWebDriver driver;

    @BeforeTest(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        // final DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setBrowserName(BrowserType.CHROME);
        // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        // driver.manage().window().maximize();
        DriverSingleton driverSingleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = driverSingleton.getDriver();
    }

  @Test(description = "Verify that Search and filters work fine", dataProvider = "test",
           dataProviderClass = DP.class,priority =2,groups = "Search and Filter flow")
    public static void TestCase02(String CityName, String Category_Filter, String DurationFilter,
            String ExpectedFilteredResults, String ExpectedUnFilteredResults)
            throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.gotoHomePage();
        Thread.sleep(2000);

        homePage.searchCity("oregoan");
        Thread.sleep(2000);
        Assert.assertTrue(homePage.isNoCityFound());
        homePage.searchCity(CityName);
        homePage.selectCity(CityName);
        AdventurePage adventures = new AdventurePage(driver);
        adventures.setFilterValue(DurationFilter);
        adventures.selectCategoryValue(Category_Filter);
        if (adventures.getResultCount() == Integer.parseInt(ExpectedFilteredResults)) {
            System.out.println("Verify that the result count is as Expected");
        } else {
            System.out.println("Mismatch in result count Expected vs actual");
        }

        adventures.clearFilters();

        if (adventures.getResultCount() == Integer.parseInt(ExpectedUnFilteredResults)) {
            System.out.println(
                    "Verify that the result count is as Expected after clearing the Filters");
        } else {
            System.out.println(
                    "Mismatch in result count Expected vs actual after clearing the Filters");
        }
    }

  @AfterSuite
    public static void quitDriver() {
        driver.quit();
    }
}
