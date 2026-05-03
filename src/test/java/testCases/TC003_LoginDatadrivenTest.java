package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilities.dataProviders;

public class TC003_LoginDatadrivenTest extends BaseClass 
{
	@Test(groups="DataDriven",dataProvider="LoginData", dataProviderClass=dataProviders.class) // getting data provider different packacge and class
	public void verify_loginDataDrivenTest(String email, String pwd, String exp)
		{
				logger.info("***Starting TC003_LoginTestDatadriven***");
			try {
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				hp.clickloginBtn();
				
				logger.info("***HomePage Actions Is Done Navigate To LoginPage***");
				
				LoginPage lp = new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				logger.info("***Loging To My Account Page and Validationg the Heading***");
				
				MyAccountPage mp = new MyAccountPage(driver);
				boolean targetpage = mp.isMyAccountPageExists();
				
		// Data Valid...
				
				if(exp.equalsIgnoreCase("Valid"))
				{
					if(targetpage==true)
					{
						mp.clickLogout();
						mp.afterlogoutcontinue();
						Assert.assertTrue(true);
					}
					else 
						{
							Assert.assertTrue(false);
						}
				}
	 // Data InValid...
				
				if(exp.equalsIgnoreCase("InValid"))
				{
					if(targetpage==true)
					{
						mp.clickLogout();
						mp.afterlogoutcontinue();
						Assert.assertTrue(false);
					}
					else
					{
						Assert.assertTrue(true);
					}
				}
			}
		catch(Exception e) 
			{
				Assert.fail();
			}
				logger.info("***Finished TC003_LoginTestDatadriven***");
	}
}
