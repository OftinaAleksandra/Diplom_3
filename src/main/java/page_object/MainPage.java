package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By createOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");


    @Step("Открытие главной страницы")
    public void mainPageOpen() {
        driver.get(MAIN_URL);
    }

    @Step("Нажатие на кнопку Войти в аккаунт")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    @Step("Нажатие на кнопку Личный кабинет")
    public void clickPersonalButton() {
        driver.findElement(personalAccountButton).click();
    }
    @Step("Проверка Авторизации, кнопка офрмления заказа отображается")
    public boolean isCreateOrderButtonDisplayed() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }
    @Step("Нажатие на кнопку Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

}
