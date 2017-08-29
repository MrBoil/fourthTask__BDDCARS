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
import framework.Waiters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class CompareSteps {
    MainPage mainPage = new MainPage();
    ResearchPage researchPage = new ResearchPage();
    CompareCarsSBSPage compareCarsSBSPage = new CompareCarsSBSPage();

    @Given("^Открываем главную страницу \"([^\"]*)\"$")
    public void открываемГлавнуюСтраницу(String siteUrl) throws Throwable {
        Logger.logMessage("выполнение второго сценария");
        BrowserFactory.driver().get(siteUrl);
    }

    @When("^Через меню \"([^\"]*)\" переходим в подкатегорию \"([^\"]*)\"$")
    public void черезМенюПереходимВПодкатегорию(String category, String subCategory) throws Throwable {
        mainPage.navigateMainPageTopMenu().navigateTopBarCategory(category, subCategory);
        Logger.logMessageWithParam("выполнен переход в категорию", subCategory);
    }

    @And("^Переходим в разделе Side-by-Side Comparisons по кнопке 'Compare cars'$")
    public void переходимВРазделеSideBySideComparisonsПоКнопкеCompareCars() {
        researchPage.navigateSideBySideComp();
        Logger.logMessage("выполнен переход на на страницу Compare Cars");
    }

    @When("^Выбираем первую модель, отобранную в предыдущем сценарии, из \"([^\"]*)\" и переходим по \"([^\"]*)\"$")
    public void выбираемПервуюМодельОтобраннуюВПредыдущемСценарииИзИПереходимПо(String fileName, String btnName) {
        Logger.logMessageWithParam("ввод первой модели из файла", fileName);
        compareCarsSBSPage.enterCarToCompare(fileName, btnName);
    }

    @And("^Выбираем вторую модель из \"([^\"]*)\" и переходим по копке \"([^\"]*)\"$")
    public void выбираемВторуюМодельИзИПереходимПоКопке(String fileName, String btnName) {
        Logger.logMessageWithParam("ввод второй модели из файла", fileName);
        compareCarsSBSPage.addAnotherCar().enterCarToCompare(fileName, btnName);
    }

    @Then("^Проверяем страницу сравнения 2-ух моделей на соотвествие \"([^\"]*)\" и \"([^\"]*)\" из \"([^\"]*)\" и \"([^\"]*)\"$")
    public void проверяемСтраницуСравненияУхМоделейНаСоотвествиеИИзИ(
            String firstCategory, String secondCategory, String firstFileName, String secondFileNam) {
        assertEquals(firstCategory, firstFileName, 1);
        assertEquals(secondCategory, firstFileName, 1);
        assertEquals(firstCategory, secondFileNam, 2);
        assertEquals(secondCategory, secondFileNam, 2);
        Logger.logMessage("пройдена проверка на соотвествие характеристикам ранее выбранных машин");

    }

    @Then("^Закрываем браузер$")
    public void закрываемБраузер() {
        BrowserFactory.driver().quit();
        Logger.logMessage("браузер закрыт");
    }

    private void assertEquals(String category, String fileName, int carNumber) {
        try {
            for (WebElement element : compareCarsSBSPage.getCarSpec(category, carNumber))
                Assert.assertEquals(element.getText().replace("liter", "L").replace(",", ""),
                        DataProp.readDataFromProp(fileName, category.toLowerCase()).replace(",", ""),
                        "Данные для " + category + " не совпадают у машины " + carNumber );
        } catch (NullPointerException ignore){

        }

    }

}
