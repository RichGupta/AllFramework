package UI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ReviewPage extends AbstractBasePageWeb {
    public By placeOrderButton = By.xpath("//*[@id=\"placeYourOrder\"]/span/input");
    public void placeorderButtonEnabled(){
        isEnabled((WebElement) placeOrderButton);
    }
}
