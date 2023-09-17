package testCases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CustomerInfo;
import pageObjects.Login;
import pageObjects.PageNavigation;
import resources.Base;
import resources.DatabaseHandler;
import resources.ReadConfig;
import resources.ReadCustomerData;

public class TC_AddEditDeleteCustomer extends Base {

	public WebDriver driver;
	private ReadConfig config;
	private ReadCustomerData customerData;
	private DatabaseHandler db;
	private int customerID;
	private CustomerInfo customer;
	private PageNavigation navigate;

	private void login() {
		driver.get(config.getApplicationUrl());
		Login login = new Login(driver);
		login.setUserId(config.getUserID());
		login.setPassword(config.getPassword());
		login.clickSubmit();
	}

	@BeforeClass
	public void initialize(){
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

		login();
		customer = new CustomerInfo(driver);
		navigate = new PageNavigation(driver);
	}

	@Test(priority = 0, dataProvider = "getNewCustomersData")
	public void addNewCustomerTest(String name, String gender, String dob, String address, String city, String state,
			String pin, String phone, String email, String password) {

		navigate.navigateToPage("New Customer");

		customer.setNewCustomerInfo(name, gender, dob, address, city, state, pin, phone, email, password);

		if (customer.customerRegistered()) {
			customerID = customer.getCustomerId();
			try {
				db.insertNewCustomer(customerID, name, gender, dob, address, city, state, pin, phone, email, password);
				Thread.sleep(1000);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Assert.assertTrue(true);

		} else {
			driver.switchTo().alert().accept();
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 1, dataProvider = "editCustomersData")
	public void editCustomerInfo(String id, String address, String city, String state, String pin, String phone,
			String email) {

		navigate.navigateToPage("Edit Customer");

		customer.enterCustomerId(id);
		customer.clickSubmitEditId();

		List<String> columnNames = new ArrayList<>();
		List<String> columnValues = new ArrayList<>();

		setIfNotNull(customer::setAddress, address, columnNames, columnValues, "address");
		setIfNotNull(customer::setCity, city, columnNames, columnValues, "city");
		setIfNotNull(customer::setState, state, columnNames, columnValues, "state");
		setIfNotNull(customer::setPin, pin, columnNames, columnValues, "pin");
		setIfNotNull(customer::setMobNumber, phone, columnNames, columnValues, "mobile_no");
		setIfNotNull(customer::setEmail, email, columnNames, columnValues, "email");

		customer.clickSubmit();
		driver.switchTo().alert().accept();	
		try {
			db.updateCustomerInfo(id, columnNames, columnValues);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static <T> void setIfNotNull(Consumer<T> setter, T value, List<String> columnNames, List<String> columnValues, String dbName) {
		if (value != null) {
			setter.accept(value);
			columnNames.add(dbName);
			columnValues.add(value.toString());
			
		}
	}

	@Test(priority = 2, dataProvider = "deleteCustomersData")
	public void deleteCustomer(String id) {

		navigate.navigateToPage("Delete Customer");

		customer.enterCustomerId(id);
		customer.clickSubmitEditId();
		try {
			db.deleteCustomer(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@DataProvider
	public Object[][] getNewCustomersData() {
		return customerData.getnewCustomerData();
	}

	@DataProvider
	public Object[][] editCustomersData() {

		Object[][] data = new Object[1][7];
		data[0][0] = String.valueOf(customerID);
		data[0][2] = "Florida";
		data[0][4] = "990011";
		return data;
	}

	@DataProvider
	public Object[][] deleteCustomersData() {

		Object[][] data = new Object[1][1];
		data[0][0] = String.valueOf(customerID);
		return data;
	}

	@AfterClass
	public void Teardown() throws SQLException {
		driver.quit();
	}
}
