package cars.forms;

import framework.DataProp;
import framework.element.Label;
import org.openqa.selenium.By;

public class CarTrimsPage {
    private Label lblEngine = new Label(By.xpath("//div[@class='cell cell-bg grow-2']"));
    private Label lblTransmission = new Label(By.xpath("//div[@class='cell grow-2']"));

    public void saveCarSpecs(final String fileName) {
        DataProp.writeDataToProp(fileName, "engine", lblEngine.getText());
        DataProp.writeDataToProp(fileName, "transmission", lblTransmission.getText());
    }

    public String getLblEngineText() {
        return lblEngine.getText();
    }

    public String getLblTransmissionText() {
        return lblTransmission.getText();
    }
}
