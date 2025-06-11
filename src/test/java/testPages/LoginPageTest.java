package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class LoginPageTest extends BasePageTest{
    static LoginPage loginPage;
    static SearchPage searchPage;

    public LoginPageTest(){super();}

    public static void setUp(){
        initialization();
        loginPage = new LoginPage();
    }
    //getusername password from csv file
    @Test
    public void loginPageTitleTest(){
        String title = loginPage.validateLoginPageTitle();
        Assert.assertTrue(title.equalsIgnoreCase(properties.getProperty("homePageTitle")));
    }
    @Test
    public void loginTest(){
        searchPage = loginPage.logIntoApplication(properties.getProperty("userName"),properties.getProperty("password"));
    }

    @AfterTest
    public static void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
