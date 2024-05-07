package KENNCompany.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KENNCompany.AbstractComponents.AbstractComponent;

import org.openqa.selenium.WebDriver;

public class ProductCatalogue extends AbstractComponent{
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".mb-3") //css so tr cssSelector koh pyw tr
	List<WebElement> products;
	
	@FindBy(css=".ng-animating") 
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement zaraCoat= getProductList().stream().filter(product->product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		return zaraCoat;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement zaraCoat=getProductByName(productName);
		zaraCoat.findElement(addToCart).click(); 
		waitForElementToAppear(toastMessage);
		waitForElementToAppearForInvisible();
	}
	
	


}
