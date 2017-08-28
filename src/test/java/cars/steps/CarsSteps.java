package cars.steps;

import cars.forms.CarPage;
import cars.forms.CarTrimsPage;
import cars.forms.MainPage;
import cars.forms.ReadSpecsTab;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BrowserFactory;
import framework.DataProp;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CarsSteps {
    WebDriver driver;
    MainPage mainPage = new MainPage();
    ReadSpecsTab readSpecsTab = new ReadSpecsTab();
    CarPage carPage = new CarPage();
    CarTrimsPage carTrimsPage = new CarTrimsPage();

    private String siteUrl;
    private String categoryMainPageMenu;

    @Given("^Производим настройку браузера и открываем главную страницу \"([^\"]*)\"$")
    public void производимНастройкуБраузераИОткрываемГлавнуюСтраницу(String siteUrl) {
        driver = BrowserFactory.instanceBrowserFactory();
        this.siteUrl = siteUrl;
        BrowserFactory.driver().get(siteUrl);
    }

    @When("^Переходим в категорию \"([^\"]*)\", для поиска машины$")
    public void переходимВКатегориюДляПоискаМашины(String category) {
        categoryMainPageMenu = category;
        mainPage.navigateSearchMenu().chooseCategory(category);
    }

    @And("^Вводим случайные данные модели, марки и года выпуска машины и сохраняем их в ([^\"]*)$")
    public void вводимСлучайныеДанныеМоделиМаркиИГодаВыпускаМашиныИСохраняемИхВФайл(String fileName) {
        readSpecsTab.selectRandomMakeModelYearAndSave(fileName);
    }

    @Then("^Если значения успешно введены, то выполним поиск$")
    public void еслиЗначенияУспешноВведеныТоВыполнимПоиск() {
        Assert.assertNotEquals(readSpecsTab.getSelectedMake(), "Select a Make");
        Assert.assertNotEquals(readSpecsTab.getSelectedModel(), "Select a Model");
        Assert.assertNotEquals(readSpecsTab.getSelectedYear(), "Select a Year");
        readSpecsTab.search();
    }

    @When("^Переходим в меню во вкладку \"([^\"]*)\", но если вкладка отсуствует,повторить предыдущие пункты с записью в ([^\"]*)$")
    public void переходимВМенюВоВкладкуНоЕслиВкладкаОтсуствуетПовторитьПредыдущиеПунктыСЗаписьюВФайл(String category, String fileName) {
        try {
            carPage.navigateToMenu().navigateCategory(category);
        } catch (TimeoutException e) {
            BrowserFactory.driver().get(siteUrl);
            переходимВКатегориюДляПоискаМашины(categoryMainPageMenu);
            вводимСлучайныеДанныеМоделиМаркиИГодаВыпускаМашиныИСохраняемИхВФайл(fileName);
            еслиЗначенияУспешноВведеныТоВыполнимПоиск();
            переходимВМенюВоВкладкуНоЕслиВкладкаОтсуствуетПовторитьПредыдущиеПунктыСЗаписьюВФайл(category, fileName);
        }
    }

    @And("^Переходим по ссылке на страницу выбора модификации$")
    public void переходимПоСсылкеНаСтраницуВыбораМодификации() {
        carPage.navigateTrimComparison();
    }

    @Then("^Успешно открылась страница выбранной модели из ([^\"]*)$")
    public void успешноОткрыласьСтраницаВыбраннойМоделиИзФайл(String fileName) {
        Assert.assertEquals(BrowserFactory.driver().getTitle().contains(DataProp.readDataFromProp(fileName, "year") + " " +
                DataProp.readDataFromProp(fileName, "make") + " " +
                DataProp.readDataFromProp(fileName, "model")), true);
    }

    @When("^Записываем характеристики: Engine, Transmission в ([^\"]*)$")
    public void записываемХарактеристикиEngineTransmissionВФайлФайл(String fileName) {
        carTrimsPage.saveCarSpecs(fileName);
    }

    @Then("^Характеристики успешно записаны в ([^\"]*)$")
    public void характеристикиУспешноЗаписаныВФайл(String fileName) throws Throwable {
        Assert.assertEquals(carTrimsPage.getLblEngineText(), DataProp.readDataFromProp(fileName, "engine"),
                "Произошла ошибка записи Engine в файл: " + fileName);
        Assert.assertEquals(carTrimsPage.getLblTransmissionText(), DataProp.readDataFromProp(fileName, "transmission"),
                "Произошла ошибка записи Transmission в файл: " + fileName);
    }
}
