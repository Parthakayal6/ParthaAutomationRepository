package Utilities;

import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static Scenario scenario;
	public static Actions action;
	public static JavascriptExecutor js;
	
	public static void initialize_before_methods(Scenario scenario) {
		BaseClass.scenario = scenario;
	}
	
	public static void capture_screenshot(){
	     byte[] screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
	          scenario.attach(screenshot, "image/png", "screenshot");
	}
	
	public static void report_log(String string) {

		scenario.log(string);
	}
	
	public static void capture_Fullpage_screenshot() throws IOException{
		Screenshot s=new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		ImageIO.write(s.getImage(),"PNG",baos);
		byte[] screenshot=baos.toByteArray();
		scenario.attach(screenshot, "image/png", "screenshot");
	}
	
	

	public String getInputData(String Keyword, String Columnname) throws IOException {
		
		List<HashMap<String, String>> ListTestData= new ArrayList<HashMap<String, String>>();		
		DataFormatter formatter = new DataFormatter();
		FileInputStream fis = new FileInputStream("TestData/DriverExcel.xlsx"); 
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("TestData");
		int rows= sheet.getPhysicalNumberOfRows();
		String Data=null;
		//int k=0;
		
		  for(int count=1; count<rows; count++) {
			  HashMap<String, String> initialMap= new HashMap<String, String>();
			  int cols= sheet.getRow(0).getPhysicalNumberOfCells();
			  for(int colcnt=0; colcnt<cols; colcnt++) { 
				  String KeyWord= formatter.formatCellValue(sheet.getRow(0).getCell(colcnt)); 
				  String value =  formatter.formatCellValue(sheet.getRow(count).getCell(colcnt)); 
				  initialMap.put(KeyWord, value);  
				  } 
			  ListTestData.add(count-1, initialMap);
			  }
	
		  for(int c=0 ; c<ListTestData.size(); c++) {
			  if (ListTestData.get(c).get("Keyword").equalsIgnoreCase(Keyword)) {
				  Data= ListTestData.get(c).get(Columnname).toString();
				  break;
			  }
		  }
		  return Data;
		 
		
	}
	
	
}
