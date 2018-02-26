package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsForHomePage extends ParentPage{
    public ProductsForHomePage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(xpath =".//*[@id='menu_categories_left']/li[7]/div[1]/a[1]")
    //@FindBy(xpath =" .//*[@href= 'http://rozetka.com.ua/bytovaya-himiya/c4429255/' and text()='Бытовая химия']")
    private WebElement elementHouseHoldChemical;

    public void clickOnHouseholdChemical() throws InterruptedException {
       // Thread.sleep(5000);
        actionsWithOurElements.clickOnInvisibleElement(elementHouseHoldChemical);
    }
}
