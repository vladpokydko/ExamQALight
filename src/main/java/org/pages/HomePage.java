package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    private Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath = "//button[@data-testid='sub-header-menu-btn']")
    private WebElement catalogButton;


 public HomePage(WebDriver webDriver) {
        super();
    }

    protected String getRelativeURL() {
        return "/";


}
    }
