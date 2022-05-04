import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

class ProfilePage extends PageBase {
    private By postSearchBy = By.xpath("//span[text()=\"What's on your mind?\"]");
    private By postPlaceBy = By.xpath("//div[text()=\"What's on your mind?\"]/../../div[2]/div/div/div/div/span");
    private By postButtonBy = By.xpath("//span[text()='Post']");
    private By profileBy = By.xpath("//span[text()='Lorand']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public String getResultText() {
        WebElement bodyElement = this.waitAndReturnElement(By.xpath("//span[text()='All']"));
        return bodyElement.getText();
    }

    public void post(String textPost) throws InterruptedException {
        this.driver.findElement(profileBy).click();
        wait.until(ExpectedConditions.elementToBeClickable(postSearchBy));
        this.driver.findElement(postSearchBy).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(postButtonBy));
        this.driver.findElement(postPlaceBy).sendKeys(textPost);
        this.driver.findElement(postButtonBy).click();

    }

}
