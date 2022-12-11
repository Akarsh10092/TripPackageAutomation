package com.yatra.trip;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TripPackageAutomation {
	WebDriver driver;
	@Parameters("browser")
	@BeforeTest
	
	public void setup(String browser) throws Exception{
	//Check if parameter passed from TestNG is 'firefox'
	if(browser.equalsIgnoreCase("firefox"))
	{
	//create firefox instance
	System.setProperty("webdriver.gecko.driver","C:\\Users\\dell\\eclipse-workspace\\TripPackageAutomation\\drivers\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.manage().window().maximize();
	System.out.println("Firefox browser is opened");
	}
	//Check if parameter passed as 'chrome'
	else if(browser.equalsIgnoreCase("chrome"))
	{
	//set path to chromedriver.exe
	System.setProperty("webdriver.chrome.driver","C:\\Users\\dell\\eclipse-workspace\\TripPackageAutomation\\drivers\\chromedriver.exe");
	
	//create chrome instance
	ChromeOptions co = new ChromeOptions();
	co.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
	driver =new ChromeDriver(co);
	driver.manage().window().maximize();                                    
	System.out.println("chrome browser is opened");
	}
	else{
		//If no browser passed throw exception
		throw new Exception("Browser is not correct");
		}
	}
	
	@Test
    public void testParameterWithXML() throws Throwable{
	//Opening Yatra.com url after initiating the driver
	driver.get("https://www.yatra.com/");
	//implicitDelay
	driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS); 

	//Find Offers and click on it 
	WebElement Offers = driver.findElement(By.xpath("//*[@id=\"themeSnipe\"]/div/div/div[4]/div[2]/div/ul/li[3]/a"));
	Offers.click();
	//Check whether the Title is "Domestic Flights Offers | Deals on Domestic Flight Booking | Yatra.com"
	String Pagetitle = driver.getTitle();
	Assert.assertEquals(Pagetitle, "Domestic Flights Offers | Deals on Domestic Flight Booking | Yatra.com");
	System.out.println("The Title expected and actual match-Checkpoint1 passed");
	//Check the banner logo is "Great Offers & Amazing Deals"
	String banner = driver.findElement(By.xpath("//*[@id=\'collapsibleSection\']/section[1]/div[1]/div/div/h2")).getText();
	Assert.assertEquals(banner,"Great Offers & Amazing Deals");
	System.out.println("The Banner match-Checkpoint2 passed");

	//Take Screenshot of the BrowserWindow
	File F= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(F,new File("C:\\Users\\dell\\eclipse-workspace\\TripPackageAutomation\\Screenshot\\Offers_page.png"));
	
	//Navigate to Holiday tab and view details of Ladhak Packages
	WebElement holidays = driver.findElement(By.xpath("//*[@id=\'offer-box-shadow\']/li[4]/a"));
	holidays.click();
	WebElement details =driver.findElement(By.xpath("//*[@id=\'collapsibleSection\']/section[1]/div[2]/div/section/div/div/ul/li[2]/a/span/span/span[3]/span"));
	details.click();
	
	//Popup window open New Tab to transfer control of driver to new window
	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	
	//Printing the 5 packages and amount in Rupees
	String pck1 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[2]/td[1]")).getText();
	System.out.println(pck1);
	String price1 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[2]/td[2]")).getText();
	System.out.println(price1);
	String pck2 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[3]/td[1]")).getText();
	System.out.println(pck2);
	String price2 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[3]/td[2]")).getText();
	System.out.println(price2);
	String pck3 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[4]/td[1]")).getText();
	System.out.println(pck3);
	String price3 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[4]/td[2]")).getText();
	System.out.println(price3);
	String pck4 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[5]/td[1]")).getText();
	System.out.println(pck4);
	String price4 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[5]/td[2]")).getText();
	System.out.println(price4);
	String pck5 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[6]/td[1]")).getText();
	System.out.println(pck5);
	String price5 = driver.findElement(By.xpath("//div[@class='bg-white mb20']/table/tbody/tr[6]/td[2]")).getText();
	System.out.println(price5);

	}
	@AfterTest

	public void Closebrowser() {

	driver.quit();
}
	}
	
