package cars.forms;

import framework.Waiters;
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

    public void selectRandomMakeModelYearAndSearch() {
        Waiters.wait.until(driver -> selectRnd(slcCarMake));
        Waiters.wait.until(driver -> selectRnd(slcCarModel));
        Waiters.wait.until(driver -> selectRnd(slcCarYear));
        search.clickViaAction();
    }

    private Boolean selectRnd(Select selectObj) {
        options = selectObj.getAllOptions();
        int rnd = new Random().nextInt(options.size());
        if (rnd==0) {
            selectObj.setValueByIndex(1);
            System.out.println(selectObj.getNameOfSelected());
            return true;
        } else {
            selectObj.setValueByIndex(rnd);
            System.out.println(selectObj.getNameOfSelected());
            return true;
        }
    }
}
