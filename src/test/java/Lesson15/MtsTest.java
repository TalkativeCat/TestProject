package Lesson15;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MtsTest {
    private static WebDriver driver;

    /**
     * Выполняем данный метод первым до начала всех тестов
     */
    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.mts.by/");
        By cookieAccept = By.xpath("//h3[contains(text(), 'Обработка файлов cookie')]/../../..//button[text() = 'Принять']");
        if(driver.findElement(cookieAccept).isDisplayed()) {
            driver.findElement(cookieAccept).click();
        }
    }

    /**
     * Выполняем данный метод последним, не зависимо от результатов тестов
     */
    @AfterAll
    public static void teardown() {
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
        String actualTitle = driver.findElement(By.xpath("//div[@class='pay__wrapper']/h2")).getText();
        assertEquals("Онлайн пополнение\nбез комиссии", actualTitle);
    }
    /**
     * Проверяем, отображаются ли логотипы платежных систем
     */
    @DisplayName("Проверка отображения логотипов платежных систем")
    @ParameterizedTest
    @ValueSource(strings = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"})
    public void checkPaymentLogos(String cardName) {
        assertTrue(driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]/..//img[@alt='" + cardName + "']")).isDisplayed());
    }

    @DisplayName("Проверка ссылки \"Подробнее о сервисе\"")
    @Test
    public void checkMoreAboutServiceLink() {
        By link = By.xpath("//a[text() = 'Подробнее о сервисе']");
        String nextPageUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        driver.findElement(link).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(1000));
        wait.until(ExpectedConditions.titleContains("Порядок оплаты и безопасность интернет платежей"));
        assertEquals(nextPageUrl, driver.getCurrentUrl());
        driver.navigate().back();
        wait.until(ExpectedConditions.titleContains("МТС – мобильный оператор в Беларуси"));
    }

    @DisplayName("Проверка корректного заполнения поля суммы и кнопки \"Продолжить\"")
    @ParameterizedTest
    @CsvSource({"0.1, 0.10", "1, 1.00", "499, 499.00", "499.99, 499.99", "-10, 10.00"})
    public void checkFillingSum(String sum, String displayedSum) {
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).sendKeys(sum);
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).sendKeys("qwwwww@dfgfg.ru");
        driver.findElement(By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe.bepaid-iframe")));
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.bepaid-iframe")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'BYN')]")));
        assertEquals(displayedSum + " BYN", driver.findElement(By.xpath("//span[contains(text(), 'BYN')]")).getText());
        assertEquals("Оплатить " + displayedSum + " BYN", driver.findElement(By.xpath("//button[contains(text(), 'Оплатить')]")).getText());
        driver.findElement(By.xpath("//svg-icon[@class = 'header__close-icon']")).click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'connection-phone']")));
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).clear();

    }
    @DisplayName("Проверка заполнения поля суммы нулём")
    @ParameterizedTest
    @ValueSource(strings = {"0"})
    public void checkFillingNullSum(String sum) {
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).sendKeys(sum);
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).sendKeys("qwwwww@dfgfg.ru");
        driver.findElement(By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']")).click();
        assertTrue(driver.findElement(By.xpath("//p[text() = 'Введите сумму платежа']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).clear();
    }
    @DisplayName("Проверка заполнения поля суммы нулём")
    @Test
    public void checkFillingEmptySum() {
        String isInputRequired = driver.findElement(By.xpath("//input[@id = 'connection-sum']")).getDomAttribute("required");
        assertEquals("true", isInputRequired);
    }

    @DisplayName("Проверка некорректного заполнения email")
    @ParameterizedTest
    @ValueSource(strings = {"000"})
    public void checkFillingIncorrectEmail(String email) {
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).sendKeys("28.57");
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).sendKeys(email);
        driver.findElement(By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']")).click();
        assertTrue(driver.findElement(By.xpath("//p[text() = 'Введите корректный адрес электронной почты.']")).isDisplayed());
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).clear();
    }

    @DisplayName("Проверка оставления поля email пустым")
    @Test
    public void checkFillingEmptyEmail() {
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).sendKeys("297777777");
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).sendKeys("28.57");
        driver.findElement(By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("iframe.bepaid-iframe")));
        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.bepaid-iframe")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(), 'BYN')]")));
        assertEquals("28.57 BYN", driver.findElement(By.xpath("//span[contains(text(), 'BYN')]")).getText());
        assertEquals("Оплатить 28.57 BYN", driver.findElement(By.xpath("//button[contains(text(), 'Оплатить')]")).getText());
        driver.findElement(By.xpath("//svg-icon[@class = 'header__close-icon']")).click();
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id = 'connection-phone']")));
        driver.findElement(By.xpath("//input[@id = 'connection-phone']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-sum']")).clear();
        driver.findElement(By.xpath("//input[@id = 'connection-email']")).clear();
    }
}
