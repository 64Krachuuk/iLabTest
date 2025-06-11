package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.CsvReader;
import utilities.Screenshots;

import java.util.List;
import java.util.Map;


public class LoginPageTest extends BasePageTest{
    LoginPage loginPage;
    SearchPage searchPage;

//    WebDriver driver;
//    ExtentTest extentTest;
//    ExtentReports extentReports;
    Screenshots screenshots;

    @BeforeEach
    void setUpPages(){
        if(driver == null){
            initialization();
        }
        loginPage = new LoginPage(driver);
        screenshots = new Screenshots();
    }

    public LoginPageTest(){super();}

    @Test
    public void loginPageTitleTest(){
        extentTest = extentReports.createTest("Verify Title");
        try{
        String title = loginPage.validateLoginPageTitle();
        List<Map<String, String>> users = CsvReader.readCsvAsMap("scenario.csv");
        for(Map<String, String> user : users) {
            Assertions.assertTrue(
                    title.equalsIgnoreCase(user.get("homePageTitle")),
                    "Title does not match the expected value"
                );
            }
        }catch (Exception e){
            String errorScreenshot = screenshots.captureScreenshot("exception");
            extentTest.fail("Test failed with exception: " + e.getMessage());
            extentTest.addScreenCaptureFromPath(errorScreenshot);
        }
    }
    @Test
    public void loginTest(){
        extentTest = extentReports.createTest("Login with valid details");
        try{
        List<Map<String, String>> users = CsvReader.readCsvAsMap("scenario.csv");
        for(Map<String, String> user : users) {
            searchPage = loginPage.logIntoApplication(
                    user.get("userName"),
                    user.get("password"));
            }
            String screenshotPath = screenshots.captureScreenshot("valid-login");
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
