package org.shoppingCartTests;

import org.baseTest.BaseTest;
import org.junit.Test;

public class FillingTheShoppingCart extends BaseTest {
    @Test
    public void fillingShoppingCart() {

        logger.info("TC: Shopping Cart appearance started");

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
                .checkIsRedirectedToProductDetailsPage("Ром Дед Менс Фінгерс")
                .clickAddToCartButton()
                .clickOnCartIcon()
                .checkIsRedirectedToCartPage()
                .checkProductIsInCart("Ром Дед Менс Фінгерс")
                .checkProductQuantityInCart("1")
                .fillNameInput("Владислав")
                .fillSurnameInput("Покидько")
                .fillPhoneInput("+380937667166")
                .fillEmailInput("test@test.co")
                .chooseInDropdownDeliveryMethod("Самовивіз")
                .filterPickupStores("Драгоманова")
                .choosePickupStore("Драгоманова")
                .chooseInDropdownPaymentMethod("Оплата готівкою")
                .enterCommentToTheOrder("Коментар до замовлення");
        //.clickOnButtonPlaceOrder()


        logger.info("TC: Shopping Cart appearance passed");
    }
}
