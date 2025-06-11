package testPages;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        LoginPageTest.class,
        SearchPageTest.class,
        SelectHotelPageTest.class
        })
public class SearchHotelTestSuite {
}
