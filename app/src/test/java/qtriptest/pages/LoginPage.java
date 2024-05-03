package qtriptest.pages;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    RemoteWebDriver driver;
    public String url = "https://qtripdynamic-qa-frontend.vercel.app/pages/login/";

    @FindBy(id = "floatingInput")
    WebElement emailTextBox;

    @FindBy(id = "floatingPassword")
    WebElement passwordTextBox;

    @FindBy(xpath = "//button[text()='Login to QTrip']")
    WebElement loginButton;

    @FindBy(xpath = "//div[text()='Logout']")
    WebElement logoutButton;


    public LoginPage(RemoteWebDriver driver) {
        this.driver = driver;
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 40), this);
    }

    public void performLogin(String username, String password) {
        System.out.println(username);
        emailTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        try {
            loginButton.click();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

    public boolean isLoginPageDisplayed() throws InterruptedException {
        Thread.sleep(5000);
        if (driver.getCurrentUrl().equalsIgnoreCase(url)) {
            return true;
        }
        return false;
    }

    public boolean isUserLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Logout']")));
        if (logoutButton.isDisplayed()) {
            return true;

        }
        return false;

    }

    public void navigateToLoginPage() {
        driver.navigate().to(url);
    }


}
