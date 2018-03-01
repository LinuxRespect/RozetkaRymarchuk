package TestSuite;

import libs.EmailSender;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.File;
import java.io.IOException;

import static libs.ConfigData.getCfgValue;

public class Case1 extends ParentTest {
    File fileListNameDevices= new File("E:\\RozetkaOlehRym\\src\\test\\resources\\listNamesSmartPhone.txt");
    final String username = "olehrym@gmail.com";
    final String password = "TestPassword";
    String pathToListEmails="E:\\RozetkaOlehRym\\src\\test\\resources\\listEmails.txt";
    String pathToAttachFile= "E:\\RozetkaOlehRym\\src\\test\\resources\\listNamesSmartPhone.txt";

    public Case1(String browser) throws IOException {
        super(browser);
    }

    @Test
    public void case1() throws InterruptedException, IOException {
        homePage.openUrl(getCfgValue("Home_Page_Rozetka"));
        homePage.moveToSectionPhoneTVAndElectronics();
        telefonTvElektronika.moveToTelefony();
        telefonyPage.moveToMobilePhone();
        mobilePhone.writeToFileNamesDeviceFrom3Pages(fileListNameDevices);
        EmailSender emailSender=new EmailSender();
        emailSender.connectionWithSmtpServer("username","password","true,","true","smtp.gmail.com","587",pathToListEmails,pathToAttachFile);

    }
}
