package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistationPage extends BasePage {
	 
public AccountRegistationPage(WebDriver driver)
	{
		super(driver);
	} 
 
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement FirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement LastName;

	@FindBy(xpath="//input[@id='input-email']")
	WebElement Email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement telephone;

	@FindBy(xpath="//input[@id='input-password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement confpassword;
	
	@FindBy(xpath="//input[@value='0']")
	WebElement newletter;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement Privacybtn;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement Continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement MsgConfirmation;
	
	
	public void setFirstName(String fname) 
		{
			FirstName.sendKeys(fname);
		}

	public void setLastName(String lname) 
		{
			LastName.sendKeys(lname);
		}

	public void setEmail(String email) 
		{
			Email.sendKeys(email);
		}

	public void setTelephone(String tel) 
		{
			telephone.sendKeys(tel);
		}

	public void setpassword(String pwd) 
		{
			password.sendKeys(pwd);
		}

	public void setConfirmPassword(String pwd) 
		{
			confpassword.sendKeys(pwd);
		}
	
	public void clicknewsletter() 
		{
			newletter.click();
		}

	public void clickPrivacyPolicy()
		{
			Privacybtn.click();
		}

	public void clickContinue() 
		{
			Continue.click();
			
			/* if Continue button dose not work will try this method..
			 * 
			 * JavascriptsExecutor js = (JavascriptsExecutor) driver;
			 * js.executeScript("argument[0].click();", Continue);
			 *
			 * JavascriptsExecutor js = (JavascriptsExecutor) driver;
			 * js.executeScript(ScrollIntoView, Continue);
			 * 
			 */
		}
	
	public String getconfirmationMsg()
	 {
		try {
				return ( MsgConfirmation.getText());
			}
		catch( Exception e )
			{
				return (e.getMessage());
			}
	} 
}
