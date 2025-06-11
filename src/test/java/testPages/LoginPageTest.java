package testPages;

import Pages.LoginPage;
import Pages.SearchPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.CsvReader;

import java.util.List;
import java.util.Map;


public class LoginPageTest extends BasePageTest{
    LoginPage loginPage;
    SearchPage searchPage;
    @BeforeEach
    void setUpPages(){
        if(driver == null){
            initialization();
        }
        loginPage = new LoginPage(driver);
    }

    public LoginPageTest(){super();}

    @Test
    public void loginPageTitleTest(){
        String title = loginPage.validateLoginPageTitle();
        List<Map<String, String>> users = CsvReader.readCsvAsMap("scenario.csv");
        for(Map<String, String> user : users) {
            Assertions.assertTrue(
                    title.equalsIgnoreCase(user.get("homePageTitle")),
                    "Title does not match the expected value"
            );
        }
    }
    @Test
    public void loginTest(){
        List<Map<String, String>> users = CsvReader.readCsvAsMap("scenario.csv");
        for(Map<String, String> user : users) {
            searchPage = loginPage.logIntoApplication(
                    user.get("userName"),
                    user.get("password"));
        }
    }

}
