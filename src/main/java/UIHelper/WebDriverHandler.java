package UIHelper;

import Utility.ILogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import java.io.File;
import java.util.HashMap;

public class WebDriverHandler implements ILogger {

    static DesiredCapabilities capabilities = null;
    static protected WebDriver driver;

    public synchronized WebDriver initialiseDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                capabilities = DesiredCapabilities.chrome();
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                HashMap<String, Object> chromeOptionsMap = new HashMap<String, Object>();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptionsMap);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                ChromeDriverService chrome;
                chrome = new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(String.valueOf(ChromeDriverUtil.getChromeExecutablePath())))
                        .usingAnyFreePort().build();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("--test-type");
                options.merge(capabilities);
                driver = new ChromeDriver(chrome,options);
                break;
            case "safari":
                driver = new SafariDriver();
            default:
                log.error("Browser Initialisation method does not exist :: "+browser);
                break;
        }
        log.info("Initialising driver for browser :: "+browser);
        return driver;
    }
}