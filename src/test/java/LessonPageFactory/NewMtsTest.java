package LessonPageFactory;

import LessonPageFactory.MainPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewMtsTest {
    static MainPage mainPage = new MainPage(new ChromeDriver());

    @BeforeAll
    public static void setup() {
        mainPage.goToMainPage();
        mainPage.acceptCookies();
    }

    @AfterAll
    public static void teardown() {
        if (mainPage.getCurrentDriver() != null) {
            mainPage.getCurrentDriver().quit();
        }
    }

    @DisplayName("Проверка названия блока")
    @ParameterizedTest
    @ValueSource(strings = {"Онлайн пополнение\nбез комиссии"})
    public void checkBlockHeader(String expectedTitle) {
        assertEquals(expectedTitle, mainPage.getPaymentBlockTitle().getText());
    }

    @DisplayName("Проверка отображения логотипов платежных систем")
    @ParameterizedTest
    @ValueSource(strings = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"})
    public void checkPaymentLogos(String cardName) {
        assertTrue(mainPage.paymentLogo(cardName));
    }

    @DisplayName("Проверка ссылки \"Подробнее о сервисе\"")
    @ParameterizedTest
    @CsvSource({"https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/, Порядок оплаты и безопасность интернет платежей"})
    public void checkMoreAboutServiceLink(String nextPageLink, String nextPageTitle) {
        mainPage.moreAboutServiceLink().click();
        mainPage.waitTitle(nextPageTitle);
        assertEquals(nextPageLink, mainPage.getCurrentUrl());
    }

    @DisplayName("Проверка корректного заполнения поля суммы и кнопки \"Продолжить\"")
    @ParameterizedTest
    @CsvSource({"0.1, 0.10",
            "1, 1.00",
            "499, 499.00",
            "499.99, 499.99",
            "-10, 10.00"})
    public void checkFillingSum(String sum, String displayedSum) {
//        mainPage.switchToMenuItem("Услуги связи");
        mainPage.checkDefaultMenuItem();
        mainPage.clearFields();
        mainPage.submitPaymentForm("297777777", sum, "qwwwww@dfgfg.ru");
        mainPage.goToFrame();
        assertEquals(mainPage.displayedSumExpected(displayedSum), mainPage.displayedSumActual());
        assertEquals(mainPage.sumInButtonExpected(displayedSum), mainPage.sumInButtonActual());
        assertEquals(mainPage.phoneExpected("297777777"), mainPage.phoneActual());
        mainPage.closeFrame();
        mainPage.clearFields();
    }

    @DisplayName("Проверка заполнения поля суммы нулём")
    @ParameterizedTest
    @ValueSource(strings = {"0"})
    public void checkFillingNullSum(String sum) {
        mainPage.checkDefaultMenuItem();

        mainPage.clearFields();
        mainPage.submitPaymentForm("297777777", sum, "qwwwww@dfgfg.ru");
        assertTrue(mainPage.isNoSumAllertDisplayed());
        mainPage.clearFields();
    }

    @DisplayName("Проверка оставления поля суммы пустым")
    @Test
    public void checkFillingEmptySum() {
        mainPage.checkDefaultMenuItem();
        assertEquals("true", mainPage.isAttributeRequired());
    }

    @DisplayName("Проверка некорректного заполнения email")
    @ParameterizedTest
    @ValueSource(strings = {"000"})
    public void checkFillingIncorrectEmail(String email) {
        mainPage.checkDefaultMenuItem();
        mainPage.submitPaymentForm("297777777", "50", email);
        assertTrue(mainPage.isNoEmailAllertDisplayed());
        mainPage.clearFields();
    }

    @DisplayName("Проверка оставления поля email пустым")
    @Test
    public void checkFillingEmptyEmail() {
        mainPage.checkDefaultMenuItem();
        mainPage.submitPaymentForm("297777777", "50", "qwwwww@dfgfg.ru");
        mainPage.goToFrame();
        mainPage.closeFrame();
        mainPage.clearFields();
    }

    @DisplayName("Проверка отображения номера телефона, названий полей в фрейме и лого платежных систем")
    @ParameterizedTest
    @CsvSource({"297777777, 50, qwwwww@dfgfg.ru, Номер карты, Срок действия, CVC, Имя держателя (как на карте)"})
    public void checkFramePhoneAndFields(String phone, String sum, String email, String card, String validityPeriod, String cvc, String holder) {
        mainPage.checkDefaultMenuItem();
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

    @DisplayName("Проверка названий полей в вариантах оплаты услуг")
    @ParameterizedTest
    @CsvSource({"Услуги связи, Номер телефона, Сумма, E-mail для отправки чека",
            "Домашний интернет, Номер абонента, Сумма, E-mail для отправки чека",
            "Рассрочка, Номер счета на 44, Сумма, E-mail для отправки чека",
            "Задолженность, Номер счета на 2073, Сумма, E-mail для отправки чека"})
    public void checkFieldsInEachService(String service, String field1, String field2, String field3)  {
        mainPage.checkDefaultMenuItem();
        List<String> expectedPlaceholder = Arrays.asList(field1, field2, field3);
        mainPage.switchToMenuItem(service);
        for (int i = 0; i < expectedPlaceholder.size(); i++) {
            assertEquals(expectedPlaceholder.get(i), mainPage.placeholders(service).get(i));
        }
    }



}
