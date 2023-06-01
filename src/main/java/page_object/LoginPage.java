package page_object;

import helper.user.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    private final By emailFieldInput = By.xpath(".//input[@name='name']");
    private final By passwordFieldInput = By.xpath(".//input[@name='Пароль']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");


    @Step("Открытие страницы авторизации")
    public void openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
    }

    @Step("Проверка, чтооткрыта страница авторизации")
    public boolean isLoginPageOpen() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Заполнение поля email")
    public void setEmailField(String email) {
        driver.findElement(emailFieldInput).click();
        driver.findElement(emailFieldInput).sendKeys(email);
    }

    @Step("Заполение поля пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordFieldInput).click();
        driver.findElement(passwordFieldInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Авторизация пользователя")
    public void loginUser(User user) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
        setEmailField(user.getEmail());
        setPasswordField(user.getPassword());
        clickLoginButton();

    }
}
