package TestSuite;

import libs.SendEmail;
import org.junit.Test;
import parentTest.ParentTest;

public class Case1 extends ParentTest {
    String currentUrl = "https://rozetka.com.ua";

    public Case1(String browser) {
        super(browser);
    }

    @Test
    public void case1() throws InterruptedException {
      /*  homePage.openUrl(currentUrl);
        homePage.moveToSectionPhoneTVAndElectronics();
        telefonTvElektronika.moveToTelefony();
        telefonyPage.moveToMobilePhone();
        mobilePhone.writeToFileNamesDeviceFrom3Pages();*/
        SendEmail s= new SendEmail();
        s.sendEmail();


    }
}
