package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SredstvaStirkiPage extends ParentPage {
    public SredstvaStirkiPage(WebDriver webDriver) {
        super(webDriver);
    }
    @FindBy(xpath=".//*[@href='https://rozetka.com.ua/sredstva-dlya-stirki4632103/c4632103/' and @class='cat-tree-l-i-link novisited']")
    private WebElement elementPoroshok;
    @FindBy(xpath = ".//li[@id='page2']//span")
    private WebElement numberPage2;
    @FindBy(xpath = ".//li[@id='page3']/span")
    private WebElement numberPage3;
    @FindBy(xpath = ".//li[@id='page4']/span")
    private WebElement numberPage4;
    @FindBy(xpath = ".//li[@id='page5']/span")
    private WebElement numberPage5;

    public void clickOnPoroshokForStirki() {
        actionsWithOurElements.clickOnElement(elementPoroshok);
    }

    public Map selectNameAndPriceInRange(int minValue, int maxValue ) throws InterruptedException {
        Map nameAndPriceInRange = new HashMap();
        ArrayList<WebElement> pages = new ArrayList<WebElement>();
        pages.add(numberPage2);
        pages.add(numberPage3);
        pages.add(numberPage4);
        pages.add(numberPage5);
        for (WebElement element : pages) {
            addToMap(nameAndPriceInRange,minValue,maxValue);
            actionsWithOurElements.clickOnInvisibleElement(element);

        }
        addToMap(nameAndPriceInRange,minValue,maxValue);
        logger.info("list="+nameAndPriceInRange);
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
        System.out.println("List =" + result);
        return result;
    }

    public Map addToMap(Map nameAndPriceInRange,int minValueRange,int maxValueRange) throws InterruptedException {
        webDriver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        Thread.sleep(5000);
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
}
