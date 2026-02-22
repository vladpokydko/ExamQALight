package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductsPage {

    private final WebDriverWait wait;
    private Logger logger = Logger.getLogger(getClass());
    private WebDriver driver;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[text()='Ром Дед Менс Фінгерс']")
    private WebElement pageTitleElement;

private WebElement addToCartButton;

    @FindBy(tagName = "h1")
    private WebElement productTitleElement;
    
    @FindBy(xpath = "*//span[text()=\"1\"]")
    private WebElement productAddedToCartMessage;

    @FindBy(xpath = "*//a[@href=\"/ua/cart\"]")
    private WebElement cartIcon;

    public ProductsPage checkIsRedirectedToProductDetailsPage(String productName) {
        wait.until(ExpectedConditions.visibilityOf(productTitleElement));

        String actualTitle = productTitleElement.getText().trim();
        if (actualTitle.contains(productName)) {
            logger.info("Successfully redirected to the product details page: " + productName);
        } else {
            logger.info("Expected product: " + productName + ", but found: " + actualTitle);
        }

        return this;
    }

    public ProductsPage checkAppearanceOfAddToCartButton() {
        By addToCartLocator = By.xpath("//*[@id=\"__next\"]/main/div/section[1]/div[2]/ul[1]/div[2]/li/div/div[2]/button");

        // Очікуємо появу кнопки у DOM та її видимість
        WebElement addToCartButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(addToCartLocator));

        // Прокрутка в центр екрану для надійності
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior:'auto', block:'center'});", addToCartButton);

        // Перевірка видимості
        if (addToCartButton.isDisplayed()) {
            logger.info("Add to Cart button is displayed.");
        } else {
            logger.error("Add to Cart button is not displayed.");
        }

        return this;
    }

    public ProductsPage clickAddToCartButton() {
        By addToCartLocator = By.xpath("//*[@id=\"__next\"]/main/div/section[1]/div[2]/ul[1]/div[2]/li/div/div[2]/button");

        // Чекаємо, поки кнопка стане клікабельною
        WebElement addToCartButton = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(addToCartLocator));

        // Прокрутка до кнопки
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({behavior:'auto', block:'center'});", addToCartButton);

        // JS-клік для надійності
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCartButton);
        logger.info("Clicked 'Add to Cart' button using JS executor");

        // Повертаємо CartPage після того, як елементи сторінки завантажилися
        return this;
    }

    public CartPage clickOnCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        logger.info("Clicked on cart icon.");
        return new CartPage(driver);

    }
}