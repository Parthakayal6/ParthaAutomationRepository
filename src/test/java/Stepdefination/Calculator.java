package Stepdefination;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import PageObjects.*;
import Utilities.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;


public class Calculator extends BaseClass{
	
	CalculatorHomePage homepage;
	
	@Before()
	public void BeforeInit(Scenario scenario)
	{
		initialize_before_methods(scenario);
	}
	
	@Given("User is able to open the production URL with {string}")
	public void OpenProdUrl(String Keyword) throws IOException {
		
		System.setProperty("webdriver.chrome.driver", "src/test/java/Drivers/chromedriver.exe");
		System.setProperty("keyword", Keyword);
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(120));
		wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		homepage = new CalculatorHomePage(driver);
		System.out.println(getInputData(System.getProperty("keyword"),"URL"));
		driver.get(getInputData(System.getProperty("keyword"),"URL"));
		Assert.assertTrue(homepage.ValidateHomePage());
		
	}
	
	@When("User provides expense details")
	public void ProvideDetails() {
		Assert.assertTrue(homepage.ProvideBorrowEstimateInputDetails());
	}
	
	@Then("The calculated amount should match with the expected amount")
	public void CalculateAmount() {
		Assert.assertTrue(homepage.CalculateBorrowEstimates());
	}
	
	@Then("After clicking on Start Over button the form got cleared")
	public void clearForm() {
		Assert.assertTrue(homepage.ValidateRefreshButton());
	}
	
	@When("User provides only living expense details")
	public void provideMonthlyExpenseDetails() {
		Assert.assertTrue(homepage.ProvideLivingExpense());
	}
	
	@Then("After clicking on Work out how much I could borrow button error message returns")
	public void ValidateErrorMessageDetails() {
		Assert.assertTrue(homepage.ValidateErrorMessage());
	}
	
	@After()
	public void AfterClassMethods() {
		driver.quit();
	}
	

}
