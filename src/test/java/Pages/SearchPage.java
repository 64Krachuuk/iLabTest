package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import testPages.BasePageTest;


public class SearchPage extends BasePageTest {
    @FindBy(xpath = "//td[@class='login_title']")
    WebElement searchPageTitle;
    @FindBy(id = "location")
    WebElement locationDropDown;
    @FindBy(id = "Submit")
    WebElement searchButton;


    public SearchPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
    public boolean verifySearchPageTitle(){
        return searchPageTitle.isDisplayed();
    }

    public SelectHotelPage clickSearchButton(){
        searchButton.click();
        return new SelectHotelPage(driver);
    }
    //convience method
    public SelectHotelPage searchHotel(String hotelLocation){
        Select select = new Select(locationDropDown);
        select.selectByVisibleText(hotelLocation);
        //add screenshot

        return clickSearchButton();
    }


}
