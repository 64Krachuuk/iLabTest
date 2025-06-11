package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import Pages.SelectHotelPage;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.CsvReader;

import java.util.List;
import java.util.Map;

public class SearchPageTest extends BasePageTest{
    private static final Logger LOG = LoggerFactory.getLogger("SearchPageTest.class");
    public LoginPage loginPage;
    public SearchPage searchPage;
    public SelectHotelPage selectHotelPage;

    public SearchPageTest(){super();}

    @BeforeEach
    void setUpPages(){
        if(driver == null){
            initialization();
        }
        loginPage = new LoginPage(driver);
    }

    @Test
    public void selectPreferredHotel(){
        List<Map<String, String>> users = CsvReader.readCsvAsMap("scenario.csv");
        for(Map<String, String> user : users) {
            searchPage = loginPage.logIntoApplication(user.get("userName"), user.get("password"));
            if (searchPage == null || !searchPage.verifySearchPageTitle()) {
                LOG.error("Search page not loaded for user: {}", user.get("username"));
                Assertions.fail("Search page not loaded");
            }
            selectHotelPage = searchPage.searchHotel(user.get("hotelLocation"));
        }
    }
}
