package UI.Pages;

import org.openqa.selenium.By;

public class ProductDetailPage extends AbstractBasePageWeb {

 public By selectdropdown = By.xpath("//*[@id=\"native_dropdown_selected_size_name\"]");

 public void clickDropdown(){
     clickElement(selectdropdown);
 }
 public void setSelectdropdown(){
     selectDropdown("dropdown_selected_size_name","34");
 }

 public By buyButton = By.xpath("//*[@id=\"buy-now-button\"]");
 public void clickBuyButton(){
     clickElement(buyButton);
 }

}
