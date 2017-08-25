package cars.tests;

import cars.forms.CarPage;
import cars.forms.MainPage;
import cars.forms.ReadSpecsTab;
import cars.menu.CarPageMenu;
import framework.BrowserFactory;
import framework.DataFromJson;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class TestCars {
    WebDriver driver;
    MainPage mainPage;
    CarPageMenu carPageMenu;
    ReadSpecsTab rsf;
    CarPage carPage;

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
    public void testCompareCars() throws InterruptedException {
        mainPage = new MainPage();
        mainPage.navigateSearchMenu().navigateSpecs();
        rsf = new ReadSpecsTab();
        rsf.selectRandomMakeModelYearAndSearch();
        carPageMenu = new CarPageMenu();
        carPageMenu.navigateTrims().navigateTrimComparison();
        carPage = new CarPage();
        carPage.saveFirstCarSpecs();
    }
}
