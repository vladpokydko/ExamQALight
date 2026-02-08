package org.ageRestrictionTests;
import org.baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;


public class AgeRestrictionTests extends BaseTest {
    /**
     * #005 Check the age restriction for alcohol products (invalid case)
     */
    @Test
    public void checkAgeRestrictionInvalidCase() {
        logger.info("TC #005: Invalid age verification started");
        pageProvider.openAgeRestrictionPage().clickNo();
        
        String currentUrl = webDriver.getCurrentUrl();
        logger.info("Redirected URL: " + currentUrl);

        Assert.assertTrue(
                "User should be redirected to google.com",
                currentUrl.contains("google.com")
        );

        logger.info("TC #005: Passed");
    }

    /**
     * #006 Check the age restriction for alcohol products (valid case)
     */
    @Test
    public void checkAgeRestrictionValidCase() {
        logger.info("TC #006: Valid age verification started");
        pageProvider.openAgeRestrictionPage().clickYes();

        String currentUrl = webDriver.getCurrentUrl();
        logger.info("Redirected URL: " + currentUrl);

        Assert.assertEquals(
                "User should be redirected to OKWINE main page",
                "https://okwine.ua/",
                currentUrl
        );

        logger.info("TC #006: Passed");
    }
}