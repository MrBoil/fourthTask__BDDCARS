package cars.forms;

import framework.DataToProp;
import framework.element.Button;
import framework.element.Label;
import framework.element.Select;
import org.openqa.selenium.By;

public class CompareCarsSBSPage {
    private Select slcMake = new Select(By.xpath("//select[@name = 'makeDropdown']"));
    private Select slcModel = new Select(By.xpath("//select[@name = 'modelDropdown']"));
    private Select slcYear = new Select(By.xpath("//select[@name = 'yearDropdown']"));
    private Button btnCompare;
    private Label lblAddAnother = new Label(By.xpath("//div[@class = 'add-car-icon']"));

    public CompareCarsSBSPage enterCarToCompare(final String fileName, final String buttonName) {
        slcMake.setValueByVisibleText(DataToProp.readDataFromProp(fileName, "make"));
        slcModel.setValueByVisibleText(DataToProp.readDataFromProp(fileName, "model"));
        slcYear.setValueByVisibleText(DataToProp.readDataFromProp(fileName, "year"));
        btnCompare = new Button(By.xpath(String.format("//button[contains(text(), \'%s\')]", buttonName)));
        btnCompare.click();
        return this;
    }

    public CompareCarsSBSPage addAnotherCar() {
        lblAddAnother.click();
        return this;
    }
}
