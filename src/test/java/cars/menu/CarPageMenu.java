package cars.menu;

import cars.forms.CarPage;
import cars.forms.ReadSpecsTab;
import framework.BrowserFactory;
import framework.element.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class CarPageMenu {
    private Label lblTrims = new Label(By.partialLinkText("Trims"));

    public CarPage navigateTrims() {
        try {
            lblTrims.clickViaJS();
        } catch (TimeoutException e) {
            e.printStackTrace();
            BrowserFactory.driver().navigate().back();
            new ReadSpecsTab().selectRandomMakeModelYearAndSearch();
        }
        return new CarPage();
    }
}
