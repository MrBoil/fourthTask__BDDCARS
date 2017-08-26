package cars.tests;

import cars.forms.CarPage;
import cars.forms.CarTrimComparison;
import cars.forms.MainPage;
import cars.forms.ReadSpecsTab;
import cars.menu.CarPageMenu;
import cars.menu.CarPageTopMenu;
import framework.BrowserFactory;
import framework.DataFromJson;
import gherkin.lexer.Da;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import javax.xml.crypto.Data;

public class TestCars {
    WebDriver driver;
    MainPage mainPage;
    CarPageMenu carPageMenu;
    ReadSpecsTab rsf;
    CarTrimComparison carTrimComparison;
    CarPageTopMenu carPageTopMenu;

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
        carTrimComparison = new CarTrimComparison();
        carTrimComparison.saveFirstCarSpecs("firstCar");

        /*driver.get(DataFromJson.getSiteURL());
        mainPage = new MainPage();
        mainPage.navigateSearchMenu().navigateSpecs();
        rsf = new ReadSpecsTab();
        rsf.selectRandomMakeModelYearAndSearch();
        carPageMenu = new CarPageMenu();
        carPageMenu.navigateTrims().navigateTrimComparison();
        carTrimComparison = new CarTrimComparison();
        carTrimComparison.saveFirstCarSpecs("secondCar");

        driver.get(DataFromJson.getSiteURL());
        carPageTopMenu = new CarPageTopMenu();
        carPageTopMenu.navigateTopBarCategory("Buy").navigateInsideTopBar("Find Cars for Sale");*/
    }
}
