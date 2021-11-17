import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.*;


public class Test {
    int result;
    @Given("I have a calculator")
    public void iHaveACalculator() {
    }

    @When("I add {int} and {int}")
    public void iAddNumAndNum(int arg0, int arg1) {
        result= arg0 + arg1 ;
    }

    @Then("I should have {int}")
    public void iShouldHaveResults( int arg0) {
        assertEquals(result, arg0);
    }
}