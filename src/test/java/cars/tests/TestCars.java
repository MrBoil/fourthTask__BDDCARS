package cars.tests;

import framework.BrowserFactory;
import framework.DataFromJson;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestCars {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = BrowserFactory.instanceBrowserFactory();
        driver.get(DataFromJson.getSiteURL());
    }

    @AfterTest
    public void close(){
        driver.quit();
    }

    @Test
    public void testCompareCars() {
        System.out.println("done");
    }
}
