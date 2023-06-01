package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccount {
    private WebDriver driver;

    public PersonalAccount(WebDriver driver) {
        this.driver = driver;
    }

    public static final String PERSONAL_ACCOUNT_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");
    private final By profileButton = By.xpath(".//a[text() = 'Профиль']");

    @Step("Открытие страницы личный кабинет")
    public void personalAccountPageOpen() {
        driver.get(PERSONAL_ACCOUNT_URL);
    }
    @Step
    public void isPersonAccountPageOpen(){
        new WebDriverWait(driver,10 )
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("Нажатие на кнопку Выход")
    public void clickLogoutButton() {

        new WebDriverWait(driver,10 )
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
        driver.findElement(logoutButton).click();
    }

}
