package ru.praktikum.stellarburgers;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

public class ConstantsData {
    static Faker faker = new Faker();
    public static String NAME = faker.name().username();
    public static String EMAIL = faker.internet().emailAddress();


    public static String PASSWORD_6_SYMBOLS = RandomStringUtils.random(6, true, true);
    public static String PASSWORD_LESS_THEN_6_SYMBOLS = RandomStringUtils.random(5, true, true);
    public static String PASSWORD_MORE_THEN_6_SYMBOLS = RandomStringUtils.random(7, true, true);

    User user = new User(EMAIL, PASSWORD_MORE_THEN_6_SYMBOLS, NAME);

    static final By BUTTON_CONSTRUCTOR = By.xpath(".//p[text() = 'Конструктор']");
    static final By BUTTON_LOGO = By.xpath(".//div[@class = 'AppHeader_header__logo__2D0X2']");
    static final By BUTTON_PERSONAL_ACCOUNT = By.xpath(".//p[text() = 'Личный Кабинет']");
    static final By BUTTON_TO_LOGIN_AT_MAIN_PAGE = By.xpath(".//button[text() = 'Войти в аккаунт']");
    static final By BUTTON_EXIT = By.xpath(".//button[text() = 'Выход']");
    static final By BUTTON_TO_LOGIN_AT_REGISTER_AND_FORGOT_PASSWORD_PAGES = By.xpath(".//a[text() = 'Войти']");


    static final String URL_MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    static final String URL_REGISTER_PAGE = "https://stellarburgers.nomoreparties.site/register";
    static final String URL_FORGOT_PASSWORD_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";
    static final String URL_LOGIN_PAGE = "https://stellarburgers.nomoreparties.site/login";


    final By headerLoginPage = By.xpath(".//h2[text()='Вход']");

    public static Object[][] getTestData() {
        return new Object[][]{
                {BUTTON_TO_LOGIN_AT_MAIN_PAGE, URL_MAIN_PAGE, "'Войти в аккаунт' на главной странице"},
                {BUTTON_PERSONAL_ACCOUNT, URL_MAIN_PAGE, "'Личный кабинет' на главной странице"},
                {BUTTON_TO_LOGIN_AT_REGISTER_AND_FORGOT_PASSWORD_PAGES, URL_REGISTER_PAGE, "'Войти' на странице регистрации"},
                {BUTTON_TO_LOGIN_AT_REGISTER_AND_FORGOT_PASSWORD_PAGES, URL_FORGOT_PASSWORD_PAGE, "'Войти' на странице восстановления пароля"},
        };
    }

    static final By sauceTab = By.xpath(".//span[text() = 'Соусы']/parent::div");
    static final By fillingTab = By.xpath(".//span[text() = 'Начинки']/parent::div");
    static final By bunTab = By.xpath(".//span[text() = 'Булки']/parent::div");

    public static Object[][] getTestDataToTabMovingTest() {
        return new Object[][]{
                {"Соусы", sauceTab},
                {"Начинки", fillingTab},
                {"Булки", bunTab},
        };
    }
}
