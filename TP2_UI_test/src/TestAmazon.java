
import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;


public class TestAmazon {

	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
        //For Firefox 
        //System.setProperty("webdriver.gecko.driver","/opt/webdriver/geckodriver");
		// For Chrome
        //System.setProperty("webdriver.chrome.driver","c://chromedriver.exe");
		//driver = new ChromeDriver();
		System.setProperty("webdriver.Opera.driver","c://operadriver.exe");
		driver = new OperaDriver();
	}

	

	@Test
	public void test() {

	    driver.get("https://www.amazon.com");
	    WebElement inputSearch = driver.findElement(By.id("twotabsearchtextbox"));
	    inputSearch.sendKeys("google pixel 3"); // popur introduire des donnees
	    inputSearch.submit();
	   // List<WebElement> elements =  (new WebDriverWait(driver, 10)).
	    // until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".a-link-normal.a-text-normal")));
		// implicite wait : wait till the timme is over
		//explicite wait tillthe element is found and it resumes excution
		List<WebElement> elements =  driver.findElements(By.cssSelector(".a-link-normal.a-text-normal"));
		for(WebElement element:elements) {
			assertTrue(element.isDisplayed());
		}

	}


	
	@AfterClass
	public static void setUpAfterClass() throws Exception {
		driver.quit();
	   
		
	}


}
