package cars.menu;

import framework.element.Label;
import org.openqa.selenium.By;

public class SearchMenu {
    private Label lblSpecs;
    private final String locOfCategoryInsideSearchMenu = "//a[contains(text(), \'%s\')]";

    public void chooseCategory(final String category) {
        lblSpecs = new Label(By.xpath(String.format(locOfCategoryInsideSearchMenu, category)));
        lblSpecs.click();
    }

    public boolean rightCategoryIsSelected(final String category) {
        return new Label(By.xpath(String.format(locOfCategoryInsideSearchMenu, category))).isSelected();
    }

}
