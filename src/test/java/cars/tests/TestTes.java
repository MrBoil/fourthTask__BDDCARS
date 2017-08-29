package cars.tests;

import cars.forms.CompareCarsSBSPage;
import framework.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TestTes {

    @Test
    public void testThis(){
        WebDriver driver = BrowserFactory.instanceBrowserFactory();
        driver.get("https://www.cars.com/research/compare/?acodes=USC00AUC042A0,USB50SBC031A0");
        CompareCarsSBSPage compareCarsSBSPage = new CompareCarsSBSPage();
        try {
            for (WebElement element : compareCarsSBSPage.getCarSpec("Transmission", 2))
            System.out.println(element.getText());
        } catch (NullPointerException e) {

        }

    }
}
