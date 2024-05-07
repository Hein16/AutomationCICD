package KENNCompany.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import KENNCompany.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> ProdcutNames;
	
	public Boolean VerifyOrderDisplay(String productName) {
		Boolean sameOrderNameOrNot =ProdcutNames.stream().anyMatch(ProdcutName->ProdcutName.getText().equalsIgnoreCase(productName));
		return sameOrderNameOrNot;
	}
	
	

}
