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
        logger.info("TC #001: Invalid age verification started");

        pageProvider
                .openAgeRestrictionPage()
                .clickNo()
                .checkIsRedirectedToGoogle();

        logger.info("TC #001: Passed");
    }

    /**
     * #006 Check the age restriction for alcohol products (valid case)
     */
    @Test
    public void checkAgeRestrictionValidCase() {
        logger.info("TC #002: Valid age verification started");

        pageProvider
                .openAgeRestrictionPage()
                .clickYes()
                .checkIsRedirectedToHomePage();

        logger.info("TC #002: Passed");
    }
}