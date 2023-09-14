package testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Login;
import pageObjects.NewCustomer;
import pageObjects.PageNavigation;
import resources.Base;
import resources.ReadConfig;
import resources.ReadCustomerData;

public class TC_addNewCustomer_01 extends Base {

	WebDriver driver;
	ReadConfig config;

	public void loginTest() {

		driver.get(config.getApplicationUrl());

		Login login = new Login(driver);
		login.setUserId(config.getUserID());
		login.setPassword(config.getPassword());
		login.clickSubmit();

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
		}
	}

	@BeforeClass
	public void initialize() throws IOException {
		try {
			driver = initializeDriver();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		config = readConfig;

		loginTest();
	}

	@Test(dataProvider = "getNewCustomersData")
	public void addNewCustomerTest(String name, String gender, String dob, String address, String city, String state,
			String pin, String phone, String email, String password) {

		PageNavigation navigate = new PageNavigation(driver);
		navigate.navigateToPage("New Customer");

		ReadCustomerData data = new ReadCustomerData();
		System.out.println(data.getnewCustomerData());

		NewCustomer customer = new NewCustomer(driver);
		customer.setCustomerName(name);
		customer.setGender(gender);
		customer.setDOB(dob);
		customer.setAddress(address);
		customer.setCity(city);
		customer.setState(state);
		customer.setPin(pin);
		customer.setMobNumber(phone);
		customer.setEmail(email);
		customer.setPassword(password);
		customer.clickSubmit();
		
		if(customer.customerRegistered()) {
			Assert.assertTrue(true);
		}else {
			Assert.assertTrue(false);
		}

	}

	@DataProvider
	public Object[][] getNewCustomersData() {

		ReadCustomerData customerData = new ReadCustomerData();
		return customerData.getnewCustomerData();
	}
	
	@AfterClass
	public void Teardown() {
		driver.close();
	}

}
