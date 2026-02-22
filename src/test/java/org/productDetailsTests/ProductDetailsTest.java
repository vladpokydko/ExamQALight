package org.productDetailsTests;

import org.baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;

public class ProductDetailsTest extends BaseTest {
    @Test
    public void checkProductDetails() {

        logger.info("TC: Product details page appearance started");

        pageProvider
                .openAgeRestrictionPage()
                .clickYes()
                .checkIsRedirectedToHomePage();

       pageProvider
                .getHomePage()
                .clickSearchInput()
                .enterSearchText("Dead Man's Fingers")
                .clickSearchButton()
                .isProductDisplayed();

        pageProvider
                .getSearchResultsPage()
                .clickOnProduct();

        pageProvider
                .getProductDetailsPage()
                .checkIsRedirectedToProductDetailsPage("Ром Дед Менс Фінгерс");

        logger.info("TC: Product details page appearance passed");
    }
}
