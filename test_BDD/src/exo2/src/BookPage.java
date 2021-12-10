package exo2.src;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertEquals;

public class BookPage extends PageObject{
    @FindBy(id="qtyTextBox")private WebElement qte;
    @FindBy(xpath = "//*[@id=\"isCartBtn_btn\"]") private WebElement add_cart_button;

    public BookPage(WebDriver driver) {
        super(driver);
    }

    public void check_qte(String value){
        assertEquals(this.qte.getAttribute("value"), "1");
    }
    public void AddToCart(){
        add_cart_button.click();
    }

}
