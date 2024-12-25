package Lesson15;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTest {
    private static WebDriver driver;

    /**
     * Выполняем данный метод первым до начала всех тестов
     */
    @BeforeAll
    public static void setup() {
        // Создание экземпляра драйвера Chrome
        driver = new ChromeDriver();
        //Разворачиваем окно браузера на весь экран
        driver.manage().window().maximize();
        // Переходим на сайт
        driver.get("https://www.mts.by/");
        // модальное окно с заголовком "Обработка файлов cookie"
        By cookieAccept = By.xpath("//h3[contains(text(), 'Обработка файлов cookie')]/../../..//button[text() = 'Принять']");
        // Действие на случай, если вылезет модалка "Обработка файлов cookie"
        if(driver.findElement(cookieAccept).isDisplayed()) {
            // Кликаем на кнопку "Принять" в модальном окне "Обработка файлов cookie"
            driver.findElement(cookieAccept).click();
        }
    }

    /**
     * Выполняем данный метод последним, не зависимо от результатов тестов
     */
    @AfterAll
    public static void teardown() {
        // Если находим активный драйвер, закрываем его, иначе окно браузера останется
        // висеть открытым после окончания тестов
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Ищем блок "Онлайн пополнение без комиссии" и проверяем его заголовок
     */
    @DisplayName("Проверка названия блока")
    @Test
    public void checkBlockHeader() {
        // Получаем текст из локатора
        String actualTitle = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText();
        // Сравниваем текст из локатора с референсным текстом
        assertEquals("Онлайн пополнение\nбез комиссии", actualTitle);
    }
    /**
     * Проверяем, отображаются ли логотипы платежных систем
     */
    @DisplayName("Проверка отображения логотипов платежных систем")
    @ParameterizedTest
    @ValueSource(strings = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"})
    public void checkPaymentLogos(String cardName) {
        // Поскольку сам локатор у нас для каждого лого платежной системы один и тот же (меняется только название),
        // мы можем запихать названия в параметры и лишь написать базовый assert, а названия запихать в параметры теста.
        // Тогда не придется в тесте создавать ArrayList с перечнем локаторов, JUnit сделает это за нас сам и проверит каждый из них
        assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]/..//img[@alt='" + cardName + "']")).isDisplayed());
    }

    @DisplayName("Проверка ссылки \"Подробнее о сервисе\"")
    @Test
    public void checkMoreAboutServiceLink() {
        // Заранее инициализируем локатор
        By link = By.xpath("//a[text() = 'Подробнее о сервисе']");
        // Заранее инициализируем строку с референсным URL
        String nextPageUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        // Кликаем на ссылку Подробнее о сервисе
        driver.findElement(link).click();
        // Ждем, пока загрузится страница. Проверяем это по появлению поля title с определенным текстом
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(1000));
        wait.until(ExpectedConditions.titleContains("Порядок оплаты и безопасность интернет платежей"));
        // Сравниваем референсный URL и URL открытой страницы
        assertEquals(nextPageUrl, driver.getCurrentUrl());
        driver.navigate().back();
        wait.until(ExpectedConditions.titleContains("МТС – мобильный оператор в Беларуси"));
    }

    @DisplayName("Проверка корректного заполнения поля суммы и кнопки \"Продолжить\"")
    @ParameterizedTest
    @ValueSource(strings = {"50"})
    public void checkFillingFieldCorrectSum(String sum) throws AWTException {
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).sendKeys(sum);
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).sendKeys("qwwwww@dfgfg.ru");
        driver.findElement(By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']")).click();
        Robot robot = new Robot();
        robot.mouseMove(100, 100);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ESCAPE).perform();
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).clear();
    }
    @DisplayName("Проверка некорректного заполнения поля суммы")
    @ParameterizedTest
    @ValueSource(strings = {"0"})
    public void checkFillingFieldIncorrectSum(String sum) {
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).sendKeys(sum);
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).sendKeys("qwwwww@dfgfg.ru");
        driver.findElement(By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']")).click();
        assertTrue(driver.findElement(By.xpath("//p[text() = 'Введите сумму платежа']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).clear();
    }
    @DisplayName("Проверка некорректного заполнения email")
    @ParameterizedTest
    @ValueSource(strings = {"000"})
    public void checkFillingFieldEmail(String email) {
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).sendKeys("28.57");
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).sendKeys(email);
        driver.findElement(By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']")).click();
        assertTrue(driver.findElement(By.xpath("//p[text() = 'Введите корректный адрес электронной почты.']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).clear();
    }
}
