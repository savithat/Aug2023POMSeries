package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelUtil {
	
	static final String registrationData_excel_loc = "./src/test/resources/testdata/register.xlsx";
//	static final String productData_excel_loc = "./src/test/resources/testdata/register.xlsx";
	static Workbook workbook;
	static Sheet sheet;
	
	public static Object[][] getExcelData(String sheetName) {
		
	//	System.out.println("raeding data from the sheet:**********"+ sheetName);
		Object[][] data = null;
		
		try {
			FileInputStream ip = new FileInputStream(registrationData_excel_loc);
			workbook = WorkbookFactory.create(ip);
			sheet = workbook.getSheet(sheetName);
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
//			System.out.println("sheet.getLastRowNum():  "+sheet.getLastRowNum());
		//	data = new Object[3][sheet.getRow(0).getLastCellNum()];
			
	//		System.out.println("Row: "+ sheet.getLastRowNum()+ "   Column: "+ sheet.getRow(0).getLastCellNum());
			for(int i=0; i < sheet.getLastRowNum(); i++) {
				for(int j=0; j < sheet.getRow(0).getLastCellNum(); j++) {
			//		System.out.println("i: "+ i+ "j: "+ j);
			//		System.out.println("DATA:::::::"+ sheet.getRow(i+1).getCell(j).toString());
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
		return data;
	}

}
