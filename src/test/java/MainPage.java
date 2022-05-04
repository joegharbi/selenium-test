import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

class MainPage extends PageBase {

    private By CookiesBy = By.xpath("//button[text()='Allow essential and optional cookies']");
    private By searchBarBy = By
            .xpath("/html/body/div[1]/div/div[1]/div/div[2]/div[2]/div/div/div[1]/div/div/label/input");
    private By usernameBy = By.xpath("//*[@id='email']");
    private By passwordBy = By.xpath("//*[@id='pass']");
    private By homeBy = By.xpath(
            "/html/body/div[1]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[1]/ul/li[1]/span/div/a/span/svg/path");
    private By signinBy = By.xpath("//*[@name='login']");
    private By profileBy = By.xpath("//span[text()='Lorand']");
    private By logoutBy = By.xpath("//span[text()='Log Out']");
    private By resultSearchBy = By.xpath("//span[text()='All']");
    private By postSearchBy = By.xpath("//span[text()=\"What's on your mind?\"]");
    private By postPlaceBy = By.xpath("//div[text()=\"What's on your mind?\"]/../../div[2]/div/div/div/div/span");
    private By postButtonBy = By.xpath("//span[text()='Post']");

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://www.facebook.com/");
    }

    public void clickCookiesButton() {
        this.waitAndReturnElement(CookiesBy).click();
    }

    public void clickHomeButton() {
        this.waitAndReturnElement(homeBy).click();
    }

    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(searchBarBy).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }

    public void post(String textPost) throws InterruptedException {
        this.driver.findElement(profileBy).click();
        wait.until(ExpectedConditions.elementToBeClickable(postSearchBy));
        this.driver.findElement(postSearchBy).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(postButtonBy));
        this.driver.findElement(postPlaceBy).sendKeys(textPost);
        this.driver.findElement(postButtonBy).click();

    }

    public void login(String userName, String password) {
        this.driver.findElement(usernameBy).sendKeys(userName);
        this.driver.findElement(passwordBy).sendKeys(password);
        this.driver.findElement(signinBy).click();

        wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(
                        "/html/body/div[1]/div/div[1]/div/div[3]/div/div/div[1]/div[1]/div/div[2]/div/div/div[1]/span")));
    }

    public void logout() {
        this.driver.findElement(profileBy).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(logoutBy));
        this.driver.findElement(logoutBy).click();
        wait.until(ExpectedConditions
                .visibilityOfElementLocated(usernameBy));

    }
}
