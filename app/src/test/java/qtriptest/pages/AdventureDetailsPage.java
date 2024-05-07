package qtriptest.pages;

import org.checkerframework.checker.units.qual.g;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AdventureDetailsPage {
    RemoteWebDriver driver;

    @FindBy(name = "name")
    WebElement guestName;

    @FindBy(name = "date")
    WebElement datepicker;

    @FindBy(name = "person")
    WebElement noOfPersons;

    @FindBy(xpath = "//button[text()='Reserve']")
    WebElement reserveButton;

    @FindBy(id = "adventure-name")
    WebElement selectedAdventure;

    public AdventureDetailsPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 20);
    }

    public void selectDate(String date) throws InterruptedException{       
        datepicker.click();
        datepicker.clear();
        Thread.sleep(1000);
        datepicker.sendKeys(date);
        Thread.sleep(1000);
        System.out.println("data selected successfully.!");
    }

    public void personsCount(String count) throws InterruptedException{        
        noOfPersons.click();
        noOfPersons.clear();
        Thread.sleep(1000);
        noOfPersons.sendKeys(count);
        Thread.sleep(1000);
        System.out.println("persons count entered successfully.!");
    }

    public Boolean isAdventureBookingSuceessful() throws InterruptedException{
        Thread.sleep(2000);
        Boolean status = false;
        try{if(selectedAdventure.isDisplayed()){            
            status =  true;
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }      
         return status;   
    }

    public void setAdventureDetails(String name, String date, String count) throws InterruptedException{
        Thread.sleep(2000);
        guestName.click();
        guestName.sendKeys(name);
        selectDate(date);
        personsCount(count);
        reserveButton.click();        
    }
    
}