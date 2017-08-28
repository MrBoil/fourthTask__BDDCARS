package cars.steps;

import cars.forms.CarPage;
import cars.forms.CompareCarsSBSPage;
import cars.forms.MainPage;
import cars.forms.ShopPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import framework.BrowserFactory;
import org.openqa.selenium.WebDriver;

public class CompareSteps {
    MainPage mainPage = new MainPage();
    ShopPage shopPage = new ShopPage();
    CompareCarsSBSPage compareCarsSBSPage = new CompareCarsSBSPage();

    @Given("^Открываем главную страницу \"([^\"]*)\"$")
    public void открываемГлавнуюСтраницу(String siteUrl) throws Throwable {
        WebDriver driver = BrowserFactory.instanceBrowserFactory();
        BrowserFactory.driver().get(siteUrl);
    }

    @When("^Через меню \"([^\"]*)\" переходим в подкатегорию \"([^\"]*)\"$")
    public void черезМенюПереходимВПодкатегорию(String category, String subCategory) throws Throwable {
        mainPage.navigateMainPageTopMenu().navigateTopBarCategory(category, subCategory);
    }

    @And("^Переходим в разделе Side-by-Side Comparisons по кнопке 'Compare cars'$")
    public void переходимВРазделеSideBySideComparisonsПоКнопкеCompareCars() {
        //shopPage.navigateSideBySideComp();
        BrowserFactory.driver().get("https://www.cars.com/research/compare");
    }

    @Then("^Открылась страница для выбора авто для сравнения$")
    public void открыласьСтраницаДляВыбораАвтоДляСравнения() {
        // Write code here that turns the phrase above into concrete actions

    }

    @When("^Выбираем первую модель, отобранную в предыдущем сценарии, из \"([^\"]*)\" и переходим по \"([^\"]*)\"$")
    public void выбираемПервуюМодельОтобраннуюВПредыдущемСценарииИзИПереходимПо(String fileName, String btnName) {
        compareCarsSBSPage.enterCarToCompare(fileName, btnName);
    }

    @And("^Выбираем вторую модель из \"([^\"]*)\" и переходим по копке \"([^\"]*)\"$")
    public void выбираемВторуюМодельИзИПереходимПоКопке(String fileName, String btnName) {
        compareCarsSBSPage.addAnotherCar().enterCarToCompare(fileName, btnName);
    }

    @When("^Проверяем страницу сравнения 2-ух моделей на соотвествие характеристикам из \"([^\"]*)\" и \"([^\"]*)\"$")
    public void проверяемСтраницуСравненияУхМоделейНаСоотвествиеХарактеристикамИзИ(String firstFileName, String secondFileName) {

    }

    @Then("^Закрываем браузер$")
    public void закрываемБраузер() {
        BrowserFactory.driver().quit();
    }
}
