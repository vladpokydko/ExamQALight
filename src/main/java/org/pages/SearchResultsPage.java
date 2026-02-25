package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//p[text()=\"Dead Man's Fingers\"]")
    private WebElement productTitle;

    @FindBy(xpath = "//a[@data-testid='integrationProductLink'][1]")
    private WebElement firstProductLink;

    public boolean isProductDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.isDisplayed();
    }

    public ProductsPage clickOnProduct() {
        // Чекаємо, поки посилання на продукт стане клікабельним, і клікаємо по ньому
        wait.until(ExpectedConditions.elementToBeClickable(firstProductLink)).click();
        return new ProductsPage(driver);
    }



}