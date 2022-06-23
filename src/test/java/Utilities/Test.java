package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(getInputData("TC_001","URL"));
	}
	
public static String getInputData(String Keyword, String Columnname) throws IOException {
		
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
