package Utility;

import UIHelper.DriverHandler;
import Utility.ExtentReport.ExtentManager;
import Utility.ExtentReport.ExtentTestManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.json.*;
import org.openqa.selenium.*;
import org.testng.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Listener Class which defines actions, post per Test Case execution(pass/fail/skip)
 * @author rich-gupta
 */
public class ListenerClass implements ILogger, ITestListener, ISuiteListener {

    private static ThreadLocal<Integer> tcCount = new ThreadLocal<>();
    private static ThreadLocal<String> suiteName = new ThreadLocal<String>();
    private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<>();
    private static ExtentTest test;
    private final static AtomicInteger passCount = new AtomicInteger(0);
    private final static AtomicInteger failCount = new AtomicInteger(0);
    private final static AtomicInteger skipCount = new AtomicInteger(0);
    private static long testCount = 0 ;
    private static List<ITestNGMethod> testMethods = null;
    private static ThreadLocal<ExtentTest> childTest = new ThreadLocal<ExtentTest>();
    private static String tcName;

    public static ExtentTest getInstance() {
        return childTest.get();
    }

    /**
     * Configurations at the start of execution
     * To instantiate extent report
     * Also to showcase number of test cases, planned for the execution
     * @param suite ISuite
     */
    @Override
    public void onStart(ISuite suite) {
        suiteName.set(suite.getName());
        ExtentManager.getInstance(suiteName.get());
        testMethods  = suite.getAllMethods();
        this.testCount = testMethods.size();
        log.info("Total number of TCs for execution :: "+testCount);
    }

    public void classStart(IClass iClass){
        log.info(iClass.getName());
    }

    /**
     * Configurations at the end of execution
     * To end the Extent report instance
     * Also to showcase total number of Test cases that actually ran in execution
     * @param suite
     */

    @Override
    public void onFinish(ISuite suite) {
        ExtentManager.getInstance(suiteName.get()).flush();
        int totalTCs = passCount.intValue()+skipCount.intValue()+failCount.intValue();
        log.info("Total Number of Passed TCs :: "+passCount);
        log.info("Total number of skipped TCs :: "+skipCount);
        log.info("Total number of failed TCs :: "+failCount);
        log.info("Total Number of TCs executed :: "+totalTCs);
    }

    /**
     * Configures test details when test execution starts
     * Also, showcase test case name in logs
     * @param result ITestResult
     */
    @Override
    public void onTestStart(ITestResult result) {
        setTestDataInReport(result);
        getInstance().info("Starting Execution for Test Case : "+result.getName());
        log.info("Starting Execution for TC :: "+result.getName());
    }

    /**a
     * Action, post a Test case successfully PASSED
     * @param result ITestResult
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        if (getInstance() != null && result != null){
            getInstance().pass(MarkupHelper.createLabel("Test Case :: " + tcName
                    + " - Passed successfully. \n Execution time : "
                    + (result.getEndMillis() - result.getStartMillis())
                    + "ms.", ExtentColor.GREEN));
            log.info("TC passed successfully :: "+tcName);
        }
        try{
            if(DriverHandler.getWebDriver().get()!=null){
                takeScreenShot(DriverHandler.getWebDriver().get());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        log.info("Passed test case count :: "+passCount.incrementAndGet());
    }

    /**
     * Action, post a test case got SKIPPED during execution
     * @param result ITestResult
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        if (getInstance() != null && result != null) {
            getInstance().skip(MarkupHelper.createLabel("Test :: " + tcName + " - skipped.",ExtentColor.YELLOW));
            log.info("TC got skipped :: "+tcName);
            childTest.get().skip(result.getThrowable());
            try{
                if(DriverHandler.getWebDriver().get()!=null){
                    takeScreenShot(DriverHandler.getWebDriver().get());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        log.info("skipped test case count :: "+skipCount.getAndIncrement());
    }

    /**
     * Action, post a test case failed
     * @param result ITestResult
     */
    @Override
    public void onTestFailure(ITestResult result) {
        if (getInstance() != null && result != null) {
            getInstance().fail(MarkupHelper.createLabel("Test :: " + tcName + " - FAILED. \n Execution Time: " + (result.getEndMillis() - result.getStartMillis() + "ms."), ExtentColor.RED));
            log.info("TC got failed :: "+tcName);
            childTest.get().fail(result.getThrowable());
            try{
                if(DriverHandler.getWebDriver().get()!=null){
                    takeScreenShot(DriverHandler.getWebDriver().get());
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        log.info("failed test case count :: "+failCount.incrementAndGet());
    }

    public void setTestDataInReport(ITestResult result){
        String execMethod = result.getMethod().getMethodName();
        String tcDescription = null;
        try{
            tcDescription = result.getMethod().getDescription();
        } catch (Exception e){
            log.info(e.getMessage());
        }
        if (parentTest.get() == null || (!(parentTest.get().getModel().getName().equals(execMethod)))){
            parentTest.set(ExtentTestManager.getExtentTest(ExtentManager.getInstance(suiteName.get()), execMethod));
            parentTest.get().assignCategory(result.getTestClass().getRealClass().getName());
            tcCount.set(1);
        }
        if(tcCount.get().intValue() >= 1){
            tcName = null;
            Object[] inputArgs = result.getParameters();
            if(inputArgs.length==0){
                if(tcDescription != null)
                    tcName = tcDescription;
                else
                    tcName = execMethod;
            } else {
                if(inputArgs[0].toString().contains("testCaseName")){
                    String tcParam = inputArgs[0].toString();
                    if(isJSONValid(tcParam)){
                        tcName = new JSONObject(tcParam).getString("testCaseName");
                    }
                }
            }
            tcCount.set(tcCount.get().intValue() + 1);
            test = ExtentTestManager.getChildExtentTest(parentTest.get(), tcName);
            childTest.set(test);
        }
    }

    public boolean isJSONValid(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

    public void takeScreenShot(WebDriver driver) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        attachScreenshot(file);
    }

    public void attachScreenshot(File file){
        if (new File(file.getAbsoluteFile().getPath()).exists()) {
            childTest.get().log(Status.INFO,"<a href='data:image/png;base64,"
                    + encodeFileToBase64Binary(file)
                    + "'data-featherlight='image'><img src='data:image/png;base64, "
                    + encodeFileToBase64Binary(file)
                    + "' alt='Screenshot for failed test' height='100px' width='125px'></a>");
        }
    }

    private static String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            BufferedImage img = ImageIO.read(file);
            ImageIO.write(img, "png", baos);
            encodedfile = Base64.getEncoder()
                    .encodeToString(baos.toByteArray());
            baos.flush();
            baos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encodedfile;
    }
}