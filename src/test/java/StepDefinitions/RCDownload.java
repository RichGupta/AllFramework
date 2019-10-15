package StepDefinitions;

import java.net.MalformedURLException;
import java.net.URL;
import UI.Pages.AbstractBasePage;
import cucumber.api.PendingException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class RCDownload  extends AbstractBasePage {
    AppiumDriver<MobileElement> driver;

    @Given("Connect with mobile and launch chrome app")
    public void setUp() throws MalformedURLException{
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("BROWSER_NAME", "Android");
//        capabilities.setCapability("VERSION", "9");
//        capabilities.setCapability("deviceName","Android SDK built for x86");
//        capabilities.setCapability("platformName","Android");
//        capabilities.setCapability("udid","emulator-5554");
//        capabilities.setCapability("avd","Pixel_2_XL_API_28");
//        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
////        capabilities.setCapability("appPackage", "com.android.calculator2");
////        capabilities.setCapability("appPackage", "com.android.chrome");
////        capabilities.setCapability("appPackage", "com.sec.android.app.popupcalculator");
////        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
////        capabilities.setCapability("appActivity","com.google.android.apps.chrome.Main");
////        capabilities.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");
////        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver.exe");
//        driver = new AppiumDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        System.out.println("Executing given statement");
    }

    @When("User go to Rummy Circle page")
    public void testCal(){
//        driver.get("https://www.google.com/");
//        driver.get("https://www.rummycircle.com");
//        driver.findElement(By.id("app-download")).click();
//        driver.context("NATIVE_APP");
//        if(isElementPresent(By.id("android:id/button1"))){
////            driver.context("CHROMIUM");
////            Point loc = driver.findElement(By.id("android:id/button1")).getLocation();
////            int x = loc.getX();
////            int y = loc.getY();
////            TouchAction actions = new TouchAction(driver);
//            driver.findElement(By.id("android:id/button1")).click();
////            actions.tap(new TapOptions().withElement(new ElementOption().withElement(driver.findElement(By.id("android:id/button1"))))).perform();
//            driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button")).click();
////            actions.tap(new TapOptions().withElement(new ElementOption().withElement(driver.findElement(By.id("android:id/button1"))))).perform();
////            driver.findElement(By.id("com.android.chrome:id/positive_button")).click();
////            actions.tap(new TapOptions().withElement(new ElementOption().withElement(driver.findElement(By.id("android:id/button1"))))).perform();
//        }
//        driver.findElement(By.id("com.android.chrome:id/button_primary")).click();
        System.out.println("Executing when statement");
    }

    @Then("Download the RC app")
    public void teardown(){
        //close the app
//        driver.quit();
//        CucumberResultsOverview results = new CucumberResultsOverview();
//        results.setOutputDirectory("target");
//        results.setOutputName("cucumber-results");
//        results.setSourceFile("./src/test/resources/cucumber.json");
//        try {
//            results.execute();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("Executing then statement");
    }
}