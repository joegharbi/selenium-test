import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTest {
    public WebDriver driver;
    public MainPage mainPage;
    public DashboardPage dashboardPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(this.driver);
        dashboardPage = mainPage.login("wagom95837@svcache.com", "password1234");
    }

    @Test
    public void testTitle() {
        Assert.assertTrue(this.driver.getTitle().contains("Facebook"));
    }

    @Test
    public void testLogin() {
        Assert.assertTrue(dashboardPage.getBodyText().contains("Welcome to Facebook, Lorand"));
    }

    @Test
    public void testLogout() {
        mainPage = dashboardPage.logout();
        Assert.assertTrue(
                mainPage.getBodyText().contains("Page"));
    }

    @Test
    public void testSearch() {
        String[] searchQueries = { "first", "second", "third" };
        for (String searchQuery : searchQueries) {
            MainPage mainPage = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String resultText = searchResultPage.getResultText();
            Assert.assertTrue(resultText.contains("All"));
        }
    }

    @Test
    public void testPost() throws InterruptedException {
        ProfilePage profilePage = mainPage.profile();
        profilePage.post("Hi");
        Assert.assertTrue(mainPage.getBodyText().contains("Hi"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
