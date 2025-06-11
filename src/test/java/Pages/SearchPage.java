package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import testPages.BasePageTest;
import utilities.Utilities;

public class SearchPage extends BasePageTest {
    @FindBy(xpath = "//td[@class='login_title']")
    WebElement searchPageTitle;
    @FindBy(id = "location")
    WebElement locationDropDown;
    @FindBy(id = "Submit")
    WebElement searchButton;

    public Utilities utilities = new Utilities();

    public SearchPage(){
        PageFactory.initElements(driver,this);
    }
    public boolean verifySearchPageTitle(){
        return searchPageTitle.isDisplayed();
    }

    public SelectHotelPage clickSearchButton(){
        searchButton.click();
        return new SelectHotelPage();
    }
    //convience method
    public SelectHotelPage searchHotel(String hotelLocation){
        Select select = new Select(locationDropDown);
        select.selectByVisibleText(hotelLocation);
        //add screenshot

        return clickSearchButton();
    }


}
