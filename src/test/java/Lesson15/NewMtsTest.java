package Lesson15;

import Lesson17.MainPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;
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
        if (mainPage.acceptCookies().isDisplayed()) {
            mainPage.acceptCookies().click();
        }
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

    @DisplayName("Проверка ссылки \"Подробнее о сервисе\"")
    @ParameterizedTest
    @CsvSource({"https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/, Порядок оплаты и безопасность интернет платежей"})
    public void checkMoreAboutServiceLink(String nextPageLink, String nextPageTitle) {
        mainPage.moreAboutServiceLink().click();
        mainPage.waitTitle(nextPageTitle);
        assertEquals(nextPageLink, mainPage.getCurrentUrl());
        mainPage.goToMainPage();
    }
    @DisplayName("Проверка корректного заполнения поля суммы и кнопки \"Продолжить\"")
    @ParameterizedTest
    @CsvSource({"0.1, 0.10",
            "1, 1.00",
            "499, 499.00",
            "499.99, 499.99",
            "-10, 10.00"})
    public void checkFillingSum(String sum, String displayedSum) {
        mainPage.submitPaymentForm("297777777", sum, "qwwwww@dfgfg.ru");
        mainPage.goToFrame();
        assertEquals(mainPage.displayedSumExpected(displayedSum), mainPage.displayedSumActual());
        assertEquals(mainPage.sumInButtonExpected(displayedSum), mainPage.sumInButtonActual());
        assertEquals(mainPage.phoneExpected(displayedSum), mainPage.phoneActual());
        mainPage.closeFrame();
        mainPage.clearFields();
    }
    @DisplayName("Проверка заполнения поля суммы нулём")
    @ParameterizedTest
    @ValueSource(strings = {"0"})
    public void checkFillingNullSum(String sum) {
        mainPage.submitPaymentForm("297777777", sum, "qwwwww@dfgfg.ru");
        assertTrue(mainPage.isNoSumAllertDisplayed());
        mainPage.clearFields();
    }
    @DisplayName("Проверка заполнения поля суммы нулём")
    @Test
    public void checkFillingEmptySum() {
        assertEquals("true", mainPage.isAttributeRequired());
    }

    @DisplayName("Проверка некорректного заполнения email")
    @ParameterizedTest
    @ValueSource(strings = {"000"})
    public void checkFillingIncorrectEmail(String email) {
        mainPage.submitPaymentForm("297777777", "50", email);
        assertTrue(mainPage.isNoEmailAllertDisplayed());
        mainPage.clearFields();
    }

    @DisplayName("Проверка оставления поля email пустым")
    @Test
    public void checkFillingEmptyEmail() {
        mainPage.submitPaymentForm("297777777", "50", "qwwwww@dfgfg.ru");
        mainPage.goToFrame();
        mainPage.closeFrame();
        mainPage.clearFields();
    }

    @DisplayName("Проверка отображения номера телефона и названий полей в фрейме")
    @ParameterizedTest
    @CsvSource({"297777777, 50, qwwwww@dfgfg.ru, Номер карты, Срок действия, CVC, Имя держателя (как на карте)"})
    public void checkFramePhoneAndFields(String phone, String sum, String email, String card, String validityPeriod, String cvc, String holder) {
        mainPage.submitPaymentForm(phone, sum, email);
        mainPage.goToFrame();
        assertEquals(mainPage.phoneExpected(phone), mainPage.phoneActual());
        assertEquals(card, mainPage.card());
        assertEquals(validityPeriod, mainPage.validityPeriod());
        assertEquals(cvc, mainPage.cvc());
        assertEquals(holder, mainPage.holder());
        for (WebElement cards : mainPage.cardLogos()) {
            mainPage.waitForElementVisibility(cards);
            assertTrue(cards.isDisplayed());
        }
        mainPage.closeFrame();
        mainPage.clearFields();
    }



}
