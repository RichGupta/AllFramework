package Tests;

import Selenium.LandingPage;
import Selenium.LoginPage;
import Selenium.NewBase;
import UI.Pages.AbstractBasePageWeb;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;

public class HomePage extends AbstractBasePageWeb {

    @BeforeMethod
    public void setup(){
        openBrowser("chrome","win");
        returnPages("LandingPage","LoginPage");
    }

        @Test
    public void basePageNavigation()

    {
               goToURL("https://amazon.in");
                landingPage.clickSignInButton();
                loginPage.loginMethod();


    }

        @AfterMethod
    public void close()
        {
            driver.close();
        }
}
