package cars.forms;

import framework.element.Button;
import framework.element.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;

public class ReadSpecsTab {
    private Select slcCarMake = new Select(By.xpath("//div[@class='hsw-makes']//select"));
    private Select slcCarModel = new Select(By.xpath("//div[@class='hsw-models']//select"));
    private Select slcCarYear = new Select(By.xpath("//div[@class='hsw-years']//select"));
    private Button search = new Button(By.xpath("//div[@class='hsw-submit']"));
    private List<WebElement> options;

    public void selectRandomMakeModelYearAndSearch() throws InterruptedException {
        options = slcCarMake.getAllOptions();
        slcCarMake.setValueByIndex(new Random().nextInt(options.size()));

        options = slcCarModel.getAllOptions();
        slcCarModel.setValueByIndex(new Random().nextInt(options.size()));

        options = slcCarYear.getAllOptions();
        slcCarYear.setValueByIndex(new Random().nextInt(options.size()));
        search.clickViaAction();
    }

}