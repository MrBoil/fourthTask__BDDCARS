package cars.steps;

import cars.forms.*;
import cucumber.api.PendingException;
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

    @Given("^We configure the browser and open the main page \"([^\"]*)\"$")
    public void weConfigureTheBrowserAndOpenTheMainPage(String siteUrl) {
        driver = BrowserFactory.instanceBrowserFactory();
        this.siteUrl = siteUrl;
        Logger.logMessageWithParam("переход на главную страницу сайта", siteUrl);
        BrowserFactory.driver().get(siteUrl);
    }

    @When("^We go to the category \"([^\"]*)\", to search for a machine$")
    public void weGoToTheCategoryToSearchForAMachine(String category) {
        categoryMainPageMenu = category;
        mainPage.navigateSearchMenu().chooseCategory(category);
        Logger.logMessageWithParam("сайт успешно открыт и выбранна категория", category);
    }

    @And("^Enter random data of the model, brand and year of manufacture of the machine and store them in ([^\"]*)$")
    public void enterRandomDataOfTheModelBrandAndYearOfManufactureOfTheMachineAndStoreThemInFile(String fileName) {
        Logger.logMessageWithParam("ввод случайных значений машины и сохранение их в файл", fileName);
        readSpecsTab.selectRandomMakeModelYearAndSave(fileName);
    }

    @Then("^If the values are correct, search$")
    public void ifTheValuesAreCorrectSearch() throws Throwable {
        Assert.assertNotEquals(readSpecsTab.getSelectedMake(), "Select a Make");
        Assert.assertNotEquals(readSpecsTab.getSelectedModel(), "Select a Model");
        Assert.assertNotEquals(readSpecsTab.getSelectedYear(), "Select a Year");
        Logger.logMessage("значения введены успешно");
        Logger.logMessage("выполняется поиск машины");
        readSpecsTab.search();
    }

    @When("^We go to the \"([^\"]*)\" tab in the menu, but if the tab does not exist, repeat the previous items with the entry in the ([^\"]*)$")
    public void weGoToTheTabInTheMenuButIfTheTabDoesNotExistRepeatThePreviousItemsWithTheEntryInTheFile(String category, String fileName) throws Throwable {
        try {
            carPage.navigateToMenu().navigateCategory(category);
            Logger.logMessageWithParam("переход выполнен успешно в", category);
        } catch (TimeoutException e) {
            Logger.logMessageWithParam("повторный поиск машины, т.к. не была найдена категория", category);
            BrowserFactory.driver().get(siteUrl);
            weGoToTheCategoryToSearchForAMachine(categoryMainPageMenu);
            enterRandomDataOfTheModelBrandAndYearOfManufactureOfTheMachineAndStoreThemInFile(fileName);
            ifTheValuesAreCorrectSearch();
            weGoToTheTabInTheMenuButIfTheTabDoesNotExistRepeatThePreviousItemsWithTheEntryInTheFile(category, fileName);
        }
    }

    @And("^Click on the link to the modification selection page$")
    public void clickOnTheLinkToTheModificationSelectionPage() {
        Logger.logMessage("выполняется переход на страницу выбора модификации");
        carPage.navigateTrimComparison();
    }

    @Then("^The page of the selected model from ([^\"]*)$")
    public void thePageOfTheSelectedModelFromFile(String fileName) {
        Assert.assertEquals(BrowserFactory.driver().getTitle().contains(DataProp.readDataFromProp(fileName, "year") + " " +
                DataProp.readDataFromProp(fileName, "make") + " " +
                DataProp.readDataFromProp(fileName, "model")), true);
        Logger.logMessage("страница соотвествует выбранной ранее машине");
    }

    @When("^we write down the characteristics: Engine, Transmission in ([^\"]*)$")
    public void weWriteDownTheCharacteristicsEngineTransmissionInFile(String fileName) {
        Logger.logMessageWithParam("запись характеристик в файл", fileName);
        carTrimsPage.saveCarSpecs(fileName);
    }

    @Then("^The characteristics are successfully written in ([^\"]*)$")
    public void theCharacteristicsAreSuccessfullyWrittenInFile(String fileName) {
        Assert.assertEquals(carTrimsPage.getLblEngineText(), DataProp.readDataFromProp(fileName, "engine"),
                "Произошла ошибка записи Engine в файл: " + fileName);
        Assert.assertEquals(carTrimsPage.getLblTransmissionText(), DataProp.readDataFromProp(fileName, "transmission"),
                "Произошла ошибка записи Transmission в файл: " + fileName);
        Logger.logMessageWithParam("характеристики успешно записаны в", fileName);
    }


}
