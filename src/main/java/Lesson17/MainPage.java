package Lesson17;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    private final String mainUrl = "https://www.mts.by/";
    private final By acceptCookieButton = By.xpath("//h3[contains(text(), 'Обработка файлов cookie')]/../../..//button[text() = 'Принять']");
    private final By paymentBlockTitle = By.xpath("//div[@class='pay__wrapper']/h2");


    public MainPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();

    }

    public WebElement paymentLogo(String cardName) {
        return driver.findElement(By.xpath("//h2[contains(text(), 'Онлайн пополнение')]/..//img[@alt='" + cardName + "']"));
    }



    public void goToMainPage() {
        driver.get(mainUrl);
    }
    public void acceptCookies() {
        if(driver.findElement(acceptCookieButton).isDisplayed()) {
            driver.findElement(acceptCookieButton).click();
        }
    }
    public WebDriver getCurrentDriver() {
        return driver;
    }
    public WebElement getPaymentBlockTitle() {
        return driver.findElement(paymentBlockTitle);
    }



}
