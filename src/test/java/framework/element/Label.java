package framework.element;

import framework.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

public class Label extends BaseElement {

    public Label(final By locator) {
        super(locator);
    }

    public void moveToElementAction() {
        new Actions(BrowserFactory.driver())
                .moveToElement(getElement(), 1, 1).build().perform();
    }

    public void submit() {
        getElement().submit();
    }

}
