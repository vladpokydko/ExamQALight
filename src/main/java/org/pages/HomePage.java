package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private Logger logger = Logger.getLogger(getClass());

 public HomePage(WebDriver webDriver) {
        super();
    }

    protected String getRelativeURL() {
        return "/";


}
    }
