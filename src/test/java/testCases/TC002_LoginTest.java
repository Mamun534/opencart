package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

	@Test(groups={"sanity", "master", "Regression"})
	public void verify_login()
		{
			logger.info("***Starting TC002_LoginTest***");
		try {
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickloginBtn();
			
			logger.info("***HomePage Actions Is Done Navigate To LoginPage***");
			
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			logger.info("***Loging To My Account Page and Validationg the Heading***");
			
			MyAccountPage mp = new MyAccountPage(driver);
			boolean targetpage = mp.isMyAccountPageExists();
			
		//	Assert.assertEquals(targetpage, true, "Login Failed");
			Assert.assertTrue(targetpage);
			}
		catch(Exception e )
			{
				Assert.fail();
			}
				logger.info("***Finished TC002_LoginTest***");
	}
}
