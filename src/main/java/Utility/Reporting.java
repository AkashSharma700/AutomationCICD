package Utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting {

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "/Deliverables/TestReports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		return extent;
	}

}
