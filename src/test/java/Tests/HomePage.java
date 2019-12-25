package Tests;

import Selenium.LandingPage;
import Selenium.LoginPage;
import Selenium.NewBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Driver;

public class HomePage extends NewBase {

    @Test
    public void basePageNavigation()

    {

        try {
            driver=initializeDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver.get("https://www.google.in");
            LandingPage la = new LandingPage(driver);
                la.getLogin().click();
        LoginPage lp = new LoginPage(driver);

                lp.getEmail().sendKeys("krunal.bhadra@gmail.com");
                lp.getContinueButton().click();
                lp.getPassword().sendKeys("Dhani@8240");
                lp.getLogin().click();


    }

}
