package utilities;

import Pages.LoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import testPages.BasePageTest;

import java.time.Duration;

public class Utilities {
    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 10;

    public WebDriver driver;
    public WebDriverWait wait;
    public BasePageTest basePageTest;
    public LoginPage loginPage;

    public Utilities(WebDriver driver) {
        this.driver = driver;
        this.loginPage = new LoginPage(driver);
    }
    public void waitForPageDocumentToBeReady(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        wait.until(webDriver -> ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
}
