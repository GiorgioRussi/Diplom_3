package ru.praktikum.stellarburgers;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@DisplayName("Регистрация пользователя")
public class RegistrationPageTest extends ConstantsData{

    private WebDriver driver;
    RegistrationPage registrationPage;
    LoginPage loginPage;
    UserMethods userMethods;
    String accessToken;

    @Before
    public void setUp() {
        driver = WebDriverSetUp.createDriver();
        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
        userMethods = new UserMethods();
        registrationPage.openUrl();
        registrationPage.implicitWait();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Step("Очистка данных")
    public void cleanUp() {
        accessToken = userMethods.login(UserLogin.fromUser(user)).path("accessToken").toString();
        userMethods.delete(accessToken);
    }


    @Test
    @DisplayName("Регистрация пользователя: пароль 6 символов")
    public void shouldRegisterFormWith6SymbolsPassword() {
        user.setPassword(PASSWORD_6_SYMBOLS);
        assertTrue(registrationPage.isRegistrationFormVisible());
        registrationPage.fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        loginPage.explicitWaitToVisibleHeaderLoginPage();
        assertTrue(loginPage.atPage());
        cleanUp();
    }

    @Test
    @DisplayName("Регистрация пользователя: пароль больше 6 символов")
    public void shouldRegistrationFormWithMore6SymbolsPassword() {
        user.setPassword(PASSWORD_MORE_THEN_6_SYMBOLS);
        assertTrue(registrationPage.isRegistrationFormVisible());
        registrationPage.fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        loginPage.explicitWaitToVisibleHeaderLoginPage();
        assertTrue(loginPage.atPage());
        cleanUp();
    }

    @Test
    @DisplayName("Регистрация пользователя: пароль меньше 6 символов")
    public void shouldNotRegisterWithLess6SymbolsPassword() {
        user.setPassword(PASSWORD_LESS_THEN_6_SYMBOLS);
        assertTrue(registrationPage.isRegistrationFormVisible());
        registrationPage.fillRegisterForm(user.getName(), user.getEmail(), user.getPassword());
        registrationPage.clickRegisterButton();
        assertTrue(registrationPage.isErrorMessageVisible("Некорректный пароль"));
    }

    @After
    public void tearDown() {
        driver.quit();
        RestAssured.reset();
    }

}
