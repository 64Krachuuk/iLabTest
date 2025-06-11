package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import Pages.SelectHotelPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SelectHotelPageTest extends BasePageTest{
    private static final Logger LOG = LoggerFactory.getLogger("SelectHotelPageTest.class");
    public static LoginPage loginPage;
    public static SearchPage searchPage;
    public static SelectHotelPage selectHotelPage;
//    public static BookHotelPage bookHotelPage;

    public SelectHotelPageTest(){
        super();
    }

    @BeforeTest
    public static void setUp(){
        initialization();
        loginPage = new LoginPage();
        searchPage = new SearchPage();
        selectHotelPage = searchPage.searchHotel(properties.getProperty("hotelLocation"));
    }
    @Test
    public void selectHotelPageTest(){
        boolean allFieldsContainPassedLocation = selectHotelPage.locationColumn("hotelLocation");
        Assert.assertTrue(allFieldsContainPassedLocation,"some fails dont contain the paased location");
    }
}
