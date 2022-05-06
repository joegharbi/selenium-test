import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FirstSeleniumTest {
    public WebDriver driver;
    public MainPage mainPage;
    public DashboardPage dashboardPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        mainPage = new MainPage(this.driver);
        dashboardPage = mainPage.login("wagom95837@svcache.com", "test1234test#");
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
        profilePage.post("Post test at: " + System.currentTimeMillis());
        Assert.assertTrue(mainPage.getBodyText().contains("1m"));
    }

    @Test
    public void testHoverMouseHome() {
        mainPage.doHover();
        String hoverText = mainPage.getHoverText();
        Assert.assertEquals(hoverText, ("Home"));
    }

    @Test
    public void testBackHistory() throws InterruptedException {
        PageBase pageBase = dashboardPage.backButton();
        Thread.sleep(6000);
        Assert.assertTrue(pageBase.getBodyText().contains("What's on your mind, Lorand?"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
