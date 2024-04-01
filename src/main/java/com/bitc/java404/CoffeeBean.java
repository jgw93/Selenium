package com.bitc.java404;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CoffeeBean extends WebBase {

    public CoffeeBean() {
        System.setProperty(WebBase.CHROME_DRIVER_ID, WebBase.CHROME_DRIVER_PATH);
        // 크롬 실행 옵션 설정
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
        // 커피빈 사이트의 매장 정보 페이지 설정
        requestUrl = "https://www.coffeebeankorea.com/store/store.asp";

        //    셀레니움에서 제공하는 클래스로 WebDriver 에서 자바스크립트를 직접 실행함
        //    executeScript(자바스크립트) : 지정한 자바스크립트 실행
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // 커피빈 매장을 1 ~ 20번까지 출력
        for (int i = 1; i <= 20; i++) {
            // 매장 찾기 페이지를 반복적으로 다시 염
            driver.get(requestUrl);
            // 화면 출력을 위해서 잠시 기다림
            Thread.sleep(1000);

            try {
                // 자바 문자열 포맷을 사용하여 자바스크립트 함수에 데이터를 변경하여 입력
                String script = String.format("storePop2(%d);", i);
                // 자바 스크립트 실행
                js.executeScript(script);
                Thread.sleep(500);

                // 현재 자바스크립트가 실행된 후 현재 페이지에 대한 html 태그를 가져옴
                String html = driver.getPageSource();

                // 가져온 자바스크립트 실행 후 전체 페이지에 대한 html 태그를 Jsoup 로 파싱
                Document doc = Jsoup.parse(html);
                // 점포 정보가 출력된 html 태그의 위치 검색
                Element popup_inner = doc.select("div.popup_inner").first();
                Element store_txt = popup_inner.select("div.store_txt").first();
                // 점포 이름이 입력되어 있는 html 태그 가져오기
                Element h2 = store_txt.select("h2").first();

                // 점포 정보가 입력되어 있는 table 태그 중 td 태그의 정보만 가져옴
                // 영업 시간, 주소, 전화번호
                Elements storeInfoList = store_txt.select("td");
                Element storeInfoTime = storeInfoList.get(0);
                Element storeInfoAddr = storeInfoList.get(2);
                Element storeInfoTel = storeInfoList.get(3);

                String storeName = h2.text();
                String storeTime = storeInfoTime.text();
                String storeAddr = storeInfoAddr.text();
                String storeTel = storeInfoTel.text();

                System.out.println("점포(" + i + ")명 : " + storeName);
                System.out.println("영업 시간 : " + storeTime);
                System.out.println("점포 위치 : " + storeAddr);
                System.out.println("전화번호 : " + storeTel);
                System.out.println("\n-----------------------\n");
            } catch (Exception e) {
            }

        }
        driver.quit();

    }
}

