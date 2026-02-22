package org.pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgeRestrictionPage {

    private WebDriver webDriver;
    public AgeRestrictionPage(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='__next']/footer/div[1]/div/div[1]/a/button[text()='Ні']")
    private WebElement buttonNo;

    @FindBy(xpath = "//*[@id='__next']/footer/div[1]/div/div[1]/div/button[text()='Так']")
    private WebElement buttonYes;

    public AgeRestrictionPage clickNo() {
        buttonNo.click();
        return this;
    }

    public AgeRestrictionPage clickYes() {
        buttonYes.click();
        return this;
    }

    public AgeRestrictionPage checkIsRedirectedToGoogle() {
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertTrue(
                "User should be redirected to google.com",
                currentUrl.contains("google.com")
        );
        return this;
    }

    public AgeRestrictionPage checkIsRedirectedToHomePage() {
        String currentUrl = webDriver.getCurrentUrl();
        Assert.assertEquals(
                "User should be redirected to OKWINE main page",
                "https://okwine.ua/",
                currentUrl
        );
        return this;
    }
}
