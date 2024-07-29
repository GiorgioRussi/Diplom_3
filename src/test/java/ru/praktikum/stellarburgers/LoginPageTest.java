package ru.praktikum.stellarburgers;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@DisplayName("Логин пользователя")
@RunWith(Parameterized.class)
public class LoginPageTest extends ConstantsData{

    private WebDriver driver;
    LoginPage loginPage;
    MainPage mainPage;
    UserMethods userMethods;
    private final By buttonToLogin;
    private final String urlOfPageWithRegisterButton;
    private final String description;
    String accessToken;

    public LoginPageTest(By buttonToLogin, String urlOfPageWithRegisterButton, String description) {
        this.buttonToLogin = buttonToLogin;
        this.urlOfPageWithRegisterButton = urlOfPageWithRegisterButton;
        this.description = description;
    }

    @Parameterized.Parameters(name = "По кнопке: {2}")
    public static Object [][] getData() {
        return getTestData();
    }

    @Before
    public void setUp() {
        userMethods = new UserMethods();
        accessToken = userMethods.create(user).path("accessToken").toString();
        driver = WebDriverSetUp.createDriver();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        loginPage.implicitWait();
    }

    @Test
    @DisplayName("Логин пользователя")
    public void shouldLoginByButton() {
        Allure.parameter("По кнопке", description);
        loginPage.openUrl(urlOfPageWithRegisterButton);
        assertTrue(loginPage.isLoginButtonVisible(buttonToLogin));
        loginPage.clickButtonToLogin(buttonToLogin);
        assertTrue(loginPage.atPage());
        loginPage.fillRegisterForm(user.getEmail(), user.getPassword());
        loginPage.clickButtonLogin();
        assertTrue(mainPage.isVisibleHeaderMainPage());
    }

    @After
    @Step("Очистка данных")
    public void tearDown() {
        driver.quit();
        userMethods.delete(accessToken);
    }

}
