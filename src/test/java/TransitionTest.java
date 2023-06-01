import helper.ConfProperties;
import helper.user.User;
import helper.user.UserClient;
import helper.user.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.*;

public class TransitionTest {
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
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fullRegistrationUser(user);
    }

    @Test
    public void openPersonalAccountTest() {
        MainPage mainPage = new MainPage(driver);
        PersonalAccount personalAccount = new PersonalAccount(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.loginUser(user);
        mainPage.clickPersonalButton();
        personalAccount.isPersonAccountPageOpen();
    }

    @Test
    public void openConstructorTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        Constructor constructor = new Constructor(driver);
        loginPage.openLoginPage();
        loginPage.loginUser(user);
        mainPage.clickPersonalButton();
        mainPage.clickConstructorButton();
        constructor.isConstructorPageOpen();
    }

    @After
    public void tearDown() {
        driver.quit();
        accessToken = userClient.loginUser(user.getEmail(), user.getPassword()).extract().path("accessToken");
        if (accessToken != null) userClient.deleteUser(accessToken);
    }

}
