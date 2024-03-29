package TestClasses;

import UI.Pages.AbstractBasePageWeb;
import UIHelper.DriverHandler;
import Utility.ILogger;
import org.testng.annotations.*;

public class sampleTest extends AbstractBasePageWeb implements ILogger {

    @BeforeMethod
    public void setup(){
        openBrowser("chrome");
        returnPages("TestPage");
    }

    @Test
    public void testOne(){
        goToURL("https://www.google.com");
        testPage.searchItem("facebook");
        testPage.verifySearch("Facebook, Inc.");
    }

    @AfterMethod
    public void tearDown(){
        DriverHandler.closeDriver();
    }
}
