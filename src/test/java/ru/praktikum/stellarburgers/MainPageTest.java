package ru.praktikum.stellarburgers;


import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@DisplayName("Главная страница")
@RunWith(Parameterized.class)
public class MainPageTest extends ConstantsData {

    private WebDriver driver;
    MainPage mainPage;
    final String tabName;
    private final By tabLocator;

    public MainPageTest(String tabName, By tabLocator) {
        this.tabName = tabName;
        this.tabLocator = tabLocator;
    }

    @Before
    public void setUp() {
        driver = WebDriverSetUp.createDriver();
        mainPage = new MainPage(driver);
        mainPage.openUrl();
        mainPage.implicitWait();
    }

    @Parameterized.Parameters(name = "Таб: {0}")
    public static Object [][] getData() {
        return getTestDataToTabMovingTest();
    }

    @Test
    @DisplayName("Проверка перехода к разделу")
    public void shouldMovingToTab() {
        Allure.parameter("Таб", tabName);
        if (!mainPage.checkClassAttributeContains(tabLocator)) {
            mainPage.clickOnTab(tabName);
        }
        assertTrue(mainPage.checkClassAttributeContains(tabLocator));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
