package cars.tests;

import cars.forms.*;
import cars.menu.CarPageMenu;
import cars.menu.MainPageTopMenu;
import framework.BrowserFactory;
import framework.DataFromJson;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestCars {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = BrowserFactory.instanceBrowserFactory();
        driver.get("https://www.cars.com/research/compare/?acodes=USA70LES061A0,USC70MSS011A0");
    }

    @AfterTest
    public void close(){
        //driver.quit();
    }

    @Test
    public void testCompareCars() throws InterruptedException {
        CompareCarsSBSPage compareCarsSBSPage = new CompareCarsSBSPage();
        System.out.println(compareCarsSBSPage.getCarSpec("Engine", 1));
        System.out.println(compareCarsSBSPage.getCarSpec("Transmission", 1));
        System.out.println(compareCarsSBSPage.getCarSpec("Engine", 2));
        System.out.println(compareCarsSBSPage.getCarSpec("Transmission", 2));
    }
}
