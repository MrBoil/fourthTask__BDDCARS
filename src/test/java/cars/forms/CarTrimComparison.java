package cars.forms;

import framework.DataToProp;
import framework.element.Label;
import org.openqa.selenium.By;

public class CarTrimComparison {
    private Label lblEngine = new Label(By.xpath("//div[@class='cell cell-bg grow-2']"));
    private Label lblTransmission = new Label(By.xpath("//div[@class='cell grow-2']"));

    public void saveCarSpecs(final String fileName) {
        DataToProp.writeDataToProp(fileName, "engine", lblEngine.getText());
        DataToProp.writeDataToProp(fileName, "transmission", lblTransmission.getText());
    }
}
