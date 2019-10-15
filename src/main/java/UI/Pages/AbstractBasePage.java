package UI.Pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class AbstractBasePage {

    AppiumDriver<MobileElement> driver;

    public boolean isElementPresent(By locator) {
        MobileElement newEle = findByWait(locator);
        if (newEle == null)
            return false;
        else
            return true;
    }

    public MobileElement findByWait(By locatn) {
        MobileElement element = null;
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
//            WebDriverWait wait = new WebDriverWait(driver, 60);
//            wait.until(ExpectedConditions.presenceOfElementLocated(locatn));
            element = driver.findElement(locatn);
//            Log.info("found element " + ele);
        } catch (Exception e) {
//            Log.info("not able to find element : " + e.getMessage());
        }
        return element;
    }

}
