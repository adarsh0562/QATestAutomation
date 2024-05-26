package com.adarsh.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	
	public static ExtentReports getExtentReport()
	{
		String path = System.getProperty("user.dir")+"//report//index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Adarsh Raj Testing Academy");
		reporter.config().setReportName("Shopping App Testing");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("QA", "Mr. Adarsh Raj Gupta");
		
		return extent;
		
	}
}
