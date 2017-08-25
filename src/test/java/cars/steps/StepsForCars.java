package cars.steps;

import cars.forms.CarPage;
import cars.forms.MainPage;
import cars.forms.ReadSpecsTab;
import cars.menu.CarPageMenu;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import framework.BrowserFactory;
import framework.DataFromJson;
import org.openqa.selenium.WebDriver;

public class StepsForCars {
    MainPage mainPage = new MainPage();
    ReadSpecsTab readSpecsTab = new ReadSpecsTab();
    CarPageMenu carPageMenu = new CarPageMenu();
    CarPage carPage = new CarPage();
    @Given("^Открываем главную страницу сайта$")
    public void открываемГлавнуюСтраницуСайта() throws Throwable {
        WebDriver driver = BrowserFactory.instanceBrowserFactory();
        driver.get(DataFromJson.getSiteURL());
    }

    @And("^Переходим в поиск машины по марке/модели/году$")
    public void переходимВПоискМашиныПоМаркеМоделиГоду() throws Throwable {
        mainPage.navigateSearchMenu().navigateSpecs();
    }

    @And("^Заполняем поля рандомными значениями и ищем$")
    public void заполняемПоляРандомнымиЗначениямиИИщем() throws Throwable {
        readSpecsTab.selectRandomMakeModelYearAndSearch();
    }

    @And("^Клацаем Trims$")
    public void клацаемTrims() throws Throwable {
        carPageMenu.navigateTrims().navigateTrimComparison();
        carPage.saveFirstCarSpecs();
    }
}
