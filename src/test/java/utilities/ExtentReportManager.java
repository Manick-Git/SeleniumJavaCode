package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
// Import the necessary method for capturing screenshots if needed
// import static YourScreenshotUtility.captureScreenshot; 

public class ExtentReportManager implements ITestListener {
    
	public static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private static ExtentReports extent;
    private ExtentTest test;
    
    public void onStart(ITestContext context) {
        // Initialization code if needed, getExtentReports() is called implicitly
        // in onTestStart below.
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Selenium Automation Report");
        htmlReporter.config().setReportName("Test Execution Results");
        htmlReporter.config().setTheme(Theme.STANDARD);
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Local Host");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Browser", "Chrome"); // Update as needed
    }

    public void onFinish(ITestContext context) {
        // Flushes the report to the HTML file once all tests in the suite are done
        ExtentReportManager.extent.flush();
    }

    public void onTestStart(ITestResult result) {
        // Creates a new test entry in the report for each test method
        test = ExtentReportManager.extent.createTest(result.getMethod().getMethodName());
        ExtentReportManager.setTest(test);
    }

    private static void setTest(ExtentTest test2) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult result) {
        // Logs a pass status for successful tests
        ExtentTest test = extent.createTest(result.getTestClass().getName());
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        // Logs a fail status and potentially attaches a screenshot
        ExtentReportManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentReportManager.getTest().log(Status.FAIL, result.getThrowable());


        try {
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName()); // Your screenshot method
            ExtentReportManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String captureScreenshot(String methodName) {
		// TODO Auto-generated method stub
		return null;
	}

	private static ExtentTest getTest() {
		// TODO Auto-generated method stub
		return null;
	}

	public void onTestSkipped(ITestResult result) {
        // Logs a skip status for skipped tests
        ExtentReportManager.getTest().log(Status.SKIP, "Test Skipped");   
        }

	}

    // Other ITestListener methods (onTestFailedButWithinSuccessPercentage, etc.) can be implemented as needed

