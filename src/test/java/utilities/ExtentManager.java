package utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    public static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private static ExtentReports extent;

    public synchronized static ExtentReports getExtentReports() {
        if (extent == null) {
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/ExtentReport.html");
            htmlReporter.config().setDocumentTitle("Selenium Automation Report");
            htmlReporter.config().setReportName("Test Execution Results");
            htmlReporter.config().setTheme(Theme.STANDARD);
            
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Host Name", "Local Host");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("User Name", System.getProperty("user.name"));
            extent.setSystemInfo("Browser", "Chrome/Firefox/Edge"); // Update as needed
        }
        return extent;
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }

    public static synchronized void setTest(ExtentTest test) {
        extentTest.set(test);
    }
}