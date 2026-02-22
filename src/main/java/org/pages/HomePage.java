package org.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

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
}