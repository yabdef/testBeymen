package test1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Thread.*;

public class test1 {
    public static void main(String[] args) throws InterruptedException {
        String link = "https://www.beymen.com/";
        String word = "pantolon";

        /*OPEN URL*/
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(link);
        driver.manage().window().maximize();
        System.out.println("Anasayfa açıldı");

        /*CHECK BUTTONS*/
        WebElement hesabim = driver.findElement(new By.ByCssSelector("a[title='Hesabım']"));
        if (hesabim!=null){
            System.out.println("Hesabım alanı aktif");
        }
        else{
            System.out.println("Hesabım alanı bulunamadı");
        }
        WebElement favorilerim = driver.findElement(new By.ByCssSelector("a[title='Favorilerim']"));
        if (favorilerim!=null){
            System.out.println("Favorilerim alanı aktif");
        }
        else{
            System.out.println("Favorilerim alanı bulunamadı");
        }
        WebElement sepetim = driver.findElement(new By.ByCssSelector("a[title='Sepetim']"));
        if (sepetim!=null){
            System.out.println("Sepetim alanı aktif");
        }
        else{
            System.out.println("Sepetim alanı bulunamadı");
        }

        /*SEARCH*/
        WebElement ara = driver.findElement(new By.ByCssSelector("input[class='default-input o-header__search--input']"));
        ara.click();
        ara.sendKeys(word);
        ara.sendKeys(Keys.RETURN);

        /*SCROLLDOWN*/
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://demo.guru99.com/test/guru99home/");
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        /*SHOW MORE*/
        WebElement dahafazlagoster = driver.findElement(By.id("moreContentButton"));
        dahafazlagoster.click();

        /*RANDOM PRODUCT SELECTION*/
        List<WebElement> selectProduct = driver.findElements(new By.ByCssSelector("div[class='o-productList__item']"));
        try{
            int sayi = (int) Math.round(Math.random()*selectProduct.size());
            selectProduct.get(sayi).click();
        }
        catch (NoSuchElementException e){
            int sayi2 = (int) Math.round(Math.random()*10);
            selectProduct.get(sayi2).click();
        }

        /*FINDING PRODUCT PAGE PRICE*/
        WebElement fiyatbul = driver.findElement(By.id("priceNew"));
        String ilkFiyat = fiyatbul.getAttribute("innerHTML");

        /*SELECTING PRODUCT SIZE*/
        try{
            WebElement selectSize = driver.findElement(new By.ByCssSelector("span[class='m-variation__item']"));
            selectSize.click();
        }
        catch (NoSuchElementException e){
            WebElement selectSize = driver.findElement(new By.ByCssSelector("span[class='m-variation__item -criticalStock']"));
            selectSize.click();
        }

        /*ADD TO CART*/
        WebElement sepeteEkle = driver.findElement(By.id("addBasket"));
        sepeteEkle.click();

        /*GO TO PAY*/
        WebElement sepetim2 = driver.findElement(new By.ByCssSelector("a[title='Sepetim']"));
        sepetim2.click();

        /*FINDING PRODUCT CART PRICE*/
        WebElement fiyatbul2 = driver.findElement(new By.ByCssSelector("span[class='m-productPrice__salePrice']"));
        String sonFiyat = fiyatbul2.getAttribute("innerHTML");

        /*COMPARE PRICES*/
        System.out.println("Ürün sayfasındaki fiyat: " +ilkFiyat+ " \nSepetteki fiyat: "+sonFiyat);

        /*SELECT QUANTITY*/
        WebElement adetSec = driver.findElement(By.id("quantitySelect0"));
        adetSec.click();
        new Select(adetSec).selectByIndex(1);
        adetSec.click();

        /*CLEAR CART*/
        WebElement urunSil = driver.findElement(By.id("removeCartItemBtn0"));
        urunSil.click();
    }
}
