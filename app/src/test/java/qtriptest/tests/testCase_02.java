package qtriptest.tests;

import qtriptest.DP;
import qtriptest.DriverSingleton;
import qtriptest.pages.AdventurePage;
import qtriptest.pages.HomePage;
import java.net.MalformedURLException;
import java.net.URL;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
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

    static ExtentTest test;
    static ExtentReports report;

    @BeforeTest(alwaysRun = true)
    public static void createDriver() throws MalformedURLException {
        // final DesiredCapabilities capabilities = new DesiredCapabilities();
        // capabilities.setBrowserName(BrowserType.CHROME);
        // driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        // driver.manage().window().maximize();
        DriverSingleton driverSingleton = DriverSingleton.getInstanceOfSingletonBrowserClass();
        driver = driverSingleton.getDriver();


        report = new ExtentReports(System.getProperty("user.dir") + "/OurExtentReport.html");

        test = report.startTest("QTrip Search and Filter");
    }

    @Test(description = "Verify that Search and filters work fine", dataProvider = "test",
            dataProviderClass = DP.class, priority = 2, groups = "Search and Filter flow")
    public static void TestCase02(String CityName, String Category_Filter, String DurationFilter,
            String ExpectedFilteredResults, String ExpectedUnFilteredResults)
            throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.gotoHomePage();
        test.log(LogStatus.INFO,"navigated to HomePage");
        Thread.sleep(2000);

        homePage.searchCity("oregoan");
        Thread.sleep(2000);
        Assert.assertTrue(homePage.isNoCityFound());
        homePage.searchCity(CityName);
        homePage.selectCity(CityName);
        AdventurePage adventures = new AdventurePage(driver);
        adventures.setFilterValue(DurationFilter);
        adventures.selectCategoryValue(Category_Filter);

        // if (adventures.getResultCount() == Integer.parseInt(ExpectedFilteredResults)) {
        //     System.out.println("Verify that the result count is as Expected");
        // } else {
        //     System.out.println("Mismatch in result count Expected vs actual");
        // }
        int actualFilteredResults = adventures.getResultCount();
        Assert.assertEquals(actualFilteredResults, Integer.parseInt(ExpectedFilteredResults),
                            "Mismatch in filtered results count");
        test.log(LogStatus.PASS, "Verified filtered result count is as expected: " + actualFilteredResults);


        adventures.clearFilters();

        // if (adventures.getResultCount() == Integer.parseInt(ExpectedUnFilteredResults)) {
        //     System.out.println(
        //             "Verify that the result count is as Expected after clearing the Filters");
        // } else {
        //     System.out.println(
        //             "Mismatch in result count Expected vs actual after clearing the Filters");
        // }
        Thread.sleep(3000);
        int actualUnfilteredResultsAfterClear = adventures.getResultCount();
        System.out.println(actualUnfilteredResultsAfterClear+" *-*-* "+ExpectedFilteredResults+" *-* "+ ExpectedUnFilteredResults);
        Assert.assertEquals(actualUnfilteredResultsAfterClear, Integer.parseInt(ExpectedUnFilteredResults),
                            "Mismatch in unfiltered results count after clearing filters");
        test.log(LogStatus.PASS, "Verified unfiltered result count is as expected after clearing filters: " + actualUnfilteredResultsAfterClear);
    }

    @AfterSuite
    public static void quitDriver() {
        driver.quit();
        report.endTest(test);
        report.flush();
    }
}
