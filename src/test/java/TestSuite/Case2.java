package TestSuite;

import libs.Database;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import parentTest.ParentTest;

import java.io.IOException;
import java.sql.SQLException;

public class Case2 extends ParentTest {
    public Case2(String browser) {
        super(browser);
    }
    static Logger logger= Logger.getLogger(Case2.class);
    static Database dbMySQL;
    @Before
    public void setUp() throws SQLException, IOException, ClassNotFoundException {
        dbMySQL = new Database("MySQL_PADB_DB","MySQL");

    }
    @After
    public void tearDown() throws SQLException {
        dbMySQL.quit();

    }

    @Test
    public void case2() throws SQLException {
        logger.info(dbMySQL.selectTable("select * from seleniumTable"));
        /*// logger.info(dbMySQL.selectTable("select * insert into seleniumTable(,idNumber,login,passWord) values('','2','Student','9','fail')"));
        logger.info(dbMySQL.selectTable("select login from seleniumTable"));
        logger.info(dbMySQL.selectTable("select * from seleniumTable").get(1));
        logger.info(dbMySQL.changeDB("Insert into seleniumTable values(301,'rymarchuk301','30125')"));
        logger.info(dbMySQL.selectTable("select * from seleniumTable"));
        // logger.info(dbMySQL.changeDB("delete  from seleniumTable where login='rymarchuk'"));
        logger.info(dbMySQL.selectTable("select * from seleniumTable"));

*/
    }
}
