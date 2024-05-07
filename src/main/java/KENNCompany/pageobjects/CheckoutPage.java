package KENNCompany.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import KENNCompany.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[placeholder='Select Country']") 
	WebElement country;

	@FindBy(className="action__submit") 
	WebElement submitBtn;
	
	@FindBy(css=".ta-item:nth-of-type(2)") 
	WebElement selectedCountry;
	
	By result=By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a=new Actions(driver);		
		a.sendKeys(country,countryName).build().perform();	
		waitForElementToAppear(result);
		selectedCountry.click();
	}
	
	public ConfirmationPage submitOrder() {
		submitBtn.click();
		return new ConfirmationPage(driver);
	}
	

	
	
	
	
}
