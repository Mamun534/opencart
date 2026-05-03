package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

// extends to BasePage constructor..

public class LoginPage extends BasePage {
	
// Creating Constructor 
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
// Find Locators..........
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtemailaddress;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	
// 	Action Methods....
	
	public void setEmail(String email )
		{
			txtemailaddress.sendKeys(email);
		}
	
	public void setPassword(String pwd)
		{
			txtpassword.sendKeys(pwd);
		}
	
	public void clickLogin()
		{
			btnLogin.click();
		}
}
