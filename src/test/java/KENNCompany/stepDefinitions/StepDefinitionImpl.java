package KENNCompany.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import KENNCompany.TestComponents.BaseTest;
import KENNCompany.pageobjects.CartPage;
import KENNCompany.pageobjects.CheckoutPage;
import KENNCompany.pageobjects.ConfirmationPage;
import KENNCompany.pageobjects.LandingPage;
import KENNCompany.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmationPage;
	public CartPage cartPage;
	
    @Given ("I landed on Ecommerce Page")  //SubmitOrder htl ka hr koh d mhr loke chin tr twy htae py mho
    public void I_landed_on_Ecommerce_Page() throws IOException //kyike dk method nk py lo ya pae mk () htl mhr pr dk hr koh bl py thint
    //chate htr dk fik dk ah twk d htl mhr br yay yay .feature file htl ka chate htr dk given htl koh youk ml
    {
    	landingPage=lunchApplication();
    }
    
    //(.+) ka name doh password doh ah twk .feature fil htl mhr br arugment htae htae match fik ag
    //ah por ka give koh just staic mho shae mhr ^ htae tr doh nouk mhr $ htae tr doh ma lo d mhr ka kya twk regular expression twy pr loh htae ya tr
    @Given ("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String userName, String password)   //cucumber ka auto thi dl 1st hr (.+) koh userName htl htae py p 2nd hr (.+) koh password htl htae py late ml
    {
    	productCatalogue=landingPage.loginApplication(userName, password);

    }
    
    
    @When ("^I add product (.+) to Cart$")
    public void I_add_product_to_Cart(String productName) throws InterruptedException {
    	List<WebElement>products=productCatalogue.getProductList();
    	productCatalogue.addProductToCart(productName);
    }
    
    //And ka previous one koh chate htr tr mho previous one name nk ll khw lo ya d mhr previous one ka when mho koh @When loh yay ll ya @And loh yay ll ya
    @When ("^Checkout (.+) and submit the order$")
    public void checkout_submite_order(String productName) {
    	cartPage=productCatalogue.goToCartPage();
		Boolean sameProductNameOrNot =cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(sameProductNameOrNot);
		
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		confirmationPage=checkoutPage.submitOrder();
    	
    }
    
    
    
    @Then ("{string} message is displayed on ConfirmationPage") //data koh tan htae tr myo so d loh {} htl htae p loke py ya dl
    public void message_displayed_confirmationPage(String string) {
    	String confirmMessage=confirmationPage.verifyConfirmationMessage();
		Assert.assertEquals(confirmMessage, string);
		driver.close();
    }
    
    
    //data koh tan htae tr koh d lo ll loke loh ya
    @Then("^\"([^\"]*)\" message is displayed$")
    public void message_displayed(String errorMessage) {
    	Assert.assertEquals(errorMessage,landingPage.getErrorMessage());
    	driver.close();
    }
    
    
}
