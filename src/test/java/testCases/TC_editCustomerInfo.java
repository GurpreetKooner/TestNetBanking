package testCases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CustomerInfo;
import pageObjects.Login;
import pageObjects.PageNavigation;
import resources.Base;
import resources.DatabaseHandler;
import resources.ReadConfig;

public class TC_editCustomerInfo extends Base {
	WebDriver driver;
	private ReadConfig config;
	private DatabaseHandler db;
	public static Logger log = LogManager.getLogger(Base.class.getName());


	private void loginTest() {
		driver.get(config.getApplicationUrl());
		Login login = new Login(driver, log);
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
		
		try {
			db = new DatabaseHandler();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loginTest();
	}

	@Test(dataProvider = "editCustomersData")
	public void editCustomerInfo(String id, String address, String city, String state, String pin, String phone,
			String email) {

		PageNavigation navigate = new PageNavigation(driver, log);
		navigate.navigateToPage("Edit Customer");

		CustomerInfo customer = new CustomerInfo(driver, log);
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
		
//		for(int i=0; i<columnValues.size(); i++) {
//			System.out.println(columnValues.get(i));
//		}
		
		
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

	@DataProvider
	public Object[][] editCustomersData() {

		Object[][] data = new Object[1][7];
		data[0][0] = "10810";
		data[0][2] = "Florida";
		data[0][4] = "990011";
		return data;
	}

}
