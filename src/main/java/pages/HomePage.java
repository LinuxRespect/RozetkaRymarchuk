package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends ParentPage {
    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath=".//*[@href='https://rozetka.com.ua/telefony-tv-i-ehlektronika/c4627949/']")
    private WebElement sectionElectronics;

    public void openUrl(String nameUrl) {
        open(nameUrl);
    }

    public void moveToSectionPhoneTVAndElectronics() {
        actionsWithOurElements.clickOnElement(sectionElectronics);
    }
}
