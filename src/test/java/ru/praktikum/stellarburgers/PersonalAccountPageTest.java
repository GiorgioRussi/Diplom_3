package ru.praktikum.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@DisplayName("Страница личного кабинета")
public class PersonalAccountPageTest extends ConstantsData {

    WebDriver driver;
    UserMethods userMethods;
    PersonalAccountPage personalAccountPage;
    LoginPage loginPage;
    MainPage mainPage;
    String accessToken;

    @Before
    public void setUp() {
        userMethods = new UserMethods();
        accessToken = userMethods.create(user).path("accessToken").toString();
        driver = WebDriverSetUp.createDriver();
        personalAccountPage = new PersonalAccountPage(driver);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.implicitWait();
        mainPage.implicitWait();
        personalAccountPage.implicitWait();
        mainPage.openUrl();
        getLogin();
    }

    @Step("Авторизация пользователя")
    public void getLogin() {
        mainPage.clickToPersonalAccountButton();
        loginPage.fillRegisterForm(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
        mainPage.explicitWaitToVisibleHeaderMainPage();
    }

    @Test
    @DisplayName("Переход на главную страницу по кнопке 'Конструктор'")
    public void shouldMovingToMainPageByConstructorButton() {
        mainPage.clickToPersonalAccountButton();
        personalAccountPage.clickConstructorButton();
        assertTrue(mainPage.atPage());
    }

    @Test
    @DisplayName("Переход на главную страницу по клику на логотип Stellar Burgers")
    public void shouldMovingToHomePageByLogo() {
        mainPage.clickToPersonalAccountButton();
        personalAccountPage.clickLogoButton();
        assertTrue(mainPage.atPage());
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выйти' в личном кабинете")
    public void shouldExitFromPersonalAccount() {
        mainPage.clickToPersonalAccountButton();
        personalAccountPage.clickExitButton();
        loginPage.explicitWaitToVisibleHeaderLoginPage();
        assertTrue(loginPage.atPage());
    }

    @After
    public void tearDown() {
        driver.quit();
        userMethods.delete(accessToken);
    }

}