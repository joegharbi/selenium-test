import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

class SearchResultPage extends PageBase {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getResultText() {
        WebElement bodyElement = this.waitAndReturnElement(By.xpath("//span[text()='All']"));
        return bodyElement.getText();
    }

}
