package cars.tests;

import cars.forms.*;
import cars.menu.CarPageMenu;
import cars.menu.MainPageTopMenu;
import framework.BrowserFactory;
import framework.DataFromJson;
import framework.DataProp;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestCars {
    WebDriver driver;
    CompareCarsSBSPage compareCarsSBSPage = new CompareCarsSBSPage();

    @BeforeTest
    public void setUp() {
        driver = BrowserFactory.instanceBrowserFactory();
        driver.get("https://www.cars.com/research/compare/?acodes=USB80SAC282A0,USC10SUC071A0");
    }

    @AfterTest
    public void close(){
        //driver.quit();
    }

    @Test
    public void testCompareCars() throws InterruptedException {
        assertEquals("Engine", "firstCar", 1);
    }

    private void assertEquals(String category, String fileName, int carNumber) {
        Assert.assertEquals(compareCarsSBSPage.getCarSpec(category, carNumber).replace("liter", "L"),
                DataProp.readDataFromProp(fileName, category.toLowerCase()).replace(",", ""),
                "Данные для " + category + " не совпадают у машины " + carNumber );
    }
}
