package cars.forms;

import framework.element.Label;
import org.openqa.selenium.By;

public class CarPage {
    private Label lblTrimComp = new Label(By.xpath("//a[contains(text(), 'trim comparison')]"));

    public void navigateTrimComparison(){
        lblTrimComp.clickViaJS();
    }
}
