package page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constructor {
    private final WebDriver driver;

    public Constructor(WebDriver driver) {
        this.driver = driver;
    }

    private final By headerCreateBurger = By.xpath(".//h1[text() = 'Соберите бургер']");
    private final By headerBuns = By.xpath(".//span[text() = 'Булки']");
    private final By headerSauce = By.xpath(".//span[text() = 'Соусы']");
    private final By headerFilling = By.xpath(".//span[text() = 'Начинки']");
    private final By sectionBuns = By.xpath(".//h2[text() = 'Булки']");
    private final By sectionSauce = By.xpath(".//h2[text() = 'Соусы']");
    private final By sectionFilling = By.xpath(".//h2[text() = 'Начинки']");
    private final By currentConstructor = By.xpath("//div[contains(@class,'tab_tab_type_current__2BEPc')]");


    @Step
    public void isConstructorPageOpen() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(headerCreateBurger));
    }

    @Step("Нажатие на секцию Булки")
    public void clickSectionBuns() {
        driver.findElement(headerBuns).click();
    }

    @Step("Нажатие на секцию Соусы")
    public void clickSectionSauce() {
        driver.findElement(headerSauce).click();
    }

    @Step("Нажатие на секцию Начинки")
    public void clickSectionFilling() {
        driver.findElement(headerFilling).click();
    }

    @Step("Проверка, что секция Булки видна")
    public boolean isSectionBunsVisible() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(sectionBuns));
        return driver.findElement(sectionBuns).isDisplayed();
    }

    @Step("Проверка, что секция Булки видна")
    public boolean isSectionSauceVisible() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(sectionSauce));
        return driver.findElement(sectionSauce).isDisplayed();
    }

    @Step("Проверка, что секция Булки видна")
    public boolean isSectionFillingVisible() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(sectionFilling));
        return driver.findElement(sectionFilling).isDisplayed();
    }
    @Step("Получение тексты выбранного конструктора")
    public String getTextFromCurrentConstructor() {
        return driver.findElement(currentConstructor).getText();
    }
}
