package parentTest;
import libs.Utils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.MobilePhonePage;
import pages.TelefonyPage;
import pages.TelethonElectronicPage;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;

@RunWith(value = Parameterized.class)



/**
 * Created by Oleg on 13.02.2018.
 */
public class ParentTest {
    public WebDriver webDriver;
    WebDriverWait webDriverWait10;
    protected Logger logger = Logger.getLogger(String.valueOf(getClass()));
    private Utils utils = new Utils();
    private String pathToScreenShot;
    private String browser;
    private boolean isTestPass = false;
    public HomePage homePage;
    public TelethonElectronicPage telefonTvElektronika;
    public TelefonyPage telefonyPage;
    public MobilePhonePage mobilePhone;



    public ParentTest(String browser) {
        this.browser = browser;

    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException {
        return Arrays.asList(new Object[][]{
//                {"fireFox"}
//                ,
                 {"chrome"}
//                ,
//                { "iedriver" }
//                ,
//                    { "opera" }
//                ,
//                {"phantomJs"}
//                ,
                //               {"remote"}
        });
    }

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setUp() throws SQLException, IOException, ClassNotFoundException {
        File file = new File("");
        //  logger.info ("path file ="+ file);

        if ("fireFox".equals(browser)) {
            logger.info("FireFox will be started ");
            //  File fileFF = new File("./src/test/resources/drivers/geckodriver");
        /*    File fileFF = new File(".//drivers//geckodriver");
            logger.info ("path file ="+ fileFF.getAbsolutePath());
            System.setProperty("webdriver.gecko.driver", fileFF.getAbsolutePath());*/
            System.setProperty("webdriver.gecko.driver","/home/oleg/work/IdeaProjects/NewWebSiteProject/src/test/resources/drivers/geckodriver");
           /* FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.startup.page", 0); // Empty start page
            profile.setPreference("browser.startup.homepage_override.mstone", "ignore"); // Suppress the "What's new" page*/
            webDriver = new FirefoxDriver();
            logger.info(" FireFox is started");
        } else if ("chrome".equals(browser)) {
            logger.info("Chrome will be started ");
           /* File fileFF = new File(".././drivers/chromedriver");
            System.setProperty("webdriver.chrome.driver", fileFF.getAbsolutePath());*/
            System.setProperty("webdriver.chrome.driver", "E:\\CopyRozetkaOlehRym\\src\\test\\resources\\drivers\\chromedriver.exe");
            webDriver = new ChromeDriver();
            logger.info(" Chrome is started");
        } else if ("iedriver".equals(browser)) {
            logger.info("IE will be started ");
            File file1 = new File(".././drivers/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file1.getAbsolutePath());
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            capabilities.setCapability("ignoreZoomSetting", true);
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            webDriver = new InternetExplorerDriver();
            logger.info(" IE is started");
        } else if ("opera".equals(browser)) {
            logger.info("Opera will be started");
            File fileOpera = new File(".././drivers/operadriver.exe");
            System.setProperty("webdriver.chrome.driver", fileOpera.getAbsolutePath());
            webDriver = new ChromeDriver();
            logger.info(" Opera is started");
        }/* else if ("phantomJs".equals(browser)) {
            logger.info("PHANTOMJS will be started");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setJavascriptEnabled(true);
            caps.setCapability("takesScreenshot", true);
            caps.setCapability(
                    PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    ".././drivers/phantomjs-2.1.1-windows/bin/phantomjs.exe"
            );
            webDriver = new PhantomJSDriver(caps);
            logger.info(" PHANTOMJS is started");
        }*/ else if ("remote".equals(browser)) {
            logger.info("Remote Driver will be started");
            try {
                webDriver = new RemoteWebDriver(
                        new URL("http://localhost:4444/wd/hub"),
                        DesiredCapabilities.chrome());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        pathToScreenShot = file.getAbsolutePath() + "//target//"
                + this.testName.getMethodName() + "-" + browser + ".jpg";

        // webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        homePage = new HomePage(webDriver);
        telefonTvElektronika = new TelethonElectronicPage(webDriver);
        telefonyPage = new TelefonyPage(webDriver);
        mobilePhone =new MobilePhonePage(webDriver);

        webDriverWait10 = new WebDriverWait(webDriver,10);

    }



    @After
    public void tearDown() throws SQLException {
        if (!(webDriver==null)) {
            if (!isTestPass) {
                utils.screenShot(pathToScreenShot, webDriver);
            }
            webDriver.quit();
        }
    }

    public void checkAC(String message, boolean actualResult
            , boolean expectedResult){
        if (!(actualResult == expectedResult)) {
            utils.screenShot(pathToScreenShot, webDriver);
            logger.info("AC failed: " + message);

            //check - rather was logger.error
        }
        Assert.assertThat(message + "Browser - " + browser + " ScreenShot " + pathToScreenShot,actualResult,is(expectedResult));
        setTestPass();
    }

    private void setTestPass() {
        isTestPass = true;
    }
}
