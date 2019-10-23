package UI.PageObjects;

import UI.Pages.AbstractBasePageWeb;
import org.openqa.selenium.By;

public class TestPageObject extends AbstractBasePageWeb {

    public By searchFiled = By.name("q");
    public By firstElement = By.xpath("(//*[@role='listbox']//*[@role='option'])[1]");
}
