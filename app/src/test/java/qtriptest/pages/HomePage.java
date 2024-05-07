package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    RemoteWebDriver driver;


    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutButton;

    @FindBy(xpath = "//a[text()='Register']")
    WebElement registerButton;

    @FindBy(id = "autocomplete")
    WebElement searchBox;

    @FindBy(xpath = "//h5[text()='No City found']")
    WebElement noCityfound;

    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 40), this);

    }

    public void gotoHomePage() throws InterruptedException {
        driver.navigate().to("https://qtripdynamic-qa-frontend.vercel.app/");
        Thread.sleep(2000);
    }

    public void clickRegister() throws InterruptedException {
        registerButton.click();

    }

    public boolean isUserLoggedIn() throws InterruptedException {
        // Thread.sleep(10000);
        // WebDriverWait wait = new WebDriverWait(driver, 10);
        // wait.until(
        // ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Logout']")));
        try {

            if (logoutButton.isDisplayed()) {
                return true;

            } else {

                return false;
            }
        } catch (Exception e) {

            return false;
        }

    }

    public void logOutUser() throws InterruptedException {
        logoutButton.click();
    }

    public void searchCity(String cityName) {
        searchBox.clear();
        searchBox.sendKeys(cityName);
    }

    public void selectCity(String cityName) throws InterruptedException {
        Thread.sleep(2000);
        By by = new By.ByXPath(String.format("//li[@id='%s']", cityName.toLowerCase()));
        driver.findElement(by).click();
    }

    public boolean isNoCityFound() {
        try {
            return noCityfound.isDisplayed();
        } catch (Exception e) {
            return false;
            // TODO: handle exception
        }
    }

    public void history() throws InterruptedException {
        
        WebElement historyLink = driver.findElement(By.xpath("//*[@id='navbarNavDropdown']/ul/li[2]/a"));
        Thread.sleep(3000);
        historyLink.click();
    }

}
