package com.freecrm.utilities;

import java.io.File;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class ExcelHandling {
	
	Configuration prop=new Configuration();
	 String filePath;
	 Sheet sh;
	
	public ExcelHandling(String sheetName) {
		 try {
			filePath=System.getProperty("user.dir")+prop.getvalue("excelLocation");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 File file=new File(filePath);
		 Workbook wb = null;
			try {
				wb = WorkbookFactory.create(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 sh=wb.getSheet(sheetName);
	}

	public HashMap<String, String> getTestDataInMap(int rowNum)  {
		
		HashMap<String, String> hm=new HashMap<String, String>();
		for(int i=0;i<sh.getRow(0).getLastCellNum();i++) {
			sh.getRow(rowNum).getCell(i).setCellType(CellType.STRING);
			hm.put(sh.getRow(0).getCell(i).toString(), sh.getRow(rowNum).getCell(i).toString());
		}
		
		return hm;
	}
	
	public int getRowCount() {		
		return sh.getLastRowNum();
	}
	
	public int getColCount() {
		return sh.getRow(0).getLastCellNum();
		
	}
} 
