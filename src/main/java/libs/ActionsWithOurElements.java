package libs;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;

public class ActionsWithOurElements {

    WebDriver webDriver;
    Logger logger;
    WebDriverWait webDriverWait5;
    private boolean state;
    String elemetsTopSale = ".//*[@class='g-tag g-tag-icon-middle-popularity sprite']";

    public ActionsWithOurElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        logger = Logger.getLogger(getClass());
        webDriverWait5 = new WebDriverWait(webDriver, 10);
    }

    /**
     * Clean and input text to field
     *
     * @param element
     * @param text
     */
    public void enterText(WebElement element, String text) {
        try {
            webDriverWait5.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
            logger.info(text + " was inputed");
        } catch (Exception e) {
            logger.error("Can't work with element " + element);
            Assert.fail("Can't work with element " + element);
        }
    }

    public void clickOnElement(WebElement element) {
        try {
            webDriverWait5.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info(element + " was clicked");
        } catch (Exception e) {
            logger.error("Can't work with element " + element);
            Assert.fail("Can't work with element " + element);
        }
    }

    public boolean isElementPresent(String locatorWithText) {
        try {

            return webDriver.findElement(By.xpath(locatorWithText)).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            logger.info("visible =" + element + "=" + element.isDisplayed());
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

    public int areElementsPresent(String locator) {
        logger.info("count = " + webDriver.findElements(By.xpath(locator)).size());
        return webDriver.findElements(By.xpath(locator)).size();

    }

    public void checkTextInElement(String xpath, String text) {
        try {
            webDriverWait5.until(ExpectedConditions.textToBePresentInElement(By.xpath(xpath), text));
            String textFromElement = webDriver.findElement(By.xpath(xpath)).getText();
            Assert.assertThat("Text in element not matched", textFromElement, is(text));
        } catch (Exception e) {
            logger.error("Can't work with element ");
            Assert.fail("Can't work with element ");
        }
    }

    public void selectTextInDDByText(WebElement dropDown, String text) {
        try {
            webDriverWait5.until(ExpectedConditions.elementToBeClickable(dropDown));
            dropDown.click();
            Select optionsFromDD = new Select(dropDown);
            optionsFromDD.selectByValue(text);
            optionsFromDD.selectByVisibleText(text);
            logger.info(text + " was selected in DropDown");
        } catch (Exception e) {
            logger.error("Can't work with dropDown ");
            Assert.fail("Can't work with dropDown ");
        }
    }

    public void deleteLine(WebElement line) {
        clickOnElement(line);

    }


    public void waitONDownload(WebElement element) {
        webDriverWait5.until(ExpectedConditions.elementToBeClickable(element));
        ;
    }

    public boolean amount(String locator, int number) {
        logger.info("how many =" + webDriver.findElements(By.xpath(locator)).size());
        return webDriver.findElements(By.xpath(locator)).size() == number;
    }

    public String getTextInElement(WebElement element) {
        webDriverWait5.until(ExpectedConditions.visibilityOf(element));
        String textFromElement = element.getText();
        logger.info("Text element = " + textFromElement);
        return textFromElement;
    }

    public boolean compareText(String text1, String text2) {
        return text1.equals(text2);

    }

    public void clickOnCheckBox(WebElement element) {
        boolean isChecked = true;
        isChecked = element.isSelected();
        logger.info("Checked properties= " + isChecked);
        if (!isChecked) {
            element.click();
            logger.info("Element " + element + " chose");
        } else
            logger.info("Checkbox is selected");

    }

    public void switchToOtherWindow(String existingWindow) {
        for (String winHandle : webDriver.getWindowHandles()) {
            if (!(winHandle.equals(existingWindow))) {
                webDriver.switchTo().window(winHandle);
            }
        }
    }

    public void clickOnInvisibleElement(WebElement element) {
      //  Thread.sleep(5000);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";

        ((JavascriptExecutor) webDriver).executeScript(script, element);
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    public void addToList(ArrayList<String> list) {
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        String titleNamePhones = ".//div[@class = 'g-i-tile-i-title clearfix']//a";
        int count = areElementsPresent(titleNamePhones);
        for (int j = 0; j < count; j++) {
            String textFromElement = null;
            textFromElement = webDriver.findElements(By.xpath(titleNamePhones)).get(j).getText();
            list.add(textFromElement);
        }
        logger.info("list= " + list);
    }

    public void writeListToFile(ArrayList<String> list) {
        File file = new File("E:\\RozetkaOlehRym\\src\\test\\resources\\listNamesSmartPhone.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(String.valueOf(list));
            fr.flush();
            logger.info("write to file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void countTopSales() {
        areElementsPresent(elemetsTopSale);

    }

    public void writeMapIntoExcel(File file, Workbook book, Sheet sheet, Map<String, Integer> m) throws IOException {
        int rownum = 0;
        Cell cell;
        Row row;
        // Нумерация начинается с нуля
        row = sheet.createRow(rownum);
        // name
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Name Device Top Sale");

        // prcie
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Price");
        for (Map.Entry<String, Integer> deviceTopSales : m.entrySet()) {
            rownum++;
            row = sheet.createRow(rownum);

            //namedevice
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(deviceTopSales.getKey());
            // price
            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(deviceTopSales.getValue());


        }
        book.write(new FileOutputStream(file));
        book.close();

    }
}
