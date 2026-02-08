package org.baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.pages.PageProvider;

import java.time.Duration;

public class BaseTest {
    private WebDriver webDriver;
    protected Logger logger = Logger.getLogger(getClass());
    protected PageProvider pageProvider;

    @Before
    public void setup(){
        //оновлюємо версію браузера
        WebDriverManager.chromedriver().setup();
        //відкриваємо браузер
        webDriver = new ChromeDriver();
        //робимо його на весь екран (не фулскрін)
        webDriver.manage().window().maximize();
        //неявне очікування на дію
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Browser was opened");

        pageProvider = new PageProvider(webDriver);
    }

    @After
    public void tearDown(){
        webDriver.quit();
        logger.info("Browser was closed");
    }
}
