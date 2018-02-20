package TestSuite;

import org.junit.Test;
import parentTest.ParentTest;

import java.io.File;
import java.io.IOException;



public class Case3 extends ParentTest {
    String currentUrl = "https://rozetka.com.ua";

    public Case3(String browser) {
        super(browser);
    }

    @Test
    public void case3() throws IOException, InterruptedException {
        String absolutePathName="E:\\RozetkaOlehRym\\src\\main\\java\\data\\testDataSuites.xls";
        homePage.openUrl(currentUrl);
        homePage.moveToSectionPhoneTVAndElectronics();
        telefonTvElektronika.moveToTelefony();
        telefonyPage.moveToMobilePhone();
        File file= new File(absolutePathName);
        mobilePhone.writeArrayIntoExcelFile(file );
    }

}
