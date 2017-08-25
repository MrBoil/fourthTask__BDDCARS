package cars.forms;

import framework.element.Label;
import org.openqa.selenium.By;

public class CarPage {
    private Label lblTrimComp = new Label(By.xpath("//a[contains(text(), 'trim comparison')]"));
    private Label lblEngine = new Label(By.xpath("/html/body/div[2]/div[1]/div[2]/div[2]/div[2]/div[4]"));
    private Label lblTransmission = new Label(By.xpath("//div[@class=cell grow-2]"));

    public void navigateTrimComparison(){
        lblTrimComp.clickViaJS();
    }

    public void saveFirstCarSpecs() {
        System.out.println(lblEngine.getElement().getText());
    }
}
