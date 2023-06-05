import helper.ConfProperties;
import helper.user.User;
import helper.user.UserClient;
import helper.user.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.Constructor;
import page_object.MainPage;
import static org.junit.Assert.assertEquals;


public class ConstructorTest {
    private UserClient userClient;
    private User user;
    String accessToken;
    private static WebDriver driver;

    @BeforeClass
    public static void startUp() {
        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("driver"));
    }

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
    }

    @Test
    @DisplayName("Переход к секции Булки")
    public void openSectionBunsTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickConstructorButton();
        Constructor constructor = new Constructor(driver);
        constructor.clickSectionFilling();
        constructor.clickSectionBuns();
        assertEquals("Булки", constructor.getTextFromCurrentConstructor());
    }

    @Test
    @DisplayName("Переход к секции Соусы")
    public void openSectionSauceTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickConstructorButton();
        Constructor constructor = new Constructor(driver);
        constructor.clickSectionSauce();
        assertEquals("Соусы", constructor.getTextFromCurrentConstructor());
    }

    @Test
    @DisplayName("Переход к секции Начинки")
    public void openSectionFillingTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickConstructorButton();
        Constructor constructor = new Constructor(driver);
        constructor.clickSectionFilling();
        assertEquals("Начинки", constructor.getTextFromCurrentConstructor());
    }

    @After
    public void tearDown() {
        driver.quit();
        accessToken = userClient.loginUser(user.getEmail(), user.getPassword()).extract().path("accessToken");
        if (accessToken != null) userClient.deleteUser(accessToken);
    }
}
