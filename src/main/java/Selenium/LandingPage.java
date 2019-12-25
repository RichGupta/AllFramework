package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
    public WebDriver driver;


   By signin = By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input");
           //By.xpath("//*[@id=\"nav-signin-tooltip\"]/a/span");

    public LandingPage(WebDriver driver){
        this.driver=driver;
    }
    public WebElement getLogin(){
        return driver.findElement(signin);
    }
}
