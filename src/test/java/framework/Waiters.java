package framework;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Waiters {

    private static WebDriverWait wait = new WebDriverWait(BrowserFactory.driver(), DataFromJson.getWebDriverWaitTime());

    public static void waitForPreOfAllElementsByLoc(final By locator) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public static void waitUntilFullyDownload(String extension) {
        wait.until((ExpectedCondition<Boolean>) driver -> {
            File steamFile = new File("src/test/resources/downloads/" + extension);
            return steamFile.exists();
        });
    }
}
