package cars.menu;

import cars.forms.CarPage;
import cars.forms.MainPage;
import cars.forms.ReadSpecsTab;
import cucumber.api.java.eo.Se;
import framework.BrowserFactory;
import framework.element.Label;
import gherkin.lexer.Ca;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class CarPageMenu {
    private Label lblTrims;

    public CarPage navigateCategory(final String category, final String emergencyFileName) {
        try {
            lblTrims = new Label(By.partialLinkText(category));
            lblTrims.clickViaJS();
        } catch (TimeoutException e) {
            e.printStackTrace();
            BrowserFactory.driver().navigate().back();
            new SearchMenu().chooseCategory("Specs & Reviews");
            new ReadSpecsTab().selectRandomMakeModelYearAndSearch(emergencyFileName);
            new CarPageMenu().navigateCategory(category, emergencyFileName);
        }
        return new CarPage();
    }
}
