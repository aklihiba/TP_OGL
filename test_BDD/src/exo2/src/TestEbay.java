package exo2.src;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TestEbay {


    public static WebDriver driver;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        //For Firefox
        //System.setProperty("webdriver.gecko.driver","/opt/webdriver/geckodriver");
        // For Chrome
        // System.setProperty("webdriver.Edge.driver","c://msedgedriver.exe");
        //driver = new EdgeDriver();
        // for opera
        System.setProperty("webdriver.Opera.driver", "c://operadriver.exe");
        driver = new OperaDriver();
        driver.get("https://www.ebay.com");
    }

    @Test
    public void test() {

        driver.get("https://www.ebay.com");
        Select languageselector = new Select( driver.findElement(By.id("gh-cat")));
        languageselector.selectByValue("267");

        WebElement inputSearch = driver.findElement(By.id("gh-ac"));
        inputSearch.sendKeys("python in easy steps");
        WebElement button =  driver.findElement(By.id("gh-btn"));
        button.click();

        List<WebElement> elements =  driver.findElements(By.cssSelector("h3.s-item__title"));
        for(WebElement element:elements) {
            if (element.getText().contains("Python in easy steps by Mike McGrath Book The Fast Free Shipping")){
                element.click();
                break;
            }
        }

        //La méthode driver.getWindowHandle() retourne le nom du Parent Window.
        //La méthode driver.getWindowHandles() retourne les noms de toutes les fenêtres(Parent et Childs). Le type retourné est Set<String>.
        //Pour faire un switch entre Parent Window et une Child Window, il faut parcourir le Set<String> et appeler la méthode driver.switchTo().window(name_of_child_window).

        Set<String> windows = new HashSet<>();
        windows.addAll(driver.getWindowHandles());
        System.out.println(windows.toString());
        for(String win : windows)  {
           if (! driver.getWindowHandle().equals(win)){
               driver.switchTo().window(win);
           }
        }
        WebElement qte = driver.findElement(By.id("qtyTextBox"));

        assertEquals(qte.getAttribute("value"),"1");

        WebElement add_cart =  driver.findElement(By.xpath("//*[@id=\"isCartBtn_btn\"]"));
        add_cart.click();

        assertEquals(driver.getCurrentUrl(),"https://cart.payments.ebay.com/");

        WebElement prix = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div/div/div[2]/div/div/div/div/div[1]/div/div[3]/div/div[1]/div[2]/div/div[1]/span/span/span"));
        assertEquals(prix.getText(),"US $12.14");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(presenceOfElementLocated(By.id("gh-cart-n")));

        WebElement icon_number = driver.findElement(By.cssSelector("#gh-cart-n"));


    }

    @Test
    public void POMtest(){

        ShopPAge p1 = new ShopPAge(driver);
        p1.enterValues("267","python in easy steps");
        p1.submitForm();

        SearchResultPage p2 = new SearchResultPage(driver);
        p2.FindBook("Python in easy steps by Mike McGrath Book The Fast Free Shipping");

        Set<String> windows = new HashSet<>();
        windows.addAll(driver.getWindowHandles());
        System.out.println(windows.toString());
        for(String win : windows)  {
            if (! driver.getWindowHandle().equals(win)){
                driver.switchTo().window(win);
            }
        }
        BookPage p3 = new BookPage(driver);
        p3.check_qte("1");
        p3.AddToCart();

        assertEquals(driver.getCurrentUrl(),"https://cart.payments.ebay.com/");

        PaymentPage p4 = new PaymentPage(driver);
        p4.checkprice("US $12.14");
        p4.waitICon();
        p4.checkIconNumber("1");
    }

}

