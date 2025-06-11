package testPages;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.CsvReader;
import utilities.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BasePageTest {

    private static final Logger LOG = LoggerFactory.getLogger("BasePageTest.class");
    protected static WebDriver driver;
    protected static Properties properties = new Properties();
    protected LoginPage loginPage;

    @BeforeAll
    public static void setUp(){
        loadProperties();
        initialization();
    }

    private static void loadProperties() {
            try {
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/scenario.properties");
                properties.load(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("X failed to load scenario.properties");
            }

    }


    public static void initialization() {
        String browserName = System.getProperty("browser",properties.getProperty("browser")).toLowerCase();
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("unsupported browser: " + browserName);
        }

        LOG.info("Browser initialized: {}", browserName);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Utilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Utilities.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);

        driver.get(properties.getProperty("baseUrl"));

    }

    @AfterAll
    public static void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

}
