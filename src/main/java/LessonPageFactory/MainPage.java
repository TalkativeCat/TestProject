package LessonPageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MainPage {
    private final WebDriver driver;

    private final String mainUrl = "https://www.mts.by/";
    private final String defaultMenuItemName = "Услуги связи";
    private final By defaultMenuItem = By.xpath("//span[@class = 'select__now']");
    private final By cookieModal = By.className("cookie__wrapper");
    private final By acceptCookieButton = By.xpath("//button[text() = 'Принять']");
    private final By paymentBlockTitle = By.xpath("//div[@class='pay__wrapper']/h2");
    private final By moreAboutService = By.xpath("//a[text() = 'Подробнее о сервисе']");
    private final By connectionPhone = By.xpath("//input[@id = 'connection-phone']");
    private final By connectionSum = By.xpath("//input[@id = 'connection-sum']");
    private final By connectionEmail = By.xpath("//input[@id = 'connection-email']");
    private final By connectionPhonePlaceholder = By.xpath("//input[@id = 'connection-phone']");
    private final By connectionSumPlaceholder = By.xpath("//input[@id = 'connection-sum']");
    private final By connectionEmailPlaceholder = By.xpath("//input[@id = 'connection-email']");
    private final By internetPhonePlaceholder = By.xpath("//input[@id = 'internet-phone']");
    private final By internetSumPlaceholder = By.xpath("//input[@id = 'internet-sum']");
    private final By internetEmailPlaceholder = By.xpath("//input[@id = 'internet-email']");
    private final By scoreInstalmentPlaceholder = By.xpath("//input[@id = 'score-instalment']");
    private final By instalmentSumPlaceholder = By.xpath("//input[@id = 'instalment-sum']");
    private final By instalmentEmailPlaceholder = By.xpath("//input[@id = 'instalment-email']");
    private final By scoreArrearsPlaceholder = By.xpath("//input[@id = 'score-arrears']");
    private final By arrearsSumPlaceholder = By.xpath("//input[@id = 'arrears-sum']");
    private final By arrearsEmailPlaceholder = By.xpath("//input[@id = 'arrears-email']");
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
    private final By noSumAllert = By.xpath("//p[text() = 'Введите сумму платежа']");
    private final By noEmailAllert = By.xpath("//p[text() = 'Введите корректный адрес электронной почты.']");
    private final By selector = By.xpath("//button[@class = 'select__header']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }
    public void checkDefaultMenuItem() {
        if(!driver.findElement(defaultMenuItem).getText().equals(defaultMenuItemName)) {
            driver.findElement(selector).click();
            WebElement item = driver.findElement(By.xpath("//p[text() = '" + defaultMenuItemName + "']"));
            waitForElementVisibility(item);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(3000));
            wait.until(ExpectedConditions.elementToBeClickable(item));
            item.click();
        }
    }

    public boolean paymentLogo(String cardName) {
        return driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]/..//img[@alt='" + cardName + "']")).isDisplayed();
    }

    public List<String> placeholders(String service) {
        List<String> placeholderList = new ArrayList<>();
        switch (service) {
            case("Услуги связи"):
                placeholderList.add(driver.findElement(connectionPhonePlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(connectionSumPlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(connectionEmailPlaceholder).getDomAttribute("placeholder"));
            case("Домашний интернет"):
                placeholderList.add(driver.findElement(internetPhonePlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(internetSumPlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(internetEmailPlaceholder).getDomAttribute("placeholder"));
            case("Рассрочка"):
                placeholderList.add(driver.findElement(scoreInstalmentPlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(instalmentSumPlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(instalmentEmailPlaceholder).getDomAttribute("placeholder"));
            case("Задолженность"):
                placeholderList.add(driver.findElement(scoreArrearsPlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(arrearsSumPlaceholder).getDomAttribute("placeholder"));
                placeholderList.add(driver.findElement(arrearsEmailPlaceholder).getDomAttribute("placeholder"));
        }
        return placeholderList;
    }

    public void switchToMenuItem(String service) {
        driver.findElement(selector).click();
        WebElement item = driver.findElement(By.xpath("//p[text() = '" + service + "']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
        waitVisibilityOfElementLocated(By.xpath("//p[text() = '" + service + "']"));
        item.click();
    }

    public void goToMainPage() {
        driver.get(mainUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(3000));
        wait.until(ExpectedConditions.titleContains("МТС – мобильный оператор в Беларуси"));

    }
    public void acceptCookies() {
        try {
            WebElement modal = driver.findElement(cookieModal);
            if (modal.isDisplayed()) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(3000));
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(acceptCookieButton));
                button.click();
            }
        } catch (NoSuchElementException e) {
        }
    }
    public WebDriver getCurrentDriver() {
        return driver;
    }
    public WebElement getPaymentBlockTitle() {
        return driver.findElement(paymentBlockTitle);
    }
    public void waitTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(3000));
        wait.until(ExpectedConditions.titleContains(title));
    }
    public WebElement moreAboutServiceLink() {
        return driver.findElement(moreAboutService);
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    public void submitPaymentForm(String phone, String sum, String email) {
        driver.findElement(connectionPhone).sendKeys(phone);
        driver.findElement(connectionSum).sendKeys(sum);
        driver.findElement(connectionEmail).sendKeys(email);
        driver.findElement(continueButton).click();
    }
    public void goToFrame() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4), Duration.ofMillis(1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(frame));
        driver.switchTo().frame(driver.findElement(frame));
        waitVisibilityOfElementLocated(finalSum);
    }
    public void closeFrame() {
        driver.findElement(closeFrameButton).click();
        driver.switchTo().defaultContent();
        waitVisibilityOfElementLocated(moreAboutService);
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
    public List<WebElement> cardLogos() {
        List<WebElement> cardLogos = new ArrayList<>();
        cardLogos.add(driver.findElement(visa));
        cardLogos.add(driver.findElement(mastercard));
        cardLogos.add(driver.findElement(belkart));
        cardLogos.add(driver.findElement(maestro));
        cardLogos.add(driver.findElement(mir));
        return cardLogos;
    }

    public void clearFields() {
        driver.findElement(connectionPhone).clear();
        driver.findElement(connectionSum).clear();
        driver.findElement(connectionEmail).clear();
    }
    private void waitVisibilityOfElementLocated(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4), Duration.ofMillis(5000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public boolean isNoSumAllertDisplayed() {
        return driver.findElement(noSumAllert).isDisplayed();
    }
    public boolean isNoEmailAllertDisplayed() {
        return driver.findElement(noEmailAllert).isDisplayed();
    }
    public String isAttributeRequired() {
        return driver.findElement(connectionSum).getDomAttribute("required");
    }
    public void waitForElementVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(3000));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
