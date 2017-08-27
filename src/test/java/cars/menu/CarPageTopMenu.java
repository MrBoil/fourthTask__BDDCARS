package cars.menu;

import framework.Waiters;
import framework.element.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CarPageTopMenu {
    private Label lblOfTopBar;
    private Label lblInsideTB;
    private String locOfTopBarLabel = "//a[contains(text(), \'%s\')]";
    private String locOfLabelInsideTopBar = "//div[@class = 'global-nav__sub-nav']//a[text() = \'%s\']";

    public CarPageTopMenu navigateTopBarCategory(final String category, final String subCategory) {
        lblOfTopBar = new Label(By.xpath(String.format(locOfTopBarLabel, category)));
        Waiters.wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath(String.format(locOfTopBarLabel, category))));
        Waiters.wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath(String.format(locOfTopBarLabel, category))));
        lblOfTopBar.click();
        lblInsideTB = new Label(By.xpath(String.format(locOfLabelInsideTopBar, subCategory)));
        lblInsideTB.clickViaAction();
        return this;
    }

}
