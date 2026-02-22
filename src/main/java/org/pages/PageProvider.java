package org.pages;

import org.openqa.selenium.WebDriver;

public class PageProvider {
    private WebDriver webDriver;

    public PageProvider(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public AgeRestrictionPage openAgeRestrictionPage() {
        return new AgeRestrictionPage(webDriver);
    }

    public CatalogPage getCatalogPage() {
        return new CatalogPage(webDriver);
    }

    public ProductsPage getProductsPage() {
        return new ProductsPage(webDriver);
    }

    public HomePage getHomePage() {
        return new HomePage(webDriver);
    }
}
