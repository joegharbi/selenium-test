import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

class ProfilePage extends PageBase {
    private By postSearchBy = By.xpath("//span[text()=\"What's on your mind?\"]");
    private By postPlaceBy = By.xpath("//br[@data-text='true']");
    private By postButtonBy = By.xpath("//span[text()='Post']");
    private By profileBy = By.xpath("//span[text()='Lorand']");
    private By postBy = By.xpath("//span[text()='1m']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void post(String textPost) throws InterruptedException {
        this.driver.findElement(profileBy).click();
        wait.until(ExpectedConditions.elementToBeClickable(postSearchBy));
        this.driver.findElement(postSearchBy).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(postButtonBy));
        this.driver.findElement(postPlaceBy).sendKeys(textPost);
        this.driver.findElement(postButtonBy).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(postBy));
    }

}
