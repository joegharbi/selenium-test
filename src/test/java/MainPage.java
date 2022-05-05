import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

class MainPage extends PageBase {

    private By CookiesBy = By.xpath("//button[text()='Allow essential and optional cookies']");
    private By searchBarBy = By
            .xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div/label/input");
    private By usernameBy = By.xpath("//*[@id='email']");
    private By passwordBy = By.xpath("//*[@id='pass']");
    private By signinBy = By.xpath("//*[@name='login']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.facebook.com/");
    }

    public ProfilePage profile() {
        return new ProfilePage(this.driver);
    }

    // public String title() {
    // return
    // wait.until(ExpectedConditions.visibilityOfElementLocated(this.driver.getTitle()));
    // }

    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }

    public DashboardPage login(String userName, String password) {
        this.waitAndReturnElement(CookiesBy).click();
        this.driver.findElement(usernameBy).sendKeys(userName);
        this.driver.findElement(passwordBy).sendKeys(password);
        this.driver.findElement(signinBy).click();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(
                        "/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div[2]/div/div/div[1]/span")));
        return new DashboardPage(this.driver);
    }

}
