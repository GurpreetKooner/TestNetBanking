package testCases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.Login;
import resources.Base;
import resources.ReadConfig;

public class TC_LoginTest_01 extends Base{
	
	public WebDriver driver;
	ReadConfig config;
	public static Logger log = LogManager.getLogger(Base.class.getName());

	
	@BeforeTest
	public void initialize() throws IOException {
		try {
			driver = initializeDriver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config = readConfig;
	}
	
	
	@Test
	public void loginTest() {
		
		driver.get(config.getApplicationUrl());

		Login login = new Login(driver, log);
		login.setUserId(config.getUserID());
		login.setPassword(config.getPassword());
		login.clickSubmit();
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}
	
}
