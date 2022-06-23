package PageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.BaseClass;

public class CalculatorHomePage extends BaseClass {

	private WebDriver driver;

	public CalculatorHomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Objects
	@FindBy(xpath = "//a[@title='ANZ Logo']")
	private WebElement AnzLogo;

	@FindBy(xpath = "//label[@for='application_type_single']")
	private WebElement SingleApplicationType;

	@FindBy(xpath = "//select[@title='Number of dependants']")
	private WebElement DependentsDropDown;

	@FindBy(xpath = "//label[@for='borrow_type_home']")
	private WebElement HomeProperty;

	@FindBy(xpath = "//label[text()='Your annual income (before tax)']/parent::div/div/input")
	private WebElement AnnualIncome;

	@FindBy(xpath = "//label[text()='Your annual other income (optional)']/parent::div/div/input")
	private WebElement AnnualOtherIncome;

	@FindBy(xpath = "//label[text()='Monthly living expenses ']/parent::div/div/input")
	private WebElement LivingExpenses;

	@FindBy(xpath = "//label[contains(text(),'Current home loan monthly')]/parent::div/div/input")
	private WebElement CurrentHomeLoanRepayments;

	@FindBy(xpath = "//label[text()='Other loan monthly repayments']/parent::div/div/input")
	private WebElement OtherLoanRepayments;

	@FindBy(xpath = "//label[text()='Other monthly commitments']/parent::div/div/input")
	private WebElement OtherCommitments;

	@FindBy(xpath = "//label[text()='Total credit card limits']/parent::div/div/input")
	private WebElement TotalCreditCardLimits;

	@FindBy(xpath = "//button[@id='btnBorrowCalculater']")
	private WebElement CalculateButton;

	@FindBy(xpath = "//span[@id='borrowResultTextAmount']")
	private WebElement ResultAmount;

	@FindBy(xpath = "//div[@class='result__restart']/button")
	private WebElement StartOver;

	@FindBy(xpath = "//div[@class='borrow__error__text']")
	private WebElement ErrorMsg;

	// Methods
	public boolean ValidateHomePage() {
		boolean res = false;
		try {
			js = ((JavascriptExecutor) driver);
			wait.until(ExpectedConditions.elementToBeClickable(AnzLogo));
			capture_screenshot();
			report_log("HomePage displayed successfully");
			res = true;
		} catch (Exception e) {
			e.printStackTrace();
			capture_screenshot();
			report_log("Error while loading Homepage");
		}
		return res;
	}

	public boolean ProvideBorrowEstimateInputDetails() {
		boolean res = false;
		try {
			SingleApplicationType.click();
			Select dropdown = new Select(DependentsDropDown);
			dropdown.selectByVisibleText(getInputData(System.getProperty("keyword"),"Dependent"));
			HomeProperty.click();
			AnnualIncome.sendKeys(getInputData(System.getProperty("keyword"),"Annualincome"));
			AnnualOtherIncome.sendKeys(getInputData(System.getProperty("keyword"),"AnnualOtherIncome"));
			LivingExpenses.sendKeys(getInputData(System.getProperty("keyword"),"LivingExpenses"));
			CurrentHomeLoanRepayments.sendKeys(getInputData(System.getProperty("keyword"),"CurrentHomeLoan"));
			OtherLoanRepayments.sendKeys(getInputData(System.getProperty("keyword"),"OtherLoan"));
			OtherCommitments.sendKeys(getInputData(System.getProperty("keyword"),"OtherCommitment"));
			TotalCreditCardLimits.sendKeys(getInputData(System.getProperty("keyword"),"CreditCradLimit"));
			res = true;
			report_log("Borrow estimate input details provided successfully");
			capture_screenshot();
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			report_log("Error while providing borrow estimate inputs");
			capture_screenshot();
		}
		return res;
	}

	public boolean CalculateBorrowEstimates() {
		boolean res = false;
		try {
			CalculateButton.click();
			Thread.sleep(2000);
			if (ResultAmount.getText().replaceAll(",", "").contentEquals(getInputData(System.getProperty("keyword"),"ExpectedResultAmount"))) {
				res = true;
				report_log("Expected amount matched");
				capture_screenshot();
			} else {
				report_log("Expected amount not matched. Amount calculated as : " + ResultAmount.getText());
				capture_screenshot();
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			report_log("Error while calculating borrow estimate");
			capture_screenshot();
		}
		return res;
	}

	public boolean ValidateRefreshButton() {
		boolean res = false;
		try {
			StartOver.click();
			Thread.sleep(2000);
			int count = 0;
			if (AnnualIncome.getAttribute("value").replaceAll(",", "").contentEquals("0")) {
				count++;
			}
			if (AnnualOtherIncome.getAttribute("value").replaceAll(",", "").contentEquals("0")) {
				count++;
			}
			if (LivingExpenses.getAttribute("value").replaceAll(",", "").contentEquals("0")) {
				count++;
			}
			if (OtherLoanRepayments.getAttribute("value").replaceAll(",", "").contentEquals("0")) {
				count++;
			}
			if (TotalCreditCardLimits.getAttribute("value").replaceAll(",", "").contentEquals("0")) {
				count++;
			}
			if (CurrentHomeLoanRepayments.getAttribute("value").replaceAll(",", "").contentEquals("0")) {
				count++;
			}
			if (OtherCommitments.getAttribute("value").replaceAll(",", "").contentEquals("0")) {
				count++;
			}

			if (count == 7) {
				res = true;
				report_log("All the input fields got reset after clicking on start over button");
				capture_screenshot();
			} else {
				report_log("Some input fields not reset to 0 after clicking on start over button");
				capture_screenshot();
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			report_log("Error while valiadting refresh button");
			capture_screenshot();
		}
		return res;
	}
	
	public boolean ProvideLivingExpense() {
		boolean res = false;
		try {
			SingleApplicationType.click();
			Select dropdown = new Select(DependentsDropDown);
			dropdown.selectByVisibleText(getInputData(System.getProperty("keyword"),"Dependent"));
			HomeProperty.click();
			LivingExpenses.sendKeys(getInputData(System.getProperty("keyword"),"LivingExpenses"));
			res = true;
			report_log("Living expense input details provided successfully");
			capture_screenshot();
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			report_log("Error while providing Living expense inputs");
			capture_screenshot();
		}
		return res;
	}
	
	public boolean ValidateErrorMessage() {
		boolean res = false;
		try {
			CalculateButton.click();
			Thread.sleep(2000);
			if (ErrorMsg.getText().contentEquals(getInputData(System.getProperty("keyword"),"ErrorMessage"))) {
				res = true;
				report_log("Error message displayed successfully");
				capture_screenshot();
			} else {
				report_log("Error message not displayed as expected: "+ ErrorMsg.getText());
				capture_screenshot();
			}
		} catch (Exception e) {
			e.printStackTrace();
			res = false;
			report_log("Error while validating error message");
			capture_screenshot();
		}
		return res;
	}
	
	

}
