package KENNCompany.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import KENNCompany.pageobjects.LandingPage;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		//properties class  (.properties file twy koh hlan 3 fo)
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\KENNCompany\\resources\\GlobalData.properties"); //GlobalData.properties yk locatrion
		// "D:\\Selenium_Workspace\\SeleniumFrameworkDesign2\\src\\main\\java\\KENNCompany\\resources\\GlobalData.properties" 
		//ah por ka location ah tai thu myr koh py yin thu htl mhr ek path nk ma tu twk fu ek tr kyg project yk location koh ah chy khn p location path koh yay ml
		//System.getProperty("user.dir")+"\\src\\main\\java\\KENNCompany\\resources\\GlobalData.properties"
		//System.getProperty("user.dir") ka project path koh automatically py ml (lat shi file shi dk project yk location koh py ml) d mhr so SeleniumFrameDesign2 yk location koh py ml (D:\Selenium_Workspace\SeleniumFrameworkDesign2)
		prop.load(fis);
		//String browserName= prop.getProperty("browser");
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser"); //d mhr System.getProperty("browser") so tr maven ka ny command prompt ka ny browser value koh pyw tr
		//maven ka ny commandprompt ka ny browser ah twk value htae py yin ek ah tai run ml tgl ma value ek ka ma htae py fu so properites file ka browser value koh u p run ml
		
		
		if(browserName.contains("chrome")) {
			ChromeOptions options= new ChromeOptions();
			System.setProperty("webdriver.chrome.driver", "C:/selenium webdriver/ChromeDriver/chromedriver.exe");
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			
			driver=new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));  //dr ka full screen koh pyw tr bl
			//maximize loke tr ka out mhr driver.manage().window().maximize(); nk yay htr dl dr pae mk headless mode nk run yin maximize koh ek lo yay yin ma support fu ek twk maximize ma fik twr fu ah por ka loh setSize nk yay mha thr headless mode mhr support ml
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:/selenium webdriver/GeckoDriver/geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "C:/selenium webdriver/MSEdgeDriver/msedgedriver.exe");
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
		public  List<HashMap<String, String>> getJsonDataToMap(String jsonFilePath) throws IOException {  
		
		//read Json to String 
		String jsonContent=FileUtils.readFileToString(new File(jsonFilePath),
				StandardCharsets.UTF_8);  //scan json and convert that into String (Purchase json htl ka hr twy koh String pyg tr)
		
		//String to Hashmap jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
		//{map,map1}
	}
	
		
		public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			File file=new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
			FileUtils.copyFile(source, file);
			return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		}	
		
	@BeforeMethod(alwaysRun=true) //alwaysRun=true loh htr htr yin thu hr thu group name include fik tr twy bl run bl ah chain bl lo run run d class koh run htl d method ka ah myl run ml
	public LandingPage lunchApplication() throws IOException {
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
