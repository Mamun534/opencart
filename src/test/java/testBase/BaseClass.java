package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

// BaseClass contains only Re-Usable Methods which we need to extends every test class
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	  
	@BeforeClass(groups={"sanity", "Regression", "master", "DataDriven"})
	@Parameters({"os", "browser"}) 
	public void setup(String os, String br) throws IOException
		{
// Loading config.properties file.....
		
			FileReader file = new FileReader("./src//test//resources//config.properties");
			p = new Properties();
			p.load(file);

// Loading log4j2.xml file....
			logger = LogManager.getLogger(this.getClass());
			
// Setup for grid enviorment from the config.properties file.............
			
			if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
					{
						DesiredCapabilities cap = new DesiredCapabilities();
					
			if(os.equalsIgnoreCase("Windows"))
					{
						cap.setPlatform(Platform.WIN11);
					}
			else if (os.equalsIgnoreCase("mac"))
					{
						cap.setPlatform(Platform.MAC); 
					}
			else
					{
						System.out.println("Browser dose not matching");
						return;
					}
			switch(br.toLowerCase())
					{
						case "chrome"  : cap.setBrowserName("chrome"); break;
						case "edge"    : cap.setBrowserName("MicrosoftEdge"); break;
						case "firefox" : cap.setBrowserName("firefox"); break;
						default        : System.out.println("Browser dose not matching"); return;
					}
						driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			}
			
// This is for Local Executions as like normal execution.....
			
			if(p.getProperty("execution_env").equalsIgnoreCase("local"))
				{
			switch(br.toLowerCase())
				{
					case "chrome" 	:  driver = new ChromeDriver(); break;
					case "edge" 	: 	 driver = new EdgeDriver(); break;
					case "firefox" 	: driver = new FirefoxDriver(); break;
					default 		: System.out.println("Invalid Browser Name"); return;
				}
			}
					driver.manage().deleteAllCookies();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					
				// Here instead of hard coding url code we can load from properties file.......
				//	driver.get("http://localhost/opencart/index.php?");
				//  driver.get("https://tutorialsninja.com/demo/");
				//	driver.get(p.getProperty("appURL1"));
					
					driver.get(p.getProperty("appURL1"));
					driver.manage().window().maximize();
				}
	
	@AfterClass(groups={"sanity", "Regression", "master", "DataDriven"})
	public void tearDown()
		{
			driver.quit();
		}

	public String randomString()
	{
		@SuppressWarnings("deprecation")
		String generatedstring  =  RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNumber()
	{
		@SuppressWarnings("deprecation")
		String generateingnumber  =  RandomStringUtils.randomNumeric(10);
		return generateingnumber;
	}
	
	public String randomAlphaNumber()
	{
		@SuppressWarnings("deprecation")
		String generatedstring  =  RandomStringUtils.randomAlphabetic(3);
		
		@SuppressWarnings("deprecation")
		String generateingnumber  =  RandomStringUtils.randomNumeric(3);
		
		return (generatedstring+"@"+generateingnumber);
	}
	
	public String captureScreen(String tname) throws IOException 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp +".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}







