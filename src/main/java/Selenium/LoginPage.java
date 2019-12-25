package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

public WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }
//By hamburger = By.xpath("//*[@id=\"nav-hamburger-menu\"]/i");
public By email = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input");
        //("//*[@id=\"ap_email\"]");
By continuebutton =By.xpath("//*[@id=\"continue\"]");
By password = By.xpath("//*[@id=\"ap_password\"]");
By login = By.xpath("//*[@id=\"signInSubmit\"]");


public WebElement getEmail(){
    return driver.findElement(email);
}

public WebElement getContinueButton(){
    return driver.findElement(continuebutton);
}
public WebElement getPassword(){
    return driver.findElement(password);
}
public WebElement getLogin(){
    return driver.findElement(login);
}
}
