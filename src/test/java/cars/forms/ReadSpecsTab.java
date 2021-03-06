package cars.forms;

import cars.models.CarModel;
import framework.DataProp;
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

    public void selectRandomMakeModelYearAndSave(final String filename) {
        Waiters.wait.until(driver -> selectRnd(slcCarMake, filename, "make"));
        Waiters.wait.until(driver -> selectRnd(slcCarModel, filename, "model"));
        Waiters.wait.until(driver -> selectRnd(slcCarYear, filename, "year"));
    }

    public void search() {
        search.clickViaAction();
    }

    private Boolean selectRnd(Select selectObj, final String carName, final String keyValue) {
        options = selectObj.getAllOptions();
        int rnd = new Random().nextInt(options.size());
        if (rnd!=0) {
            selectObj.setValueByIndex(rnd);
            CarModel.putValue(carName, keyValue, selectObj.getNameOfSelected());
            return true;
        } else {
            selectObj.setValueByIndex(1);
            CarModel.putValue(carName, keyValue, selectObj.getNameOfSelected());
            return true;
        }
    }

    public String getSelectedMake() {
        return slcCarMake.getNameOfSelected();
    }

    public String getSelectedModel() {
        return slcCarModel.getNameOfSelected();
    }

    public String getSelectedYear() {
        return slcCarYear.getNameOfSelected();
    }
}
