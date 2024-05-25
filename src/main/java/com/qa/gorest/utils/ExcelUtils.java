package com.qa.gorest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
public  Object[][]  excelReader(String sheetName)throws IOException {
		
		File file=new File ("C:\\Users\\Amit Singh\\OneDrive\\Desktop\\myExcelData.xlsx");
		System.out.println(file.exists());
		FileInputStream fis=new FileInputStream(file);
		
		XSSFWorkbook wb=new XSSFWorkbook(fis);	
		XSSFSheet sheet= wb.getSheet(sheetName);
		
		int noOfRow=sheet.getPhysicalNumberOfRows();
		System.out.println(noOfRow);//all row num 
		System.out.println(noOfRow-1);
		int noOfCell=sheet.getRow(0).getLastCellNum();
		System.out.println(noOfCell);
		
		//noOfRow-1 once you make the changes then exception thrown Index 2 out of bounds for length 2
		Object[][] data=new String[noOfRow-1][noOfCell];
		
		//now changes required to run the loop fro
		for(int i=0;i<noOfRow-1;i++) {
			for(int j=0;j<noOfCell;j++) {
				DataFormatter df=new DataFormatter();
				//start the row after header
				data[i][j]=df.formatCellValue(sheet.getRow(i+1).getCell(j));
				//System.out.println(data);
			}
			
		}
		
		wb.close();
		fis.close();
		
		return data;
	}

}



