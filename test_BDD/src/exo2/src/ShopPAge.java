package exo2.src;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
