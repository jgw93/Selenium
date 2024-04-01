package com.bitc.java404;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NateLogin extends WebBase {
    private String nateurl ="https://www.nate.com/";
    private WebDriver driver;
    private ChromeOptions options;
    public NateLogin(){
        System.setProperty(WebBase.CHROME_DRIVER_ID, WebBase.CHROME_DRIVER_PATH);
        options = new ChromeOptions();
        driver = new ChromeDriver(options);
    }

    public ChromeOptions OPtionSetup(){
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        opt.addArguments("--disabled-popup-blocking");
        opt.addArguments("--remote-allow-origins=*");
        return opt;
    }

    public void login() throws InterruptedException {
      driver.get(nateurl);
      Thread.sleep(3000);

      WebElement loginId = driver.findElement(By.id("ID"));
      loginId.sendKeys("abcd1234");
      Thread.sleep(2000);

      WebElement loginPw = driver.findElement(By.id("PASSDM"));
      loginPw.sendKeys("abcd");
      Thread.sleep(2000);

      WebElement loginBtn = driver.findElement(By.id("btnLOGIN"));
      loginBtn.click();
      Thread.sleep(5000);

        driver.quit();
    }
}
