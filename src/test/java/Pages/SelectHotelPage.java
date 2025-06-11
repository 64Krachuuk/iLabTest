package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testPages.BasePageTest;

import java.util.List;

public class SelectHotelPage extends BasePageTest {
    private static final Logger LOG = LoggerFactory.getLogger("SelectHotelPage.class");
    @FindBy(className = "login_title")
    WebElement selectHotelPageTitle;
    @FindBy(xpath = "/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table")
    WebElement table;

    public SelectHotelPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public boolean locationColumn(String selectedLocation){
        List<WebElement> rows = driver.findElements(
                By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table"));

        Boolean allContainsSelectedLocation = true;
        for (WebElement row : rows){
            WebElement locationCell = row.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]"));
            String locationText = locationCell.getText();

            if(!locationText.contains(selectedLocation)){
                LOG.info("Non-" + selectedLocation +"row found :" +locationText );
                allContainsSelectedLocation = false;
                break;
            }
        }
        if (allContainsSelectedLocation){
            LOG.info("All locations contain: " + selectedLocation);
            return true;
        }else{
            LOG.error("Some locations do not contain: " + selectedLocation);
            return false;
        }
    }

}
