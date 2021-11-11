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

public class BookPage extends PageObject{
    @FindBy(id="qtyTextBox")private WebElement qte;
    @FindBy(xpath = "//*[@id=\"isCartBtn_btn\"]") private WebElement add_cart_button;

    public BookPage(WebDriver driver) {
        super(driver);
    }

    public void check_qte(String value){
        assertEquals(this.qte.getAttribute("value"),"1");
    }
    public void AddToCart(){
        add_cart_button.click();
    }

}
