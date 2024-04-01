package com.bitc.java404;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebBase {
    public final static String CHROME_DRIVER_ID = "webdriver.chrome.driver";
    public final static String CHROME_DRIVER_PATH = "C:\\chromedriver.exe";
    public String requestUrl = "http://www.google.co.kr";
    public WebDriver driver;
    public ChromeOptions options;
}
