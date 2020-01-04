package Tests;

import UI.Pages.AbstractBasePageWeb;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class HomePage extends AbstractBasePageWeb {

    @BeforeMethod
    public void setup(){
        openBrowser("chrome");
        returnPages("LandingPage","LoginPage");
    }

        @Test(priority = 1)
    public void Case1() throws InterruptedException {
               goToURL("https://amazon.in");

               landingPage.clickSignInButton();
               Thread.sleep(10000);
               loginPage.loginMethod();
               landingPage.miTV();
               Thread.sleep(5000);
               landingPage.searchSubmit();
               landingPage.verifyText();
    }
        @Test(priority = 2)
    public void Case2(){
                landingPage.searchSketchers();
                scrollDowns("0","8900");
                landingPage.clickSecondbuttonlink();
        }
        @Test(priority = 3)
    public void Case3() throws InterruptedException {
                landingPage.goHomePage();
                Thread.sleep(5000);
                landingPage.searchTrimmer();
                scrollDowns("0","1000");
               // landingPage.selectPhilipCheckbox();
    }

    //@AfterMethod
   // public void close()
        //{
          //  driver.close();
       // }
}
