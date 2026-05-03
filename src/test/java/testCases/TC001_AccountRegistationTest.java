package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.AccountRegistationPage;
import pageObject.HomePage;
import testBase.BaseClass;
 
public class TC001_AccountRegistationTest extends BaseClass 
{
	@Test( groups={"Regression","master"}) 
	public void verify_account_registation()
		{
			logger.info("***Starting TC001_AccountRegistationTest****");
		try {
			HomePage hm = new HomePage(driver);
			hm.clickMyAccount();
			logger.info("***Clicked on MyAccount****");
			hm.clickRegister();
			logger.info("***Clicked on Register****");
		
			logger.info("***Providing Customer Details****");
			AccountRegistationPage ar = new AccountRegistationPage(driver);
			ar.setFirstName(randomString().toUpperCase());
			ar.setLastName(randomString().toUpperCase());
			ar.setEmail(randomString()+"@gmail.com");
			
			ar.setTelephone(randomNumber());
			
			String password = randomAlphaNumber();
			
			ar.setpassword(password);
			ar.setConfirmPassword(password);
			
			ar.clicknewsletter();
			ar.clickPrivacyPolicy();
			ar.clickContinue();
			
			logger.info("***Validating Expected Massage****");
			String confmsg = ar.getconfirmationMsg();
			
		if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
		else
			{	
				logger.error("Test Failed");
				logger.debug("Debug Logs...");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e)
				{
					Assert.fail();
				}
					logger.info("Finished TC001_AccountRegistationTest...");
		}
}
