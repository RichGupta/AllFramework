package Selenium;

import UI.Pages.AbstractBasePageWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage extends AbstractBasePageWeb {



   public By signin = By.xpath("//*[@id=\"nav-signin-tooltip\"]/a/span");

public void clickSignInButton(){
    clickElement(signin);
}

}
