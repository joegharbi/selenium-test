import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {
    public WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public DashboardPage login() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.clickCookiesButton();
        mainPage.login("wagom95837@svcache.com", "password1234");
        DashboardPage dashbaordPage = new DashboardPage(this.driver);
        return dashbaordPage;
    }

    @Test
    public void testLogin() throws InterruptedException {
        Assert.assertTrue(login().getBodyText().contains("Welcome to Facebook, Lorand"));
    }

    @Test
    public void testLogout() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.clickCookiesButton();
        mainPage.login("wagom95837@svcache.com", "password1234");
        mainPage.logout();
        Assert.assertTrue(
                mainPage.getBodyText().contains("Page"));
    }

    @Test
    public void testSearch() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.clickCookiesButton();
        mainPage.login("wagom95837@svcache.com", "password1234");
        String[] searchQueries = { "first", "second", "third"
        };
        for (String searchQuery : searchQueries) {
            MainPage mainPage1 = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage1.search(searchQuery);
            String resultText = searchResultPage.getResultText();
            Assert.assertTrue(resultText.contains("All"));
        }
    }

    @Test
    public void testPost() throws InterruptedException {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.clickCookiesButton();
        mainPage.login("wagom95837@svcache.com", "password1234");
        mainPage.post("Hi");
        Assert.assertTrue(mainPage.getBodyText().contains("Hi"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
