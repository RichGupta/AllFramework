package UI.Pages;

import UI.Pages.AbstractBasePageWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class LandingPage extends AbstractBasePageWeb {



    public By signin = By.xpath("//*[@id=\"nav-signin-tooltip\"]/a/span");
    public void clickSignInButton(){
        clickElement(signin);}
    public By searchtextbox = By.xpath("//*[@id=\"twotabsearchtextbox\"]");
    public void miTV(){
        enterText(searchtextbox,"Mi TV"); }
    public By searchbuttons = By.xpath("/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[2]/div/input");
    public void searchSubmit(){
        clickElement(searchbuttons);}
    public By han = By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[1]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span");
    // By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[3]/div/span/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span");
    public void verifyText() {
        getText(han);
        Assert.assertEquals(getText(han), "Mi LED TV 4C PRO 80 cm (32) HD Ready Android TV (Black)");
    }
    public void searchSketchers(){
        enterText(searchtextbox,"Sketchers shoes men");
        clickElement(searchbuttons);
    }

    public By secondlink = By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[8]/div/span/div/div/ul/li[3]/a");
    public void clickSecondbuttonlink(){
        clickElement(secondlink);
    }
    public By homePage = By.xpath("//*[@id=\"nav-logo\"]/a[1]");
    public void goHomePage(){
        clickElement(homePage);
    }
    public void searchTrimmer(){
        enterText(searchtextbox,"trimmer");
        clickElement(searchbuttons);}

    public By philipCheckbox = By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div/div[3]/span/div/div[5]/ul/li[1]/span/a/div/label/i");

    public By philipPageDisplayed = By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/div/span[4]/div[1]/div[1]/div/span/div/div/div[2]/h2/a/span");

    public void selectPhilipCheckbox()  {
        clickElement(philipCheckbox);
        getText(philipPageDisplayed);
        Assert.assertEquals(getText(philipPageDisplayed),"Philips QT4001/15 cordless rechargeable Beard Trimmer - 10 length settings");
    }
    public By clickBenMartinJeans = By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[4]/div[1]/div[3]/div/span/div/div/div/div/div[4]/div/div/a");

    public void searchBenJeans(){
        enterText(searchtextbox,"Ben Martin Jeans for men");
        clickElement(searchbuttons);

    }
    public void clickBenJeans(){
        clickElement(clickBenMartinJeans);
    }


}