package cars.steps;

import cars.forms.CarPage;
import cars.forms.MainPage;
import cars.forms.ReadSpecsTab;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BrowserFactory;
import framework.DataProp;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CarsSteps {
    WebDriver driver;
    MainPage mainPage = new MainPage();
    ReadSpecsTab readSpecsTab = new ReadSpecsTab();
    CarPage carPage = new CarPage();

    @Given("^Производим настройку браузера и открываем главную страницу \"([^\"]*)\"$")
    public void производимНастройкуБраузераИОткрываемГлавнуюСтраницу(String siteUrl) {
        driver = BrowserFactory.instanceBrowserFactory();
        driver.get(siteUrl);
    }

    @When("^Переходим в категорию \"([^\"]*)\", для поиска машины$")
    public void переходимВКатегориюДляПоискаМашины(String category) {
        mainPage.navigateSearchMenu().chooseCategory(category);
    }

    @And("^Вводим случайные данные модели, марки и года выпуска машины и сохраняем их в ([^\"]*)$")
    public void вводимСлучайныеДанныеМоделиМаркиИГодаВыпускаМашиныИСохраняемИхВФайл(String fileName) {
        readSpecsTab.selectRandomMakeModelYearAndSave(fileName);
    }

    @Then("^Если начения успешно введены, то выполним поиск$")
    public void еслиНаченияУспешноВведеныТоВыполнимПоиск() throws Throwable {
        Assert.assertNotEquals(readSpecsTab.getSelectedMake(), "Select a Make");
        Assert.assertNotEquals(readSpecsTab.getSelectedModel(), "Select a Model");
        Assert.assertNotEquals(readSpecsTab.getSelectedYear(), "Select a Year");
        readSpecsTab.search();
    }

    @Then("^Успешно открылась страница выбранной модели$")
    public void успешноОткрыласьСтраницаВыбраннойМодели() {
        Assert.assertEquals(BrowserFactory.driver().getTitle().contains(
                DataProp.readDataFromProp("firstCar", "year") + " " +
                        DataProp.readDataFromProp("firstCar", "make") + " " +
                DataProp.readDataFromProp("firstCar", "model")), true);
    }

    @When("^Переходим в меню во вкладку \"([^\"]*)\", но если вкладка отсуствует,повторить предыдущий пункт с записью в ([^\"]*)$")
    public void переходимВМенюВоВкладкуНоЕслиВкладкаОтсуствуетПовторитьПредыдущийПунктСЗаписьюВФайл(String category, String fileName) throws Throwable {
        carPage.navigateToMenu().navigateCategory(category, fileName);
    }
}
