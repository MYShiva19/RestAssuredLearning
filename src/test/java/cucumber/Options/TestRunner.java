package cucumber.Options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="/Users/yogashivamathivanan/eclipse-workspace2021/RestAssuredProject2022/src/test/java/features" ,
glue={"stepDefinitions"} /*tags = "@DeletePlace"*/)
public class TestRunner {
	
	
	

}
