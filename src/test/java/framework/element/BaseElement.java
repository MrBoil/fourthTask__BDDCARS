package framework.element;

import framework.BrowserFactory;
import framework.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public abstract class BaseElement {

    protected By locator;

    protected BaseElement(final By locator) {
        this.locator = locator;
    }

    public WebElement getElement(){
        Waiters.waitForVisOfElementByLoc(locator);
        return BrowserFactory.driver().findElement(locator);
    }

    public List<WebElement> getElements(){
        Waiters.waitForPreOfAllElementsByLoc(locator);
        return BrowserFactory.driver().findElements(locator);
    }

    public void click() {
        getElement().click();
    }

    public void clickViaJS() {
        ((JavascriptExecutor) BrowserFactory.driver()).executeScript("arguments[0].click()",
                getElement());
    }

    public void clickViaAction() {
        new Actions(BrowserFactory.driver()).moveToElement(
                getElement(), 1, 1).click().build().perform();
    }

    public boolean isEnabled() {
        return getElement().isEnabled();
    }

    public String getText() {
        return getElement().getText();
    }
}
