package qtriptest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWrapper {

    public static boolean click(WebElement elementToClick,WebDriver driver){
        if(elementToClick.isDisplayed()){
            try {
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", elementToClick);
            //  ({JavascriptExecutor}driver).executeScript("arguments[0].scrollIntoView(true);",elementToClick);
                Thread.sleep(1000);
                elementToClick.click();
                return true;
            } catch (Exception e) {
                return false;
                //TODO: handle exception
            }
        }
        return false;
    }

    public static boolean sendKeys(WebElement inputBox,String keysToSend){
        try {
            inputBox.clear();
            inputBox.sendKeys(keysToSend);
            return true;
        } catch (Exception e) {
            return false;
            //TODO: handle exception
        }

    }

    public static boolean navigate(WebDriver driver, String url){
        try {
            if(driver.getCurrentUrl().equals(url)){
                return true;
            }else{
                driver.get(url);
                return driver.getCurrentUrl()==url;
            }
        } catch (Exception e) {
            return false;
            //TODO: handle exception
        }
    }

    public static WebElement findElementWithRetry(WebDriver driver,By by,int retryCount) throws Exception{
        int counter=0;
        Exception ex=new Exception();
        while(counter<=retryCount){
            try {
                return driver.findElement(by);
            } catch (Exception e) {
                counter++;
                ex=e;
                //TODO: handle exception
            }
        }
        throw new Exception(ex.getCause());

    }
    
}
