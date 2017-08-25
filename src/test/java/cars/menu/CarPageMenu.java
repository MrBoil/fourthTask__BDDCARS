package cars.menu;

import cars.forms.CarPage;
import framework.element.Label;
import org.openqa.selenium.By;

public class CarPageMenu {
    private Label lblTrims = new Label(By.partialLinkText("Trims"));

    public CarPage navigateTrims() {
        lblTrims.clickViaJS();
        return new CarPage();
    }
}
