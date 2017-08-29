package cars.menu;

import framework.element.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class MainPageTopMenu {
    private Label lblOfTopBar;
    private Label lblInsideTB;
    private String locOfTopBarLabel = "//*[contains(text(), \'%s\')]";
    private String locOfLabelInsideTopBar = "//div[@class = 'global-nav__sub-nav']//a[text() = \'%s\']";

    public MainPageTopMenu navigateTopBarCategory(final String category, final String subCategory) {
        lblOfTopBar = new Label(By.xpath(String.format(locOfTopBarLabel, category)));
        lblInsideTB = new Label(By.xpath(String.format(locOfLabelInsideTopBar, subCategory)));
        try {
            lblOfTopBar.clickViaJS();
            lblInsideTB.clickViaJS();
        } catch (TimeoutException e) {
            lblOfTopBar.clickViaAction();
            lblInsideTB.clickViaAction();
        }
        return this;
    }

}
