package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {
    private WebDriver driver;
    public RecoverPasswordPage(WebDriver driver){
        this.driver = driver;
    }
    public static final String RECOVER_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private final By loginButton = By.xpath(".//a[text()='Войти']");

    @Step("Открытие страницы восстановления пароля")
    public void registrationPageOpen() {
        driver.get(RECOVER_PASSWORD_PAGE_URL);
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

}
