package Lesson15;

import Lesson17.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewMtsTest {
    static MainPage mainPage = new MainPage(new ChromeDriver());

    /**
     * Выполняем данный метод первым до начала всех тестов
     */
    @BeforeAll
    public static void setup() {
        mainPage.goToMainPage();
        mainPage.acceptCookies();
    }

    /**
     * Выполняем данный метод последним, не зависимо от результатов тестов
     */
    @AfterAll
    public static void teardown() {
        if (mainPage.getCurrentDriver() != null) {
            mainPage.getCurrentDriver().quit();
        }
    }

    /**
     * Ищем блок "Онлайн пополнение без комиссии" и проверяем его заголовок
     */
    @DisplayName("Проверка названия блока")
    @ParameterizedTest
    @ValueSource(strings = {"Онлайн пополнение\nбез комиссии"})
    public void checkBlockHeader(String expectedTitle) {
        assertEquals(expectedTitle, mainPage.getPaymentBlockTitle().getText());
    }

    /**
     * Проверяем, отображаются ли логотипы платежных систем
     */
    @DisplayName("Проверка отображения логотипов платежных систем")
    @ParameterizedTest
    @ValueSource(strings = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"})
    public void checkPaymentLogos(String cardName) {
        assertTrue(mainPage.paymentLogo(cardName).isDisplayed());
    }


}
