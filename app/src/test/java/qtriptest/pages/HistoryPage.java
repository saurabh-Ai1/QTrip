
package qtriptest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocator;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HistoryPage {
    RemoteWebDriver driver;
    
    @FindBy(xpath = "//tbody[@id='reservation-table']/tr/th")
    WebElement transactionId;

    @FindBy(id = "no-reservation-banner")
    WebElement noReservation;

    @FindBy(xpath = "//button[text()='Cancel']")
    WebElement cancelButton;

public HistoryPage(RemoteWebDriver driver){
    this.driver = driver;
    PageFactory.initElements(driver, this);
    AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,20);
}
public String transactionDetails(){
    String tansactionId = "";
    tansactionId = this.transactionId.getText();
    System.out.println("Transaction Id : " + tansactionId);
    return tansactionId;
}

public void cancelReservation() throws InterruptedException{
    Thread.sleep(2000);
    cancelButton.click();
}

public Boolean isTransactionRemoved() throws InterruptedException{
    Thread.sleep(2000);
    Boolean status = false;
    try{if(noReservation.isDisplayed()){            
        status =  true;
    }
    }
    catch(Exception e){
        e.printStackTrace();
    }      
     return status;   
}
}