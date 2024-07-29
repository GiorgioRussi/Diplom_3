package ru.praktikum.stellarburgers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetUp {
    public static WebDriver createDriver() {
        WebDriver driver = null;
        //ключ -Dbrowser=chrome
        //ключ -Dbrowser=yandex
        //по умолчанию chrome
        String browser = System.getProperty("browser", "chrome");
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
            driver = new ChromeDriver();
        } else {
            System.out.println("Неподдерживаемый браузер: " + browser);
        }
        return driver;
    }
}
