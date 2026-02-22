package org.productSearchTests;

import org.baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ProductSearchTest extends BaseTest {
    @Test
    public void checkSearchFunctionality() {

        logger.info("TC: Search product started");

        pageProvider
                .openAgeRestrictionPage()
                .clickYes()
                .checkIsRedirectedToHomePage();

        boolean isProductDisplayed = pageProvider
                .getHomePage()
                .clickSearchInput()
                .enterSearchText("Dead Man's Fingers")
                .clickSearchButton()
                .isProductDisplayed();

        Assert.assertTrue("Product is not displayed in search results", isProductDisplayed);

        logger.info("TC: Search product passed");
    }
}
