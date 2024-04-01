package com.bitc.java404;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NaverLogin {
    private final static String CHROME_DRIVER_ID = "webdriver.chrome.driver";
    private final static String CHROME_DRIVER_PATH = "C:\\chromedriver.exe";

    private WebDriver driver;
    private String url = "https://www.naver.com";

    public void login() throws InterruptedException {
        System.setProperty(CHROME_DRIVER_ID, CHROME_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get(url);
        Thread.sleep(5000);

        WebElement mainPageLoginButton = driver.findElement(By.className
               ("MyView-module__link_login___HpHMW"));
        mainPageLoginButton.click();

        Thread.sleep(1000);

        WebElement loginId = driver.findElement(By.id("id"));
        WebElement loginPw = driver.findElement(By.name("pw"));
        WebElement loginbtn = driver.findElement(By.xpath("//*[@id=\"log.login\"]"));

        loginId.sendKeys("testid");
        Thread.sleep(2000);

        loginPw.sendKeys("testpw");
        Thread.sleep(2000);

        loginbtn.submit();
        Thread.sleep(5000);

        driver.quit();
    }
}
