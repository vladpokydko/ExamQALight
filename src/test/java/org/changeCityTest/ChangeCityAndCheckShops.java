package org.changeCityTest;

import org.baseTest.BaseTest;
import org.junit.Test;

public class ChangeCityAndCheckShops extends BaseTest {
    @Test
    public void checkCityChangeAndCheckShops() {

        logger.info("TC: Change city started");

        pageProvider
                .openAgeRestrictionPage()
                .clickYes()
                .checkIsRedirectedToHomePage();

        pageProvider
                .getHomePage()
                .clickNoButton()
                .enterCityName("Львів")
                .selectCity("Львів")
                .clickContacts()
                .verifySelectedCityAndDistricts();

        logger.info("TC: Change city passed");
    }
}
