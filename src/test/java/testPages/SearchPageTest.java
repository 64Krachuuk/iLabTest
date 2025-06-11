package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import Pages.SelectHotelPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchPageTest extends BasePageTest{
    private static final Logger LOG = LoggerFactory.getLogger("SearchPageTest.class");
    public static LoginPage loginPage;
    public static SearchPage searchPage;
    public static SelectHotelPage selectHotelPage;

    public SearchPageTest(){super();}

    @BeforeTest
    public static void  setUp(){
        initialization();
        loginPage = new LoginPage();
        searchPage = loginPage.logIntoApplication(properties.getProperty("userName"),properties.getProperty("password"));
        selectHotelPage = new SelectHotelPage();
    }

    @Test
    public void selectPreferredHotel(){
        if(searchPage.verifySearchPageTitle()){
            selectHotelPage = searchPage.searchHotel(properties.getProperty("hotelLocation"));
        }else {
            LOG.error("Search page not loaded");
        }
    }

    @AfterTest
    public static void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
