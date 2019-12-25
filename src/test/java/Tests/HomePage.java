package Tests;

import UI.Pages.AbstractBasePageWeb;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
