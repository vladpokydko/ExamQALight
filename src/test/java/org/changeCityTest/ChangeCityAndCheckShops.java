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
                .clickNoButton()                  // натиснути "Ні"
                .enterCityName("Львів")           // ввести "Львів"
                .selectCity("Львів")              // обрати зі списку
                .clickContacts()                  // натиснути "Контакти"
                .verifySelectedCityAndDistricts();    // перевірити, що написано "Львів"

        logger.info("TC: Change city passed");
    }
}
