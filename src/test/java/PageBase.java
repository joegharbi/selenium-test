import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.By;

class PageBase {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private By bodyBy = By.tagName("body");
    protected By profileBy = By.xpath("//span[text()='Lorand']");
    protected By resultBy = By.xpath("//span[text()='All']");
    protected By postBy = By.xpath("//span[text()=\"What's on your mind, Lorand?\"]");

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 200);
    }

    protected WebElement waitAndReturnElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    public String getResultText() {
        WebElement bodyElement = this.waitAndReturnElement(resultBy);
        return bodyElement.getText();
    }

    public String getBodyText() {
        WebElement bodyElement = this.waitAndReturnElement(bodyBy);
        return bodyElement.getText();
    }

    public void hoverOverElement(By locator) {
        WebElement element = this.driver.findElement(locator);
        Actions actions = new Actions(this.driver);
        actions.moveToElement(element).perform();
    }

    public String getTextHover(By locator) {
        return this.driver.findElement(locator).getAttribute("aria-label");
    }

    public PageBase backButton() {
        this.driver.navigate().back();
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(postBy));
        return this;
    }

}
