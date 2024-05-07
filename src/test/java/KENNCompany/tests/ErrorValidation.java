package KENNCompany.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import KENNCompany.TestComponents.Retry;


import KENNCompany.TestComponents.BaseTest;
import KENNCompany.pageobjects.CartPage;
import KENNCompany.pageobjects.CheckoutPage;
import KENNCompany.pageobjects.ConfirmationPage;
import KENNCompany.pageobjects.LandingPage;
import KENNCompany.pageobjects.ProductCatalogue;
import org.testng.IRetryAnalyzer;

public class ErrorValidation extends BaseTest{
	//BaseTest koh inheritance loke htr loh ek htl ka BeforeMethod ka ah ku file koh run tr nk thu hr thu auto run twr ml
	@Test(groups= {"Error Handling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException{
		String productName="ZARA COAT 3";
		landingPage.loginApplication("kenn22@gmail.com", "KENNhtike");
		
		Assert.assertEquals("Incorrect email password.",landingPage.getErrorMessage());//thet thet fail tr sit chin loh mhr ag yay htr tr ah mhn ka "Incorrect email or password."
		//BaseTest htl ka after method ka ah kone pe b driver.close() koh run py twr ml
	}
	
	@Test
	public void ProductErrorValidation() throws IOException,InterruptedException{
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("hein22@gmail.com", "KENNhtike@22");
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();//inheritance loke htr loh parent htl ka goToCartPage method koh child ka ny u 3 htr tr
		
		Boolean sameProductNameOrNot =cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(sameProductNameOrNot);
		
		
		//BaseTest htl ka after method ka ah kone pe b driver.close() koh run py twr ml
	}

}
