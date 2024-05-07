package KENNCompany.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	
	public static ExtentReports getReportObject() {
		//ExtentReports, ExtentSparkReporter
				String path=System.getProperty("user.dir")+"\\reports\\index.html"; //report htae fo file 1 ku sout lyk tr
				ExtentSparkReporter reporter=new ExtentSparkReporter(path);
				reporter.config().setReportName("Web Automation Results");  //koh test twy yk result ko page yk name koh py tr(right top mhr ko py htr dk argument htl ka name por ny ml)
				reporter.config().setDocumentTitle("Test Result"); //koh test twy yk result page yk URL name koh py tr
				
				ExtentReports extent=new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "KENN");
				return extent;
	}

}
