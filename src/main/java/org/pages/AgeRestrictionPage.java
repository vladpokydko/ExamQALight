package org.pages;

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

    public void clickNo() {
        buttonNo.click();
    }

    public void clickYes() {
        buttonYes.click();
    }
}
