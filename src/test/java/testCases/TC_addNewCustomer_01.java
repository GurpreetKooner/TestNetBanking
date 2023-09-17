package testCases;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.Login;
import pageObjects.CustomerInfo;
import pageObjects.PageNavigation;
import resources.Base;
import resources.DatabaseHandler;
import resources.ReadConfig;
import resources.ReadCustomerData;

public class TC_addNewCustomer_01 extends Base {

	public WebDriver driver;
	private ReadConfig config;
	private ReadCustomerData customerData;
	private DatabaseHandler db;

	private void loginTest() {

		driver.get(config.getApplicationUrl());

		Login login = new Login(driver);
		login.setUserId(config.getUserID());
		login.setPassword(config.getPassword());
		login.clickSubmit();

		if (driver.getTitle().equals("Guru99 Bank Manager HomePage")) {
			Assert.assertTrue(true);
		} else {
			Assert.assertTrue(false);
			driver.close();
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

		customerData = new ReadCustomerData();
		try {
			db = new DatabaseHandler();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loginTest();
	}

	@Test(dataProvider = "getNewCustomersData")
	public void addNewCustomerTest(String name, String gender, String dob, String address, String city, String state,
			String pin, String phone, String email, String password) {

		PageNavigation navigate = new PageNavigation(driver);
		navigate.navigateToPage("New Customer");

		CustomerInfo customer = new CustomerInfo(driver);

//		customer.setCustomerName(name);
//		customer.setGender(gender);
//		customer.setDOB(dob);
//		customer.setAddress(address);
//		customer.setCity(city);
//		customer.setState(state);
//		customer.setPin(pin);
//		customer.setMobNumber(phone);
//		customer.setEmail(email);
//		customer.setPassword(password);
//		customer.clickSubmit();

//		OR

		customer.setNewCustomerInfo(name, gender, dob, address, city, state, pin, phone, email, password);

		if (customer.customerRegistered()) {
			Assert.assertTrue(true);
			int id = customer.getCustomerId();
			try {
				db.insertNewCustomer(id, name, gender, dob, address, city, state, pin, phone, email, password);
				Thread.sleep(1000);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			driver.switchTo().alert().accept();
			Assert.assertTrue(false);
		}

	}

	@DataProvider
	public Object[][] getNewCustomersData() {

		return customerData.getnewCustomerData();
	}

	@AfterClass
	public void Teardown() throws SQLException {

		driver.quit();

//		db.con.close();
//
//		driver.close();
	}

}
