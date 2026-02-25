package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    public Logger logger = Logger.getLogger(getClass());
    private WebDriver driver;
    private WebDriverWait wait;


    @FindBy(xpath = "*//h1[@id=\"UserForm\"]")
    private WebElement cartPageTitle;

    @FindBy(xpath = "*//a[text()=\"Ром Дед Менс Фінгерс\"]")
    private WebElement productInCart;

    @FindBy(xpath = "*//input[@value=\"1\"]")
    private WebElement productQuantityInCart;

    @FindBy(xpath = "*//input[@data-testid=\"firstNameId\"]")
    private WebElement nameInput;

    @FindBy(xpath = "*//input[@data-testid=\"lastNameId\"]")
    private WebElement surnameInput;

    @FindBy(xpath = "*//input[@data-testid=\"phoneId\"]")
    private WebElement phoneInput;

    @FindBy(xpath = "*//input[@data-testid=\"emailId\"]")
    private WebElement emailInput;

    @FindBy(xpath = "*//span[text()=\"Самовивіз\"]")
    private WebElement deliveryMethodDropdown;

    @FindBy(xpath = "*//span[text()=\"Оплата готівкою\"]")
    private WebElement paymentMethodDropdown;

    @FindBy(xpath = "*//textarea[@id=\"commentId0\"]")
    private WebElement commentInput;

    @FindBy(xpath = "*//input[@placeholder=\"Почніть вводити адресу\"]")
    private WebElement deliveryAddressSearchInput;

    @FindBy(xpath = "*//span[text()=\"Забрати тут\"]")
    private WebElement deliveryAddressOption;

    @FindBy(xpath = "//div[contains(text(),'Оплата готівкою')]")
    private WebElement cashPaymentOption;


    protected void safeClick(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        wait.until(ExpectedConditions.elementToBeClickable(locator));

        try {
            element.click(); // нормальний клік
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element); // fallback
        }
    }


    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);

        // Очікуємо реальний заголовок
        wait.until(ExpectedConditions.visibilityOf(cartPageTitle));
        logger.info("Cart page is loaded");
    }

    public CartPage checkIsRedirectedToCartPage() {
        wait.until(driver -> cartPageTitle.isDisplayed());
        if (cartPageTitle.isDisplayed()) {
            logger.info("Successfully redirected to the cart page.");
        } else {
            logger.error("Failed to redirect to the cart page.");
        }
        return this;
    }

    public CartPage checkProductIsInCart(String productName) {

        By productTitleLocator = By.xpath("//a[contains(text(),'" + productName + "')]");

        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productTitleLocator)
        );

        if (product.isDisplayed()) {
            logger.info("Product is present in cart: " + productName);
        } else {
            logger.error("Product NOT found in cart: " + productName);
        }

        return this;
    }

    public CartPage checkProductQuantityInCart(String count) {
        wait.until(driver -> productQuantityInCart.isDisplayed());
        if (productQuantityInCart.getAttribute("value").equals(count)) {
            logger.info("Product quantity in cart is correct: " + count);
        } else {
            logger.error("Expected product quantity: " + count + ", but found: " + productQuantityInCart.getAttribute("value"));
        }
        return this;

    }


    public ProductsPage checkIsProductAddedToCartMessageDisplayed() {
        wait.until(driver -> productInCart.isDisplayed());
        if (productInCart.isDisplayed()) {
            logger.info("Product added to cart message is displayed.");
        } else {
            logger.error("Product added to cart message is not displayed.");
        }
        return new ProductsPage(driver);
    }

    public CartPage fillNameInput(String name) {

        wait.until(ExpectedConditions.elementToBeClickable(nameInput));

        nameInput.click();
        nameInput.clear();
        nameInput.sendKeys(name);

        logger.info("Entered name: " + name);

        return this;
    }

    public CartPage fillSurnameInput(String surname) {

        wait.until(ExpectedConditions.elementToBeClickable(surnameInput));

        surnameInput.click();
        surnameInput.clear();
        surnameInput.sendKeys(surname);

        logger.info("Entered surname: " + surname);

        return this;
    }


    public CartPage fillPhoneInput(String number) {

        wait.until(ExpectedConditions.elementToBeClickable(phoneInput));

        phoneInput.click();
        phoneInput.clear();
        phoneInput.sendKeys(number);

        logger.info("Entered phone: " + number);

        return this;
    }

    public CartPage fillEmailInput(String mail) {

        wait.until(ExpectedConditions.elementToBeClickable(emailInput));

        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(mail);

        logger.info("Entered email: " + mail);

        return this;
    }

    public CartPage chooseInDropdownDeliveryMethod(String deliveryMethod) {

        By optionLocator = By.xpath(
                "//span[text()='" + deliveryMethod + "']/ancestor::*[self::label or self::div][1]"
        );

        safeClick(optionLocator);

        WebElement option = driver.findElement(optionLocator);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

        logger.info("Selected delivery method: " + deliveryMethod);

        // Чекаємо повного зникнення dropdown overlay
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@class,'dropdown') and contains(@class,'open')]")
        ));

        // Чекаємо появу блоку магазинів
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Почніть вводити адресу']")
        ));

        return this;
    }

    public CartPage chooseInDropdownPaymentMethod(String paymentMethod) {

        logger.info("Selecting payment method: Готівка");

        // Чекаємо, поки елемент стане видимим
        wait.until(ExpectedConditions.visibilityOf(cashPaymentOption));

        // Скролимо центром вікна
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", cashPaymentOption);

        // Клікаємо через JS
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", cashPaymentOption);

        logger.info("Selected payment method: Готівка");
        return this;
    }

    public CartPage enterDeliveryAddress(String address) {

        By addressInputLocator = By.xpath("//input[contains(@placeholder,'Введіть адресу')]");

        WebElement addressInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(addressInputLocator)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", addressInput);

        wait.until(ExpectedConditions.elementToBeClickable(addressInput));

        addressInput.click();
        addressInput.clear();
        addressInput.sendKeys(address);

        logger.info("Entered delivery address: " + address);

        return this;
    }

    public CartPage chooseDeliveryAddressFromDropdown(String address) {

        By dropdownOption = By.xpath("//div[contains(text(),'" + address + "')]");

        WebElement option = wait.until(
                ExpectedConditions.visibilityOfElementLocated(dropdownOption)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        wait.until(ExpectedConditions.elementToBeClickable(option));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", option);

        logger.info("Selected delivery address from dropdown: " + address);

        return this;
    }

    public CartPage filterPickupStores(String address) {

        wait.until(ExpectedConditions.visibilityOf(deliveryAddressSearchInput));
        wait.until(ExpectedConditions.elementToBeClickable(deliveryAddressSearchInput));

        deliveryAddressSearchInput.click();
        deliveryAddressSearchInput.clear();
        deliveryAddressSearchInput.sendKeys(address);

        logger.info("Filtered pickup stores by address: " + address);

        return this;
    }

    public CartPage choosePickupStore(String address) {
        logger.info("Selecting pickup store: " + address);

        // Чекаємо, поки кнопка "Забрати тут" стане видимою
        wait.until(ExpectedConditions.visibilityOf(deliveryAddressOption));

        // Скролимо до кнопки центром вікна
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", deliveryAddressOption);

        // Клікаємо через JS (обхід можливих intercept)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", deliveryAddressOption);

        logger.info("Selected pickup store: " + address);
        return this;
    }


    public void enterCommentToTheOrder(String коментарДоЗамовлення) {
        wait.until(ExpectedConditions.elementToBeClickable(commentInput));

        commentInput.click();
        commentInput.clear();
        commentInput.sendKeys(коментарДоЗамовлення);

        logger.info("Entered comment to the order: " + коментарДоЗамовлення);
    }
}
