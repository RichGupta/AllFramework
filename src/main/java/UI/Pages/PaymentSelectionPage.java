package UI.Pages;

import org.openqa.selenium.By;

public class PaymentSelectionPage extends AbstractBasePageWeb {
    public By sbiccv = By.xpath("//*[@id=\"pp-uCJyVk-179\"]");
    public void enterCVV(){
        enterText(sbiccv,"123");
    }
    public By continueButton = By.xpath("//*[@id=\"pp-uCJyVk-291\"]/span/input");
    public void clickContinue(){
        clickElement(continueButton);
    }

}
