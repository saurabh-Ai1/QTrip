package qtriptest.pages;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegisterPage {
    RemoteWebDriver driver;
    public String lastGeneratedUsername;
    public String url="https://qtripdynamic-qa-frontend.vercel.app/pages/register/";

    @FindBy(id="floatingInput")
    WebElement emailTextBox;

    @FindBy(id="floatingPassword")
    WebElement passwordTextBox;

    @FindBy(xpath="//input[@placeholder='Retype Password to Confirm']")
    WebElement confirmpasswordTextBox;

    @FindBy(xpath="//button[text()='Register Now']")
    WebElement registerNowButton;

    public RegisterPage(RemoteWebDriver driver){
        this.driver=driver;
       // driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,40),this);
    }

    public void registerNewUser(String userName,String password,String confirmPassword,Boolean makeDynamic) throws InterruptedException{
        // if(makeDynamic){
        //     userName=userName+UUID.randomUUID().toString()+"@gmail.com";
        //     this.lastGeneratedUsername=userName;
        // }
        // String testUserName;
        System.out.println(userName);

        if(makeDynamic){
            // testUserName=userName.split("@")[0];
            userName=UUID.randomUUID().toString()+userName;
            System.out.println(userName);
          //  this.lastGeneratedUsername=testUserName;
            // this.lastGeneratedUsername=userName;

        }
        // if (makeDynamic)
        // testUserName = userName + UUID.randomUUID().toString();
        // else
        // testUserName = userName;
        this.lastGeneratedUsername=userName;
        
        emailTextBox.sendKeys(userName);
        passwordTextBox.sendKeys(password);
        Thread.sleep(2000);
        confirmpasswordTextBox.sendKeys(confirmPassword);
        System.out.println(lastGeneratedUsername);
        Thread.sleep(1000);
        try {
            registerNowButton.click();
            System.out.println("click the register button");
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
        }

    }

    public boolean validateRegisterPage(){
        if(driver.getCurrentUrl().equalsIgnoreCase(url)){
            return true;
        }
        return false;
    }
}
