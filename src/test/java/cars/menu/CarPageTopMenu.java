package cars.menu;

import framework.element.Label;
import org.openqa.selenium.By;

public class CarPageTopMenu {
    private Label lblOfTopBar;
    private Label lblInsideTB;
    private String locOfTopBarLabel = "//li[@cui-nav-option-parent-list-item = \"%s\"]";
    private String locOfLabelInsideTopBar = "//div[@class = 'global-nav__sub-nav']//a[text() = \"%s\"]";

    public CarPageTopMenu navigateTopBarCategory(final String category) {
        lblOfTopBar = new Label(By.xpath(String.format(locOfTopBarLabel, category)));
        lblOfTopBar.clickViaAction();
        return this;
    }

    public CarPageTopMenu navigateInsideTopBar(final String category) {
        System.out.println(String.format(locOfLabelInsideTopBar, category));
        lblInsideTB = new Label(By.xpath(String.format(locOfLabelInsideTopBar, category)));
        lblInsideTB.clickViaAction();
        return this;
    }

}
