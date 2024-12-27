package Lesson17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    private final String mainUrl = "https://www.mts.by/";
    private final By acceptCookieButton = By.xpath("//h3[contains(text(), 'Обработка файлов cookie')]/../../..//button[text() = 'Принять']");
    private final By paymentBlockTitle = By.xpath("//div[@class='pay__wrapper']/h2");
    private final By moreAboutService = By.xpath("//a[text() = 'Подробнее о сервисе']");
    private final By phoneInput = By.xpath("//input[@id = 'connection-phone']");
    private final By sumInput = By.xpath("//input[@id = 'connection-sum']");
    private final By emailInput = By.xpath("//input[@id = 'connection-email']");
    private final By continueButton = By.xpath("//form[@id = 'pay-connection']//button[text() = 'Продолжить']");
    private final By frame = By.cssSelector("iframe.bepaid-iframe");
    private final By finalSum = By.xpath("//span[contains(text(), 'BYN')]");
    private final By payButton = By.xpath("//button[contains(text(), 'Оплатить')]");
    private final By closeFrameButton = By.xpath("//svg-icon[@class = 'header__close-icon']");
    private final By displayedPhone = By.xpath("//span[contains(text(), 'Номер')]");
    private final By cardInputLabel = By.xpath("//input[@formcontrolname = 'creditCard']/..//label");
    private final By validityPeriodLabel = By.xpath("//input[@formcontrolname = 'expirationDate']/..//label");
    private final By cvcInputLabel = By.xpath("//input[@formcontrolname = 'cvc']/..//label");
    private final By holderNameLabel = By.xpath("//input[@formcontrolname = 'holder']/..//label");
    private final By visa = By.xpath("//img[contains(@src, 'visa-system')]");
    private final By mastercard = By.xpath("//img[contains(@src, 'mastercard-system')]");
    private final By belkart = By.xpath("//img[contains(@src, 'belkart-system')]");
    private final By maestro = By.xpath("//img[contains(@src, 'maestro-system')]");
    private final By mir = By.xpath("//img[contains(@src, 'mir-system-ru')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    public WebElement paymentLogo(String cardName) {
        return driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]/..//img[@alt='" + cardName + "']"));
    }
    public List<WebElement> cardLogos() {
        List<WebElement> cardLogos = new ArrayList<>();
        cardLogos.add(driver.findElement(visa));
        cardLogos.add(driver.findElement(mastercard));
        cardLogos.add(driver.findElement(belkart));
        cardLogos.add(driver.findElement(maestro));
        cardLogos.add(driver.findElement(mir));
        return cardLogos;
    }

    public void goToMainPage() {
        driver.get(mainUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(1000));
        wait.until(ExpectedConditions.titleContains("МТС – мобильный оператор в Беларуси"));
    }
    public WebElement acceptCookies() {
        return driver.findElement(acceptCookieButton);
    }
    public WebDriver getCurrentDriver() {
        return driver;
    }
    public WebElement getPaymentBlockTitle() {
        return driver.findElement(paymentBlockTitle);
    }
    public void waitTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(1000));
        wait.until(ExpectedConditions.titleContains(title));
    }
    public WebElement moreAboutServiceLink() {
        return driver.findElement(moreAboutService);
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public void submitPaymentForm(String phone, String sum, String email) {
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(sumInput).sendKeys(sum);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(continueButton).click();
    }
    public void goToFrame() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(frame));
        driver.switchTo().frame(driver.findElement(frame));
        waitVisibilityOfElementLocated(finalSum);
    }
    public void closeFrame() {
        driver.findElement(closeFrameButton).click();
        driver.switchTo().defaultContent();
    }
    public String displayedSumExpected(String displayedSum) {
        return displayedSum + " BYN";
    }
    public String displayedSumActual() {
        return driver.findElement(finalSum).getText();
    }
    public String sumInButtonExpected(String displayedSum) {
        return "Оплатить " + displayedSum + " BYN";
    }
    public String sumInButtonActual() {
        return driver.findElement(payButton).getText();
    }
    public String phoneExpected(String displayedPhone) {
        return "Оплата: Услуги связи Номер:375" + displayedPhone;
    }
    public String phoneActual() {
        return driver.findElement(displayedPhone).getText();
    }
    public String card() {
        return driver.findElement(cardInputLabel).getText();
    }
    public String validityPeriod() {
        return driver.findElement(validityPeriodLabel).getText();
    }
    public String cvc() {
        return driver.findElement(cvcInputLabel).getText();
    }
    public String holder() {
        return driver.findElement(holderNameLabel).getText();
    }

    public void clearFields() {
        driver.findElement(phoneInput).clear();
        driver.findElement(sumInput).clear();
        driver.findElement(emailInput).clear();
    }
    private void waitVisibilityOfElementLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOf(element));
    }



}
