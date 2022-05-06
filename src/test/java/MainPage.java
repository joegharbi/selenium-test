import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

class MainPage extends PageBase {

    private By CookiesBy = By.xpath("//button[text()='Allow essential and optional cookies']");
    private By searchBarBy = By.xpath("(//input[@aria-label='Search Facebook'])[1]");
    private By usernameBy = By.xpath("//*[@id='email']");
    private By passwordBy = By.xpath("//*[@id='pass']");
    private By signinBy = By.xpath("//*[@name='login']");
    private By logedInBy = By.xpath("//span[text()='Welcome to Facebook, Lorand']");
    private By hoverBy = By.xpath("//a[@aria-label='Home']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.facebook.com/");
    }

    public ProfilePage profile() {
        return new ProfilePage(this.driver);
    }

    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }

    public DashboardPage login(String userName, String password) {
        this.waitAndReturnElement(CookiesBy).click();
        this.driver.findElement(usernameBy).sendKeys(userName);
        this.driver.findElement(passwordBy).sendKeys(password);
        this.driver.findElement(signinBy).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(logedInBy));
        return new DashboardPage(this.driver);
    }

    public MainPage doHover() {
        hoverOverElement(hoverBy);
        return this;
    }

    public String getHoverText() {
        return getTextHover(hoverBy);
    }
}
