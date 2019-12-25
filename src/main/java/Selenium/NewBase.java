package Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class NewBase {

    public WebDriver driver;
    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Users\\Krunal\\IdeaProjects\\AllFramework\\src\\main\\java\\Selenium\\alldata.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        if(browserName.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver","C:\\Users\\Krunal\\Downloads\\chromedriver_win32//chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browserName.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver","C:\\Users\\Krunal\\Downloads\\geckodriver-v0.19.1-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if(browserName.equals("IE"))
        {
            System.setProperty("webdriver.ie.driver","C:\\Users\\Krunal\\Downloads\\IEDriverServer_x64_3.14.0\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;

    }
}
