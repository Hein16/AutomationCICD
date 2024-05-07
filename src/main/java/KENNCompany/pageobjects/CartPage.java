package KENNCompany.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import KENNCompany.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProdcuts;
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean sameProductNameOrNot =cartProdcuts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return sameProductNameOrNot;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		//CheckoutPage checkoutPage=new CheckoutPage(driver);
		//return checkoutPage;
		
		return new CheckoutPage(driver);
	}
	

}
