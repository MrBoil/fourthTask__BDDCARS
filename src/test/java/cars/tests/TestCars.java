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
    MainPage mainPage;
    CarPageMenu carPageMenu;
    ReadSpecsTab readSpecsTab;
    CarTrimComparison carTrimComparison;
    MainPageTopMenu mainPageTopMenu;
    ShopPage shopPage;
    CompareCarsSBSPage compareCarsSBSPage;

    @BeforeTest
    public void setUp() {
        driver = BrowserFactory.instanceBrowserFactory();
        driver.get(DataFromJson.getSiteURL());
    }

    @AfterTest
    public void close(){
        //driver.quit();
    }

    @Test
    public void testCompareCars() throws InterruptedException {
        mainPage = new MainPage();
        mainPage.navigateSearchMenu().chooseCategory("Specs & Reviews");
        readSpecsTab = new ReadSpecsTab();
        readSpecsTab.selectRandomMakeModelYearAndSave("firstCar");
        carPageMenu = new CarPageMenu();
        carPageMenu.navigateCategory("Trims", "firstCar").navigateTrimComparison();
        carTrimComparison = new CarTrimComparison();
        carTrimComparison.saveCarSpecs("firstCar");

        driver.get(DataFromJson.getSiteURL());
        mainPage = new MainPage();
        mainPage.navigateSearchMenu().chooseCategory("Specs & Reviews");
        readSpecsTab = new ReadSpecsTab();
        readSpecsTab.selectRandomMakeModelYearAndSave("secondCar");
        carPageMenu = new CarPageMenu();
        carPageMenu.navigateCategory("Trims", "secondCar").navigateTrimComparison();
        carTrimComparison = new CarTrimComparison();
        carTrimComparison.saveCarSpecs("secondCar");

        driver.get(DataFromJson.getSiteURL());
        mainPageTopMenu = new MainPageTopMenu();
        mainPageTopMenu.navigateTopBarCategory("Buy", "Find Cars for Sale");

        shopPage = new ShopPage();
        shopPage.navigateSideBySideComp();

        compareCarsSBSPage = new CompareCarsSBSPage();
        compareCarsSBSPage.enterCarToCompare("firstCar", "Start Comparing Now")
                .addAnotherCar().enterCarToCompare("secondCar", "Done");
    }
}
