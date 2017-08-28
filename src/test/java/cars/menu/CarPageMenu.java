package cars.menu;

import cars.forms.CarPage;
import cars.forms.ReadSpecsTab;
import framework.BrowserFactory;
import framework.element.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class CarPageMenu {
    private Label lblTrims;

    public CarPage navigateCategory(final String category) {
        lblTrims = new Label(By.partialLinkText(category));
        lblTrims.clickViaJS();
        return new CarPage();
    }
}
