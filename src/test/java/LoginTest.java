import helper.ConfProperties;
import helper.user.User;
import helper.user.UserClient;
import helper.user.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RecoverPasswordPage;
import page_object.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class LoginTest {
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
    @DisplayName("Авторизация через личный кабинет")
    public void loginInPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickPersonalButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через кнопку Войти в аккаунт на главной странице")
    public void loginInLoginButtonOnMainPage() {
        MainPage mainPage = new MainPage(driver);
        mainPage.mainPageOpen();
        mainPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через кнопку Войти в аккаунт на странице регистрации")
    public void loginInLoginButtonOnRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationPageOpen();
        registrationPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Авторизация через кнопку Войти в аккаунт на странице восстановления пароль")
    public void loginInLoginButtonOnRecoverPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        RecoverPasswordPage recoverPasswordPage = new RecoverPasswordPage(driver);
        recoverPasswordPage.registrationPageOpen();
        recoverPasswordPage.clickLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginUser(user);
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }

    @After
    public void tearDown2() {
        driver.quit();
        accessToken = userClient.loginUser(user.getEmail(), user.getPassword()).extract().path("accessToken");
        if (accessToken != null) userClient.deleteUser(accessToken);
    }
}


