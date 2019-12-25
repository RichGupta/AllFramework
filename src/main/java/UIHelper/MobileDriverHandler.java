package UIHelper;

import Utility.ILogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;

public class MobileDriverHandler implements ILogger {

    public static DesiredCapabilities capabilities = new DesiredCapabilities();
    public static AppiumDriver<MobileElement> driver;

    public static synchronized AppiumDriver initialiseDriver(String OS, String browser) throws MalformedURLException {
        switch (OS.toLowerCase()) {
            case "android":
                capabilities.setCapability("BROWSER_NAME", "Android");
                capabilities.setCapability("VERSION", "9");
                capabilities.setCapability("deviceName","Android SDK built for x86");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("udid","emulator-5554");
                capabilities.setCapability("avd","Pixel_2_XL_API_28");
                switch (browser.toLowerCase()){
                    case "chrome":
                        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, BrowserType.CHROME);
                        break;
                    default:
                        break;
                }
            case "ios":
                break;
            default:
                break;
        }
        log.info("Initialising driver for OS :- "+ OS);
        driver = new AppiumDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        return driver;
    }
}