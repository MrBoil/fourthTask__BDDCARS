package cars.menu;

import framework.element.Label;
import org.openqa.selenium.By;

public class SearchMenu {
    Label lblSpecs = new Label(By.xpath("//a[text() ='Read Specs & Reviews']"));

    public void navigateSpecs() {
        lblSpecs.click();
    }
}
