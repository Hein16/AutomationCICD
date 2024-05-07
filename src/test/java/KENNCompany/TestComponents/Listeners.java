package KENNCompany.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import KENNCompany.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentText=new ThreadLocal<ExtentTest>(); //Thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
		test=extent.createTest(result.getMethod().getMethodName());//sa run tr nk report file twy htoke py fo listen py fo
		//concurrency ma fik ag extentText.set() argument htl mhr test koh htae py tr
		extentText.set(test);//unique thread example: id(ErrorValidationTest)->test
		//d lo set nk test koh htae pe b so test 1 ku chin c ah twk unique ID nk loke py ml 
		//ek tr kyg test ny yr twy koh extentText.get() nk ah sr htoe concurrency ma fik ag
		//d loh Thread safe nk yay lyk b so parallel run chin ll report twy ka correctly ah loke loke ml
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentText.get().log(Status.PASS, "Test Passed"); //pass fik dk test twy mhr Test Passed so p pya ml
	}

	@Override
	public void onTestFailure(ITestResult result) {
		//test.log(Status.FAIL, "Test Failed");  // d lo yay ll ya
		//test.fail(result.getThrowable()); //d lo so yin twk br lo fail dl so tr g koh thu hr thu pyw py late ml fail ya dk reason koh report mhr htoke pya late ml
		//concurrency ma fik ag ah por ka loh ma 3 bl out ka loh 3 tr
		extentText.get().fail(result.getThrowable());

		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentText.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		
		//screenshot, Attach to report,
	}


	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
