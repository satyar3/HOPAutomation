package com.qa.hop.util;

import org.testng.annotations.DataProvider;

public class DataProviderClass
{
	
	@DataProvider(name="dummy")
	public Object[][] dummy()
	{
		Object ob[][] = TestUtil.geDataFromExcel("Sheet1");
		return ob;
	}
}
