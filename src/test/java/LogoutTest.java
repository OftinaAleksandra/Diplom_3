import helper.ConfProperties;
import org.junit.BeforeClass;
import page_object.PersonalAccount;
import helper.user.User;
import helper.user.UserClient;
import helper.user.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page_object.LoginPage;
import page_object.MainPage;
import page_object.RegistrationPage;

import static org.junit.Assert.assertTrue;

public class LogoutTest {
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
    @DisplayName("Выход из личного кабинета")
    public void logoutTest() {
        PersonalAccount personalAccount = new PersonalAccount(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.loginUser(user);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalButton();
        personalAccount.clickLogoutButton();
        assertTrue(loginPage.isLoginPageOpen());
    }

    @After
    public void tearDown() {
        driver.quit();
        accessToken = userClient.loginUser(user.getEmail(), user.getPassword()).extract().path("accessToken");
        if (accessToken != null) userClient.deleteUser(accessToken);
    }
}
