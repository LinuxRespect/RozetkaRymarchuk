package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Oleg on 14.02.2018.
 */
public class TelefonyPage extends ParentPage {
    public TelefonyPage(WebDriver webDriver) {
        super(webDriver);
    }

    @FindBy(xpath = ".//*[@class='m-cat-l-i-title-link']")
    private WebElement sectionSmartPhone;
   @FindBy(xpath=".//div[@class = 'g-i-tile-i-title clearfix']//a")
   // @FindBy(xpath = ".//*[@class = 'g-i-tile-i-box' and @value='001']")
    private WebElement catalogBlock;
    public void moveToMobilePhone() {
        actionsWithOurElements.clickOnElement(sectionSmartPhone);
    }

    public void clickOnTelephonr() {
        ArrayList<String> list= new ArrayList<String>();
        int count = actionsWithOurElements.areElementsPresent(".//div[@class = 'g-i-tile-i-title clearfix']//a");
//       actionsWithOurElements.areElementsPresent(".//div[@class = 'g-i-tile-i-title clearfix']//a");
//       actionsWithOurElements.getTextInElement(catalogBlock);
//        String textFromElement = webDriver.findElements(By.xpath(".//div[@class = 'g-i-tile-i-title clearfix']//a")).get(1).getText();
        for(int i=0;i<count;i++) {
            String textFromElement = null;
            textFromElement = webDriver.findElements(By.xpath(".//div[@class = 'g-i-tile-i-title clearfix']//a")).get(i).getText();
            list.add(textFromElement);
        }
        logger.info("Text element = "+list);
        // actionsWithOurElements.getTextInElement(catalogBlock);
        File file = new File("E:\\RozetkaRymarchuk\\src\\test\\resources\\nameSmartPhone.txt");
        FileWriter fr = null;
        try{
            fr =new FileWriter(file);
            fr.write(String.valueOf(list));
            fr.flush();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
