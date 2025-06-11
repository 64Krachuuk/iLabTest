package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import testPages.BasePageTest;

import java.io.File;
import java.io.IOException;

public class Screenshots extends BasePageTest {
    public String captureScreenshot(String fileName) {
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
