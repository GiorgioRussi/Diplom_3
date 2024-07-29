package ru.praktikum.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends ConstantsData {

    private WebDriver driver;
    private final By emailLocator = By.xpath(".//label[text() = 'Email']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private final By passwordLocator = By.xpath(".//label[text() = 'Пароль']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private final By buttonLogin = By.xpath(".//button[text() = 'Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание")
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }
    @Step("Открыть url")
    public void openUrl(String url) {
        driver.get(url);
    }

    @Step("Проверка видимости кнопки 'Войти'")
    public boolean isLoginButtonVisible(By buttonToLogin) {
        return driver.findElement(buttonToLogin).isDisplayed();
    }

    @Step("Кликаем по кнопке")
    public void clickButtonToLogin(By buttonToLogin) {
        driver.findElement(buttonToLogin).click();
    }

    @Step("Проверка перехода на страницу логина")
    public boolean atPage() {
        return driver.getCurrentUrl().equals(URL_LOGIN_PAGE);
    }

    @Step("Заполнение поля email, password")
    public void fillRegisterForm(String email, String password) {
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    @Step("Ожидание прогрузки страницы")
    public void explicitWaitToVisibleHeaderLoginPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(headerLoginPage)));
    }

}
