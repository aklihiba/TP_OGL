package exo2.src;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class TestExo2 {
    public static WebDriver driver;
    private ShopPAge p1;
    private SearchResultPage p2;
    private BookPage p3;
    private PaymentPage p4;

    @Given("I open opera")
    public void iOpenOpera() {
        System.setProperty("webdriver.Opera.driver", "c://operadriver.exe");
        driver = new OperaDriver();

    }

    @When("i visit {string}")
    public void iVisitHttpsWwwEbayCom(String s) {
        driver.get(s);
    }

    @And("I select the category Books and search for {string}")
    public void iSelectTheCategoryBooks(String s) {
        p1 = new ShopPAge(driver);
        p1.enterValues("267",s);

    }


    @And("I submit")
    public void iSubmit() {
        p1.submitForm();
    }

    @And("I select the Book {string}")
    public void iSelectTheBookInTheResult(String args0) {
        p2 = new SearchResultPage(driver);
        p2.FindBook(args0);
        //switching to its page
        Set<String> windows = new HashSet<>();
        windows.addAll(driver.getWindowHandles());
        System.out.println(windows.toString());
        for(String win : windows)  {
            if (! driver.getWindowHandle().equals(win)){
                driver.switchTo().window(win);
            }
        }
    }

    @And("i set the quantity to {int}")
    public void iSetTheQuantity(int i) {
        p3 = new BookPage(driver);
        p3.check_qte(String.valueOf(i));
    }

    @And("i add it")
    public void iAddIt() {
        p3.AddToCart();
    }

    @Then("i get the page{string}")
    public void iGetThePage(String arg0) {
        assertEquals(driver.getCurrentUrl(),arg0);

    }

    @And("I get the price {string}")
    public void iGetThePrice(String arg0) {
        PaymentPage p4 = new PaymentPage(driver);
        p4.checkprice("US $12.14");
    }

    @And("I have {int} item in my basket")
    public void iHaveOneItemInMyBasket(int i) {
        p4.waitICon();
        p4.checkIconNumber(String.valueOf(i));
    }
}
