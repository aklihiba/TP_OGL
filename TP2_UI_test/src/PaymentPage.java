import static org.junit.Assert.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends PageObject {

    @FindBy(css = "#mainContent > div > div.left-section > div.app-cart > div > div > div:nth-child(2) > div > div > div > div > div.listsummary-content > div > div.grid__group.grid__cell--7of16-lg.grid__cell--5of16.t-align-r.rightmost-cell > div > div:nth-child(1) > div.grid__cell--one-half.rightmost-cell > div > div.item-price > span > span > span")  private WebElement prix;
    @FindBy (id = "gh-cart-n") private WebElement icon;
    public PaymentPage(WebDriver driver) {
        super(driver);
    }
    public void checkprice(String price){
        assertEquals(this.prix.getText(),price);
    }
    public void waitICon(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(By.id("gh-cart-n")));
    }
   public void checkIconNumber(String number){
       assertEquals(icon.getText(),number);
   }
}
