import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

class DashboardPage extends PageBase {
    private By profileBy = By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[4]/div[1]/span/div/div[1]");
    private By logoutBy = By.xpath("//span[text()='Log Out']");
    private By usernameBy = By.xpath("//*[@id='email']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public MainPage logout() {
        this.driver.findElement(profileBy).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(logoutBy));
        this.driver.findElement(logoutBy).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(usernameBy));
        return new MainPage(this.driver);
    }
}
