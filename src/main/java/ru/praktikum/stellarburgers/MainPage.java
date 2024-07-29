package ru.praktikum.stellarburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainPage extends ConstantsData {


    private WebDriver driver;
    private final By buttonToMakeOrderOnHomePage = By.xpath(".//button[text()='Оформить заказ']");
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открыть главную страницу")
    public void openUrl() {
        driver.get(URL_MAIN_PAGE);
    }

    @Step("Ожидание")
    public void implicitWait() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickToPersonalAccountButton() {
        driver.findElement(BUTTON_PERSONAL_ACCOUNT).click();
    }

    @Step("Проверка текущей страницы")
    public boolean atPage() {
        return driver.getCurrentUrl().equals(URL_MAIN_PAGE);
    }

    @Step("Клик по разделу")
    public void clickOnTab(String tabName) {
        driver.findElement(By.xpath(".//span[text() = '" + tabName + "']/parent::div")).click();
    }

    @Step("Проверка класса")
    public boolean checkClassAttributeContains(By locator) {
        return driver.findElement(locator).getAttribute("class").contains("current");
    }

    @Step("Ожидание кнопки 'Оформить заказ'")
    public void explicitWaitToVisibleHeaderHomePage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOf(driver.findElement(buttonToMakeOrderOnHomePage)));
    }
    @Step("Проверка смены кнопки 'Войти в аккаунт' на кнопку 'Оформить заказ'")
    public boolean isVisibleHeaderHomePage() {
        return driver.findElement(buttonToMakeOrderOnHomePage).isDisplayed();
    }
}
