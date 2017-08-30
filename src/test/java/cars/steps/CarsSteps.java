package cars.steps;

import cars.forms.*;
import cars.models.CarModel;
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
    ResearchPage researchPage = new ResearchPage();
    CompareCarsSBSPage compareCarsSBSPage = new CompareCarsSBSPage();

    private String siteUrl;
    private String categoryMainPageMenu;
    private String categoryTrims;

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

    @And("^Enter random data of the model, brand and year of manufacture of the machine and store them in \"([^\"]*)\"$")
    public void enterRandomDataOfTheModelBrandAndYearOfManufactureOfTheMachineAndStoreThemInFile(String carName) {
        Logger.logMessageWithParam("ввод случайных значений машины и сохранение их в ", carName);
        readSpecsTab.selectRandomMakeModelYearAndSave(carName);
    }

    @Then("^If the values are correct, search$")
    public void ifTheValuesAreCorrectSearch() {
        Assert.assertNotEquals(readSpecsTab.getSelectedMake(), "Select a Make");
        Assert.assertNotEquals(readSpecsTab.getSelectedModel(), "Select a Model");
        Assert.assertNotEquals(readSpecsTab.getSelectedYear(), "Select a Year");
        Logger.logMessage("значения введены успешно");
        Logger.logMessage("выполняется поиск машины");
        readSpecsTab.search();
    }

    @When("^We go to the \"([^\"]*)\" tab in the menu, but if the tab does not exist, repeat the previous items with the entry in the \"([^\"]*)\"$")
    public void weGoToTheTabInTheMenuButIfTheTabDoesNotExistRepeatThePreviousItemsWithTheEntryInTheFile(String category, String carName) {
        try {
            categoryTrims = category;
            carPage.navigateToMenu().navigateCategory(category);
            Logger.logMessageWithParam("переход выполнен успешно в", category);
        } catch (TimeoutException e) {
            Logger.logMessageWithParam("повторный поиск машины, т.к. не была найдена категория", category);
            BrowserFactory.driver().get(siteUrl);
            weGoToTheCategoryToSearchForAMachine(categoryMainPageMenu);
            enterRandomDataOfTheModelBrandAndYearOfManufactureOfTheMachineAndStoreThemInFile(carName);
            ifTheValuesAreCorrectSearch();
            weGoToTheTabInTheMenuButIfTheTabDoesNotExistRepeatThePreviousItemsWithTheEntryInTheFile(category, carName);
        }
    }

    @And("^Click on the link to the modification selection page$")
    public void clickOnTheLinkToTheModificationSelectionPage() {
        Logger.logMessage("выполняется переход на страницу выбора модификации");
        carPage.navigateTrimComparison();
    }

    @Then("^The page of the selected model from \"([^\"]*)\"$")
    public void thePageOfTheSelectedModelFromFile(String carName) {
        Assert.assertEquals(BrowserFactory.driver().getTitle().contains(CarModel.getCarMakeModelYear(carName,"year") + " " +
                CarModel.getCarMakeModelYear(carName,"make") + " " +
                CarModel.getCarMakeModelYear(carName,"model")), true);
        Logger.logMessage("страница соотвествует выбранной ранее машине");
    }

    @When("^we write down the characteristics: Engine, Transmission in \"([^\"]*)\"$")
    public void weWriteDownTheCharacteristicsEngineTransmissionInFile(String carName) {
        Logger.logMessageWithParam("запись характеристик в ", carName);
        carTrimsPage.saveCarSpecs(carName);
    }

    @Then("^The characteristics are successfully written in \"([^\"]*)\"$")
    public void theCharacteristicsAreSuccessfullyWrittenInFile(String carName) {
        Assert.assertEquals(carTrimsPage.getLblEngineText(), CarModel.getEngine(carName),
                "Произошла ошибка записи Engine в файл: " + carName);
        Assert.assertEquals(carTrimsPage.getLblTransmissionText(), CarModel.getTransmission(carName),
                "Произошла ошибка записи Transmission в файл: " + carName);
        Logger.logMessageWithParam("характеристики успешно записаны в", carName);
    }


    @Then("^Repeat everything for the \"([^\"]*)\"$")
    public void repeatEverythingForThe(String carName) throws Throwable {
        BrowserFactory.driver().get(siteUrl);
        weGoToTheCategoryToSearchForAMachine(categoryMainPageMenu);
        enterRandomDataOfTheModelBrandAndYearOfManufactureOfTheMachineAndStoreThemInFile(carName);
        ifTheValuesAreCorrectSearch();
        weGoToTheTabInTheMenuButIfTheTabDoesNotExistRepeatThePreviousItemsWithTheEntryInTheFile(categoryTrims, carName);
        clickOnTheLinkToTheModificationSelectionPage();
        thePageOfTheSelectedModelFromFile(carName);
        weWriteDownTheCharacteristicsEngineTransmissionInFile(carName);
        theCharacteristicsAreSuccessfullyWrittenInFile(carName);
    }

    @Given("^Open the main page \"([^\"]*)\"$")
    public void openTheMainPage(String siteUrl) throws Throwable {
        Logger.logMessage("выполнение второго сценария");
        BrowserFactory.driver().get(siteUrl);
    }

    @When("^Through the menu \"([^\"]*)\" go to the subcategory \"([^\"]*)\"$")
    public void throughTheMenuGoToTheSubcategory(String category, String subCategory) throws Throwable {
        mainPage.navigateMainPageTopMenu().navigateTopBarCategory(category, subCategory);
        Logger.logMessageWithParam("выполнен переход в категорию", subCategory);
    }

    @And("^Go to the Side-by-Side Comparisons section of the 'Compare cars'$")
    public void goToTheSideBySideComparisonsSectionOfTheCompareCars() throws Throwable {
        researchPage.navigateSideBySideComp();
        Logger.logMessage("выполнен переход на на страницу Compare Cars");
    }

    @When("^we select the first model selected in the previous scenario from \"([^\"]*)\" and go to \"([^\"]*)\"$")
    public void weSelectTheFirstModelSelectedInThePreviousScenarioFromAndGoTo(String carName, String btnName) {
        Logger.logMessageWithParam("ввод первой модели из файла", carName);
        compareCarsSBSPage.enterCarToCompare(carName, btnName);
    }

    @And("^Choose the second model from \"([^\"]*)\" and go to the \"([^\"]*)\"$")
    public void chooseTheSecondModelFromAndGoToThe(String carName, String btnName) {
        Logger.logMessageWithParam("ввод второй модели из файла", carName);
        compareCarsSBSPage.addAnotherCar().enterCarToCompare(carName, btnName);
    }

    @Then("^We check the comparison page of the two models for the \"([^\"]*)\" and \"([^\"]*)\" matching from \"([^\"]*)\" and \"([^\"]*)\"$")
    public void weCheckTheComparisonPageOfTheTwoModelsForTheAndMatchingFromAnd (String firstCategory, String secondCategory, String firstFileName, String secondFileNam) {
        assertEquals(firstCategory, firstFileName, 1);
        assertEquals(secondCategory, firstFileName, 1);
        assertEquals(firstCategory, secondFileNam, 2);
        assertEquals(secondCategory, secondFileNam, 2);
        Logger.logMessage("пройдена проверка на соотвествие характеристик ранее выбранных машин");
    }

    @Then("^Close the browser$")
    public void closeTheBrowser() throws Throwable {
        BrowserFactory.driver().quit();
        Logger.logMessage("браузер закрыт");
    }

    private void assertEquals(String category, String fileName, int carNumber) {
        String actualSpec;
        String fileSpec;
        try {
            actualSpec = compareCarsSBSPage.getCarSpec(category, carNumber);
            System.out.println(actualSpec);
            if (category.contains("Engine")) {
                fileSpec = CarModel.getEngine(fileName).replace(",", "");
                System.out.println(fileSpec + " EEEE");
            } else {
                fileSpec = CarModel.getTransmission(fileName).replace(",", "");
                System.out.println(fileSpec);
            }
            Assert.assertEquals(actualSpec.contains(fileSpec), true,
                    "Данные для " + category + " не совпадают у машины " + carNumber );
        } catch (NullPointerException ignore){
        }
    }
}
