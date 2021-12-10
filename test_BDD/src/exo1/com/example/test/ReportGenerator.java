package exo1.com.example.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features",plugin={"pretty","html:reports.html"})
public class ReportGenerator {
}
