package UI.Pages;

import UI.Pages.AbstractBasePageWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractBasePageWeb {





public By email = By.xpath("//*[@id=\"ap_email\"]");
public By continuebutton =By.xpath("//*[@id=\"continue\"]");
public By password = By.xpath("//*[@id=\"ap_password\"]");
public By login = By.xpath("//*[@id=\"signInSubmit\"]");

public void loginMethod(){
    enterText(email,"krunal.bhadra@gmail.com");
    clickElement(continuebutton);
    enterText(password,"Dhani@8240");
    clickElement(login);
}

}
