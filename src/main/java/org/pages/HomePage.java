package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    public Logger logger = Logger.getLogger(getClass());
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@data-testid='integrationSearchBarInput']")
    private WebElement searchInput;

    @FindBy(xpath = "//button[@data-testid='integrationSearchGoButton']")
    private WebElement searchButton;

    @FindBy(xpath = "//button[@data-theme='defaultInvert' and text()='Ні']")
    private WebElement noButton;

    @FindBy(xpath = "//input[@placeholder='Введіть назву населеного пункту']")
    private WebElement cityInput;

    @FindBy(xpath = "//span[text()='Львів']")
    private WebElement lvivOption;

    @FindBy(xpath = "//a[@href='/ua/contacts' and text()='Контакти']")
    private WebElement contactsLink;

    @FindBy(xpath = "//button/span[text()='Львів']")
    private WebElement selectedCityButton;

    @FindBy(xpath = "//p[text()='Франківський']")
    private WebElement frankivskyiDistrict;

    @FindBy(xpath = "//p[text()='Сихівський']")
    private WebElement sykhivskyiDistrict;

    @FindBy(xpath = "//p[text()='Шевченківський']")
    private WebElement shevchenkivskyiDistrict;

    public HomePage clickSearchInput() {
        wait.until(ExpectedConditions.elementToBeClickable(searchInput)).click();
        return this;
    }

    public HomePage enterSearchText(String text) {
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.clear();
        searchInput.sendKeys(text);
        return this;
    }

    public SearchResultsPage clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
        return new SearchResultsPage(driver);
    }

    // Натиснути кнопку "Ні"
    public HomePage clickNoButton() {
        // Чекати поки кнопка видима
        wait.until(ExpectedConditions.visibilityOf(noButton));

        // Прокрутка елемента в центр вікна
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior:'auto', block:'center'});", noButton);

        // Виконати клік через JavaScript (обходить перехоплення)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", noButton);

        logger.info("Clicked 'Ні' button using JS executor");
        return this;
    }

    // Ввести назву міста
    public HomePage enterCityName(String cityName) {
        wait.until(ExpectedConditions.visibilityOf(cityInput)).clear();
        cityInput.sendKeys(cityName);
        logger.info("Entered city name: " + cityName);
        return this;
    }

    // Обрати місто зі списку
    public HomePage selectCity(String cityName) {
        // Чекаємо, поки потрібний елемент стане видимим і clickable
        wait.until(driver -> {
            try {
                WebElement cityOption = driver.findElement(By.xpath("//span[text()='" + cityName + "']"));
                return cityOption.isDisplayed() && cityOption.isEnabled();
            } catch (StaleElementReferenceException e) {
                return false; // повторимо чек, якщо елемент став stale
            }
        });

        // Після очікування отримуємо елемент знову
        WebElement cityOption = driver.findElement(By.xpath("//span[text()='" + cityName + "']"));

        // Скролимо до елемента і клікаємо через JS
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior:'auto', block:'center'});", cityOption);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", cityOption);

        logger.info("Selected city: " + cityName);
        return this;
    }

    // Натиснути "Контакти"
    public HomePage clickContacts() {
        wait.until(ExpectedConditions.visibilityOf(contactsLink));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior:'auto', block:'center'});", contactsLink);

        // Виконати клік через JavaScript (обходить перехоплення)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", contactsLink);

        logger.info("Clicked 'Контакти' button using JS executor");
        return this;
    }

    // Перевірити, що вибране місто

    public HomePage verifySelectedCityAndDistricts() {
        // Чекати видимість районів
        wait.until(ExpectedConditions.visibilityOf(frankivskyiDistrict));
        wait.until(ExpectedConditions.visibilityOf(sykhivskyiDistrict));
        wait.until(ExpectedConditions.visibilityOf(shevchenkivskyiDistrict));

        // Логування результатів
        if (frankivskyiDistrict.isDisplayed() &&
                sykhivskyiDistrict.isDisplayed() &&
                shevchenkivskyiDistrict.isDisplayed()) {
            logger.info("All districts are visible: Франківський, Сихівський, Шевченківський");
        } else {
            logger.error("Some districts are missing!");
        }

        return this;
    }
}