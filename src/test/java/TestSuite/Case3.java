package TestSuite;

import libs.EmailSender;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.File;
import java.io.IOException;

import static libs.ConfigData.getCfgValue;


public class Case3 extends ParentTest {
    final String username = "olehrym@gmail.com";
    final String password = "testPassword";
    String pathToListEmails="E:\\RozetkaOlehRym\\src\\test\\resources\\listEmails.txt";
    String pathToAttachFile= "E:\\RozetkaOlehRym\\src\\test\\resources\\listNamesSmartPhone.txt";
    String pathNameToData ="E:\\RozetkaOlehRym\\src\\main\\java\\data\\testDataSuitesSuite3.xls";


    public Case3(String browser) {
        super(browser);
    }

    @Test
    public void case3() throws IOException, InterruptedException {

        homePage.openUrl(getCfgValue("Home_Page_Rozetka"));
        homePage.moveToSectionPhoneTVAndElectronics();
        telefonTvElektronika.moveToTelefony();
        telefonyPage.moveToMobilePhone();
        File file= new File(pathNameToData);
        mobilePhone.writeArrayIntoExcelFile(file );
        EmailSender emailSender=new EmailSender();
        emailSender.connectionWithSmtpServer(username,password,"true,","true","smtp.gmail.com","587",pathToListEmails,pathNameToData);
    }

}
