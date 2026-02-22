package org.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private WebElement deliveryAddressInput;

    @FindBy(xpath = "*//span[text()=\"Забрати тут\"]")
    private WebElement deliveryAddressOption;

    



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

        By optionLocator = By.xpath("//span[text()='" + deliveryMethod + "']");

        WebElement option = wait.until(
                ExpectedConditions.visibilityOfElementLocated(optionLocator)
        );

        // Скролимо до елемента
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        // Чекаємо щоб став клікабельним
        wait.until(ExpectedConditions.elementToBeClickable(option));

        // Клік через JS (обхід перехоплення)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", option);

        logger.info("Selected delivery method: " + deliveryMethod);

        return this;
    }

    public CartPage chooseInDropdownPaymentMethod(String paymentMethod) {

        By optionLocator = By.xpath("//span[text()='" + paymentMethod + "']");

        WebElement option = wait.until(
                ExpectedConditions.visibilityOfElementLocated(optionLocator)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", option);

        wait.until(ExpectedConditions.elementToBeClickable(option));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", option);

        logger.info("Selected payment method: " + paymentMethod);

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
}
