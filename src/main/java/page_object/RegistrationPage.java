package page_object;

import helper.user.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    public static final By nameFieldInput = By.xpath(".//label[text() = 'Имя']/../input[@name = 'name']");
    public static final By emailFieldInput = By.xpath(".//label[text() = 'Email']/../input[@name = 'name']");
    private final By passwordFieldInput = By.xpath(".//input[@name = 'Пароль']");
    private final By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By incorrectPasswordError = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By loginButton = By.xpath(".//a[contains(@href, '/login') and text() = 'Войти']");

    @Step("Открытие страницы регистрации")
    public void registrationPageOpen() {
        driver.get(REGISTRATION_PAGE_URL);
    }

    @Step("Заполнение поля имя")
    public void setNameFiled(String name) {
        driver.findElement(nameFieldInput).click();
        driver.findElement(nameFieldInput).sendKeys(name);
    }

    @Step("Заполнение поля Email")
    public void setEmailField(String email) {
        driver.findElement(emailFieldInput).click();
        driver.findElement(emailFieldInput).sendKeys(email);
    }

    @Step("Заполнение поля пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordFieldInput).click();
        driver.findElement(passwordFieldInput).sendKeys(password);
    }

    @Step("Нажатие на кнопку Зарегистрироваться")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }


    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Сообщение о некорректном пароле")
    public boolean isIncorrectPasswordDisplayed() {
        return driver.findElement(incorrectPasswordError).isDisplayed();
    }
    @Step
    public void fullRegistrationUser(User user){
        registrationPageOpen();
        setNameFiled(user.getName());
        setEmailField(user.getEmail());
        setPasswordField(user.getPassword());
        clickRegistrationButton();
    }
}
