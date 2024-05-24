package qtriptest.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

public class AdventurePage {
    RemoteWebDriver driver;

    @FindBy(id = "duration-select")
    WebElement durationFilter;

    @FindBy(id = "category-select")
    WebElement categoryFilter;

    @FindBy(xpath = "//div[@onclick='clearDuration(event)']")
    WebElement clearDuration;

    @FindBy(xpath = "//div[@onclick='clearCategory(event)']")
    WebElement clearCategory;

    public AdventurePage(RemoteWebDriver driver) {
        this.driver = driver;
        // driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 40), this);
    }

    public void setFilterValue(String value) {
        try {
            durationFilter.click();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        Select FilterDropDown = new Select(durationFilter);
        FilterDropDown.selectByVisibleText(value);
    }

    public void selectCategoryValue(String value) {
        categoryFilter.click();
        Select FilterDropDown = new Select(categoryFilter);
        FilterDropDown.selectByVisibleText(value);

    }

    public int getResultCount() {
        List<WebElement> resultGrid = this.driver.findElements(By.xpath("//div[@class='activity-card']"));
        return resultGrid.size();
    }

    public void selectAdventure(String adventureName) throws InterruptedException {

        Thread.sleep(5000);

        WebElement adventure = this.driver.findElement(
                By.xpath(String.format("//h5[text()='%s']", adventureName)));
                
        adventure.click();
        Thread.sleep(4000);
    }

    public void clearFilters() {
        try {
            clearDuration.click();
            clearCategory.click();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }



}
