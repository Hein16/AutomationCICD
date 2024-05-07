package KENNCompany.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import KENNCompany.pageobjects.CartPage;
import KENNCompany.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css="[routerlink*='cart']") 
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']") 
	WebElement orderHeader;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitForElementToAppearForInvisible() throws InterruptedException {
		Thread.sleep(2000);
		//invisible sout ya dk hr twy ah twk ah por ka loh ma yay chin yin Thread.sleep(1000); nk ll sout loh ya
	}

	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}
}
