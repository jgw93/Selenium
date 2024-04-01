package com.bitc.java404;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

// 문제 1) CoffeeBean 사이트 크롤링을 참고하려 스타벅스 사이트의 매장 정보를 가져오는 프로그램을 작성하세요.
// # 매장명, 매장주소, 매장 전화번호를 출력
// # 매장 번호 1 ~ 10 까지만 사용
public class StarBucks extends WebBase {
    public StarBucks() {
        System.setProperty(WebBase.CHROME_DRIVER_ID, WebBase.CHROME_DRIVER_PATH);
        options = chromeOptionsSetup();
        driver = new ChromeDriver(options);
    }

    public ChromeOptions chromeOptionsSetup() {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--start-maximized");
        opt.addArguments("--disabled-popup-blocking");
        opt.addArguments("--remote-allow-origins=*");
        return opt;
    }

    public void getStoreInfo() throws InterruptedException {
        // 스타벅스 매장 찾기 주소 설정
        requestUrl = "https://www.starbucks.co.kr/store/store_map.do";
        JavascriptExecutor js = (JavascriptExecutor) driver;

        for (int i = 1; i < 10; i++) {
            driver.get(requestUrl);
            Thread.sleep(1000);

            try {
                // 스타벅스의 매장 상세 정보 보기 자바스크립트 함수가 getStoreDetail() 임
                String script = String.format("getStoreDetail(%d);", i);
                js.executeScript(script);
                Thread.sleep(500);

                String html = driver.getPageSource();
                Document doc = Jsoup.parse(html);
                Element shopArea_pop01_inner = doc.select("section.shopArea_pop01_inner").first();
                Element titl = shopArea_pop01_inner.select("header.titl").first().select("h6").first();

                Elements storeInfoList = shopArea_pop01_inner.select("div.shopArea_infoWrap").first().select("dd");
                Element addr = storeInfoList.get(0);
                Element tel = storeInfoList.get(1);

                String storeName = titl.text();
                String storeAddr = addr.text();
                String storeTel = tel.text();

                System.out.println("점포(" + i + ")명 : " + storeName);
                System.out.println("점포 위치 : " + storeAddr);
                System.out.println("전화번호 : " + storeTel);
                System.out.println("\n-----------------------\n");
            } catch (Exception e) {
            }
        }
        driver.quit();
    }


}

