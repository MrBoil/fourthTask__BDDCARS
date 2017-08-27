package cars.steps;

import cars.forms.MainPage;
import cars.forms.ReadSpecsTab;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CarsSteps {
    WebDriver driver;
    MainPage mainPage = new MainPage();
    ReadSpecsTab readSpecsTab = new ReadSpecsTab();

    @Given("^Производим настройку браузера и открываем главную страницу \"([^\"]*)\"$")
    public void производимНастройкуБраузераИОткрываемГлавнуюСтраницу(String siteUrl) {
        driver = BrowserFactory.instanceBrowserFactory();
        driver.get(siteUrl);
    }

    @When("^Переходим в категорию \"([^\"]*)\", для поиска машины$")
    public void переходимВКатегориюДляПоискаМашины(String category) {
        mainPage.navigateSearchMenu().chooseCategory(category);
    }

    @Then("^Категория \"([^\"]*)\" упешно открыта$")
    public void категорияУпешноОткрыта(String category) {

    }
}
