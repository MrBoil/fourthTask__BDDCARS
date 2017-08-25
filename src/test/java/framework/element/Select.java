package framework.element;

import framework.BrowserFactory;
import framework.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Select extends BaseElement {
    org.openqa.selenium.support.ui.Select select;

    public Select(By locator) {
        super(locator);
    }

    private org.openqa.selenium.support.ui.Select instanceSelect() {
        return new org.openqa.selenium.support.ui.Select(getElement());
    }

    public void setValueByIndex(final int value) {
        select = instanceSelect();
        select.selectByIndex(value);
    }

    public List<WebElement> getAllOptions() {
        select = instanceSelect();
        return select.getOptions();
    }

}
