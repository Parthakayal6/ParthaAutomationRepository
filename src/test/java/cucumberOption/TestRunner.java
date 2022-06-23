package cucumberOption;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;

@CucumberOptions(features = "src/test/java/features", 
glue = "Stepdefination",
plugin = {"json:Result/CucumberJson/cucumber.json"}
)
public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	// groups = "cucumber", description = "Runs Cucumber Feature",

	@Test(dataProvider = "features")
	public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
		if (testNGCucumberRunner == null) {
			System.out.println("runner is returning null: runscenario");

		} else {
			System.out.println("Runner is not null: runscenario");
		}
		testNGCucumberRunner.runScenario(pickle.getPickle());
	}

	//
	@DataProvider(name = "features")
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			System.out.println("runner is returning null: dataprovider");
			testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		} else {
			System.out.println("Runner is not null: dataprovider");
		}
		return testNGCucumberRunner.provideScenarios();
	}
	
	 @AfterClass(alwaysRun = true)
	    public void tearDownClass() throws Exception {
	    	try{
	    	testNGCucumberRunner.finish();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
			/*
			 * File reportOutputDirectory = new File("Result");
			 * 
			 * List<String> jsonFiles = new ArrayList<>();
			 * jsonFiles.add("Result/CucumberJson/cucumber.json"); String projectName =
			 * "cucumberProject";
			 * 
			 * Configuration configuration = new Configuration(reportOutputDirectory,
			 * projectName);
			 * 
			 * 
			 * ReportBuilder reportBuilder=new ReportBuilder(jsonFiles,configuration);
			 * reportBuilder.generateReports();
			 */
	    }

}
