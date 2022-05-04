import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

class ProfilePage extends PageBase {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getResultText() {
        WebElement bodyElement = this.waitAndReturnElement(By.xpath("//span[text()='All']"));
        return bodyElement.getText();
    }

}
