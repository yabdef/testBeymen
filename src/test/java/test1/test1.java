package test1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class test1 {
    public static void main(String[] args) {
        /*CREATING WEBDRIVER INSTANCE*/
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String link = "https://www.beymen.com/";
        String word = "pantolon";

        test(driver,link,word);
    }
    public static void test(WebDriver driver,String link,String word) {
        /*OPEN URL*/
        openURL(driver,link);
        /*CHECK BUTTONS*/
        checkButtons(driver);
        /*SEARCH*/
        searchProduct(driver,word);
        /*SCROLLDOWN*/
        scrollDown(driver);
        /*SHOW MORE*/
        showMore(driver);
        /*RANDOM PRODUCT SELECTION*/
        selectRandomProduct(driver);
        /*FINDING PRODUCT PAGE PRICE*/
        String ilkFiyat= findProductPagePrice(driver);
        /*SELECTING PRODUCT SIZE*/
        selectProductSize(driver);
        /*ADD TO CART*/
        addToCart(driver);
        /*GO TO PAY*/
        goToPay(driver);
        /*FINDING PRODUCT CART PRICE*/
        String sonFiyat= findProductCartPrice(driver);
        /*COMPARE PRICES*/
        comparePrices(ilkFiyat,sonFiyat);
        /*SELECT QUANTITY*/
        selectQuantity(driver);
        /*CLEAR CART*/
        clearCart(driver);
    }

    private static void openURL(WebDriver driver,String link){
        driver.get(link);

        try{
            driver.manage().window().maximize();
            System.out.println("Anasayfa açıldı");
        }
        catch(Exception e){
            System.out.println("Anasayfa açılamadı");
        }

    }

    private static void checkButtons(WebDriver driver){
        try{
            WebElement hesabim = driver.findElement(new By.ByCssSelector("a[title='Hesabım']"));
            System.out.println("Hesabım alanı aktif");
        }
        catch(Exception e){
            System.out.println("Hesabım alanı bulunamadı");
        }
        try{
            WebElement favorilerim = driver.findElement(new By.ByCssSelector("a[title='Favorilerim']"));
            System.out.println("Favorilerim alanı aktif");
        }
        catch(Exception e){
            System.out.println("Favorilerim alanı bulunamadı");
        }
        try{
            WebElement sepetim = driver.findElement(new By.ByCssSelector("a[title='Sepetim']"));
            System.out.println("Sepetim alanı aktif");
        }
        catch(Exception e){
            System.out.println("Sepetim alanı bulunamadı");
        }

    }

    private static void searchProduct(WebDriver driver,String word) {
        try{
            WebElement ara = driver.findElement(new By.ByCssSelector("input[class='default-input o-header__search--input']"));
            ara.click();
            ara.sendKeys(word);
            ara.sendKeys(Keys.RETURN);
            System.out.println(word+" adlı ürün başarıyla arandı");
        }
        catch(Exception e){
            System.out.println("Ürün araması gerçekleştirilemedi.");
        }
    }
    private static void scrollDown(WebDriver driver) {
        try{
            JavascriptExecutor js = (JavascriptExecutor) driver;
            driver.get("http://demo.guru99.com/test/guru99home/");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            System.out.println("Scrolldown işlemi başarıyla gerçekleştirildi");
        }
        catch(Exception e){
            System.out.println("Scrolldown işlemi gerçekleştirilemedi.");
        }

    }
    private static void showMore(WebDriver driver) {
        try{
            WebElement dahafazlagoster = driver.findElement(By.id("moreContentButton"));
            dahafazlagoster.click();
            System.out.println("Daha fazla göster butonuna tıklandı.");
        }
        catch(Exception e){
            System.out.println("Daha fazla göster butonuna tıklanamadı.");
        }

    }
    private static void selectRandomProduct(WebDriver driver) {
        try{
            List<WebElement> selectProduct = driver.findElements(new By.ByCssSelector("div[class='o-productList__item']"));
            try{
                int sayi = (int) Math.round(Math.random()*selectProduct.size());
                selectProduct.get(sayi).click();
            }
            catch (NoSuchElementException e){
                int sayi2 = (int) Math.round(Math.random()*10);
                selectProduct.get(sayi2).click();
            }
            System.out.println("Rastgele ürün seçildi");
        }
        catch(Exception e){
            System.out.println("Rastgele ürün seçilemedi");
        }
    }
    private static void selectProductSize(WebDriver driver) {
        try{
            try{
                WebElement selectSize = driver.findElement(new By.ByCssSelector("span[class='m-variation__item']"));
                selectSize.click();
            }
            catch (NoSuchElementException e){
                WebElement selectSize = driver.findElement(new By.ByCssSelector("span[class='m-variation__item -criticalStock']"));
                selectSize.click();
            }
            System.out.println("Ürün bedeni seçildi");
        }
        catch(Exception e){
            System.out.println("Ürün bedeni seçilemedi");
        }
    }

    private static void addToCart(WebDriver driver) {
        try{
            WebElement sepeteEkle = driver.findElement(By.id("addBasket"));
            sepeteEkle.click();
            System.out.println("Ürün sepete eklendi");
        }
        catch(Exception e){
            System.out.println("Ürün sepete eklenemedi");
        }
    }
    private static void goToPay(WebDriver driver) {
        try{
            WebElement sepetim2 = driver.findElement(new By.ByCssSelector("a[title='Sepetim']"));
            sepetim2.click();
            System.out.println("Sepete git butonuna tıklandı");
        }
        catch(Exception e){
            System.out.println("Sepete git butonuna tıklanamadı");
        }
    }
    private static String findProductPagePrice(WebDriver driver) {
        try{
            WebElement fiyatbul = driver.findElement(By.id("priceNew"));
            String ilkFiyat = fiyatbul.getAttribute("innerHTML");
            System.out.println("Ürün sayfasındaki fiyat bilgisi bulundu");
            return ilkFiyat;
        }
        catch(Exception e){
            System.out.println("Ürün sayfasındaki fiyat bilgisi bulunamadı");
            return null;
        }

    }
    private static String findProductCartPrice(WebDriver driver) {
        try{
            WebElement fiyatbul2 = driver.findElement(new By.ByCssSelector("span[class='m-productPrice__salePrice']"));
            String sonFiyat = fiyatbul2.getAttribute("innerHTML");
            System.out.println("Sepetteki fiyat bilgisi bulundu");
            return sonFiyat;
        }
        catch(Exception e){
            System.out.println("Sepetteki fiyat bilgisi bulunamadı");
            return null;
        }
    }
    private static void comparePrices(String ilkFiyat,String sonFiyat) {
        try{
            System.out.println("Ürün sayfasındaki fiyat: " +ilkFiyat+ " \nSepetteki fiyat: "+sonFiyat);
            if (ilkFiyat.equals(sonFiyat)){
                System.out.println("Ürün fiyatları aynı");
            }
            else{
                System.out.println("Ürün fiyatlarında farklılık var");
            }
        }
        catch(Exception e){
            System.out.println("Fiyat bilgisi bulunamadı");
        }
    }
    private static void selectQuantity(WebDriver driver) {
        try{
            WebElement adetSec = driver.findElement(By.id("quantitySelect0"));
            adetSec.click();
            new Select(adetSec).selectByIndex(1);
            adetSec.click();
            System.out.println("Ürün adeti güncellendi");
        }
        catch(Exception e){
            System.out.println("Ürün adeti güncellenemedi");
        }
    }
    private static void clearCart(WebDriver driver) {
        try{
            WebElement urunSil = driver.findElement(By.id("removeCartItemBtn0"));
            urunSil.click();
            System.out.println("Ürün sepetten silindi");
        }
        catch(Exception e){
            System.out.println("Ürün sepetten silinemedi");
        }
    }


}
