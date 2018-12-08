package com.qa.hop.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class TestUtil 
{
	static String TESTDATA_SHEET_PATH = Constants.excel_path;
	static Workbook book;
	static Sheet sheet;

	public static Object[][] geDataFromExcel(String sheetName)
	{

		FileInputStream file = null;
		try
		{
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			book = WorkbookFactory.create(file);
		}
		catch (InvalidFormatException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);

		//Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		Object[][] data = new Object[sheet.getLastRowNum()][1];

		int temp = 0;
		for (int i = 0; i < sheet.getLastRowNum(); i++)
		{
			Hashtable<String, String> table = new Hashtable<String, String>();
			String cellValue;
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++)
			{
				try
				{
					//data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					cellValue = sheet.getRow(i + 1).getCell(k).toString();
				}
				catch (Exception n)
				{
					//data[i][k] = "";
					cellValue = "";
				}
				
				table.put(sheet.getRow(0).getCell(k).toString(), cellValue);
			}
			
			data[temp][0] = table;
			temp++;
		}
		
		return data;		
	}
}
