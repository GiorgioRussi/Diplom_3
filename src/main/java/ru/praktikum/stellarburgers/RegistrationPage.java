package ru.praktikum.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class RegistrationPage extends ConstantsData {

    private WebDriver driver;
    private final By nameLocator = By.xpath(".//label[text() = 'Имя']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private final By emailLocator = By.xpath(".//label[text() = 'Email']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private final By passwordLocator = By.xpath(".//label[text() = 'Пароль']/parent::div/input[@class = 'text input__textfield text_type_main-default']");
    private final By buttonOfRegistration = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By registrationForm = By.xpath(".//form[@class = 'Auth_form__3qKeq mb-20']");


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Открыть страницу")
    public void openUrl() {
        driver.get(URL_REGISTER_PAGE);
    }

    @Step("Заполнить поля name, email, password")
    public void fillRegisterForm(String name, String email, String password) {
        driver.findElement(nameLocator).sendKeys(name);
        driver.findElement(emailLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(buttonOfRegistration).click();
    }

    @Step("Проверка видимости регистрации")
    public boolean isRegistrationFormVisible() {
        return driver.findElement(registrationForm).isDisplayed();
    }

    @Step("Проверка ошибки 'Некорректный пароль'")
    public boolean isErrorMessageVisible(String errorMessage) {
        return driver.findElement(By.xpath(".//p[@class = 'input__error text_type_main-default' and text() = '" + errorMessage + "']")).isDisplayed();
    }

    @Step("Проверка текущей страницы")
    public boolean atPage() {
        return driver.getCurrentUrl().equals(URL_REGISTER_PAGE);
    }

}
