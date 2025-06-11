package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import Pages.SelectHotelPage;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.CsvReader;
import utilities.Screenshots;

import java.util.List;
import java.util.Map;

public class SearchPageTest extends BasePageTest{
    private static final Logger LOG = LoggerFactory.getLogger("SearchPageTest.class");
    public LoginPage loginPage;
    public SearchPage searchPage;
    public SelectHotelPage selectHotelPage;
    Screenshots screenshots;

    public SearchPageTest(){super();}

    @BeforeEach
    void setUpPages(){
        if(driver == null){
            initialization();
        }
        loginPage = new LoginPage(driver);
        screenshots = new Screenshots();
    }

    @Test
    public void selectPreferredHotelTest() throws InterruptedException {
        extentTest = extentReports.createTest("Search Hotel");
        try{
            List<Map<String, String>> users = CsvReader.readCsvAsMap("scenario.csv");
            for(Map<String, String> user : users) {
                searchPage = loginPage.logIntoApplication(user.get("userName"), user.get("password"));
                if (searchPage == null || !searchPage.verifySearchPageTitle()) {
                    LOG.error("Search page not loaded for user: {}", user.get("username"));
                    Assertions.fail("Search page not loaded");
                }
                selectHotelPage = searchPage.searchHotel(user.get("hotelLocation"));
            }
            String screenshotPath = screenshots.captureScreenshot("Search hotel");
            extentTest.addScreenCaptureFromPath(screenshotPath);
        }catch (Exception e){
            String errorScreenshot = screenshots.captureScreenshot("exception");
            extentTest.fail("Test failed with exception: " + e.getMessage());
            extentTest.addScreenCaptureFromPath(errorScreenshot);
        }
    }
    @AfterEach
    void tear(){
        if (driver != null) {
            driver.quit();
        }
        extentReports.flush();
    }
}
