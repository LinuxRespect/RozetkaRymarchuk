package pages;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MobilePhonePage extends ParentPage {
    public MobilePhonePage(WebDriver webDriver) {
        super(webDriver);

    }

    @FindBy(xpath = ".//li[@id='page2']//span")
    private WebElement numberPage2;
    @FindBy(xpath = ".//li[@id='page3']/span")
    private WebElement numberPage3;
    @FindBy(xpath = ".//li[@id='page4']/span")
    private WebElement numberPage4;
    @FindBy(xpath = ".//li[@id='page5']/span")
    private WebElement numberPage5;
    @FindBy(id = "navigation_block")
    private WebElement block;
    @FindBy(xpath = ".//*[@class='g-tag g-tag-icon-middle-popularity sprite']")
    private WebElement elementsTopSales;
    @FindBy(xpath = ".//div[@class = 'g-i-tile-i-title clearfix']//a")
    private WebElement titleNameDevices;
    @FindBy(xpath = ".//*[@name  ='prices_active_element_original']")
    private WebElement imageTopSale;
    @FindBy(xpath = ".//*[@class='g-price-uah']")
    private WebElement elementsPrice;


    public void writeToFileNamesDeviceFrom3Pages() throws InterruptedException {
        String titleNamePhones = ".//div[@class = 'g-i-tile-i-title clearfix']//a";
        ArrayList<String> listNameDevices = new ArrayList<String>();
        ArrayList<WebElement> pages = new ArrayList<WebElement>();
        pages.add(numberPage2);
        pages.add(numberPage3);
        for (WebElement element : pages) {
            actionsWithOurElements.addToList(listNameDevices);
            actionsWithOurElements.clickOnInvisibleElement(element);
        }

        actionsWithOurElements.addToList(listNameDevices);
        actionsWithOurElements.writeListToFile(listNameDevices);

    }


    public Map chooseNameAndPriceTopSaleFrom3Page() throws InterruptedException {
        //   open("https://rozetka.com.ua/mobile-phones/c80003/page=1;preset=smartfon/");
        Map topSales = new HashMap();
        ArrayList<WebElement> pages = new ArrayList<WebElement>();
        pages.add(numberPage2);
        pages.add(numberPage3);
        for (WebElement element : pages) {
            addTopSaleToMapList(topSales);
            actionsWithOurElements.clickOnInvisibleElement(element);
        }
        addTopSaleToMapList(topSales);
        return sortByValue(topSales);
    }

    public Map chooseNameAndPriceInRange() throws InterruptedException {
        open("https://rozetka.com.ua/mobile-phones/c80003/page=1;preset=smartfon/");
        Map nameAndPriceInRange = new HashMap();
        ArrayList<WebElement> pages = new ArrayList<WebElement>();
        pages.add(numberPage2);
        pages.add(numberPage3);
        pages.add(numberPage4);
        pages.add(numberPage5);
        for (WebElement element : pages) {
            addToMap(nameAndPriceInRange);
            actionsWithOurElements.clickOnInvisibleElement(element);

        }
        addToMap(nameAndPriceInRange);
        return sortByValue(nameAndPriceInRange);
    }

    public Map addTopSaleToMapList(Map topSales) throws InterruptedException {
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        Thread.sleep(5000);
        final int expectHeightImageTopSale = 42;
        final int expectWidthImageTopSale = 105;
        String titleNamePhones = ".//div[@class = 'g-i-tile-i-title clearfix']//a";
        String elementsPrice = ".//*[@class='g-price-uah']";
        String imageWithSizeTopSale = ".//*[@name  ='prices_active_element_original']";
        int count = actionsWithOurElements.areElementsPresent(titleNamePhones);
        int j = 0;
        for (int i = 0; i < count; i++) {
            webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            String textFromElement = webDriver.findElements(By.xpath(titleNamePhones)).get(i).getText();
            String price = webDriver.findElements(By.xpath(elementsPrice)).get(i).getText();
            j = i * 2;
            if ((j % 2) == 0) {
                Dimension sizeImageTopSale = webDriver.findElements(By.xpath(imageWithSizeTopSale)).get(j).getSize();
                if ((sizeImageTopSale.getHeight() == expectHeightImageTopSale) && (sizeImageTopSale.getWidth() == expectWidthImageTopSale)) {
//                    logger.info("textFromElement=" + textFromElement);
//                    logger.info("price =" + price);
                    topSales.put(textFromElement, parsePrise(price));
                }
            }

        }
        return topSales;
    }

    public Map addToMap(Map nameAndPriceInRange) throws InterruptedException {
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        Thread.sleep(5000);
        final int minValueRange = 3000;
        final int maxValueRange = 6000;
        String elementsPrice = ".//*[@class='g-price-uah']";
        String titleNamePhones = ".//div[@class = 'g-i-tile-i-title clearfix']//a";
        int count = actionsWithOurElements.areElementsPresent(elementsPrice);
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        for (int i = 0; i < count; i++) {
            String price;
            webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
            price = webDriver.findElements(By.xpath(elementsPrice)).get(i).getText();
            if ((parsePrise(price) > minValueRange) && (parsePrise(price) < maxValueRange)) {
                String textFromElement = webDriver.findElements(By.xpath(titleNamePhones)).get(i).getText();
               // logger.info("Name element=" + textFromElement);
                // logger.info("Price =" + parsePrise(price));
                nameAndPriceInRange.put(textFromElement, parsePrise(price));


            }
        }
        return nameAndPriceInRange;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        System.out.println("List sort desc =" + result);
        return result;
    }

    private int parsePrise(String text) {

        Pattern pattern;
        Matcher matcher;
        String REGEX = "[0-9]";
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(text);
        Integer result = 0;
        int start = 0;
        String sumValue = "";
        while (matcher.find(start)) {
            String value = text.substring(matcher.start(), matcher.end());
            sumValue = sumValue + value;
            result = Integer.valueOf(sumValue);
            start = matcher.end();
        }
        return result;
    }

    public void writeArrayIntoExcelFile(File file) throws IOException, InterruptedException {

        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet("TopSales");
        Sheet sheet1 = book.createSheet("NameAndPriceInRange");
        Map<String, Integer> topSales = chooseNameAndPriceTopSaleFrom3Page();
        actionsWithOurElements.writeMapIntoExcel(file, book, sheet, topSales);
        Map<String, Integer> inRange = chooseNameAndPriceInRange();
        actionsWithOurElements.writeMapIntoExcel(file, book, sheet1, inRange);
    }
}







































