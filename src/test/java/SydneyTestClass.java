import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.annotations.VisibleForTesting;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;

public class SydneyTestClass {
    WebDriver driver;
    ExtentTest extentTest;
    ExtentReports extentReports;

    @Test
    public void testForSydney(){
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/sydneyTestReport");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://adactinhotelapp.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        try{

//            testWithValidLoginDetails();
            searchHotelPage();

        }finally {
            driver.quit();
            extentReports.flush();
        }

    }

    private void testWithValidLoginDetails() throws InterruptedException {
        extentTest = extentReports.createTest("Login with valid info");
        try{

            WebElement username = driver.findElement(By.id("username"));
            username.clear();
            username.sendKeys("KgodishoRachuene");
            WebElement password = driver.findElement(By.id("password"));
            password.clear();
            password.sendKeys("@RSkp9409");
            WebElement login = driver.findElement(By.id("login"));
            //TODO redirect to Search Hotel page
            login.click();
            //allow for error to display
            Thread.sleep(20);

            //capture screenshot
            String screenshotPath = captureScreenshot("valid-login");
            extentTest.addScreenCaptureFromPath(screenshotPath);

        }catch (Exception e){
            String errorScreenshot = captureScreenshot("exception");
            extentTest.fail("Test failed with exception: " + e.getMessage());
            extentTest.addScreenCaptureFromPath(errorScreenshot);
        }

    }
    //TODO move to independent page
    private void searchHotelPage() {
        extentTest = extentReports.createTest("Hotel search with valid info");
        try{
            WebElement verifyPage = driver.findElement(By.xpath("//td[@class='login_title']"));
            if(verifyPage.toString().equalsIgnoreCase("Search Hotel")){
            WebElement locationDropDown = driver.findElement(By.id("location"));
            Select select = new Select(locationDropDown);
            select.selectByVisibleText("Sydney");
            //capture screenshot
            String screenshotPath = captureScreenshot("Sydney selcted");
            extentTest.addScreenCaptureFromPath(screenshotPath);
            }else{

            }

        }catch (Exception e){
            String errorScreenshot = captureScreenshot("exception");
            extentTest.fail("Test failed with exception: " + e.getMessage());
            extentTest.addScreenCaptureFromPath(errorScreenshot);
        }
    }


    private String captureScreenshot(String fileName) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String destPath = "screenshot/" + fileName + ".png";
        File destFile = new File(destPath);
        try{
            FileHandler.createDir(new File("screenshot"));
            FileHandler.copy(srcFile,destFile);
        }catch (IOException e){
            e.printStackTrace();
        }
        return destPath;
    }
}
