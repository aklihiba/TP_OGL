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

public class ShopPAge extends PageObject{

        @FindBy(id="gh-cat") private WebElement categorie;
        @FindBy(id="gh-ac") private  WebElement inputsearch;
        @FindBy(id="gh-btn") private WebElement button;


    public ShopPAge(WebDriver driver) {
        super(driver);
    }


    public void enterValues (String selct, String input){
        Select s =new Select( categorie);
        this.inputsearch.sendKeys(input);
        s.selectByValue(selct);
    }
    public void submitForm(){
        button.click();
    }


}
