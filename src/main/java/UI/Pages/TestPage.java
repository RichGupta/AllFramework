package UI.Pages;

import UI.PageObjects.TestPageObject;
import org.testng.Assert;

public class TestPage extends TestPageObject {

    public void searchItem(String item){
        enterText(searchFiled, item);
        clickElement(firstElement);
    }

    public void verifySearch(String item){
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains(item));
        log.info("Verified Searched element : "+item);
    }
}
