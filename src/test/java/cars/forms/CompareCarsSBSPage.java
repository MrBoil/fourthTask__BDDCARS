package cars.forms;

import cars.models.CarModel;
import framework.DataProp;
import framework.element.Button;
import framework.element.Label;
import framework.element.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CompareCarsSBSPage {
    private Select slcMake = new Select(By.xpath("//select[@name = 'makeDropdown']"));
    private Select slcModel = new Select(By.xpath("//select[@name = 'modelDropdown']"));
    private Select slcYear = new Select(By.xpath("//select[@name = 'yearDropdown']"));
    private Button btnCompare;
    private Label lblAddAnother = new Label(By.xpath("//div[@class = 'add-car-icon']"));

    private Label lblSectionOfCompare;
    private String locSection = "//cars-compare-compare-info[@header = \'%s\']";
    private String locCharacteristic = "//span[%s]//p";

    public CompareCarsSBSPage enterCarToCompare(final String carName, final String buttonName) {
        slcMake.setValueByVisibleText(CarModel.getCarMakeModelYear(carName, "make"));
        slcModel.setValueByVisibleText(CarModel.getCarMakeModelYear(carName, "model"));
        slcYear.setValueByVisibleText(CarModel.getCarMakeModelYear(carName, "year"));
        btnCompare = new Button(By.xpath(String.format("//button[contains(text(), \'%s\')]", buttonName)));
        btnCompare.click();
        return this;
    }

    public CompareCarsSBSPage addAnotherCar() {
        lblAddAnother.click();
        return this;
    }

    public String getCarSpec(final String category, final int carNumber) {
        String spec = "";
        try {
            lblSectionOfCompare = new Label(By.xpath(String.format(locSection, category)
                    + String.format(locCharacteristic, carNumber, 2)));
            WebElement[] actualSpecs = lblSectionOfCompare.getElements().toArray(new WebElement[2]);
            for (WebElement element:actualSpecs) {
                spec += element.getText().replace("liter", "L").replace(",", "");
            }
            return spec;
        } catch (NullPointerException ignore) {
            return spec;
        }
    }

}
