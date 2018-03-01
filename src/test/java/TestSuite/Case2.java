package TestSuite;

import dbTest.TestDataBase;
import libs.Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static libs.ConfigData.getCfgValue;


public class Case2 extends ParentTest {
    public Case2(String browser) {
        super(browser);
    }

    static Database dbMySQL;

    @Test
    public void case2() throws SQLException, InterruptedException, IOException, ClassNotFoundException {
        homePage.open(getCfgValue("Home_Page_Rozetka"));
        homePage.clickOnProductsForHome();
        productsForHomePage.clickOnHouseholdChemical();
        houseHoldChemicalPage.clickOnsredstvaStirki();
        sredstvaStirkiPage.clickOnPoroshokForStirki();
        Map<String, Integer> map = sredstvaStirkiPage.selectNameAndPriceInRange(100, 300);

        dbMySQL = new Database("MySQL_PADB_DB", "MySQL");
        dbMySQL.changeDB("Delete from Products");
        logger.info("select table =" + dbMySQL.selectTable("select * from Products"));
        Set<Entry<String, Integer>> entries = map.entrySet();
        logger.info("entries=" + entries);
        for (Entry<String, Integer> entry : entries) {

            dbMySQL.insert(entry.getKey(), entry.getValue());

        }
        logger.info("select table =" + dbMySQL.selectTable("select * from Products"));

    }
}
