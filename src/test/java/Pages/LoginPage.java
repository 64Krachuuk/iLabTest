package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testPages.BasePageTest;


public class LoginPage extends BasePageTest {
    @FindBy(id = "username")
    WebElement userNameField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(id = "login")
    WebElement loginField;

    public LoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
//        BasePageTest.driver = driver;
    }
    public String validateLoginPageTitle(){
        return driver.getTitle();
    }
    public void setUserNameField(String username){
        userNameField.clear();
        userNameField.sendKeys(username);
    }
    public void setPasswordField(String password){
        passwordField.clear();
        passwordField.sendKeys(password);
    }
    public SearchPage clickLoginButton() throws InterruptedException {
        Thread.sleep(20);
        loginField.click();
        return new SearchPage(driver);
    }

    //convenience method.
    public SearchPage logIntoApplication(String username, String password) throws InterruptedException {
        setUserNameField(username);
        setPasswordField(password);
        return clickLoginButton();
    }

    //TODO enter error message

}
