package utilities;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
// Import the necessary method for capturing screenshots if needed
// import static YourScreenshotUtility.captureScreenshot; 

public class ExtentListeners implements ITestListener {

    public void onStart(ITestContext context) {
        // Initialization code if needed, getExtentReports() is called implicitly
        // in onTestStart below.
    }

    public void onFinish(ITestContext context) {
        // Flushes the report to the HTML file once all tests in the suite are done
        ExtentManager.flushReports();
    }

    public void onTestStart(ITestResult result) {
        // Creates a new test entry in the report for each test method
        ExtentTest test = ExtentManager.getExtentReports().createTest(result.getMethod().getMethodName());
        ExtentManager.setTest(test);
    }

    public void onTestSuccess(ITestResult result) {
        // Logs a pass status for successful tests
        ExtentManager.getTest().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        // Logs a fail status and potentially attaches a screenshot
        ExtentManager.getTest().log(Status.FAIL, "Test Failed");
        ExtentManager.getTest().log(Status.FAIL, result.getThrowable());

        // Code to capture and attach screenshot can be added here
        /*
        try {
            String screenshotPath = captureScreenshot(result.getMethod().getMethodName()); // Your screenshot method
            ExtentManager.getTest().addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

    public void onTestSkipped(ITestResult result) {
        // Logs a skip status for skipped tests
        ExtentManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    // Other ITestListener methods (onTestFailedButWithinSuccessPercentage, etc.) can be implemented as needed
}
