package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class TelethonElectronicPage extends ParentPage {
    public TelethonElectronicPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy (xpath = ".//*[@href='http://rozetka.com.ua/telefony/c4627900/']")
    private WebElement sectionTelefony;

    public void moveToTelefony() {
        actionsWithOurElements.clickOnElement(sectionTelefony);
    }
}
