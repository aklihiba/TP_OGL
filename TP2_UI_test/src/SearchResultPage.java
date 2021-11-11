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




public class SearchResultPage extends PageObject{
    @FindBy(className = "s-item__title") private List<WebElement> books;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
    public void FindBook(String bookname){
        for(WebElement element:books) {
            if (element.getText().contains(bookname)){
                element.click();
                break;
            }
        }
    }
}
