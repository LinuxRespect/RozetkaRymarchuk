package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Oleg on 24.02.2018.
 */
public class HouseHoldChemicalPage extends ParentPage {
    public HouseHoldChemicalPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(xpath = ".//*[text()='Для стирки']")
    private WebElement elementFotStirki;

    public void clickOnsredstvaStirki() {
        actionsWithOurElements.clickOnInvisibleElement(elementFotStirki);
    }
}
