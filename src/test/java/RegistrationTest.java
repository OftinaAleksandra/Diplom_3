import helper.ConfProperties;
import helper.user.User;
import helper.user.UserClient;
import helper.user.UserGenerator;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.LoginPage;
import page_object.RegistrationPage;

public class RegistrationTest {
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
        WebDriverManager.chromedriver().setup();
    }

    @Test
    @DisplayName("Корректная регистрация")
    public void correctedRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationPageOpen();
        registrationPage.clickRegistrationButton();
        registrationPage.setNameFiled(user.getName());
        registrationPage.setEmailField(user.getEmail());
        registrationPage.setPasswordField(user.getPassword());
        registrationPage.clickRegistrationButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(user);
    }

    @Test
    @DisplayName("Регистрация с некорректным паролем")
    public void registrationWithUncorrectedPassword() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationPageOpen();
        registrationPage.clickRegistrationButton();
        registrationPage.setNameFiled(user.getName());
        registrationPage.setEmailField(user.getEmail());
        registrationPage.setPasswordField("12345");
        registrationPage.clickRegistrationButton();
        registrationPage.isIncorrectPasswordDisplayed();
    }

    @After
    public void tearDown() {
        driver.quit();
        accessToken = userClient.loginUser(user.getEmail(), user.getPassword()).extract().path("accessToken");
        if (accessToken != null) userClient.deleteUser(accessToken);
    }
}
