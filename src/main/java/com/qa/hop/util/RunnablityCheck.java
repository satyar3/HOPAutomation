package com.qa.hop.util;

import org.testng.SkipException;

@SuppressWarnings("serial")
public class RunnablityCheck extends SkipException
{

	public RunnablityCheck(String skipMessage)
	{
		super(skipMessage);
	}
	
	
//	If want to mark the test case as failed
//	public boolean isSkip() 
//	{
//		return false;
//	}
	
	public static void isTestRunnable(String runMode, String testCaseNumber)
	{
		if(runMode.equals(Constants.tc_run_mode_N))
		{
			
			RunnablityCheck r = new RunnablityCheck("Skipping the test case : "+testCaseNumber+" as the run mode is \"No\"");
			r.reduceStackTrace();
			throw r;
			//new SkipException("Skipping the test case : "+testCaseNumber+" as the run mode is \"No\"");
		}
	}

}
