package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//button[@data-testid='buyToCartButton']")
    private WebElement addToCartButton;

    @FindBy(tagName = "h1")
    private WebElement productTitleElement;

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
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));
        if (addToCartButton.isDisplayed()) {
            logger.info("Add to Cart button is displayed.");
        } else {
            logger.error("Add to Cart button is not displayed.");
        }
        return this;
    }
}