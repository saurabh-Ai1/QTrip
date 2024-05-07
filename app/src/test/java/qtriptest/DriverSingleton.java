package qtriptest;

import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSingleton {
    private static DriverSingleton driverSingleton;
    
    private RemoteWebDriver driver;

    private DriverSingleton() throws MalformedURLException{
        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
        driver.manage().window().maximize();

    }

    public static DriverSingleton getInstanceOfSingletonBrowserClass() throws MalformedURLException{
        if(driverSingleton==null){
            driverSingleton=new DriverSingleton();
        }
        return driverSingleton;

    }
    public RemoteWebDriver getDriver(){
        return driver;
    }
    
}
