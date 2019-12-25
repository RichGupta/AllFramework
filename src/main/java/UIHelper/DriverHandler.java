package UIHelper;

import Utility.ILogger;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;

public class DriverHandler implements ILogger {

    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    static protected WebDriver driver;

    public DriverHandler() {
    }

    private static WebDriverHandler webDriverHandler = new WebDriverHandler();
    private static MobileDriverHandler mobileDriverHandler = new MobileDriverHandler();

    public static ThreadLocal<WebDriver> getWebDriver() {
        return webDriver;
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void closeDriver() {
        while (webDriver.get()!=null){
            webDriver.get().quit();
            webDriver.remove();
            log.info("Closed browser");
        }
    }

    public static WebDriver getDriver(String browser){
        if(getWebDriver().get()==null){
            setWebDriver(webDriverHandler.initialiseDriver(browser));
        }
        driver = getWebDriver().get();
        return driver;
    }

    public static WebDriver getDriver(String OS, String browser){
        if(getWebDriver().get()==null){
            try {
                setWebDriver(mobileDriverHandler.initialiseDriver(OS,browser));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        driver = getWebDriver().get();
        return driver;
    }
}
