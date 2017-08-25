package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waiters {

    private static WebDriverWait wait = new WebDriverWait(BrowserFactory.driver(), DataFromJson.getWebDriverWaitTime());

    public static void waitForVisOfElementByLoc(final By locator) {
        BrowserFactory.driver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        BrowserFactory.driver().manage().timeouts().implicitlyWait(DataFromJson.getImplicityWaitTime(), TimeUnit.SECONDS);
    }

    public static void waitForPreOfElementByLoc(final By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForPreOfAllElementsByLoc(final By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }
}
