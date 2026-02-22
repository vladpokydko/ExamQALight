package org.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class CatalogPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // ← ВАЖНО!
    }

    @FindBy(xpath = "//button[@data-testid='sub-header-menu-btn']")
    private WebElement catalogButton;

    @FindBy(xpath = "//div[contains(@class,'sub-header')]//ul")
    private WebElement catalogDropdown;

    @FindBy(xpath = "//div[contains(@class,'sub-header')]//ul/li/a")
    private List<WebElement> catalogCategories;

    @FindBy(xpath = "//a[normalize-space()='Віскі']")
    private WebElement whiskyCategory;

    @FindBy(xpath = "//a[normalize-space()='Віскі']/following-sibling::div//a")
    private List<WebElement> whiskySubCategories;

    @FindBy(xpath = "//a[contains(@href,'/ua/single-malt-scotch-whiskey') and normalize-space()='Односолодовий віскі']")
    private WebElement singleMaltWhisky;

    public void openCatalog() {
        wait.until(ExpectedConditions.elementToBeClickable(catalogButton)).click();
    }

}