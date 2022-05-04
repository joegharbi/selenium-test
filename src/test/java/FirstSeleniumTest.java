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

    @Test
    public void testTitle() {
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(this.driver.getTitle().contains("Facebook - log in or sign up"));
    }

    @Test
    public void testLogin() {
        MainPage mainPage = new MainPage(this.driver);
        DashboardPage dashboardPage = mainPage.login("wagom95837@svcache.com",
                "password1234");
        Assert.assertTrue(dashboardPage.getBodyText().contains("Welcome to Facebook, Lorand"));
    }

    @Test
    public void testLogout() {
        MainPage mainPage = new MainPage(this.driver);
        DashboardPage dashboardPage = mainPage.login("wagom95837@svcache.com",
                "password1234");
        mainPage = dashboardPage.logout();
        Assert.assertTrue(
                mainPage.getBodyText().contains("Page"));
    }

    @Test
    public void testSearch() {
        MainPage mainPage = new MainPage(this.driver);
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
        mainPage.login("wagom95837@svcache.com", "password1234");
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
