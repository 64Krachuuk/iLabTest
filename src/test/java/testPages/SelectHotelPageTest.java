package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import Pages.SelectHotelPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.CsvReader;

import java.util.List;
import java.util.Map;


public class SelectHotelPageTest extends BasePageTest{
    private static final Logger LOG = LoggerFactory.getLogger("SelectHotelPageTest.class");
    public LoginPage loginPage;
    public SearchPage searchPage;
    public SelectHotelPage selectHotelPage;

    public SelectHotelPageTest(){
        super();
    }

    @BeforeEach
    void setUpPages(){
        if(driver == null){
            initialization();
        }
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
    }

    @Test
    public void selectHotelPageTest(){
        List<Map<String, String>> users = CsvReader.readCsvAsMap("scenario.csv");
        for(Map<String, String> user : users) {
            searchPage = loginPage.logIntoApplication(user.get("userName"), user.get("password"));
            selectHotelPage = searchPage.searchHotel(user.get("hotelLocation"));
            boolean allFieldsContainPassedLocation = selectHotelPage.locationColumn(user.get("hotelLocation"));
            Assertions.assertTrue(allFieldsContainPassedLocation, "some fails dont contain the paased location");
        }
    }
}
