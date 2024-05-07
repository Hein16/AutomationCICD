package KENNCompany.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import KENNCompany.TestComponents.BaseTest;
import KENNCompany.pageobjects.CartPage;
import KENNCompany.pageobjects.CheckoutPage;
import KENNCompany.pageobjects.ConfirmationPage;
import KENNCompany.pageobjects.LandingPage;
import KENNCompany.pageobjects.OrderPage;
import KENNCompany.pageobjects.ProductCatalogue;

public class StandAloneTestWithDesignPattern extends BaseTest{
	String productName="ZARA COAT 3";
	//BaseTest koh inheritance loke htr loh ek htl ka BeforeMethod ka ah ku file koh run tr nk thu hr thu auto run twr ml
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws IOException,InterruptedException{
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCartPage();//inheritance loke htr loh parent htl ka goToCartPage method koh child ka ny u 3 htr tr
		
		Boolean sameProductNameOrNot =cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(sameProductNameOrNot);
		
		CheckoutPage checkoutPage=cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.submitOrder();
		String confirmMessage=confirmationPage.verifyConfirmationMessage();
		Assert.assertEquals(confirmMessage, "THANKYOU FOR THE ORDER.");
		//BaseTest htl ka after method ka ah kone pe b driver.close() koh run py twr ml
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue=landingPage.loginApplication("kenn22@gmail.com", "KENNhtike@22");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
		
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		//return new Object[][] {{"kenn22@gmail.com","KENNhtike@22","ZARA COAT 3"},{"hein22@gmail.com","KENNhtike@22","ADIDAS ORIGINAL"}}; //d loh ma htae bl HashMap 3 p htae ml
		//__________________________________
		
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "kenn22@gmail.com");
//		map.put("password", "KENNhtike@22");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "hein22@gmail.com");
//		map1.put("password", "KENNhtike@22");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
//		return new Object[][] {{map},{map1}}; 
		//___________________________________
		
//HashMap koh mha d tai ma yay bl purchase json htl ka hr koh String pyg dl ek String koh List<Hashmap<String,String>> ah ny nk pyg dl

		List<HashMap<String, String>>data= getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\KENNCompany\\data\\PurchaseOrder.json"); //parent class fik dk BaseTest htl mhr shi loh (argument htl mhr ka Purchase Json file yk location)
		return new Object[][] {{data.get(0)},{data.get(1)}}; 

		
	}

}
