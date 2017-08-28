package cars.steps;

import cars.forms.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BrowserFactory;
import framework.DataProp;
import framework.Logger;
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
        Logger.logMessageWithParam("переход на главную страницу сайта", siteUrl);
        BrowserFactory.driver().get(siteUrl);
    }

    @When("^Переходим в категорию \"([^\"]*)\", для поиска машины$")
    public void переходимВКатегориюДляПоискаМашины(String category) {
        categoryMainPageMenu = category;
        mainPage.navigateSearchMenu().chooseCategory(category);
        Logger.logMessageWithParam("сайт успешно открыт и выбранна категория", category);
    }

    @And("^Вводим случайные данные модели, марки и года выпуска машины и сохраняем их в ([^\"]*)$")
    public void вводимСлучайныеДанныеМоделиМаркиИГодаВыпускаМашиныИСохраняемИхВФайл(String fileName) {
        Logger.logMessageWithParam("ввод случайных значений машины и сохранение их в файл", fileName);
        readSpecsTab.selectRandomMakeModelYearAndSave(fileName);
    }

    @Then("^Если значения успешно введены, то выполним поиск$")
    public void еслиЗначенияУспешноВведеныТоВыполнимПоиск() {
        Assert.assertNotEquals(readSpecsTab.getSelectedMake(), "Select a Make");
        Assert.assertNotEquals(readSpecsTab.getSelectedModel(), "Select a Model");
        Assert.assertNotEquals(readSpecsTab.getSelectedYear(), "Select a Year");
        Logger.logMessage("значения введены успешно");
        Logger.logMessage("выполняется поиск машины");
        readSpecsTab.search();
    }

    @When("^Переходим в меню во вкладку \"([^\"]*)\", но если вкладка отсуствует,повторить предыдущие пункты с записью в ([^\"]*)$")
    public void переходимВМенюВоВкладкуНоЕслиВкладкаОтсуствуетПовторитьПредыдущиеПунктыСЗаписьюВФайл(String category, String fileName) {
        try {
            Logger.logMessageWithParam("выполняется переход в", category);
            carPage.navigateToMenu().navigateCategory(category);
            Logger.logMessage("переход выполнен успешно");
        } catch (TimeoutException e) {
            Logger.logMessageWithParam("повторный поиск машины, т.к. не была найдена категория", category);
            BrowserFactory.driver().get(siteUrl);
            переходимВКатегориюДляПоискаМашины(categoryMainPageMenu);
            вводимСлучайныеДанныеМоделиМаркиИГодаВыпускаМашиныИСохраняемИхВФайл(fileName);
            еслиЗначенияУспешноВведеныТоВыполнимПоиск();
            переходимВМенюВоВкладкуНоЕслиВкладкаОтсуствуетПовторитьПредыдущиеПунктыСЗаписьюВФайл(category, fileName);
        }
    }

    @And("^Переходим по ссылке на страницу выбора модификации$")
    public void переходимПоСсылкеНаСтраницуВыбораМодификации() {
        Logger.logMessage("выполняется переход на страницу выбора модификации");
        carPage.navigateTrimComparison();
    }

    @Then("^Успешно открылась страница выбранной модели из ([^\"]*)$")
    public void успешноОткрыласьСтраницаВыбраннойМоделиИзФайл(String fileName) {
        Assert.assertEquals(BrowserFactory.driver().getTitle().contains(DataProp.readDataFromProp(fileName, "year") + " " +
                DataProp.readDataFromProp(fileName, "make") + " " +
                DataProp.readDataFromProp(fileName, "model")), true);
        Logger.logMessage("страница соотвествует выбранной ранее машине");

    }

    @When("^Записываем характеристики: Engine, Transmission в ([^\"]*)$")
    public void записываемХарактеристикиEngineTransmissionВФайлФайл(String fileName) {
        Logger.logMessageWithParam("запись характеристик в файл", fileName);
        carTrimsPage.saveCarSpecs(fileName);
    }

    @Then("^Характеристики успешно записаны в ([^\"]*)$")
    public void характеристикиУспешноЗаписаныВФайл(String fileName) throws Throwable {
        Assert.assertEquals(carTrimsPage.getLblEngineText(), DataProp.readDataFromProp(fileName, "engine"),
                "Произошла ошибка записи Engine в файл: " + fileName);
        Assert.assertEquals(carTrimsPage.getLblTransmissionText(), DataProp.readDataFromProp(fileName, "transmission"),
                "Произошла ошибка записи Transmission в файл: " + fileName);
        Logger.logMessageWithParam("характеристики успешно записаны в", fileName);
    }
}
