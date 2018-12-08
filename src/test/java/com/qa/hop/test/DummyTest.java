package com.qa.hop.test;

import java.util.Hashtable;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import com.qa.hop.util.Constants;
import com.qa.hop.util.DataProviderClass;
import com.qa.hop.util.RunnablityCheck;

public class DummyTest
{
	@Test(dataProviderClass = DataProviderClass.class, dataProvider = "dummy")
	public void test(Hashtable<String,String> data)
	{
		for(Entry<String,String> e: data.entrySet())
		{
			System.out.println(e.getKey());
			System.out.println(e.getValue());
		}
		
		RunnablityCheck.isTestRunnable(data.get(Constants.run_mode), data.get(Constants.test_case));
	}
}
