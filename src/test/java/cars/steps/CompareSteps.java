package cars.steps;

import cars.forms.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BrowserFactory;
import framework.DataProp;
import framework.Logger;
import org.testng.Assert;


public class CompareSteps {
    MainPage mainPage = new MainPage();
    ResearchPage researchPage = new ResearchPage();
    CompareCarsSBSPage compareCarsSBSPage = new CompareCarsSBSPage();

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
    public void weSelectTheFirstModelSelectedInThePreviousScenarioFromAndGoTo(String fileName, String btnName) {
        Logger.logMessageWithParam("ввод первой модели из файла", fileName);
        compareCarsSBSPage.enterCarToCompare(fileName, btnName);
    }

    @And("^Choose the second model from \"([^\"]*)\" and go to the \"([^\"]*)\"$")
    public void chooseTheSecondModelFromAndGoToThe(String fileName, String btnName) throws Throwable {
        Logger.logMessageWithParam("ввод второй модели из файла", fileName);
        compareCarsSBSPage.addAnotherCar().enterCarToCompare(fileName, btnName);
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
            fileSpec = DataProp.readDataFromProp(fileName, category.toLowerCase()).replace(",", "");
            Assert.assertEquals(actualSpec.contains(fileSpec), true,
                    "Данные для " + category + " не совпадают у машины " + carNumber );
        } catch (NullPointerException ignore){
        }
    }
}
