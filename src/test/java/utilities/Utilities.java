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

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static BasePageTest basePageTest;
    protected static LoginPage loginPage;


    public void waitForPageDocumentToBeReady(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
        wait.until(webDriver -> ((JavascriptExecutor)webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
}
