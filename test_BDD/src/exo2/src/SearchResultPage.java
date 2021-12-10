package exo2.src;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


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
