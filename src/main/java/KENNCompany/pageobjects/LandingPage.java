package KENNCompany.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import KENNCompany.AbstractComponents.AbstractComponent;

import org.openqa.selenium.WebDriver;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	//constructor ma sout bl d tai so driver ka br mha ma shi dk d tai driver bl fik ny ml constructor 3 p chate htr twk StandAloneTestWithDesignPattern class ka driver fik twr yor
	public LandingPage(WebDriver driver) {
		super(driver);//parent class fik dk AbstractComponent koh driver so dk WebDriver koh super() nk child ka ny parent koh pyn py tr 
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);//this so tr this.driver koh pyw tr
	}
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail; //d 2 kyaung yay tr ka ah por ka yay htr dk 1 kyaung nk tu tu bl PageFactory design pattern koh 3 mhr mho loh
	
	
	@FindBy(id="userPassword")
	WebElement passwordEle; 
	
	@FindBy(id="login")
	WebElement submit; 
	
	//.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error //class of incorrect email or password toast message
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMesssage; 
	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMesssage);
		return errorMesssage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

}
