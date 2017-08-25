package cars.steps;

import cars.forms.MainPage;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import framework.BrowserFactory;
import framework.DataFromJson;
import org.openqa.selenium.WebDriver;

public class CarsSetps implements En {
    WebDriver driver;
    MainPage mainPage;

    public CarsSetps() {
        Given("^setUp a driver$", () -> {
            driver = BrowserFactory.instanceBrowserFactory();
            driver.get(DataFromJson.getSiteURL());
            throw new PendingException();
        });
        And("^navigate to RST$", () -> {
            mainPage.navigateSearchMenu().navigateSpecs();
            throw new PendingException();
        });
    }

}
