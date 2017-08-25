package framework.element;

import framework.BrowserFactory;
import org.openqa.selenium.By;

public class Select extends BaseElement {
    org.openqa.selenium.support.ui.Select select;

    public Select(By locator) {
        super(locator);
        select = new org.openqa.selenium.support.ui.Select(BrowserFactory.driver().findElement(locator));
    }

    public void setValidBirthDate(final String yourYear) {
        select.selectByValue(yourYear);
    }

}
